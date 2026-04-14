package io.papermc.testplugin.commands;

import net.kyori.adventure.text.Component;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

public class AgletCommand implements CommandExecutor {

  @Override
  public boolean onCommand(
      @NotNull CommandSender sender,
      @NotNull Command command,
      @NotNull String label,
      @NotNull String[] args) {
    Player player = (Player) sender;
    ItemStack item = new ItemStack(Material.OAK_LOG);
    item.setAmount(1);
    ItemMeta meta = item.getItemMeta();
    if (meta != null) {
      meta.displayName(Component.text("Aglet"));
      item.setItemMeta(meta);
    }

    player.getInventory().addItem(item);
    return true;
  }
}
