package org.bonge.bukkit.server.plugin.loader;

import java.net.URL;
import java.net.URLClassLoader;

public class BongeURLClassLoader extends URLClassLoader {

    public BongeURLClassLoader(URL[] urls) {
        super(urls, BongePluginLoader.class.getClassLoader());
    }
}
