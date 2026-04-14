package io.papermc.testplugin;

import java.util.UUID;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.kyori.adventure.text.Component;

public final class AccessoriesService {
  private final AccessoriesManager manager;

  private static final double AGLET_SPEED = 0.12;
  private static final double DEFAULT_SPEED = 0.1;

  public AccessoriesService(AccessoriesManager manager) {
    this.manager = manager;
  }

  public void updateAglet(Player player) {
    UUID uuid = player.getUniqueId();

    ItemStack[] items = manager.get(uuid);

    boolean hasAglet = false;
    for (ItemStack item : items) {
      if (isAglet(item)) {
        hasAglet = true;
        break;
      }
    }
    var attr = player.getAttribute(Attribute.MOVEMENT_SPEED);
    if (attr == null)
      return;

    if (hasAglet) {
      attr.setBaseValue(AGLET_SPEED);
    } else {
      attr.setBaseValue(DEFAULT_SPEED);
    }
  }

  private boolean isAglet(ItemStack item) {
    if (item == null)
      return false;
    if (!item.hasItemMeta())
      return false;

    ItemMeta meta = item.getItemMeta();
    if (meta == null)
      return false;

    Component name = meta.displayName();
    if (name == null)
      return false;

    return name.equals(Component.text("Aglet"));
  }
}
