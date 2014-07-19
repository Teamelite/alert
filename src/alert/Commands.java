package alert;

import static alert.BlockPlace.prefix;
import static alert.ChestCount.count;
import static alert.ChestCount.count2;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class Commands implements CommandExecutor {

    private static final Plugin plugin = Alert.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        String worldname = plugin.getConfig().getString("World");
        int chests = plugin.getConfig().getInt("Chests");
        int echests = plugin.getConfig().getInt("EnderChests");

        if (cmd.getName().equalsIgnoreCase("setworld") && sender.isOp() || sender.hasPermission("alert.commands")) {
            if (args.length <1) {
                return false;
            }
            World world = (Bukkit.getServer().getWorld(args[0]));
            if (world == null) {
                sender.sendMessage(prefix + ChatColor.GREEN + "The world " + ChatColor.GOLD + args[0] + ChatColor.GREEN + " does not exsist.");
                return true;
            } else {
                plugin.getConfig().set("World", args[0]);
                plugin.getConfig().set("Chests", 0);
                plugin.getConfig().set("EnderChests", 0);
                sender.sendMessage(prefix + ChatColor.GREEN + "The world has been set to " + ChatColor.GOLD + args[0]);
                return true;
            }
        }

        if (cmd.getName().equalsIgnoreCase("showworld")) {
            if (worldname == null) {
                sender.sendMessage(prefix + ChatColor.GREEN + "The world is not set.");
                return true;
            } else {
                sender.sendMessage(prefix + ChatColor.GREEN + "The selected world is " + ChatColor.GOLD + worldname);
                return true;
            }
        }

        if (cmd.getName().equalsIgnoreCase("countchests")) {

            if (chests == 0 && echests == 0) {
                sender.sendMessage(prefix + ChatColor.GREEN + "There are no chests on the world.");
                return true;
            } else {
                sender.sendMessage(prefix + ChatColor.GREEN + "Chests: " + ChatColor.GOLD + chests);
                sender.sendMessage(prefix + ChatColor.GREEN + "Ender Chests: " + ChatColor.GOLD + echests);
                return true;
            }
        }

        if (cmd.getName().equalsIgnoreCase("reset") && sender.isOp() || sender.hasPermission("alert.commands")) {
            if (chests == 0 && echests == 0) {
                sender.sendMessage(prefix + ChatColor.GREEN + "Chests are already 0");
                return true;
            } else {
                plugin.getConfig().set("Chests", 0);
                plugin.getConfig().set("EnderChests", 0);
                count = 0;
                count2 = 0;
                sender.sendMessage(prefix + ChatColor.GREEN + "The counter has been reset.");
                return true;
            }
        }
        return false;
    }
}
