package org.bonge.command;

import org.bonge.bukkit.r1_14.server.BongeServer;
import org.bonge.bukkit.r1_14.server.plugin.BongePluginManager;
import org.bonge.command.argument.PluginArgument;
import org.bonge.launch.BongeLaunch;
import org.bonge.util.ArrayUtils;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPluginLoader;
import org.spongepowered.api.command.Command;
import org.spongepowered.api.command.CommandExecutor;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.exception.CommandException;
import org.spongepowered.api.command.parameter.CommandContext;
import org.spongepowered.api.command.parameter.Parameter;
import org.spongepowered.api.service.permission.Subject;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

import java.util.*;
import java.util.stream.Stream;

public class BongeCommand {

    private static final Parameter.Value<Plugin> PLUGIN = Parameter.builder(Plugin.class).parser(new PluginArgument()).build();
    private static final Parameter.Value<String> FLAG = Parameter.string().setKey("flag").optional().build();

    private static class DumpCMD implements CommandExecutor {

        @Override
        public CommandResult execute(CommandContext args) {
            Optional<String> opFlag = args.getOne(FLAG);
            Subject src = args.getSubject();
            if(!opFlag.isPresent()) {
                ((BongeServer) Bukkit.getServer()).getPluginManager().getBongePlugins().stream().filter(l -> l instanceof JavaPluginLoader).forEach(l -> {
                    args.sendMessage(Text.of(((JavaPluginLoader) l).getYaml().getFullName()));
                    ((JavaPluginLoader) l).getClasses().forEach(c -> args.sendMessage(Text.of(" - " + c.getName())));
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
                                args.sendMessage(Text.of("- " + m.getName() + " | " + m.getParameters()[0].getType().getSimpleName() + " | " + e.getHolder().getName() + " | " + m.getDeclaringClass().getSimpleName()));
                            });
                });
                return CommandResult.success();
            }
            if(flag.equalsIgnoreCase("fired-events")){
                List<Class<?>> list = ((BongePluginManager)Bukkit.getServer().getPluginManager()).getFiredEvents();
                for(int A = list.size() - 5; A < list.size(); A++){
                    args.sendMessage(Text.of("- " + list.get(A).getSimpleName()));
                }
                return CommandResult.success();
            }
            return CommandResult.empty();
        }
    }

    static class ShowCMD implements CommandExecutor {

        @Override
        public CommandResult execute(CommandContext args){
            Plugin plugin = args.<Plugin>getOne(PLUGIN).get();
            ((BongeServer) Bukkit.getServer()).getCommandManager().getCommands(plugin).forEach(c -> args.sendMessage(Text.join(Text.builder("Command: " + c.getName() + " ").color(TextColors.AQUA).build(), Text.builder(c.getDescription()).build())));
            return CommandResult.success();
        }
    }

    private static class InfoCMD implements CommandExecutor {

        @Override
        public CommandResult execute(CommandContext args) throws CommandException {
            if(BongeLaunch.isBukkitAPILoaded()) {
                args.sendMessage(Text.join(Text.builder("version: ").build(), Text.builder(Bukkit.getServer().getVersion()).build()));
            }else{
                args.sendMessage(Text.builder("warning: The Bukkit API has not been loaded, make sure it is downloaded and then restart the server to use Bukkit plugins").build());
                args.sendMessage(Text.join(Text.builder("version: ").build(), Text.builder(BongeLaunch.PLUGIN_VERSION + "(" + BongeLaunch.IMPLEMENTATION_VERSION + ")").build()));
            }
            return CommandResult.success();
        }
    }

    private static class PluginsCMD implements CommandExecutor {

        @Override
        public CommandResult execute(CommandContext args) throws CommandException {
            Optional<Plugin> opPlugin = args.getOne(PLUGIN);
            if (opPlugin.isPresent()) {
                String api = opPlugin.get().getDescription().getAPIVersion();
                String website = opPlugin.get().getDescription().getWebsite();
                Map<String, Map<String, Object>> cmds = opPlugin.get().getDescription().getCommands();
                args.sendMessage(Text.builder("Plugin: " + opPlugin.get().getDescription().getFullName()).build());
                args.sendMessage(Text.builder("Version: " + opPlugin.get().getDescription().getVersion()).build());
                args.sendMessage(Text.builder("Description: " + opPlugin.get().getDescription().getDescription()).build());
                args.sendMessage(Text.builder("Bukkit API version: " + (api == null ? "Legacy" : api)).build());
                args.sendMessage(Text.builder("Website: " + (website == null ? "Unknown" : website)).build());
                if (cmds == null) {
                    args.sendMessage(Text.builder("Commands: None").build());
                } else {
                    args.sendMessage(Text.builder("Commands: " + ArrayUtils.toString(", ", n -> n, cmds.keySet())).build());
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
            args.sendMessage(text);
            return CommandResult.success();
        }
    }

    public static Command.Parameterized build() {
        Command.Parameterized pluginsCMD = Command.builder()
                .setShortDescription(Text.builder("List all Bukkit plugins running").build())
                .setExecutor(new PluginsCMD())
                .parameter(PLUGIN)
                .setPermission(Permissions.BONGE_PLUGINS)
                .build();
        Command.Parameterized infoCMD = Command.builder()
                .setShortDescription(Text.builder("Info about Bonge").build())
                .setExecutor(new InfoCMD())
                .build();

        Command.Parameterized commandShowCMD = Command.builder()
                .setShortDescription(Text.builder("Bukkit commands").build())
                .setExecutor(new ShowCMD())
                .parameter(PLUGIN)
                .build();

        Command.Parameterized dumpCMD = Command.builder()
                .setShortDescription(Text.builder("Display all loaded classes").build())
                .setExecutor(new DumpCMD())
                .parameter(FLAG)
                .build();

        return Command.builder()
                .child(pluginsCMD, "plugins")
                .child(infoCMD, "info")
                .child(commandShowCMD, "show")
                .child(dumpCMD, "dump")
                .build();
    }
}
