package alert;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Alert extends JavaPlugin {

    public static Plugin plugin;

    @Override
    public void onEnable() {
        plugin = this;
        Bukkit.getLogger().info("Plugin Enabled");
        getConfig().options().copyDefaults(true);
        saveConfig();
        PluginManager pm = this.getServer().getPluginManager();
        pm.registerEvents(new BlockPlace(), this);
        pm.registerEvents(new Interact(), this);
        pm.registerEvents(new ChestCount(), this);
        Commands cmds = new Commands();
                
        getCommand("setworld").setExecutor(cmds);
        getCommand("showworld").setExecutor(cmds);
        getCommand("countchests").setExecutor(cmds);
        getCommand("reset").setExecutor(cmds);
        getCommand("ignoreworld").setExecutor(cmds);
        getCommand("logworld").setExecutor(cmds);

        LogFile log = new LogFile(this);
        log.logFile("Block place and interactions");
    }

    public void onDisable() {
        Bukkit.getLogger().info("Plugin Disabled");
        getConfig().set("World", "world");
        getConfig().set("World.Chests", 0);
        getConfig().set("World.EnderChests", 0);
    }
    
    public static Plugin getInstance(){
        return plugin;
    }
}
