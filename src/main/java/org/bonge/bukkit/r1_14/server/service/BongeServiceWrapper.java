package org.bonge.bukkit.r1_14.server.service;

import org.bonge.bukkit.r1_14.server.plugin.SpongePlugin;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.ServicePriority;
import org.spongepowered.api.Sponge;

public class BongeServiceWrapper<T> extends RegisteredServiceProvider<T> {

    public BongeServiceWrapper(Class<T> spongeService) {
        super(spongeService, Sponge.getServiceManager().getRegistration(spongeService).get().getProvider(), ServicePriority.Lowest, null);
    }

    @Override
    public Class<T> getService() {
        return super.getService();
    }

    @Override
    public Plugin getPlugin() {
        return new SpongePlugin(Sponge.getServiceManager().getRegistration(this.getService()).get().getPlugin());
    }

    @Override
    public T getProvider() {
        return super.getProvider();
    }

    @Override
    public ServicePriority getPriority() {
        return super.getPriority();
    }

    @Override
    public int compareTo(RegisteredServiceProvider<?> other) {
        return super.compareTo(other);
    }
}
