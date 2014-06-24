
package alert;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Alert extends JavaPlugin {

    
    @Override
    public void onEnable() {
        Bukkit.getLogger() .info("Plugin Enabled");
        PluginManager pm = this.getServer() .getPluginManager();
        pm.registerEvents(new BlockPlace() , this);
        pm.registerEvents(new Interact() , this);
        getCommand("jail").setExecutor(new Commands());
        LogFile log = new LogFile(this);
        log.logFile("Block place and interactions");
        getConfig().options().copyDefaults(true);
        saveConfig();
    }
    


    
}

