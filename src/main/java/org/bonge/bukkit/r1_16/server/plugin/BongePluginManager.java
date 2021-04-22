package org.bonge.bukkit.r1_16.server.plugin;

import org.array.utils.ArrayUtils;
import org.bonge.Bonge;
import org.bonge.bukkit.r1_16.command.BongeCommandManager;
import org.bonge.bukkit.r1_16.server.BongeServer;
import org.bonge.bukkit.r1_16.server.plugin.event.EventData;
import org.bonge.bukkit.r1_16.server.plugin.loader.BongeURLClassLoader;
import org.bonge.bukkit.r1_16.server.plugin.loader.IBongePluginLoader;
import org.bonge.launch.BongeLaunch;
import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.event.*;
import org.bukkit.permissions.Permissible;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.*;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.JavaPluginLoader;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.service.ServiceRegistration;
import org.spongepowered.api.service.permission.PermissionService;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BongePluginManager implements org.bukkit.plugin.PluginManager {

    private final Set<IBongePluginLoader> plugins = new HashSet<>();
    private final List<Class<?>> eventClasses = new ArrayList<>();
    private final Set<EventData<?>> datas = new HashSet<>();
    private final Set<Permission> permissions = new HashSet<>();
    private BongeURLClassLoader loader;

    public Set<IBongePluginLoader> getBongePlugins() {
        return this.plugins;
    }

    public BongeURLClassLoader getLoader() {
        return this.loader;
    }

    public List<Class<?>> getFiredEvents() {
        return this.eventClasses;
    }

    public Optional<IBongePluginLoader> getPluginLoader(Plugin plugin) {
        return this.plugins.stream().filter(pl -> {
            Optional<Plugin> opPlugin = pl.getPlugin();
            if (opPlugin.isPresent()) {
                if (opPlugin.get() instanceof JavaPlugin && plugin instanceof JavaPlugin) {
                    return ((JavaPlugin) opPlugin.get()).match((JavaPlugin) plugin);
                } else {
                    return opPlugin.get().equals(plugin);
                }
            }
            return pl.getPluginClass().get().isInstance(plugin);
        }).findAny();
    }

    public Set<PluginCommand> bootCommands(JavaPlugin plugin) {
        Map<String, Map<String, Object>> commands = plugin.getDescription().getCommands();
        return commands.entrySet().stream().map((entry) -> {
            PluginCommand command = new PluginCommand(entry.getKey(), plugin);
            String description = (String) entry.getValue().get("description");
            if (description != null) {
                command.setDescription(description);
            }
            String usage = (String) entry.getValue().get("usage");
            if (usage != null) {
                command.setUsage(usage);
            }
            List<String> aliases = (List<String>) entry.getValue().get("aliases");
            if (aliases != null) {
                command.setAliases(aliases);
            }
            return command;
        }).collect(Collectors.toSet());
    }

    public void bootPlugins() {
        List<IBongePluginLoader> plugins = new ArrayList<>(this.plugins);
        for (IBongePluginLoader loader : plugins) {
            if (!loader.getPlugin().get().getDescription().getDepend().stream().allMatch(n -> plugins.stream().anyMatch(p -> p.getPlugin().get().getName().equals(n)))) {
                BongePluginManager.this.disablePlugin(loader.getPlugin().get());
            }
        }
        plugins.sort((iBongePluginLoader, t1) -> {
            PluginDescriptionFile plugin1 = iBongePluginLoader.getPlugin().get().getDescription();
            PluginDescriptionFile plugin2 = t1.getPlugin().get().getDescription();
            if (plugin1.getDepend().contains(plugin2.getName())) {
                return 1;
            }
            if (plugin1.getSoftDepend().contains(plugin2.getName())) {
                return 1;
            }
            if (plugin1.getLoadBefore().contains(plugin2.getName())) {
                return -1;
            }
            if (plugin2.getDepend().contains(plugin1.getName())) {
                return -1;
            }
            if (plugin2.getSoftDepend().contains(plugin1.getName())) {
                return -1;
            }
            if (plugin2.getLoadBefore().contains(plugin1.getName())) {
                return 1;
            }
            return 0;
        });
        for (IBongePluginLoader loader : plugins) {
            if (loader.getPlugin().get().isEnabled()) {
                System.out.println("Order: " + loader.getPlugin().get().getName());
            }
        }
        plugins.forEach(p -> {
            BongeCommandManager cmdManager = ((BongeServer) Bukkit.getServer()).getCommandManager();
            JavaPlugin plugin = (JavaPlugin) p.getPlugin().get();
            Set<PluginCommand> commands = bootCommands(plugin);
            commands.forEach(cmdManager::register);
            plugin.getDescription().getPermissions().forEach(this::addPermission);
            String api = plugin.getDescription().getAPIVersion();
            if (api == null) {
                BongeLaunch.getLogger().warn("The plugin '" + plugin.getName() + "' was built for Legacy Bukkit API, this is not (and will never be) supported by Bonge. While this plugin may still work, if there is an issue caused by a legacy call then the plugin will never work.");
            }
            try {
                plugin.onEnable();
                if (p instanceof JavaPluginLoader) {
                    ((JavaPluginLoader) p).setEnabled(true);
                }
            } catch (Throwable e) {
                Bonge.createCrashFile(plugin, "OnEnable", e);
                e.printStackTrace();
            }
        });
    }

    public Set<EventData<?>> getEventData() {
        return this.datas;
    }

    @Override
    public void registerInterface(Class<? extends PluginLoader> aClass) throws IllegalArgumentException {
    }

    @Override
    public Plugin getPlugin(@NotNull String s) {
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
        for (int A = 0; A < this.plugins.size(); A++) {
            plugins[A] = ((JavaPluginLoader) pluginA[A]).getPlugin().get();
        }
        return plugins;
    }

    @Override
    public boolean isPluginEnabled(@NotNull String s) {
        Plugin plugin = getPlugin(s);
        if (plugin == null) {
            System.err.println("Unknown plugin of '" + s + "'. A plugin checked if was enabled");
            return false;
        }
        return isPluginEnabled(plugin);
    }

    @Override
    public boolean isPluginEnabled(Plugin plugin) {
        return plugin.isEnabled();
    }

    public Plugin initPlugin(@NotNull File file) {
        JavaPluginLoader loader = new JavaPluginLoader(file, this.loader);
        try {
            this.plugins.add(loader);
            JavaPlugin plugin = loader.getOrLoadPlugin();
            plugin.setLoader(loader);
            return plugin;
        } catch (InvalidDescriptionException e) {
            this.plugins.remove(loader);
            System.err.println(e.getMessage());
            throw new IllegalArgumentException(e);
        } catch (Throwable e) {
            this.plugins.remove(loader);
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Plugin loadPlugin(@NotNull File file) {
        Optional<JavaPluginLoader> opLoader = this.plugins
                .parallelStream()
                .filter(pluginLoader -> pluginLoader instanceof JavaPluginLoader)
                .map(pluginLoader -> (JavaPluginLoader) pluginLoader)
                .filter(pluginLoader -> pluginLoader.getFile().equals(file))
                .findFirst();
        if (!opLoader.isPresent()) {
            throw new IllegalStateException("Plugin has not been init");
        }
        Plugin plugin = opLoader.get().getPlugin().orElseThrow(() -> new IllegalStateException("Plugin has not loaded"));
        plugin.onLoad();
        return plugin;
    }

    public Plugin[] initPlugins(File file) {
        File[] files = file.listFiles((file1, s) -> s.endsWith(".jar"));
        if (files == null) {
            return new Plugin[0];
        }
        List<File> list = new ArrayList<>();
        List<File> pluginFiles = Arrays.asList(files);
        list.addAll(pluginFiles);
        this.loader = new BongeURLClassLoader(ArrayUtils.convert(URL.class, f -> {
            try {
                return f.toURI().toURL();
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return null;
            }
        }, files));

        try {
            Class.forName("org.apache.commons.lang.Validate");
        } catch (ClassNotFoundException e) {
            BongeLaunch.getLogger().error("Your launcher requires patching to allow Apache Commons 2.6 to run. Run Bonge as a program to launch the patcher.");
            return new Plugin[0];
        }

        Plugin[] plugins = new Plugin[files.length];
        int pluginId = 0;
        for (int A = 0; A < files.length; A++) {
            try {
                File pluginFile = files[A];
                plugins[pluginId] = initPlugin(pluginFile);
                pluginId++;
            } catch (Throwable e) {
                e.printStackTrace();
                Bonge.createCrashFile(null, null, e);
            }
        }
        return plugins;
    }

    @Override
    public Plugin[] loadPlugins(File file) {
        File[] files = file.listFiles((file1, s) -> s.endsWith(".jar"));
        if (files == null) {
            return new Plugin[0];
        }
        Plugin[] plugins = new Plugin[files.length];
        int pluginId = 0;
        for (File pluginFile : files) {
            try {
                plugins[pluginId] = loadPlugin(pluginFile);
                pluginId++;
            } catch (Throwable e) {
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
        this.eventClasses.add(event.getClass());
        List<EventData<?>> listeners = this.datas.stream().filter(e -> e.isValid(event)).sorted(Comparator.comparingInt(eventData -> -eventData.getPriority().getSlot())).collect(Collectors.toList());
        listeners.forEach(new Consumer<EventData<?>>() {
            @Override
            public void accept(EventData<?> eventData) {
                try {
                    this.run(eventData);
                } catch (EventException e) {
                    Bonge.createCrashFile(eventData.getHolder(), "listener/" + event.getClass().getSimpleName(), e);
                    if (e.getCause() instanceof InvocationTargetException) {
                        InvocationTargetException c = (InvocationTargetException) e.getCause();
                        c.getTargetException().printStackTrace();
                    } else {
                        e.printStackTrace();
                    }
                }
            }

            private <T extends Event> void run(EventData<T> eventData) throws EventException {
                eventData.run((T) event);
            }
        });
    }

    @Override
    public void registerEvents(Listener listener, @NotNull Plugin plugin) {
        Stream.of(listener.getClass().getMethods())
                .filter(m -> Stream.of(m.getAnnotations()).anyMatch(a -> a.annotationType().isAssignableFrom(EventHandler.class)))
                .filter(m -> m.getParameterCount() == 1)
                .forEach(m -> {
                    EventHandler handler = (EventHandler) Stream.of(m.getAnnotations()).filter(a -> a.annotationType().isAssignableFrom(EventHandler.class)).findAny().get();
                    Class<? extends Event> event = (Class<? extends Event>) m.getParameters()[0].getType();
                    EventData<?> data = new EventData<>(event, handler.priority(), listener, plugin);
                    this.datas.add(data);
                });
    }

    @Override
    public void registerEvent(@NotNull Class<? extends Event> aClass, @NotNull Listener listener, @NotNull EventPriority eventPriority, @NotNull EventExecutor eventExecutor, @NotNull Plugin plugin) {
        this.datas.add(new EventData<>(aClass, eventPriority, listener, eventExecutor, plugin));
    }

    @Override
    public void registerEvent(@NotNull Class<? extends Event> aClass, @NotNull Listener listener, @NotNull EventPriority eventPriority, @NotNull EventExecutor eventExecutor, @NotNull Plugin plugin, boolean b) {
        this.registerEvent(aClass, listener, eventPriority, eventExecutor, plugin);
    }

    @Override
    public void enablePlugin(@NotNull Plugin plugin) {
        if (!(plugin instanceof JavaPlugin)) {
            return;
        }
        JavaPluginLoader loader = ((JavaPluginLoader) this.getPluginLoader(plugin).get());
        if (loader.isEnabled()) {
            return;
        }
        loader.setEnabled(true);
        plugin.onEnable();
    }

    @Override
    public void disablePlugin(@NotNull Plugin plugin) {
        if (!(plugin instanceof JavaPlugin)) {
            return;
        }
        JavaPluginLoader loader = ((JavaPluginLoader) this.getPluginLoader(plugin).get());
        if (!loader.isEnabled()) {
            return;
        }
        loader.setEnabled(false);
        plugin.onDisable();
    }

    @Override
    public Permission getPermission(@NotNull String s) {
        return null;
    }

    @Override
    public void addPermission(@NotNull Permission permission) {
        Optional<ServiceRegistration<PermissionService>> opPermissionsService = Sponge.serviceProvider().registration(PermissionService.class);
        if (!opPermissionsService.isPresent()) {
            return;
        }
        PermissionService permissionProvider = opPermissionsService.get().service();
        if (this.permissions.add(permission)) {
            permissionProvider
                    .newDescriptionBuilder(BongeLaunch.getContainer())
                    .id(permission.getName())
                    .description(Bonge.getInstance().convertText(permission.getDescription()))
                    .register();
        }

    }

    @Override
    public void removePermission(@NotNull Permission permission) {

    }

    @Override
    public void removePermission(String s) {

    }

    @Override
    public @NotNull Set<Permission> getDefaultPermissions(boolean b) {
        return null;
    }

    @Override
    public void recalculatePermissionDefaults(@NotNull Permission permission) {

    }

    @Override
    public void subscribeToPermission(@NotNull String s, @NotNull Permissible permissible) {

    }

    @Override
    public void unsubscribeFromPermission(@NotNull String s, @NotNull Permissible permissible) {

    }

    @Override
    public @NotNull Set<Permissible> getPermissionSubscriptions(String s) {
        return Bukkit.getServer().getOnlinePlayers().stream().filter(p -> p.hasPermission(s)).collect(Collectors.toSet());
    }

    @Override
    public void subscribeToDefaultPerms(boolean b, @NotNull Permissible permissible) {

    }

    @Override
    public void unsubscribeFromDefaultPerms(boolean b, @NotNull Permissible permissible) {

    }

    @Override
    public @NotNull Set<Permissible> getDefaultPermSubscriptions(boolean b) {
        return null;
    }

    @Override
    public @NotNull Set<Permission> getPermissions() {
        return this.permissions;
    }

    @Override
    public boolean useTimings() {
        return false;
    }
}
