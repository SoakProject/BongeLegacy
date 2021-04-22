package org.bonge.launch;

import com.google.inject.Inject;
import org.bonge.bukkit.r1_16.command.BongeCommandManager;
import org.bonge.bukkit.r1_16.command.RawSpongeCommand;
import org.bonge.bukkit.r1_16.server.BongeServer;
import org.bonge.bukkit.r1_16.server.plugin.BongePluginManager;
import org.bonge.command.BongeCommand;
import org.bonge.config.BongeConfig;
import org.bonge.util.PluginLogger;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.spongepowered.api.Client;
import org.spongepowered.api.Server;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.Command;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.lifecycle.*;
import org.spongepowered.plugin.PluginContainer;
import org.spongepowered.plugin.jvm.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.function.IntFunction;
import java.util.logging.Logger;
import java.util.stream.Stream;

@Plugin(BongeLaunch.PLUGIN_ID)
public final class BongeLaunch {

    public static final String PLUGIN_ID = "bonge";
    public static final String PLUGIN_NAME = "Bonge";
    public static final String PLUGIN_VERSION = "2.0-SNAPSHOT";
    public static final String IMPLEMENTATION_VERSION = "1.16.5";

    private final org.apache.logging.log4j.Logger logger;
    private final PluginContainer container;
    private RegisterCommandEvent<Command.Raw> commandEvent;

    private BongeConfig config;
    private boolean isBukkitAPILoaded = true;
    private boolean isSpigotAPILoaded;
    private boolean isPaperAPILoaded;

    private static BongeLaunch instance;

    @Inject
    public BongeLaunch(final PluginContainer container) {
        instance = this;
        this.container = container;
        this.logger = container.getLogger();
        System.out.println("Bonge Launch");
    }

    @Listener
    public void onRegisterCommand(RegisterCommandEvent<Command.Parameterized> event) {
        System.out.println("Bonge Command register: " + this.container);
        Command.Parameterized cmd = BongeCommand.build();
        event.register(this.container, cmd, "bonge", "bukkit");
    }

    @Listener
    public void onRegisterPluginCommand(RegisterCommandEvent<Command.Raw> event) {
        BongeServer server = (BongeServer) Bukkit.getServer();
        BongePluginManager pluginManager = server.getPluginManager();
        BongeCommandManager commandManager = server.getCommandManager();
        org.bukkit.plugin.Plugin[] plugins = pluginManager.initPlugins(BongeLaunch.getConfig().getOrElse(BongeConfig.PATH_PLUGINS_FILE));
        Stream.of(plugins)
                .filter(plugin -> plugin instanceof JavaPlugin)
                .map(plugin -> (JavaPlugin) plugin)
                .forEach(plugin -> pluginManager.bootCommands(plugin).forEach(command -> {
                    String[] aliases = command.getAliases().toArray(new String[0]);
                    event.register(this.container, new RawSpongeCommand(command), command.getLabel(), aliases);
                    commandManager.register(command);
                }));
        this.commandEvent = event;
    }

    @Listener
    public void onConstruct(ConstructPluginEvent event) {
        System.out.println("Bonge construct plugin event");
        this.isBukkitAPILoaded = true;
        BongeServer server = new BongeServer();
        Bukkit.setServer(server);
        File file = new File("config/bonge/config.json");
        file.getParentFile().mkdirs();
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.config = new BongeConfig(file);
        File pluginsFile = this.config.getOrElse(BongeConfig.PATH_PLUGINS_FILE);
        pluginsFile.mkdirs();

    }

    @Listener
    public void onStartingServer(StartingEngineEvent<Server> event) {
        if (!this.isBukkitAPILoaded) {
            return;
        }
        BongeServer server = (BongeServer) Bukkit.getServer();
        server.setSpongeServer(Sponge.server());
        BongeBukkitLaunch.onInit();

    }

    @Listener
    public void onStartedServer(StartedEngineEvent<Server> event) {
        System.out.println("Bonge starting server");
        if (!this.isBukkitAPILoaded) {
            return;
        }
        BongeBukkitLaunch.onLoad(this);
        BongeBukkitLaunch.onEnable();
    }

    /*@Listener
    public void onStartingClient(StartingEngineEvent<Client> event){
        System.out.println("Bonge starting client");
        if(!this.isBukkitAPILoaded){
            return;
        }
        BongeBukkitLaunch.onEnable();
    }*/

    @Listener
    public void onDisableServer(StoppingEngineEvent<Server> event) {
        BongeBukkitLaunch.onDisable(this);
    }

    @Listener
    public void onDisableClient(StoppingEngineEvent<Client> event) {
        BongeBukkitLaunch.onDisable(this);
    }

    public static org.apache.logging.log4j.Logger getLogger() {
        return instance.logger;
    }

    public static Logger getLogger(JavaPlugin plugin) {
        return PluginLogger.createLogger(plugin);
    }

    public static BongeConfig getConfig() {
        return instance.config;
    }

    public static BongeLaunch getInstance() {
        return instance;
    }

    public static PluginContainer getContainer() {
        return getInstance().container;
    }

    public static boolean isBukkitAPILoaded() {
        return getInstance().isBukkitAPILoaded;
    }

    public static boolean isSpigotAPILoaded() {
        return getInstance().isSpigotAPILoaded;
    }

    public static boolean isPaperAPILoaded() {
        return getInstance().isPaperAPILoaded;
    }

}
