package org.bonge.launch;

import org.bonge.Bonge;
import org.bonge.bukkit.r1_16.material.BongeMaterial;
import org.bonge.bukkit.r1_16.server.BongeServer;
import org.bonge.bukkit.r1_16.server.plugin.BongePluginManager;
import org.bonge.config.BongeConfig;
import org.bonge.convert.block.BlockTypeConverter;
import org.bonge.convert.bossbar.ColorConverter;
import org.bonge.convert.bossbar.StyleConverter;
import org.bonge.convert.command.CommandSubjectSourceConverter;
import org.bonge.convert.entity.EntityConverter;
import org.bonge.convert.entity.EntityTypeConverter;
import org.bonge.convert.entity.GameModeConverter;
import org.bonge.convert.entity.ProjectileSourceConverter;
import org.bonge.convert.inventory.item.ItemStackConverter;
import org.bonge.convert.inventory.item.ItemStackSnapshotConverter;
import org.bonge.convert.inventory.item.ItemTypeConverter;
import org.bonge.convert.scoreboard.CriteriaConverter;
import org.bonge.convert.scoreboard.DisplaySlotConverter;
import org.bonge.convert.world.WorldConverter;
import org.bonge.listeners.*;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.spongepowered.api.Sponge;

import java.lang.reflect.Modifier;
import java.util.stream.Stream;

public class BongeBukkitLaunch {

    static void onEnable() {
        BongeServer server = (BongeServer) Bukkit.getServer();
        BongePluginManager manager = server.getPluginManager();
        manager.bootPlugins();
    }

    static void onInit() {
        System.out.println("Bonge init");
        registerMaterials();
        registerConverters();
        registerBlockData();
        ((BongePluginManager) Bukkit.getPluginManager()).initPlugins(BongeLaunch.getConfig().getOrElse(BongeConfig.PATH_PLUGINS_FILE));

    }

    static void onLoad(BongeLaunch launch) {
        //((BongePluginManager) Bukkit.getPluginManager()).initPlugins(BongeLaunch.getConfig().getOrElse(BongeConfig.PATH_PLUGINS_FILE));
        Bukkit.getPluginManager().loadPlugins(BongeLaunch.getConfig().getOrElse(BongeConfig.PATH_PLUGINS_FILE));
        //Sponge.getCommandManager().getStandardRegistrar().register(BongeLaunch.getContainer(), BongeControlCommand.createCommand(), "control", "bongecontrol");
        Sponge.eventManager().registerListeners(BongeLaunch.getContainer(), new BlockListener());
        Sponge.eventManager().registerListeners(BongeLaunch.getContainer(), new ConnectionListener());
        Sponge.eventManager().registerListeners(BongeLaunch.getContainer(), new InventoryListener());
        Sponge.eventManager().registerListeners(BongeLaunch.getContainer(), new PlayerListener());
        Sponge.eventManager().registerListeners(BongeLaunch.getContainer(), new SourceListener());
    }

    private static void registerBlockData() {
    }

    static void registerMaterials() {
        Stream.of(Material.class.getDeclaredFields())
                .filter(f -> Modifier.isStatic(f.getModifiers()))
                .filter(f -> f.getDeclaringClass().isAssignableFrom(Material.class))
                .forEach(f -> {
                    try {
                        f.setAccessible(true);
                        Object bMaterial = f.get(null);
                        BongeMaterial material = ((Material) bMaterial).getWrapper();
                        material.toBlock().ifPresent(block -> Bonge.getInstance().register(block));
                        material.toItem().ifPresent(block -> Bonge.getInstance().register(block));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                });
    }

    static void registerConverters() {
        Bonge.getInstance().register(new GameModeConverter());
        Bonge.getInstance().register(new EntityConverter());
        Bonge.getInstance().register(new EntityTypeConverter());
        Bonge.getInstance().register(new ColorConverter());
        Bonge.getInstance().register(new StyleConverter());
        Bonge.getInstance().register(new DisplaySlotConverter());
        Bonge.getInstance().register(new ProjectileSourceConverter());
        Bonge.getInstance().register(new CommandSubjectSourceConverter());
        Bonge.getInstance().register(new ItemTypeConverter());
        Bonge.getInstance().register(new ItemStackConverter());
        Bonge.getInstance().register(new ItemStackSnapshotConverter());
        Bonge.getInstance().register(new CriteriaConverter());
        Bonge.getInstance().register(new WorldConverter());
        Bonge.getInstance().register(new BlockTypeConverter());
        Bonge.getInstance().register(new ItemTypeConverter());
        //Bonge.getInstance().register(new GeneratorTypeConverter());
    }

    static void onDisable(BongeLaunch launch) {
        BongeLaunch.getConfig().save();
    }
}
