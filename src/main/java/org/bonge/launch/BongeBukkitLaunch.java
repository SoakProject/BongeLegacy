package org.bonge.launch;

import org.bonge.Bonge;
import org.bonge.bukkit.r1_13.server.BongeServer;
import org.bonge.bukkit.r1_13.server.plugin.BongePluginManager;
import org.bonge.command.BongeCommand;
import org.bonge.config.BongeConfig;
import org.bonge.convert.block.BlockTypeConverter;
import org.bonge.convert.bossbar.ColorConverter;
import org.bonge.convert.bossbar.StyleConverter;
import org.bonge.convert.command.CommandSourceConverter;
import org.bonge.convert.entity.GameModeConverter;
import org.bonge.convert.entity.ProjectileSourceConverter;
import org.bonge.convert.inventory.item.ItemTypeConverter;
import org.bonge.convert.scoreboard.CriteriaConverter;
import org.bonge.convert.scoreboard.DisplaySlotConverter;
import org.bonge.convert.text.TextConverter;
import org.bonge.convert.world.DirectionConverter;
import org.bonge.convert.world.LocationConverter;
import org.bonge.convert.world.TransformConverter;
import org.bonge.convert.world.vector.Vector3dConverter;
import org.bonge.convert.world.vector.Vector3iConverter;
import org.bonge.listeners.BlockListener;
import org.bonge.listeners.ConnectionListener;
import org.bonge.listeners.InventoryListener;
import org.bonge.listeners.PlayerListener;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.spongepowered.api.Sponge;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.util.stream.Stream;

public class BongeBukkitLaunch {

    static void onEnable(){
        BongeServer server = (BongeServer) Bukkit.getServer();
        BongePluginManager manager = server.getPluginManager();
        manager.bootPlugins();
    }

    static void onLoad(BongeLaunch launch){
        registerMaterials();
        registerConverters();
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

    static void registerMaterials(){
        Stream.of(Material.class.getDeclaredFields())
                .filter(f -> Modifier.isStatic(f.getModifiers()))
                .filter(f -> f.getDeclaringClass().isAssignableFrom(Material.class))
                .forEach(f -> {
                    try {
                        Bonge.getInstance().register((Material) f.get(null));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                });
    }

    static void registerConverters(){
        Bonge.getInstance().register(new GameModeConverter());
        Bonge.getInstance().register(new ColorConverter());
        Bonge.getInstance().register(new StyleConverter());
        Bonge.getInstance().register(new DisplaySlotConverter());
        Bonge.getInstance().register(new ProjectileSourceConverter(), new CommandSourceConverter());
        Bonge.getInstance().register(new TextConverter());
        Bonge.getInstance().register(new CriteriaConverter());
        Bonge.getInstance().register(new LocationConverter());
        Bonge.getInstance().register(new TransformConverter());
        Bonge.getInstance().register(new Vector3dConverter(), new Vector3iConverter());
        Bonge.getInstance().register(new BlockTypeConverter(), new ItemTypeConverter());
        Bonge.getInstance().register(new DirectionConverter());
    }

    static void onDisable(BongeLaunch launch){
        launch.config.save();
    }
}
