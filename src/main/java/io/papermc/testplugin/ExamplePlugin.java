package io.papermc.testplugin;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.joml.Math;

import io.papermc.testplugin.commands.AgletCommand;

// DONE all players have 9 saved accessories that are items
//
// DONE function updateAglet(Player player)
// checks if saved accessories have oak plank named "Aglet"
// if yes: set generic movement speed of player to 0.12
// else: reset generic movement speed
//
// DONE command /aglet
// gives player oak plank named "Aglet"
//
// TODO: command /akcesoria
// opens chest ui with single row, named "Akcesoria"
// user can get accesory from own inventory, and drag it to this gui
// if item is not accesory, at this moment oak plank named "Aglet"
// sends message "To nie jest akcesorium"
// on close of this menu:
// saves items in gui to user's saved accessories
//
// DONE on join: updateAglet()

public class ExamplePlugin extends JavaPlugin implements Listener {
  AccessoriesService accessoriesService;

  @Override
  public void onEnable() {
    Bukkit.getPluginManager().registerEvents(this, this);

    AccessoriesManager manager = new AccessoriesManager(this);
    this.accessoriesService = new AccessoriesService(manager);

    getCommand("aglet").setExecutor(new AgletCommand());
  }

  @EventHandler
  public void onPlayerJoin(PlayerJoinEvent event) {
    Player player = event.getPlayer();
    this.accessoriesService.updateAglet(player);
  }

  @EventHandler
  public void onPlayerMove(PlayerMoveEvent event) {
    if (Math.random() > 0.95) {
      event.getPlayer().sendMessage(Component.text("masz skill issue naucz się chodzić"));
      event.setCancelled(true);
    }
  }
}
