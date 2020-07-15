package org.bonge.launch;

import com.sun.istack.internal.logging.Logger;
import org.bonge.command.BongeCommand;
import org.bonge.config.BongeConfig;
import org.spongepowered.api.Client;
import org.spongepowered.api.Server;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.lifecycle.ConstructPluginEvent;
import org.spongepowered.api.event.lifecycle.StartingEngineEvent;
import org.spongepowered.api.event.lifecycle.StoppingEngineEvent;
import org.spongepowered.plugin.PluginContainer;
import org.spongepowered.plugin.jvm.Plugin;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;

@Plugin(BongeLaunch.PLUGIN_ID)
public class BongeLaunch {

    public static final String PLUGIN_ID ="bonge";
    public static final String PLUGIN_NAME = "Bonge";
    public static final String PLUGIN_VERSION = "1.0-SNAPSHOT";
    public static final String IMPLEMENTATION_VERSION = "1.13.2";

    private final Logger logger;
    private final PluginContainer container;

    private BongeConfig config;
    private boolean isBukkitAPILoaded;
    private boolean isSpigotAPILoaded;
    private boolean isPaperAPILoaded;

    private static BongeLaunch instance;

    @Inject
    public BongeLaunch(final Logger logger, final PluginContainer container){
        instance = this;
        this.logger = logger;
        this.container = container;
    }

    @Listener
    public void onConstruct(ConstructPluginEvent event){
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
        Sponge.getCommandManager().getStandardRegistrar().register(container, BongeCommand.build(), "bonge", "bukkit");
        if(this.isBukkitAPILoaded) {
            BongeBukkitLaunch.onLoad(this);
        }
    }

    @Listener
    public void onStartingServer(StartingEngineEvent<Server> event){
        if(!this.isBukkitAPILoaded){
            return;
        }
        BongeBukkitLaunch.onEnable();
    }

    @Listener
    public void onStartingClient(StartingEngineEvent<Client> event){
        if(!this.isBukkitAPILoaded){
            return;
        }
        BongeBukkitLaunch.onEnable();
    }

    @Listener
    public void onDisableServer(StoppingEngineEvent<Server> event){
        BongeBukkitLaunch.onDisable(this);
    }

    @Listener
    public void onDisableClient(StoppingEngineEvent<Client> event){
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
