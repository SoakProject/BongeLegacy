package org.bonge.launch;

import org.bonge.bukkit.r1_13.server.BongeServer;
import org.bonge.bukkit.r1_13.server.plugin.BongePluginManager;
import org.bonge.command.BongeCommand;
import org.bonge.config.BongeConfig;
import org.bonge.listeners.BlockListener;
import org.bonge.listeners.ConnectionListener;
import org.bonge.listeners.InventoryListener;
import org.bonge.listeners.PlayerListener;
import org.bukkit.Bukkit;
import org.spongepowered.api.Sponge;

import java.io.File;
import java.io.IOException;

public class BongeBukkitLaunch {

    static void onEnable(){
        BongeServer server = (BongeServer) Bukkit.getServer();
        BongePluginManager manager = server.getPluginManager();
        manager.bootPlugins();
    }

    static void onLoad(BongeLaunch launch){
        BongeServer server = new BongeServer(Sponge.getServer());
        Bukkit.setServer(server);
        File file = new File("config/bonge/config.json");
        file.getParentFile().mkdirs();
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        launch.config = new BongeConfig(file);
        File pluginsFile = launch.config.getOrElse(BongeConfig.PATH_PLUGINS_FILE);
        pluginsFile.mkdirs();
        Sponge.getCommandManager().register(launch, BongeCommand.build(), "bonge", "bukkit");
        server.getPluginManager().loadPlugins(pluginsFile);
        Sponge.getEventManager().registerListeners(launch, new BlockListener());
        Sponge.getEventManager().registerListeners(launch, new ConnectionListener());
        Sponge.getEventManager().registerListeners(launch, new InventoryListener());
        Sponge.getEventManager().registerListeners(launch, new PlayerListener());
    }

    static void onDisable(BongeLaunch launch){
        launch.config.save();
    }
}
