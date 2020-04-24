package org.bonge.bukkit.r1_13.server.service;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.ServicesManager;

import java.util.*;
import java.util.stream.Collectors;

public class BongeServiceManager implements ServicesManager {

    Set<RegisteredServiceProvider<?>> services = new HashSet<>();

    @Override
    public <T> void register(Class<T> service, T provider, Plugin plugin, ServicePriority priority) {
        this.services.add(new RegisteredServiceProvider<>(service, provider, priority, plugin));
    }

    @Override
    public void unregisterAll(Plugin plugin) {
        this.services = this.services.stream().filter(s -> !s.getPlugin().equals(plugin)).collect(Collectors.toSet());
    }

    @Override
    public void unregister(Class<?> service, Object provider) {
        this.unregister(provider);
    }

    @Override
    public void unregister(Object provider) {
        this.services = this.services.stream().filter(s -> !s.getProvider().equals(provider)).collect(Collectors.toSet());
    }

    @Override
    public <T> T load(Class<T> service) {
        RegisteredServiceProvider<T> service1 = getRegistration(service);
        if(service1 != null){
            return service1.getProvider();
        }
        return null;
    }

    @Override
    public <T> RegisteredServiceProvider<T> getRegistration(Class<T> service) {
        return (RegisteredServiceProvider<T>) this.services.stream().filter(p -> p.getService().isAssignableFrom(service)).findAny().orElse(null);

    }

    @Override
    public List<RegisteredServiceProvider<?>> getRegistrations(Plugin plugin) {
        return this.services.stream().filter(p -> p.getPlugin().equals(plugin)).collect(Collectors.toList());
    }

    @Override
    public <T> Collection<RegisteredServiceProvider<T>> getRegistrations(Class<T> service) {
        List<RegisteredServiceProvider<T>> list = new ArrayList<>();
        this.services.stream().filter(p -> p.getService().isAssignableFrom(service)).forEach(r -> list.add((RegisteredServiceProvider<T>) r));
        return list;
    }

    @Override
    public Collection<Class<?>> getKnownServices() {
        return new ArrayList<>();
    }

    @Override
    public <T> boolean isProvidedFor(Class<T> service) {
        return false;
    }
}
