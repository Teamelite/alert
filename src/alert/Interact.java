package alert;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class Interact implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {

        if (e.getClickedBlock().getType() == Material.FURNACE) {
            alertPlayers(e);
        }

        if (e.getClickedBlock().getType() == Material.DISPENSER) {
            alertPlayers(e);
        }

        if (e.getClickedBlock().getType() == Material.DROPPER) {
            alertPlayers(e);
        }

        if (e.getClickedBlock().getType() == Material.CHEST) {
            alertPlayers(e);
        }

        if (e.getClickedBlock().getType() == Material.ENDER_CHEST) {
            alertPlayers(e);
        }

        if (e.getClickedBlock().getType() == Material.TRAPPED_CHEST) {
            alertPlayers(e);
        }

        if (e.getClickedBlock().getType() == Material.HOPPER) {
            alertPlayers(e);
        }

        if (e.getClickedBlock().getType() == Material.HOPPER_MINECART) {
            alertPlayers(e);
        }

        if (e.getClickedBlock().getType() == Material.POWERED_MINECART) {
            alertPlayers(e);
        }

        if (e.getClickedBlock().getType() == Material.STORAGE_MINECART) {
            alertPlayers(e);
        }

    }

    private void alertPlayers(PlayerInteractEvent e) {
        Block pBlock = e.getClickedBlock();
        String name = e.getPlayer().getDisplayName();
        String locText = pBlock.getLocation().getX() + ", " + pBlock.getLocation().getY() + ", " + pBlock.getLocation().getZ();
        String prefix = ChatColor.DARK_GRAY + "[" + ChatColor.DARK_RED + "Team" + ChatColor.GOLD + "Elite" + ChatColor.DARK_GRAY + "]";
        Material block = e.getClickedBlock().getType();
        String world = e.getPlayer().getWorld().getName();

        if (!e.getPlayer().hasPermission("alert.message")) {
            LogFile.logFile(name + " interacted with " + block + " at " + locText + " on world " + world);
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (p.hasPermission("alert.message") || p.isOp()) {
                    p.sendMessage(prefix + ChatColor.RED + name + ChatColor.GREEN + " has interacted with " + ChatColor.GOLD + block + ChatColor.GREEN + " at " + ChatColor.RED + locText + ChatColor.GREEN + " on world " + ChatColor.GOLD + world);
                }
            }
            if (e.getClickedBlock() == null) {
                return;
            }
        }
    }
}
