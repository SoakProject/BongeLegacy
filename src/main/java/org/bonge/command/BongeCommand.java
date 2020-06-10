package org.bonge.command;

import org.bonge.bukkit.r1_13.server.BongeServer;
import org.bonge.bukkit.r1_13.server.plugin.BongePluginManager;
import org.bonge.bukkitloader.BukkitDownloader;
import org.bonge.bukkitloader.BukkitVersion;
import org.bonge.launch.BongeLaunch;
import org.bukkit.plugin.java.JavaPluginLoader;
import org.bonge.command.argument.PluginArgument;
import org.bonge.util.ArrayUtils;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.boss.BossBarColors;
import org.spongepowered.api.boss.BossBarOverlay;
import org.spongepowered.api.boss.BossBarOverlays;
import org.spongepowered.api.boss.ServerBossBar;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.action.ClickAction;
import org.spongepowered.api.text.action.TextActions;
import org.spongepowered.api.text.format.TextColors;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.stream.Stream;

public class BongeCommand {

    private static final Text PLUGIN = Text.of("plugin");
    private static final Text FLAG = Text.of("flag");

    private static class DumpCMD implements CommandExecutor {

        @NotNull
        @Override
        public CommandResult execute(@NotNull CommandSource src, @NotNull CommandContext args) {
            Optional<String> opFlag = args.getOne(FLAG);
            if(!opFlag.isPresent()) {
                ((BongeServer) Bukkit.getServer()).getPluginManager().getBongePlugins().stream().filter(l -> l instanceof JavaPluginLoader).forEach(l -> {
                    src.sendMessage(Text.of(((JavaPluginLoader) l).getYaml().getFullName()));
                    ((JavaPluginLoader) l).getClasses().forEach(c -> src.sendMessage(Text.of(" - " + c.getName())));
                });
                return CommandResult.success();
            }
            String flag = opFlag.get();
            if(flag.equalsIgnoreCase("listeners")){
                ((BongePluginManager)Bukkit.getServer().getPluginManager()).getEventData().forEach(e -> {
                    Stream.of(e.getListener().getClass().getDeclaredMethods())
                            .filter(m -> m.isAnnotationPresent(EventHandler.class))
                            .filter(m -> m.getParameterCount() == 1)
                            .filter(m -> m.getParameters()[0].getType().isAssignableFrom(e.getEvent()))
                            .forEach(m -> {
                        src.sendMessage(Text.of("- " + m.getName() + " | " + m.getParameters()[0].getType().getSimpleName() + " | " + e.getHolder().getName() + " | " + m.getDeclaringClass().getSimpleName()));
                    });
                });
                return CommandResult.success();
            }
            if(flag.equalsIgnoreCase("fired-events")){
                List<Class<?>> list = ((BongePluginManager)Bukkit.getServer().getPluginManager()).getFiredEvents();
                for(int A = list.size() - 5; A < list.size(); A++){
                    src.sendMessage(Text.of("- " + list.get(A).getSimpleName()));
                }
                return CommandResult.success();
            }
            return CommandResult.empty();
        }
    }

    static class ShowCMD implements CommandExecutor {

        @NotNull
        @Override
        public CommandResult execute(@NotNull CommandSource src, CommandContext args) {
            Plugin plugin = args.<Plugin>getOne(PLUGIN).get();
            ((BongeServer) Bukkit.getServer()).getCommandManager().getCommands(plugin).forEach(c -> src.sendMessage(Text.join(Text.builder("Command: " + c.getName() + " ").color(TextColors.AQUA).build(), Text.builder(c.getDescription()).build())));
            return CommandResult.success();
        }
    }

    private static class InfoCMD implements CommandExecutor {

        @NotNull
        @Override
        public CommandResult execute(CommandSource src, @NotNull CommandContext args) {
            if(BongeLaunch.isBukkitAPILoaded()) {
                src.sendMessage(Text.join(Text.builder("version: ").build(), Text.builder(Bukkit.getServer().getVersion()).build()));
            }else{
                src.sendMessage(Text.builder("warning: The Bukkit API has not been loaded, make sure it is downloaded and then restart the server to use Bukkit plugins").build());
                src.sendMessage(Text.join(Text.builder("version: ").build(), Text.builder(BongeLaunch.PLUGIN_VERSION + "(" + BongeLaunch.IMPLEMENTATION_VERSION + ")").build()));
            }
            return CommandResult.success();
        }
    }

    private static class DownloadCMD implements CommandExecutor {

        @Override
        public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
            Sponge.getScheduler().createTaskBuilder().async().delayTicks(1).execute(() -> {
                ClickAction.OpenUrl action = null;
                try {
                    Optional<URL> opUrl = BukkitDownloader.DEFAULT_VERSION.getDownload();
                    if (opUrl.isPresent()) {
                        action = TextActions.openUrl(opUrl.get());
                    } else {
                        src.sendMessage(Text.of("Unknown download URL. This is a issue with Bonge"));
                        return;
                    }
                } catch (MalformedURLException e) {
                    src.sendMessage(Text.of(e.getMessage()));
                    e.printStackTrace();
                    return;
                }
                BukkitDownloader downloader = new BukkitDownloader(BukkitDownloader.DEFAULT_VERSION, BukkitDownloader.DEFAULT_OUTPUT, false);
                src.sendMessage(Text.join(Text.builder("Downloading BukkitAPI from ").color(TextColors.AQUA).build(), Text.builder("Maven (Nexus)").onClick(action).build(), Text.of(" to " + downloader.getOutput().getPath())));
                final ServerBossBar sbb = ServerBossBar.builder().name(Text.of("download")).overlay(BossBarOverlays.PROGRESS).color(BossBarColors.RED).percent(0).build();
                if(src instanceof Player){
                    sbb.addPlayer((Player)src);
                }
                try {
                    downloader.download((c, t) -> {
                        if((src instanceof Player) && sbb != null){
                            sbb.setPercent(c / t);
                            return;
                        }
                        src.sendMessage(Text.builder("Download Percent: " + ((c * 100)/t)).color(TextColors.AQUA).build());
                    });
                    src.sendMessage(Text.of("Complete"));
                } catch (IOException e) {
                    src.sendMessage(Text.of(e.getMessage()));
                    e.printStackTrace();
                    return;
                }
            }).submit(BongeLaunch.getContainer());
            return CommandResult.success();
        }
    }

    private static class PluginsCMD implements CommandExecutor {

        @NotNull
        @Override
        public CommandResult execute(@NotNull CommandSource src, CommandContext args) {
            Optional<Plugin> opPlugin = args.getOne(PLUGIN);
            if (opPlugin.isPresent()) {
                String api = opPlugin.get().getDescription().getAPIVersion();
                String website = opPlugin.get().getDescription().getWebsite();
                Map<String, Map<String, Object>> cmds = opPlugin.get().getDescription().getCommands();
                src.sendMessage(Text.builder("Plugin: " + opPlugin.get().getDescription().getFullName()).build());
                src.sendMessage(Text.builder("Version: " + opPlugin.get().getDescription().getVersion()).build());
                src.sendMessage(Text.builder("Description: " + opPlugin.get().getDescription().getDescription()).build());
                src.sendMessage(Text.builder("Bukkit API version: " + (api == null ? "Legacy" : api)).build());
                src.sendMessage(Text.builder("Website: " + (website == null ? "Unknown" : website)).build());
                if (cmds == null) {
                    src.sendMessage(Text.builder("Commands: None").build());
                } else {
                    src.sendMessage(Text.builder("Commands: " + ArrayUtils.toString(", ", n -> n, cmds.keySet())).build());
                }
                return CommandResult.success();
            }

            List<Plugin> plugins = Arrays.asList(Bukkit.getPluginManager().getPlugins());
            plugins.sort(Comparator.comparing(Plugin::getName, Comparator.naturalOrder()));
            Text text = Text.of("Plugins(" + plugins.size() + "): ");
            for (int A = 0; A < plugins.size(); A++) {
                Plugin plugin = plugins.get(A);
                text = Text.join(text, Text.builder(plugin.getName() + (A == (plugins.size() - 1) ? "" : ", ")).color(plugin.isEnabled() ? TextColors.GREEN : TextColors.RED).build());
            }
            src.sendMessage(text);
            return CommandResult.success();
        }
    }

    public static CommandSpec build() {
        CommandSpec pluginsCMD = CommandSpec.builder()
                .description(Text.builder("List all bukkit plugins running").build())
                .executor(new PluginsCMD())
                .arguments(GenericArguments.optional(new PluginArgument(PLUGIN)))
                .permission(Permissions.BONGE_PLUGINS)
                .build();
        CommandSpec infoCMD = CommandSpec.builder()
                .description(Text.builder("Info about bonge").build())
                .executor(new InfoCMD())
                .build();
        CommandSpec commandShowCMD = CommandSpec.builder()
                .description(Text.builder("Bukkit commands").build())
                .arguments(new PluginArgument(PLUGIN))
                .executor(new ShowCMD())
                .build();
        CommandSpec dumpCMD = CommandSpec.builder()
                .description(Text.builder("Display all loaded classes").build())
                .executor(new DumpCMD())
                .arguments(GenericArguments.optional(GenericArguments.withSuggestions(GenericArguments.string(FLAG), Arrays.asList("listeners", "fired-events"))))
                .build();
        CommandSpec downloadCMD = CommandSpec.builder()
                .description(Text.builder("Download BukkitAPI").build())
                .executor(new DownloadCMD())
                .build();
        Map<List<String>, CommandSpec> child = new HashMap<>();
        if(BongeLaunch.isBukkitAPILoaded()){
            child.put(Arrays.asList("plugins"), pluginsCMD);
            child.put(Arrays.asList("info"), infoCMD);
            child.put(Arrays.asList("show"), commandShowCMD);
            child.put(Arrays.asList("dump"), dumpCMD);
        }
        //child.put(Arrays.asList("download"), downloadCMD);
        return CommandSpec.builder()
                .children(child)
                .build();
    }
}
