package io.papermc.testplugin;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class AccessoriesManager {

  private final JavaPlugin plugin;
  private final Map<UUID, ItemStack[]> storage = new HashMap<>();

  private static final int SIZE = 9;

  public AccessoriesManager(JavaPlugin plugin) {
    this.plugin = plugin;
  }

  public ItemStack[] get(UUID uuid) {
    return storage.computeIfAbsent(uuid, this::loadFromDisk);
  }

  public void set(UUID uuid, ItemStack[] items) throws IllegalArgumentException {
    if (items.length != SIZE) {
      throw new IllegalArgumentException("Expected " + SIZE + " slots");
    }
    storage.put(uuid, items);
  }

  public void save(UUID uuid) {
    ItemStack[] items = storage.get(uuid);
    if (items == null)
      return;

    File file = file(uuid);
    YamlConfiguration cfg = new YamlConfiguration();

    for (int i = 0; i < SIZE; i++) {
      cfg.set("slots." + i, items[i]);
    }

    try {
      cfg.save(file);
    } catch (IOException e) {
      plugin.getLogger().severe("Failed to save accessories for " + uuid);
    }
  }

  public void saveAll() {
    for (UUID uuid : storage.keySet()) {
      save(uuid);
    }
  }

  public void unload(UUID uuid) {
    save(uuid);
    storage.remove(uuid);
  }

  private ItemStack[] loadFromDisk(UUID uuid) {
    File file = file(uuid);
    ItemStack[] items = new ItemStack[SIZE];

    if (!file.exists()) {
      return items;
    }

    YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

    for (int i = 0; i < SIZE; i++) {
      items[i] = cfg.getItemStack("slots." + i);
    }

    return items;
  }

  private File file(UUID uuid) {
    File dir = new File(plugin.getDataFolder(), "data");
    if (!dir.exists()) {
      dir.mkdirs();
    }
    return new File(dir, uuid.toString() + ".yml");
  }
}
