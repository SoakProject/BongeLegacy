package org.bonge.bukkit.r1_15.server.plugin.loader;

import org.bukkit.plugin.java.JavaPluginLoader;

import java.net.URL;
import java.net.URLClassLoader;

public class BongeURLClassLoader extends URLClassLoader {

    public BongeURLClassLoader(URL... urls) {
        super(urls, JavaPluginLoader.class.getClassLoader());
    }
}
