package org.ve;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

public class BongeVault extends JavaPlugin {

    public void onEnable(){
        Bukkit.getServer().getServicesManager().register(Economy.class, new BongeEco(), this, ServicePriority.Highest);
    }
}
