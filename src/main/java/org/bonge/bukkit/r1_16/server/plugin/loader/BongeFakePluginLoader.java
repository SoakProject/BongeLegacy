package org.bonge.bukkit.r1_16.server.plugin.loader;

import org.bukkit.event.Event;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.RegisteredListener;
import org.bukkit.plugin.UnknownDependencyException;

import java.io.File;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Pattern;

public class BongeFakePluginLoader implements IBongePluginLoader {

    private Plugin plugin;

    public BongeFakePluginLoader(Plugin plugin){
        this.plugin = plugin;
    }

    @Deprecated
    @Override
    public Plugin loadPlugin(File file) throws UnknownDependencyException {
        return this.plugin;
    }

    @Override
    public PluginDescriptionFile getPluginDescription(File file) {
        return this.plugin.getDescription();
    }

    @Override
    public Pattern[] getPluginFileFilters() {
        return new Pattern[0];
    }

    @Override
    public Map<Class<? extends Event>, Set<RegisteredListener>> createRegisteredListeners(Listener listener, Plugin plugin) {
        return null;
    }

    @Override
    public void enablePlugin(Plugin plugin) {

    }

    @Override
    public void disablePlugin(Plugin plugin) {

    }

    @Override
    public Optional<Plugin> getPlugin() {
        return Optional.of(this.plugin);
    }

    @Override
    public Optional<Class<Plugin>> getPluginClass() {
        return Optional.of((Class<Plugin>)this.plugin.getClass());
    }


}
