package alert;

import static alert.Alert.getInstance;
import static alert.BlockPlace.prefix;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

public class Commands implements CommandExecutor {

    private static Alert plugin;

    private final Plugin instance = getInstance();

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (plugin != null) {
            String worldname = plugin.getConfig().getString("World");
            World world = (Bukkit.getServer().getWorld(args[0]));
            int chests = plugin.getConfig().getInt("Chests");
            int echests = plugin.getConfig().getInt("EnderChests");

            if (cmd.getName().equalsIgnoreCase("setworld")) {
                if (world == null) {
                    sender.sendMessage(prefix + ChatColor.GREEN + "The world " + ChatColor.GOLD + args[0] + ChatColor.GREEN + " does not exsist.");
                } else {
                    plugin.getConfig().set("World", args[0]);
                    plugin.getConfig().set("Chests", 0);
                    plugin.getConfig().set("EnderChests", 0);
                    sender.sendMessage(prefix + ChatColor.GREEN + "The world has been set to " + ChatColor.GOLD + args[0]);
                }
            }

            if (cmd.getName().equalsIgnoreCase("showworld")) {
                if (worldname == null) {
                    sender.sendMessage(prefix + ChatColor.GREEN + "The world is not set.");
                } else {
                    sender.sendMessage(prefix + ChatColor.GREEN + "The selected world is " + ChatColor.GOLD + worldname);
                }
            }

            if (cmd.getName().equalsIgnoreCase("countchests")) {

                if (chests == 0 && echests == 0) {
                    sender.sendMessage(prefix + ChatColor.GREEN + "There are no chests on the world.");
                } else {
                    sender.sendMessage(prefix + ChatColor.GREEN + "Chests: " + ChatColor.GOLD + chests);
                    sender.sendMessage(prefix + ChatColor.GREEN + "Ender Chests: " + ChatColor.GOLD + echests);
                }
            }

            if (cmd.getName().equalsIgnoreCase("reset")) {
                if (chests == 0 && echests == 0) {
                    sender.sendMessage(prefix + ChatColor.GREEN + "Chests are already 0");
                } else {
                    plugin.getConfig().set("Chests", 0);
                    plugin.getConfig().set("EnderChests", 0);
                    sender.sendMessage(prefix + ChatColor.GREEN + "The counter has been reset.");
                }
            }

        } else {
            sender.sendMessage(prefix + ChatColor.RED + "There was an internal error in the plugin.");
        }
        return false;
    }
}
