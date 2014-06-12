package alert;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class LogFile {

    public static PrintWriter pw;
    private static Alert plugin;

    public LogFile(Alert instance) {
        LogFile.plugin = instance;
    }
    
    public static void logFile(String message) {
        try {

            File pluginFolder = plugin.getDataFolder();
            File log = new File(plugin.getDataFolder(), "log.txt");
            FileWriter fw = new FileWriter(log, true);
            pw = new PrintWriter(fw);
            
            if (!pluginFolder.exists()) {
                pluginFolder.mkdir();
            }

            if (!log.exists()) {
                log.createNewFile();
            }
            
            pw.write(message + "\n");
            pw.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
