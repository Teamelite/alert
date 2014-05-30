/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alert;

import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

/**
 *
 * @author Dave's
 */
public class BlockListener implements Listener {

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        String name = event.getPlayer().toString();
        String locText = event.getBlockPlaced().getLocation().toString();
        String prefix = ChatColor.DARK_GRAY + "[" + ChatColor.DARK_RED + "Team" + ChatColor.GOLD + "Elite" + ChatColor.DARK_GRAY + "]";
        String block = event.getBlockPlaced().toString();

        if (event.getBlock().getType() == Material.FURNACE) {
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (p.hasPermission("alert.message") || p.isOp()) {
                    p.sendMessage(prefix + ChatColor.RED + name + ChatColor.GREEN + " Has Placed " + ChatColor.GOLD + block + ChatColor.GREEN + "at" + ChatColor.RED + locText);
                }
            }

        }
    }

}
