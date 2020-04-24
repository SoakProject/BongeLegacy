package org.bonge.launch;

import org.bonge.config.BongeConfig;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GamePostInitializationEvent;
import org.spongepowered.api.event.game.state.GameStartingServerEvent;
import org.spongepowered.api.event.game.state.GameStoppedServerEvent;
import org.spongepowered.api.plugin.Plugin;

import javax.inject.Inject;
import java.util.logging.Logger;

@Plugin(id = BongeLaunch.PLUGIN_ID, name = BongeLaunch.PLUGIN_NAME, version = BongeLaunch.PLUGIN_VERSION)
public class BongeLaunch {

    public static final String PLUGIN_ID ="bonge";
    public static final String PLUGIN_NAME = "Bonge";
    public static final String PLUGIN_VERSION = "1.0-SNAPSHOT";

    @Inject
    private Logger logger;

    BongeConfig config;

    private static BongeLaunch instance;

    public BongeLaunch(){
        instance = this;
    }

    @Listener
    public void onLoad(GamePostInitializationEvent event){
        BongeBukkitLaunch.onLoad(this);
    }

    @Listener
    public void onStarting(GameStartingServerEvent event){
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

}
