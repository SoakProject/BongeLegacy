package org.bonge.bukkit.server.plugin;

import org.bonge.Bonge;
import org.bonge.bukkit.command.BongeCommandManager;
import org.bonge.bukkit.server.BongeServer;
import org.bonge.bukkit.server.plugin.event.EventData;
import org.bonge.bukkit.server.plugin.loader.BongePluginLoader;
import org.bonge.bukkit.server.plugin.loader.BongeURLClassLoader;
import org.bonge.bukkit.server.plugin.loader.IBongePluginLoader;
import org.bonge.convert.InterfaceConvert;
import org.bonge.launch.BongeLaunch;
import org.bonge.util.ArrayUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.event.*;
import org.bukkit.permissions.Permissible;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.*;
import org.bukkit.plugin.java.JavaPlugin;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.service.permission.PermissionService;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BongePluginManager implements org.bukkit.plugin.PluginManager {

    private Set<IBongePluginLoader> plugins = new HashSet<>();
    private Set<EventData<?>> datas = new HashSet<>();
    private Set<Permission> permissions = new HashSet<>();
    private BongeURLClassLoader loader;

    public Set<IBongePluginLoader> getBongePlugins(){
        return this.plugins;
    }

    public BongeURLClassLoader getLoader(){
        return this.loader;
    }

    public Optional<IBongePluginLoader> getPluginLoader(Plugin plugin){
        System.out.println("PluginLoaders: " + plugins.size());
        return this.plugins.stream().filter(pl -> {
            Optional<Plugin> opPlugin = pl.getPlugin();
            System.out.println("isPlugin: " + opPlugin);
            if(opPlugin.isPresent()) {
                return opPlugin.get().equals(plugin);
            }
            boolean isIns = pl.getPluginClass().get().isInstance(plugin);
            System.out.println("IsInstance: " + isIns + " | " + pl.getPluginClass().get().getSimpleName() + " - " + plugin.getClass().getSimpleName());
            return isIns;
        }).findAny();
    }

    public void bootPlugins(){
        List<IBongePluginLoader> plugins = new ArrayList<>(this.plugins);
        plugins.sort((iBongePluginLoader, t1) -> {
            PluginDescriptionFile plugin1 = iBongePluginLoader.getPlugin().get().getDescription();
            PluginDescriptionFile plugin2 = t1.getPlugin().get().getDescription();
            if(plugin1.getDepend().contains(plugin2.getName())){
                return 1;
            }
            if(plugin1.getSoftDepend().contains(plugin2.getName())){
                return 1;
            }
            if(plugin1.getLoadBefore().contains(plugin2.getName())){
                return -1;
            }
            if(plugin2.getDepend().contains(plugin1.getName())){
                return -1;
            }
            if(plugin2.getSoftDepend().contains(plugin1.getName())){
                return -1;
            }
            if(plugin2.getLoadBefore().contains(plugin1.getName())){
                return 1;
            }
            return 0;
        });
        plugins.forEach(p -> {
            BongeCommandManager cmdManager = ((BongeServer)Bukkit.getServer()).getCommandManager();
            JavaPlugin plugin = (JavaPlugin) p.getPlugin().get();
            Map<String, Map<String, Object>> commands = plugin.getDescription().getCommands();
            if(commands != null){
                commands.forEach((key, value) -> {
                    PluginCommand command = new PluginCommand(key, plugin);
                    String description = (String) value.get("description");
                    if (description != null) {
                        command.setDescription(description);
                    }
                    String usage = (String) value.get("usage");
                    if (usage != null) {
                        command.setUsage(usage);
                    }
                    List<String> aliases = (List<String>) value.get("aliases");
                    if (aliases != null) {
                        command.setAliases(aliases);
                    }
                    cmdManager.register(command);
                });
            }
            plugin.getDescription().getPermissions().forEach(this::addPermission);
            try {
                plugin.onEnable();
                if(p instanceof BongePluginLoader){
                    ((BongePluginLoader) p).setEnabled(true);
                }
            }catch (Throwable e){
                Bonge.createCrashFile(plugin, "OnEnable", e);
                e.printStackTrace();
            }
            cmdManager.registerWithSponge();
        });
    }

    @Override
    public void registerInterface(Class<? extends PluginLoader> aClass) throws IllegalArgumentException {
    }

    @Override
    public Plugin getPlugin(String s) {
        Optional<IBongePluginLoader> opLoader = this.plugins.stream()
                .filter(p -> p.getPlugin().isPresent())
                .filter(p -> {
                    String name = p.getPlugin().get().getName();
                    return name.equals(s);
                })
                .findAny();
        return opLoader.flatMap(IBongePluginLoader::getPlugin).orElse(null);
    }

    @Override
    public Plugin[] getPlugins() {
        Plugin[] plugins = new Plugin[this.plugins.size()];
        Object[] pluginA = this.plugins.toArray();
        for(int A = 0; A < this.plugins.size(); A++){
            plugins[A] = ((BongePluginLoader)pluginA[A]).getPlugin().get();
        }
        return plugins;
    }

    @Override
    public boolean isPluginEnabled(String s) {
        return isPluginEnabled(getPlugin(s));
    }

    @Override
    public boolean isPluginEnabled(Plugin plugin) {
        return plugin.isEnabled();
    }

    @Override
    public Plugin loadPlugin(File file) {
        BongePluginLoader loader = new BongePluginLoader(file, this.loader);
        try {
            this.plugins.add(loader);
            JavaPlugin plugin = loader.getOrLoadPlugin();
            plugin.setLoader(loader);
            plugin.onLoad();
            return plugin;
        } catch (InvalidDescriptionException e){
            this.plugins.remove(loader);
            System.err.println(e.getMessage());
            e.printStackTrace();
        } catch (Throwable e) {
            this.plugins.remove(loader);
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Plugin[] loadPlugins(File file) {
        File[] files = file.listFiles((file1, s) -> s.endsWith(".jar"));
        if (files == null){
            return new Plugin[0];
        }
        this.loader = new BongeURLClassLoader(ArrayUtils.convert(URL.class, f -> {
            try {
                return f.toURI().toURL();
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return null;
            }
        }, files));
        Plugin[] plugins = new Plugin[files.length];
        for (int A = 0; A < files.length; A++){
            try {
                plugins[A] = loadPlugin(files[A]);
            }catch (Throwable e){
                e.printStackTrace();
                Bonge.createCrashFile(null, null, e);
            }
        }
        return plugins;
    }

    @Override
    public void disablePlugins() {
        this.plugins.forEach(p -> disablePlugin(p.getPlugin().get()));
        this.plugins.clear();
    }

    @Override
    public void clearPlugins() {
        this.plugins.clear();
    }

    @Override
    public void callEvent(Event event) throws IllegalStateException {
        List<EventData<?>> listeners = this.datas.stream().filter(e -> e.isValid(event)).sorted(Comparator.comparingInt(eventData -> eventData.getPriority().getSlot())).collect(Collectors.toList());
        listeners.forEach(new Consumer<EventData<?>>() {
            @Override
            public void accept(EventData<?> eventData) {
                try {
                    this.run(eventData);
                } catch (EventException e) {
                    Bonge.createCrashFile(eventData.getHolder(), "listener/" + event.getClass().getSimpleName(), e);
                    e.printStackTrace();
                }
            }

            private <T extends Event> void run(EventData<T> eventData) throws EventException {
                eventData.run((T)event);
            }
        });
    }

    @Override
    public void registerEvents(Listener listener, Plugin plugin) {
        Stream.of(listener.getClass().getMethods())
                .filter(m -> Stream.of(m.getAnnotations()).anyMatch(a -> a.annotationType().isAssignableFrom(EventHandler.class)))
                .filter(m -> m.getParameterCount() == 1)
                .forEach(m -> {
                    EventHandler handler = (EventHandler)Stream.of(m.getAnnotations()).filter(a -> a.annotationType().isAssignableFrom(EventHandler.class)).findAny().get();
                    Class<? extends Event> event = (Class<? extends Event>) m.getParameters()[0].getType();
                    EventData<?> data = new EventData<>(event, handler.priority(), listener, plugin);
                    this.datas.add(data);
                });
    }

    @Override
    public void registerEvent(Class<? extends Event> aClass, Listener listener, EventPriority eventPriority, EventExecutor eventExecutor, Plugin plugin) {
        this.datas.add(new EventData<>(aClass, eventPriority, listener, eventExecutor, plugin));
    }

    @Override
    public void registerEvent(Class<? extends Event> aClass, Listener listener, EventPriority eventPriority, EventExecutor eventExecutor, Plugin plugin, boolean b) {
        this.registerEvent(aClass, listener, eventPriority, eventExecutor, plugin);
    }

    @Override
    public void enablePlugin(Plugin plugin) {
        if(!(plugin instanceof JavaPlugin)){
            return;
        }
        BongePluginLoader loader = ((BongePluginLoader)this.getPluginLoader(plugin).get());
        if (loader.isEnabled()){
            return;
        }
        loader.setEnabled(true);
        plugin.onEnable();
    }

    @Override
    public void disablePlugin(Plugin plugin) {
        if(!(plugin instanceof JavaPlugin)){
            return;
        }
        BongePluginLoader loader = ((BongePluginLoader)this.getPluginLoader(plugin).get());
        if (!loader.isEnabled()){
            return;
        }
        loader.setEnabled(false);
        plugin.onDisable();
    }

    @Override
    public Permission getPermission(String s) {
        return null;
    }

    @Override
    public void addPermission(Permission permission) {
        PermissionService permissionProvider = Sponge.getServiceManager().getRegistration(PermissionService.class).get().getProvider();
        if (this.permissions.add(permission)) {
            permissionProvider
                    .newDescriptionBuilder(BongeLaunch.getInstance())
                    .id(permission.getName())
                    .description(InterfaceConvert.fromString(permission.getDescription()))
                    .register();
        }

    }

    @Override
    public void removePermission(Permission permission) {

    }

    @Override
    public void removePermission(String s) {

    }

    @Override
    public Set<Permission> getDefaultPermissions(boolean b) {
        return null;
    }

    @Override
    public void recalculatePermissionDefaults(Permission permission) {

    }

    @Override
    public void subscribeToPermission(String s, Permissible permissible) {

    }

    @Override
    public void unsubscribeFromPermission(String s, Permissible permissible) {

    }

    @Override
    public Set<Permissible> getPermissionSubscriptions(String s) {
        return Bukkit.getServer().getOnlinePlayers().stream().filter(p -> p.hasPermission(s)).collect(Collectors.toSet());
    }

    @Override
    public void subscribeToDefaultPerms(boolean b, Permissible permissible) {

    }

    @Override
    public void unsubscribeFromDefaultPerms(boolean b, Permissible permissible) {

    }

    @Override
    public Set<Permissible> getDefaultPermSubscriptions(boolean b) {
        return null;
    }

    @Override
    public Set<Permission> getPermissions() {
        return this.permissions;
    }

    @Override
    public boolean useTimings() {
        return false;
    }
}
