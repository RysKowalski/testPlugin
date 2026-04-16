package io.papermc.testplugin.gui;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import io.papermc.testplugin.AccessoriesManager;
import io.papermc.testplugin.AccessoriesService;
import net.kyori.adventure.text.Component;

public final class AccessoriesGui {

  private final AccessoriesManager manager;
  private final AccessoriesService service;

  public AccessoriesGui(AccessoriesManager manager, AccessoriesService service) {
    this.manager = manager;
    this.service = service;
  }

  public Inventory create(Player player) {
    Inventory inv = Bukkit.createInventory(
        player,
        27,
        Component.text("Accessories"));

    ItemStack[] items = manager.get(player.getUniqueId());

    // populate slots
    for (int i = 0; i < items.length; i++) {
      inv.setItem(i, items[i]);
    }

    return inv;
  }

  public boolean isThisGui(Inventory inventory) {
    return inventory.getHolder() instanceof AccessoriesHolder;
  }

  public void handleClick(InventoryClickEvent event) {
    // TODO: all click logic here
  }

  public void handleDrag(InventoryDragEvent event) {
    // TODO: drag rules here
  }

  public void handleClose(InventoryCloseEvent event) {
    Player player = (Player) event.getPlayer();
    Inventory inv = event.getInventory();

    ItemStack[] contents = new ItemStack[inv.getSize()];
    for (int i = 0; i < inv.getSize(); i++) {
      contents[i] = inv.getItem(i);
    }

    manager.set(player.getUniqueId(), contents);
    manager.save(player.getUniqueId());

    service.updateAglet(player);
  }
}
