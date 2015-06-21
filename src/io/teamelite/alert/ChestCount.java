package io.teamelite.alert;

import static io.teamelite.alert.Alert.plugin;
import static io.teamelite.alert.BlockPlace.prefix;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.Plugin;

public class ChestCount implements Listener {

    String world = plugin.getConfig().getString("World");
    public static int count = plugin.getConfig().getInt("Chests");
    public static int count2 = plugin.getConfig().getInt("EnderChests");

    @EventHandler
    public void onChestPlace(BlockPlaceEvent e) {

        Player p = e.getPlayer();
        if (e.getBlock().getType() == Material.CHEST && e.getPlayer().getWorld().getName().equals(world)) {
            plugin.getConfig().set("Chests", count++);
            p.sendMessage(prefix + ChatColor.GOLD + "There are: " + count + " Chests on the map.");
        }

        if (e.getBlock().getType() == Material.ENDER_CHEST && e.getPlayer().getWorld().getName().equals(world)) {
            plugin.getConfig().set("EnderChests", count2++);
            p.sendMessage(prefix + ChatColor.GOLD + "There are: " + count2 + " Ender Chests on the map.");
        }

    }

    @EventHandler
    public void onChestBreak(BlockBreakEvent e) {

        Player p = e.getPlayer();
        if (count == 0 && count2 == 0) {
        } else {
            if (e.getBlock().getType() == Material.CHEST && e.getPlayer().getWorld().getName().equals(world)) {
                plugin.getConfig().set("Chests", count--);
                p.sendMessage(prefix + ChatColor.GOLD + "There are: " + count + " Chests on the map.");
            }

            if (e.getBlock().getType() == Material.ENDER_CHEST && e.getPlayer().getWorld().getName().equals(world)) {
                plugin.getConfig().set("EnderChests", count2--);
                p.sendMessage(prefix + ChatColor.GOLD + "There are: " + count2 + " Ender Chests on the map.");
            }
        }
    }

}
