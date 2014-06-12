
package alert;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlace implements Listener {

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {

        if (e.getBlock().getType() == Material.FURNACE) {
            alertPlayers(e);
        }

        if (e.getBlock().getType() == Material.DISPENSER) {
            alertPlayers(e);
        }

        if (e.getBlock().getType() == Material.DROPPER) {
            alertPlayers(e);
        }

        if (e.getBlock().getType() == Material.CHEST) {
            alertPlayers(e);
        }

        if (e.getBlock().getType() == Material.ENDER_CHEST) {
            alertPlayers(e);
        }

        if (e.getBlock().getType() == Material.TRAPPED_CHEST) {
            alertPlayers(e);
        }

        if (e.getBlock().getType() == Material.HOPPER) {
            alertPlayers(e);
        }

        if (e.getBlock().getType() == Material.HOPPER_MINECART) {
            alertPlayers(e);
        }

        if (e.getBlock().getType() == Material.POWERED_MINECART) {
            alertPlayers(e);
        }

        if (e.getBlock().getType() == Material.STORAGE_MINECART) {
            alertPlayers(e);
        }
    }

    private void alertPlayers(BlockPlaceEvent e) {
        Block pBlock = e.getBlockPlaced();
        String name = e.getPlayer().getDisplayName();
        String locText = pBlock.getLocation().getX() + ", " + pBlock.getLocation().getY() + ", " + pBlock.getLocation().getZ();
        String prefix = ChatColor.DARK_GRAY + "[" + ChatColor.DARK_RED + "Team" + ChatColor.GOLD + "Elite" + ChatColor.DARK_GRAY + "]";
        Material block = e.getBlockPlaced().getType();
        String world = e.getPlayer().getWorld().getName();

        if (!e.getPlayer().hasPermission("alert.message")) {
            LogFile.logFile(name + " placed " + block + " at " + locText + " on world " + world);
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (p.hasPermission("alert.message") || p.isOp()) {
                    p.sendMessage(prefix + ChatColor.RED + name + ChatColor.GREEN + " has placed " + ChatColor.GOLD + block + ChatColor.GREEN + " at " + ChatColor.RED + locText + ChatColor.GREEN + " on world " + ChatColor.GOLD + world);
                }
            }

            if (e.getBlock() == null) {
                return;
            }
        }
    }

}
