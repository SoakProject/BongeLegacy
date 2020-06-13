package org.bonge.launch;

import org.bonge.command.BongeCommand;
import org.bonge.config.BongeConfig;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GamePostInitializationEvent;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.event.game.state.GameStartingServerEvent;
import org.spongepowered.api.event.game.state.GameStoppedServerEvent;
import org.spongepowered.api.plugin.Dependency;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.plugin.PluginContainer;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.logging.Logger;

@Plugin(id = BongeLaunch.PLUGIN_ID, name = BongeLaunch.PLUGIN_NAME, version = BongeLaunch.PLUGIN_VERSION)
public class BongeLaunch {

    public static final String PLUGIN_ID ="bonge";
    public static final String PLUGIN_NAME = "Bonge";
    public static final String PLUGIN_VERSION = "1.0-SNAPSHOT";
    public static final String IMPLEMENTATION_VERSION = "1.13.2";

    @Inject
    private Logger logger;

    @Inject
    private PluginContainer container;

    private BongeConfig config;
    private boolean isBukkitAPILoaded;
    private boolean isSpigotAPILoaded;
    private boolean isPaperAPILoaded;

    private static BongeLaunch instance;

    public BongeLaunch(){
        instance = this;
    }

    @Listener
    public void onLoad(GamePostInitializationEvent event){
        this.isBukkitAPILoaded = true;
        File file = new File("config/bonge/config.json");
        file.getParentFile().mkdirs();
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.config = new BongeConfig(file);
        File pluginsFile = this.config.getOrElse(BongeConfig.PATH_PLUGINS_FILE);
        pluginsFile.mkdirs();
        Sponge.getCommandManager().register(this, BongeCommand.build(), "bonge", "bukkit");
        if(this.isBukkitAPILoaded) {
            BongeBukkitLaunch.onLoad(this);
        }
    }

    @Listener
    public void onStarting(GameStartingServerEvent event){
        if(!this.isBukkitAPILoaded){
            return;
        }
        BongeBukkitLaunch.onEnable();
    }

    @Listener
    public void onDisable(GameStoppedServerEvent event){
        BongeBukkitLaunch.onDisable(this);
    }

    public static Logger getLogger(){
        return instance.logger;
    }

    public static BongeConfig getConfig(){
        return instance.config;
    }

    public static BongeLaunch getInstance(){
        return instance;
    }

    public static PluginContainer getContainer() {
        return getInstance().container;
    }

    public static boolean isBukkitAPILoaded(){
        return getInstance().isBukkitAPILoaded;
    }

    public static boolean isSpigotAPILoaded(){
        return getInstance().isSpigotAPILoaded;
    }

    public static boolean isPaperAPILoaded(){
        return getInstance().isPaperAPILoaded;
    }

}
