package org.bonge.bukkit.r1_13.server.plugin.loader;

import java.net.URL;
import java.net.URLClassLoader;

public class BongeURLClassLoader extends URLClassLoader {

    public BongeURLClassLoader(URL[] urls) {
        super(urls, BongePluginLoader.class.getClassLoader());
    }
}
