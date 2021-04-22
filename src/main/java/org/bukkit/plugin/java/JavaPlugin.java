package org.bukkit.plugin.java;

import com.google.common.base.Charsets;
import org.bonge.bukkit.r1_16.server.plugin.BongePluginManager;
import org.bonge.bukkit.r1_16.server.plugin.loader.BongeURLClassLoader;
import org.bonge.bukkit.r1_16.server.plugin.loader.IBongePluginLoader;
import org.bonge.config.BongeConfig;
import org.bonge.launch.BongeLaunch;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.PluginBase;
import org.bukkit.plugin.PluginDescriptionFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.regex.Pattern;

public abstract class JavaPlugin extends PluginBase {

    private JavaPluginLoader loader;
    private FileConfiguration storedConfig;
    private UUID uuid;

    public JavaPlugin() {
        this.uuid = UUID.randomUUID();
    }

    //THIS IS ONLY TO COMPILE, ITS NOT REALLY USED
    public void init(JavaPluginLoader loader, Server server, PluginDescriptionFile description, File location, File file, PluginClassLoader cLoader) {
        this.loader = loader;
    }

    public File getConfigFile() {
        return new File(getDataFolder(), "config.yml");
    }

    public File getFile() {
        return this.loader.getFile();
    }

    @NotNull
    @Override
    public File getDataFolder() {
        return new File(BongeLaunch.getConfig().getOrElse(BongeConfig.PATH_PLUGINS_CONFIG), this.getName());
    }

    @NotNull
    @Override
    public PluginDescriptionFile getDescription() {
        return this.getPluginLoader().getYaml();
    }

    @NotNull
    @Override
    public FileConfiguration getConfig() {
        if (storedConfig == null) {
            reloadConfig();
        }
        return storedConfig;
    }

    @Nullable
    @Override
    public InputStream getResource(@NotNull String filename) {
        return ((BongePluginManager) Bukkit.getServer().getPluginManager()).getLoader().getResourceAsStream(filename);
    }

    @Override
    public void saveConfig() {
        try {
            getConfig().save(this.getConfigFile());
        } catch (IOException e) {
            System.out.println("Could not save config to " + this.getConfigFile().getAbsolutePath());
            e.printStackTrace();
        }
    }

    @Override
    public void saveDefaultConfig() {
        if (!getConfigFile().exists()) {
            saveResource("config.yml", false);
        }
    }

    @Override
    public void saveResource(@NotNull String resourcePath, boolean replace) {
        if (resourcePath == null || resourcePath.equals("")) {
            throw new IllegalStateException("Unknown resource path");
        }
        resourcePath = resourcePath.replaceAll(Pattern.quote("\\"), "/");
        InputStream stream = this.getResource(resourcePath);
        if (stream == null) {
            throw new IllegalStateException("Unknown resource file at '" + resourcePath + "'");
        }

        File outputFile = new File(this.getDataFolder(), resourcePath);
        int lastIndex = resourcePath.lastIndexOf('/');
        try {
            if ((outputFile.exists() && replace) || !outputFile.exists()) {
                if (!outputFile.exists()) {
                    outputFile.getParentFile().createNewFile();
                    outputFile.createNewFile();
                }
                FileOutputStream fos = new FileOutputStream(outputFile);
                int read;
                while ((read = stream.read()) != -1) {
                    fos.write(read);
                }
                fos.flush();
                fos.close();
                stream.close();
                return;
            }
        } catch (IOException e) {
            throw new IllegalStateException("Could not save '" + outputFile.getAbsolutePath() + "'", e);
        }
    }

    @Override
    public void reloadConfig() {
        this.storedConfig = YamlConfiguration.loadConfiguration(this.getConfigFile());
        InputStream stream = this.getResource("config.yml");
        if (stream == null) {
            System.err.println("No default config found for '" + this.getName() + "': using blank");
            return;
        }
        this.storedConfig.setDefaults(YamlConfiguration.loadConfiguration(new InputStreamReader(stream, Charsets.UTF_8)));
    }

    public void setEnabled(boolean check) {
        this.getPluginLoader().setEnabled(check);
    }

    @Override
    @NotNull
    public JavaPluginLoader getPluginLoader() {
        if (this.loader != null) {
            return this.loader;
        }
        Optional<IBongePluginLoader> opPlugin = ((BongePluginManager) Bukkit.getServer().getPluginManager()).getPluginLoader(this);
        return (JavaPluginLoader) opPlugin.get();
    }

    @NotNull
    @Override
    public Server getServer() {
        return Bukkit.getServer();
    }

    @Override
    public boolean isEnabled() {
        return this.getPluginLoader().isEnabled();
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

    @Nullable
    @Override
    public ChunkGenerator getDefaultWorldGenerator(@NotNull String worldName, @Nullable String id) {
        return null;
    }

    @NotNull
    @Override
    public Logger getLogger() {
        return Logger.getLogger("[" + this.getName() + "]");
    }

    @Nullable
    public PluginCommand getCommand(@NotNull String name) {
        String alias = name.toLowerCase(java.util.Locale.ENGLISH);
        PluginCommand command = getServer().getPluginCommand(alias);
        if (command == null || !equals(command.getPlugin())) {
            command = getServer().getPluginCommand(this.getDescription().getName().toLowerCase() + ":" + alias);
        }
        if (command != null && equals(command.getPlugin())) {
            return command;
        }
        return null;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return false;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        return new ArrayList<>();
    }

    public void setLoader(JavaPluginLoader loader) {
        this.loader = loader;
    }

    public BongeURLClassLoader getClassLoader() {
        return ((BongePluginManager) Bukkit.getServer().getPluginManager()).getLoader();
    }

    public boolean match(JavaPlugin plugin) {
        //HACK FOR PLUGINBASE
        return this.uuid.equals(plugin.uuid);
    }
}