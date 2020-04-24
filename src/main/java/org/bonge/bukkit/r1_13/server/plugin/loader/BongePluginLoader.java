package org.bonge.bukkit.r1_13.server.plugin.loader;

import org.bukkit.event.Event;
import org.bukkit.event.Listener;
import org.bukkit.plugin.InvalidDescriptionException;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.RegisteredListener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.regex.Pattern;

public class BongePluginLoader implements IBongePluginLoader {

    private File file;
    private JavaPlugin plugin;
    private Class<Plugin> pluginClass;
    private Set<Class<?>> classes = new HashSet<>();
    private boolean isEnabled;
    private PluginDescriptionFile descriptionFile;
    private BongeURLClassLoader loader;

    public BongePluginLoader(File file, BongeURLClassLoader loader) {
        this.file = file;
        this.loader = loader;
    }

    public Set<Class<?>> getClasses(){
        return this.classes;
    }

    public boolean isEnabled(){
        return this.isEnabled;
    }

    public void setEnabled(boolean check){
        this.isEnabled = check;
    }

    public PluginDescriptionFile getYaml(){
        return this.descriptionFile;
    }

    @Override
    public Optional<Plugin> getPlugin(){
        return Optional.ofNullable(this.plugin);
    }

    public Optional<Class<Plugin>> getPluginClass(){
        return Optional.ofNullable(this.pluginClass);
    }

    public JavaPlugin getOrLoadPlugin() throws IOException, InvocationTargetException, InstantiationException, InvalidDescriptionException {
        if (this.plugin == null){
            loadPlugin();
        }
        return this.plugin;
    }

    public File getFile(){
        return this.file;
    }

    private Plugin loadPlugin() throws IOException, InvocationTargetException, InstantiationException, InvalidDescriptionException {
        JarFile jar = new JarFile(this.file);
        Enumeration<JarEntry> entries = jar.entries();
        while(entries.hasMoreElements()){
            JarEntry entry = entries.nextElement();
            if(entry.isDirectory()){
                continue;
            }
            String name = entry.getName();
            if (!name.endsWith(".class")){
                if (name.endsWith("plugin.yml")){
                    this.descriptionFile = new PluginDescriptionFile(jar.getInputStream(entry));
                }
                continue;
            }
            name = name.substring(0, name.length() - 6).replace("/", ".");
            try {
                Class<?> class1 = this.loader.loadClass(name);
                this.classes.add(class1);
            }catch (ClassNotFoundException | NoClassDefFoundError e){
                //throw new UnknownClassException(name, this.file, e);
            }
        }
        if(this.descriptionFile == null){
            throw new InvalidDescriptionException("No plugin.yml file found in file: " + this.file.getPath());
        }
        String mainTag = this.descriptionFile.getMain();
        if(mainTag == null){
            throw new InvalidDescriptionException("No main tag found in file: " + this.file.getPath());
        }
        Optional<Class<?>> opMain = this.classes.stream().filter(c -> c.getName().equals(mainTag)).findAny();
        if(opMain.isPresent()){
            try {
                this.pluginClass = (Class<Plugin>) opMain.get();
                this.plugin = (JavaPlugin) opMain.get().getConstructor().newInstance();
                return this.plugin;
            }catch (IllegalAccessException e){
                throw new IOException("Default constructor must be public in " + opMain.get().getName() + " inside file " + this.file.getPath(), e);
            }catch (NoSuchMethodException e){
                throw new IOException("Default constructor must be present in " + opMain.get().getName() + " inside file " + this.file.getPath(), e);
            }
        }
        throw new InvalidDescriptionException("Invalid main tag of '" + mainTag + "' in file: " + this.file.getPath());
    }

    @Override
    @Deprecated
    public Plugin loadPlugin(File file) {
        return null;
    }

    @Override
    @Deprecated
    public PluginDescriptionFile getPluginDescription(File file) {
        return null;
    }

    @Override
    @Deprecated
    public Pattern[] getPluginFileFilters() {
        return new Pattern[0];
    }

    @Override
    @Deprecated
    public Map<Class<? extends Event>, Set<RegisteredListener>> createRegisteredListeners(Listener listener, Plugin plugin) {
        return null;
    }

    @Override
    @Deprecated
    public void enablePlugin(Plugin plugin) {

    }

    @Override
    @Deprecated
    public void disablePlugin(Plugin plugin) {

    }
}
