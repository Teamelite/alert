package io.teamelite.alert;

import static io.teamelite.alert.BlockPlace.prefix;
import static io.teamelite.alert.ChestCount.count;
import static io.teamelite.alert.ChestCount.count2;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

public class Commands implements CommandExecutor {

    private static final Plugin plugin = Alert.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        List<String> worldList = plugin.getConfig().getStringList("IgnoreWorlds");
        String worldname = plugin.getConfig().getString("World");
        int chests = plugin.getConfig().getInt("Chests");
        int echests = plugin.getConfig().getInt("EnderChests");

        if (cmd.getName().equalsIgnoreCase("setworld") && sender.isOp()) {
            if (args.length < 1) {
                return false;
            }
            World world = (Bukkit.getServer().getWorld(args[0]));
            if (world == null) {
                sender.sendMessage(prefix + ChatColor.GOLD + "The world " + ChatColor.GREEN + args[0] + ChatColor.GOLD + " does not exsist.");
                return true;
            } else {
                plugin.getConfig().set("World", args[0]);
                plugin.getConfig().set("Chests", 0);
                plugin.getConfig().set("EnderChests", 0);
                plugin.saveConfig(); 
                sender.sendMessage(prefix + ChatColor.GOLD + "The world has been set to " + ChatColor.GREEN + args[0]);
                return true;
            }
        }

        if (cmd.getName().equalsIgnoreCase("showworld")) {
            if (worldname == null) {
                sender.sendMessage(prefix + ChatColor.GOLD + "The world is not set.");
                return true;
            } else {
                sender.sendMessage(prefix + ChatColor.GOLD + "The selected world is " + ChatColor.GREEN + worldname);
                return true;
            }
        }

        if (cmd.getName().equalsIgnoreCase("countchests")) {

            if (chests == 0 && echests == 0) {
                sender.sendMessage(prefix + ChatColor.GOLD + "There are no chests on the world.");
                return true;
            } else {
                sender.sendMessage(prefix + ChatColor.GOLD + "Chests: " + ChatColor.GREEN + chests);
                sender.sendMessage(prefix + ChatColor.GOLD + "Ender Chests: " + ChatColor.GREEN + echests);
                return true;
            }
        }

        if (cmd.getName().equalsIgnoreCase("reset") && sender.isOp()) {
            if (chests == 0 && echests == 0) {
                sender.sendMessage(prefix + ChatColor.GOLD + "Chests are already 0");
                return true;
            } else {
                plugin.getConfig().set("Chests", 0);
                plugin.getConfig().set("EnderChests", 0);
                count = 0;
                count2 = 0;
                sender.sendMessage(prefix + ChatColor.GOLD + "The counter has been reset.");
                return true;
            }
        }

        if (cmd.getName().equalsIgnoreCase("ignoreworld")) {
            if (args.length < 1) {
                return false;
            }
            World world = (Bukkit.getServer().getWorld(args[0]));
            if (world == null) {
                sender.sendMessage(prefix + ChatColor.GOLD + "The world " + ChatColor.GREEN + args[0] + ChatColor.GOLD + " does not exsist.");
                return true;
            } else {
                worldList.add(args[0]);
                plugin.getConfig().set("IgnoreWorlds", worldList);
                plugin.saveConfig();
                sender.sendMessage(prefix + " " + ChatColor.GREEN + args[0] + ChatColor.GOLD + " is now Ignored.");
                return true;
            }
        }

        if (cmd.getName().equalsIgnoreCase("logworld")) {
            if (args.length < 1) {
                return false;
            }
            World world = (Bukkit.getServer().getWorld(args[0]));
            if (world == null) {
                sender.sendMessage(prefix + ChatColor.GOLD + "The world " + ChatColor.GREEN + args[0] + ChatColor.GOLD + " does not exsist.");
                return true;
            } else {
                worldList.remove(args[0]);
                plugin.getConfig().set("IgnoreWorlds", worldList);
                plugin.saveConfig();
                sender.sendMessage(prefix + " " + ChatColor.GREEN + args[0] + ChatColor.GOLD + " is now logged.");
                return true;
            }
        }
        return false;
    }
}
