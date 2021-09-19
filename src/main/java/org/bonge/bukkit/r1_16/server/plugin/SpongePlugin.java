package org.bonge.bukkit.r1_16.server.plugin;

import org.bonge.util.exception.NotImplementedException;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginLoader;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.plugin.PluginContainer;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Logger;

public class SpongePlugin implements Plugin {

    private final PluginContainer plugin;

    public SpongePlugin(PluginContainer plugin) {
        this.plugin = plugin;
    }

    @Override
    public @NotNull File getDataFolder() {
        return new File("config/" + this.plugin.metadata().id());
    }

    @Override
    public @NotNull PluginDescriptionFile getDescription() {
        return new PluginDescriptionFile(this.plugin.metadata().name().orElse(this.plugin.metadata().id()), this.plugin.metadata().version().toString(), "Unknown");
    }

    @Override
    public @NotNull FileConfiguration getConfig() {
        throw new NotImplementedException("Not implemented yet");

    }

    @Override
    public InputStream getResource(@NotNull String filename) {
        throw new NotImplementedException("Not implemented yet");

    }

    @Override
    public void saveConfig() {

    }

    @Override
    public void saveDefaultConfig() {

    }

    @Override
    public void saveResource(@NotNull String resourcePath, boolean replace) {

    }

    @Override
    public void reloadConfig() {

    }

    @Override
    public @NotNull PluginLoader getPluginLoader() {
        throw new NotImplementedException("Not implemented yet");
    }

    @Override
    public @NotNull Server getServer() {
        return Bukkit.getServer();
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public void onDisable() {

    }

    @Override
    public void onLoad() {

    }

    @Override
    public void onEnable() {

    }

    @Override
    public boolean isNaggable() {
        throw new NotImplementedException("Not implemented yet");
    }

    @Override
    public void setNaggable(boolean canNag) {
        throw new NotImplementedException("Not implemented yet");
    }

    @Override
    public ChunkGenerator getDefaultWorldGenerator(@NotNull String worldName, String id) {
        throw new NotImplementedException("Not implemented yet");
    }

    @Override
    public @NotNull Logger getLogger() {
        throw new IllegalStateException("Not implemented yet");
    }

    @Override
    public @NotNull String getName() {
        return this.plugin.metadata().name().orElse(this.plugin.metadata().id());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return null;
    }
}
