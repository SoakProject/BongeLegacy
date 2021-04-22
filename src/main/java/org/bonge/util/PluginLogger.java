package org.bonge.util;

import org.bonge.launch.BongeLaunch;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.OutputStream;
import java.util.logging.SimpleFormatter;
import java.util.logging.StreamHandler;

public interface PluginLogger {

    class PluginStreamHandler extends OutputStream {

        private String buffer = "";
        private final JavaPlugin plugin;

        public PluginStreamHandler(JavaPlugin plugin) {
            this.plugin = plugin;
        }

        @Override
        public void write(int b) {
            String write = new String(new int[]{b}, 0, 1);
            if (!write.equals("\n")) {
                this.buffer += write;
                return;
            }
            flushComplete();
        }

        public void flushComplete() {
            if (this.plugin == null) {
                BongeLaunch.getLogger().info(this.buffer);
            } else {
                BongeLaunch.getLogger().info("[" + BongeLaunch.PLUGIN_NAME + ": " + this.plugin.getName() + "] " + this.buffer);
            }
            this.buffer = "";
        }
    }

    static java.util.logging.Logger createLogger(JavaPlugin plugin) {
        if (plugin == null) {
            java.util.logging.Logger logger = java.util.logging.Logger.getLogger("Bonge");
            logger.addHandler(new StreamHandler(new PluginStreamHandler(null), new SimpleFormatter()));
            return logger;
        }
        java.util.logging.Logger logger = java.util.logging.Logger.getLogger(plugin.getName());
        logger.addHandler(new StreamHandler(new PluginStreamHandler(plugin), new SimpleFormatter()));
        return logger;
    }

}
