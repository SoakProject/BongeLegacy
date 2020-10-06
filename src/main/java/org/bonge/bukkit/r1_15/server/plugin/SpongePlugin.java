package org.bonge.bukkit.r1_15.server.plugin;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginLoader;
import org.spongepowered.plugin.PluginContainer;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Logger;

public class SpongePlugin implements Plugin {

    private PluginContainer plugin;

    public SpongePlugin(PluginContainer plugin){
        this.plugin = plugin;
    }
    @Override
    public File getDataFolder() {
        return new File("config/" + this.plugin.getMetadata().getId());
    }

    @Override
    public PluginDescriptionFile getDescription() {
        return new PluginDescriptionFile(this.plugin.getMetadata().getName().orElse(this.plugin.getMetadata().getId()), this.plugin.getMetadata().getVersion(), "Unknown");
    }

    @Override
    public FileConfiguration getConfig() {
        return null;
    }

    @Override
    public InputStream getResource(String filename) {
        return null;
    }

    @Override
    public void saveConfig() {

    }

    @Override
    public void saveDefaultConfig() {

    }

    @Override
    public void saveResource(String resourcePath, boolean replace) {

    }

    @Override
    public void reloadConfig() {

    }

    @Override
    public PluginLoader getPluginLoader() {
        return null;
    }

    @Override
    public Server getServer() {
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
        return false;
    }

    @Override
    public void setNaggable(boolean canNag) {

    }

    @Override
    public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
        return null;
    }

    @Override
    public Logger getLogger() {
        return null;
    }

    @Override
    public String getName() {
        return this.plugin.getMetadata().getName().orElse(this.plugin.getMetadata().getId());
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
