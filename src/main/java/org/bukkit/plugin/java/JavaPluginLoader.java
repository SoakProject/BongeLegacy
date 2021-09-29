package org.bukkit.plugin.java;

import org.bonge.bukkit.r1_16.server.plugin.loader.BongeURLClassLoader;
import org.bonge.bukkit.r1_16.server.plugin.loader.IBongePluginLoader;
import org.bonge.util.exception.NotImplementedException;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.event.Event;
import org.bukkit.event.Listener;
import org.bukkit.plugin.InvalidDescriptionException;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.RegisteredListener;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.regex.Pattern;

public class JavaPluginLoader implements IBongePluginLoader {

    private final File file;
    private JavaPlugin plugin;
    private Class<Plugin> pluginClass;
    private final Set<Class<?>> classes = new HashSet<>();
    private boolean isEnabled;
    private PluginDescriptionFile descriptionFile;
    private final BongeURLClassLoader loader;
    public Server server = Bukkit.getServer();

    public JavaPluginLoader(File file, BongeURLClassLoader loader) {
        this.file = file;
        this.loader = loader;
    }

    public Set<Class<?>> getClasses() {
        return this.classes;
    }

    public void setClass(String name, Class<?> clazz) {
        Class<?> clazz1 = this.getClassByName(name);
        if (clazz1 != null) {
            this.classes.remove(clazz1);
        }
        this.classes.add(clazz);
    }

    public Class<?> getClassByName(String name) {
        return this.getClasses().stream().filter(c -> c.getSimpleName().equalsIgnoreCase(name)).findAny().orElse(null);
    }

    public boolean isEnabled() {
        return this.isEnabled;
    }

    public void setEnabled(boolean check) {
        this.isEnabled = check;
    }

    public PluginDescriptionFile getYaml() {
        return this.descriptionFile;
    }

    @Override
    public Optional<Plugin> getPlugin() {
        return Optional.ofNullable(this.plugin);
    }

    public Optional<Class<Plugin>> getPluginClass() {
        return Optional.ofNullable(this.pluginClass);
    }

    public JavaPlugin getOrLoadPlugin() throws IOException, InvocationTargetException, InstantiationException, InvalidDescriptionException {
        if (this.plugin == null) {
            this.plugin = loadPlugin();
        }
        return this.plugin;
    }

    public File getFile() {
        return this.file;
    }

    private JavaPlugin loadPlugin() throws IOException, InvocationTargetException, InstantiationException, InvalidDescriptionException {
        JarFile jar = new JarFile(this.file);
        Enumeration<JarEntry> entries = jar.entries();
        while (entries.hasMoreElements()) {
            JarEntry entry = entries.nextElement();
            if (entry.isDirectory()) {
                continue;
            }
            String name = entry.getName();
            if (!name.endsWith(".class")) {
                if (name.endsWith("plugin.yml")) {
                    this.descriptionFile = new PluginDescriptionFile(jar.getInputStream(entry));
                }
                continue;
            }
            name = name.substring(0, name.length() - 6).replace("/", ".");
            try {
                Class<?> class1 = this.loader.loadClass(name);
                this.classes.add(class1);
            } catch (ClassNotFoundException | NoClassDefFoundError ignored) {
                //throw new UnknownClassException(name, this.file, e);
            }
        }
        if (this.descriptionFile == null) {
            throw new InvalidDescriptionException("No plugin.yml file found in file: " + this.file.getPath());
        }
        String mainTag = this.descriptionFile.getMain();
        if (mainTag == null) {
            throw new InvalidDescriptionException("No main tag found in file: " + this.file.getPath());
        }
        Optional<Class<?>> opMain = this.classes.stream().filter(c -> c.getName().equals(mainTag)).findAny();
        if (opMain.isPresent()) {
            try {
                this.pluginClass = (Class<Plugin>) opMain.get();
                this.plugin = (JavaPlugin) opMain.get().getConstructor().newInstance();
                return this.plugin;
            } catch (IllegalAccessException e) {
                throw new IOException("Default constructor must be public in " + opMain.get().getName() + " inside file " + this.file.getPath(), e);
            } catch (NoSuchMethodException e) {
                throw new IOException("Default constructor must be present in " + opMain.get().getName() + " inside file " + this.file.getPath(), e);
            }
        }
        throw new InvalidDescriptionException("Invalid main tag of '" + mainTag + "' in file: " + this.file.getPath());
    }

    @Override
    @Deprecated
    public @NotNull Plugin loadPlugin(@NotNull File file) {
        throw new NotImplementedException("Will never be implemented");
    }

    @Override
    @Deprecated
    public @NotNull PluginDescriptionFile getPluginDescription(@NotNull File file) {
        throw new NotImplementedException("Will never be implemented");
    }

    @Override
    @Deprecated
    public Pattern[] getPluginFileFilters() {
        return new Pattern[0];
    }

    @Override
    @Deprecated
    public @NotNull Map<Class<? extends Event>, Set<RegisteredListener>> createRegisteredListeners(@NotNull Listener listener, @NotNull Plugin plugin) {
        throw new NotImplementedException("Will never be implemented");

    }

    @Override
    @Deprecated
    public void enablePlugin(@NotNull Plugin plugin) {

    }

    @Override
    @Deprecated
    public void disablePlugin(@NotNull Plugin plugin) {

    }
}