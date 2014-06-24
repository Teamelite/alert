package alert;

import static alert.BlockPlace.prefix;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class ChestCount implements Listener {

    private static Alert plugin;

    public ChestCount(Alert instance) {
        ChestCount.plugin = instance;
    }

    String world = plugin.getConfig().getString("World");
    int count = plugin.getConfig().getInt("World.Chests");
    int count2 = plugin.getConfig().getInt("World.EnderChests");

    @EventHandler
    public void onChestPlace(BlockPlaceEvent e) {

        Player p = e.getPlayer();

        if (e.getBlock().getType() == Material.CHEST && e.getPlayer().getWorld().getName().equals(world)) {
            plugin.getConfig().set("World.Chests", count++);
            p.sendMessage(prefix + ChatColor.GREEN + "There are: " + ChatColor.GOLD + count + ChatColor.GREEN + " Chests on the map.");
        }

        if (e.getBlock().getType() == Material.ENDER_CHEST && e.getPlayer().getWorld().getName().equals(world)) {
            plugin.getConfig().set("World.EnderChests", count2++);
            p.sendMessage(prefix + ChatColor.GREEN + "There are: " + ChatColor.GOLD + count2 + ChatColor.GREEN + " Ender Chests on the map.");
        }

    }

    public void onChestBreak(BlockBreakEvent e) {

        Player p = e.getPlayer();

        if (e.getBlock().getType() == Material.CHEST && e.getPlayer().getWorld().getName().equals(world)) {
            plugin.getConfig().set("World.Chests", count--);
            p.sendMessage(prefix + ChatColor.GREEN + "There are: " + ChatColor.GOLD + count + ChatColor.GREEN + " Chests on the map.");
        }

        if (e.getBlock().getType() == Material.ENDER_CHEST && e.getPlayer().getWorld().getName().equals(world)) {
            plugin.getConfig().set("World.EnderChests", count2--);
            p.sendMessage(prefix + ChatColor.GREEN + "There are: " + ChatColor.GOLD + count2 + ChatColor.GREEN + " Ender Chests on the map.");
        }
    }

}
