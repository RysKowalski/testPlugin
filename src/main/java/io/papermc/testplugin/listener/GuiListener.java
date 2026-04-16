package io.papermc.testplugin.listener;

import java.net.http.WebSocket.Listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;

import io.papermc.testplugin.gui.AccessoriesGui;

public final class GuiListener implements Listener {

  private final AccessoriesGui gui;

  public GuiListener(AccessoriesGui gui) {
    this.gui = gui;
  }

  @EventHandler
  public void onClick(InventoryClickEvent event) {
    if (!gui.isThisGui(event.getInventory()))
      return;
    gui.handleClick(event);
  }

  @EventHandler
  public void onDrag(InventoryDragEvent event) {
    if (!gui.isThisGui(event.getInventory()))
      return;
    gui.handleDrag(event);
  }

  @EventHandler
  public void onClose(InventoryCloseEvent event) {
    if (!gui.isThisGui(event.getInventory()))
      return;
    gui.handleClose(event);
  }
}
