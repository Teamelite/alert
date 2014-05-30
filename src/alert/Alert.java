
package alert;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author Dave's
 */
public class Alert extends JavaPlugin {

    
    @Override
    public void onEnable() {
        Bukkit.getLogger() .info("Plugin Enabled");
        PluginManager pm = this.getServer() .getPluginManager();
        pm.registerEvents(new BlockListener() , this);
         
    }
    
}
