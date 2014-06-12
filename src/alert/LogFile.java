package alert;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LogFile {

    public static BufferedWriter pw;
    private static Alert plugin;

    public LogFile(Alert instance) {
        LogFile.plugin = instance;
    }
    
    public static void logFile(String message) {
        try {

            File pluginFolder = plugin.getDataFolder();
            File log = new File(plugin.getDataFolder(), "log.txt");
            pw = new BufferedWriter(new FileWriter(log));

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
