
package io.papermc.testplugin.gui;

import java.util.UUID;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public final class AccessoriesHolder implements InventoryHolder {

  private final UUID playerId;

  public AccessoriesHolder(UUID playerId) {
    this.playerId = playerId;
  }

  @Override
  public Inventory getInventory() {
    return null;
  }

  public UUID getPlayerId() {
    return playerId;
  }
}
