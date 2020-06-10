package org.bonge.launch;

import org.bonge.Bonge;
import org.bonge.bukkit.r1_13.block.data.BongeAbstractBlockData;
import org.bonge.bukkit.r1_13.block.data.blocks.directional.sign.BongeWallSign;
import org.bonge.bukkit.r1_13.server.BongeServer;
import org.bonge.bukkit.r1_13.server.plugin.BongePluginManager;
import org.bonge.command.BongeCommand;
import org.bonge.command.BongeControlCommand;
import org.bonge.config.BongeConfig;
import org.bonge.convert.block.BlockTypeConverter;
import org.bonge.convert.bossbar.ColorConverter;
import org.bonge.convert.bossbar.StyleConverter;
import org.bonge.convert.command.CommandSourceConverter;
import org.bonge.convert.entity.EntityConverter;
import org.bonge.convert.entity.EntityTypeConverter;
import org.bonge.convert.entity.GameModeConverter;
import org.bonge.convert.entity.ProjectileSourceConverter;
import org.bonge.convert.inventory.item.ItemStackConverter;
import org.bonge.convert.inventory.item.ItemStackSnapshotConverter;
import org.bonge.convert.inventory.item.ItemTypeConverter;
import org.bonge.convert.scoreboard.CriteriaConverter;
import org.bonge.convert.scoreboard.DisplaySlotConverter;
import org.bonge.convert.text.TextConverter;
import org.bonge.convert.world.*;
import org.bonge.convert.world.vector.Vector3dConverter;
import org.bonge.convert.world.vector.Vector3iConverter;
import org.bonge.listeners.*;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.block.BlockTypes;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.logging.Handler;
import java.util.logging.LogManager;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
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
        registerBlockData();
        BongeServer server = new BongeServer(Sponge.getServer());
        Bukkit.setServer(server);
        Bukkit.getPluginManager().loadPlugins(BongeLaunch.getConfig().getOrElse(BongeConfig.PATH_PLUGINS_FILE));
        Sponge.getCommandManager().register(launch, BongeControlCommand.createCommand(), "control", "bongecontrol");
        Sponge.getEventManager().registerListeners(launch, new BlockListener());
        Sponge.getEventManager().registerListeners(launch, new ConnectionListener());
        Sponge.getEventManager().registerListeners(launch, new InventoryListener());
        Sponge.getEventManager().registerListeners(launch, new PlayerListener());
        Sponge.getEventManager().registerListeners(launch, new SourceListener());
    }

    private static void registerBlockData(){
        BongeAbstractBlockData.register(new BongeWallSign(BlockTypes.WALL_SIGN.getDefaultState()));
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
        Bonge.getInstance().register(new EntityConverter());
        Bonge.getInstance().register(new EntityTypeConverter());
        Bonge.getInstance().register(new ColorConverter());
        Bonge.getInstance().register(new StyleConverter());
        Bonge.getInstance().register(new DisplaySlotConverter());
        Bonge.getInstance().register(new ProjectileSourceConverter(), new CommandSourceConverter());
        Bonge.getInstance().register(new ItemTypeConverter());
        Bonge.getInstance().register(new ItemStackConverter(), new ItemStackSnapshotConverter());
        Bonge.getInstance().register(new TextConverter());
        Bonge.getInstance().register(new CriteriaConverter());
        Bonge.getInstance().register(new WorldConverter());
        Bonge.getInstance().register(new LocationConverter());
        Bonge.getInstance().register(new TransformConverter());
        Bonge.getInstance().register(new Vector3dConverter(), new Vector3iConverter());
        Bonge.getInstance().register(new BlockTypeConverter(), new ItemTypeConverter());
        Bonge.getInstance().register(new DirectionConverter());
        Bonge.getInstance().register(new DimensionConverter());
        Bonge.getInstance().register(new GeneratorTypeConverter());
    }

    static void onDisable(BongeLaunch launch){
        BongeLaunch.getConfig().save();
    }
}
