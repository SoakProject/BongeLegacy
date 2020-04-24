package org.bonge.bukkit.r1_13.server.plugin.loader;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginLoader;

import java.util.Optional;

public interface IBongePluginLoader extends PluginLoader {

    Optional<Plugin> getPlugin();
    Optional<Class<Plugin>> getPluginClass();
}
