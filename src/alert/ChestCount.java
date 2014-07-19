package alert;

import static alert.BlockPlace.prefix;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.Plugin;

public class ChestCount implements Listener {
    
    private static final Plugin plugin = Alert.getInstance();

    private final String world = plugin.getConfig().getString("World");
    int count = plugin.getConfig().getInt("Chests");
    int count2 = plugin.getConfig().getInt("EnderChests");

    @EventHandler
    public void onChestPlace(BlockPlaceEvent e) {

        Player p = e.getPlayer();

        if (e.getBlock().getType() == Material.CHEST && e.getPlayer().getWorld().getName().equals(world)) {
            plugin.getConfig().set("Chests", count++);
            p.sendMessage(prefix + ChatColor.GREEN + "There are: " + ChatColor.GOLD + count + ChatColor.GREEN + " Chests on the map.");
        }

        if (e.getBlock().getType() == Material.ENDER_CHEST && e.getPlayer().getWorld().getName().equals(world)) {
            plugin.getConfig().set("EnderChests", count2++);
            p.sendMessage(prefix + ChatColor.GREEN + "There are: " + ChatColor.GOLD + count2 + ChatColor.GREEN + " Ender Chests on the map.");
        }

    }
    @EventHandler
    public void onChestBreak(BlockBreakEvent e) {

        Player p = e.getPlayer();

        if (e.getBlock().getType() == Material.CHEST && e.getPlayer().getWorld().getName().equals(world)) {
            plugin.getConfig().set("Chests", count--);
            p.sendMessage(prefix + ChatColor.GREEN + "There are: " + ChatColor.GOLD + count + ChatColor.GREEN + " Chests on the map.");
        }

        if (e.getBlock().getType() == Material.ENDER_CHEST && e.getPlayer().getWorld().getName().equals(world)) {
            plugin.getConfig().set("EnderChests", count2--);
            p.sendMessage(prefix + ChatColor.GREEN + "There are: " + ChatColor.GOLD + count2 + ChatColor.GREEN + " Ender Chests on the map.");
        }
    }

}
