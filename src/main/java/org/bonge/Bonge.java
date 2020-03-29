package org.bonge;


import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class Bonge {

    public static void createCrashFile(Plugin plugin, String where, Throwable throwable){
        LocalDateTime time = LocalDateTime.now();
        File file = new File("crash-reports/bonge/" + (plugin == null ? "" : plugin.getName() + "/" + where + "/") + time.getYear() + " " + time.getMonth().name() + " " + time.getDayOfMonth() + " at " + time.getHour() + "." + time.getMinute() + "." + time.getSecond() + "." + time.getNano() + ".txt");
        try {
            file.getParentFile().mkdirs();
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            throwable.printStackTrace(new PrintWriter(writer));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
