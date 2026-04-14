package io.papermc.testplugin.commands;

import net.kyori.adventure.text.Component;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

public class AkcesoriaCommand implements CommandExecutor {

  @Override
  public boolean onCommand(

      @NotNull CommandSender sender,
      @NotNull Command command,
      @NotNull String label,
      @NotNull String[] args) {
    if (!(sender instanceof Player player)) {
      sender.sendMessage("This command can only be used by players.");
      return true;
    }

    Inventory gui = Bukkit.createInventory(
        null,
        9,
        Component.text("Akcesoria"));
    player.openInventory(gui);
    return true;
  }
}
