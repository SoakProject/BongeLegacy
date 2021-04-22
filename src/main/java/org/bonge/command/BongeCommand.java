package org.bonge.command;

import net.kyori.adventure.identity.Identity;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.array.utils.ArrayUtils;
import org.bonge.bukkit.r1_16.server.BongeServer;
import org.bonge.bukkit.r1_16.server.plugin.BongePluginManager;
import org.bonge.command.argument.PluginArgument;
import org.bonge.launch.BongeLaunch;
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

import java.util.*;
import java.util.stream.Stream;

public class BongeCommand {

    private static final Parameter.Value<Plugin> PLUGIN_OPTIONAL = Parameter.builder(Plugin.class).key(Parameter.key("plugin", Plugin.class)).optional().addParser(new PluginArgument()).build();
    private static final Parameter.Value<Plugin> PLUGIN = Parameter.builder(Plugin.class).key(Parameter.key("plugin", Plugin.class)).addParser(new PluginArgument()).build();

    private static final Parameter.Value<String> FLAG = Parameter.string().key("flag").optional().build();

    private static class DumpCMD implements CommandExecutor {

        @Override
        public CommandResult execute(CommandContext args) {
            Optional<String> opFlag = args.one(FLAG);
            Subject src = args.subject();
            if (!opFlag.isPresent()) {
                ((BongeServer) Bukkit.getServer()).getPluginManager().getBongePlugins().stream().filter(l -> l instanceof JavaPluginLoader).forEach(l -> {
                    args.sendMessage(Identity.nil(), Component.text((((JavaPluginLoader) l).getYaml().getFullName())).color(NamedTextColor.WHITE));
                    ((JavaPluginLoader) l).getClasses().forEach(c -> args.sendMessage(Identity.nil(), Component.text(" - " + c.getName())));
                });
                return CommandResult.success();
            }
            String flag = opFlag.get();
            if (flag.equalsIgnoreCase("listeners")) {
                ((BongePluginManager) Bukkit.getServer().getPluginManager()).getEventData().forEach(e -> Stream.of(e.getListener().getClass().getDeclaredMethods())
                        .filter(m -> m.isAnnotationPresent(EventHandler.class))
                        .filter(m -> m.getParameterCount() == 1)
                        .filter(m -> m.getParameters()[0].getType().isAssignableFrom(e.getEvent()))
                        .forEach(m -> {
                            args.sendMessage(Identity.nil(), Component.text("- " + m.getName() + " | " + m.getParameters()[0].getType().getSimpleName() + " | " + e.getHolder().getName() + " | " + m.getDeclaringClass().getSimpleName()).color(NamedTextColor.WHITE));
                        }));
                return CommandResult.success();
            }
            if (flag.equalsIgnoreCase("fired-events")) {
                List<Class<?>> list = ((BongePluginManager) Bukkit.getServer().getPluginManager()).getFiredEvents();
                for (int A = list.size() - 5; A < list.size(); A++) {
                    args.sendMessage(Identity.nil(), Component.text("- " + list.get(A).getSimpleName()).color(NamedTextColor.WHITE));
                }
                return CommandResult.success();
            }
            return CommandResult.empty();
        }
    }

    static class ShowCMD implements CommandExecutor {

        @Override
        public CommandResult execute(CommandContext args) {
            Optional<Plugin> opPlugin = args.one(PLUGIN);
            if (!opPlugin.isPresent()) {
                return CommandResult.error(Component.text("Unknown plugin"));
            }
            ((BongeServer) Bukkit.getServer())
                    .getCommandManager()
                    .getCommands(opPlugin.get())
                    .forEach(c -> args.sendMessage(Identity.nil(), Component
                            .join(Component
                                            .text("Command: " + c.getName() + ": ")
                                            .color(TextColor.color(11)),
                                    Component
                                            .text(c.getDescription()).color(NamedTextColor.WHITE))));
            return CommandResult.success();
        }
    }

    private static class InfoCMD implements CommandExecutor {

        @Override
        public CommandResult execute(CommandContext args) throws CommandException {
            if (BongeLaunch.isBukkitAPILoaded()) {
                args.sendMessage(Identity.nil(), Component.join(Component.text("version: "), Component.text(Bukkit.getServer().getVersion()).color(NamedTextColor.WHITE)));
            } else {
                args.sendMessage(Identity.nil(), Component.text("warning: The Bukkit API has not been loaded, make sure it is downloaded and then restart the server to use Bukkit plugins").color(NamedTextColor.WHITE));
                args.sendMessage(Identity.nil(), Component.join(Component.text("version: "), Component.text(BongeLaunch.PLUGIN_VERSION + "(" + BongeLaunch.IMPLEMENTATION_VERSION + ")")).color(NamedTextColor.WHITE));
            }
            return CommandResult.success();
        }
    }

    private static class PluginsCMD implements CommandExecutor {

        @Override
        public CommandResult execute(CommandContext args) throws CommandException {
            Optional<Plugin> opPlugin = args.one(PLUGIN_OPTIONAL);
            if (opPlugin.isPresent()) {
                String api = opPlugin.get().getDescription().getAPIVersion();
                String website = opPlugin.get().getDescription().getWebsite();
                Map<String, Map<String, Object>> cmds = opPlugin.get().getDescription().getCommands();
                args.sendMessage(Identity.nil(), Component.text("Plugin: " + opPlugin.get().getDescription().getFullName()));
                args.sendMessage(Identity.nil(), Component.text("Version: " + opPlugin.get().getDescription().getVersion()));
                args.sendMessage(Identity.nil(), Component.text("Description: " + opPlugin.get().getDescription().getDescription()));
                args.sendMessage(Identity.nil(), Component.text("Bukkit API version: " + (api == null ? "Legacy" : api)));
                args.sendMessage(Identity.nil(), Component.text("Website: " + (website == null ? "Unknown" : website)));
                if (cmds == null) {
                    args.sendMessage(Identity.nil(), Component.text("Commands: None"));
                } else {
                    args.sendMessage(Identity.nil(), Component.text("Commands: " + ArrayUtils.toString(", ", n -> n, cmds.keySet())));
                }
                return CommandResult.success();
            }

            List<Plugin> plugins = Arrays.asList(Bukkit.getPluginManager().getPlugins());
            plugins.sort(Comparator.comparing(Plugin::getName, Comparator.naturalOrder()));
            TextComponent text = Component.text("Plugins(" + plugins.size() + "): ");
            for (int A = 0; A < plugins.size(); A++) {
                Plugin plugin = plugins.get(A);
                text = Component.join(text, Component.text(plugin.getName() + (A == (plugins.size() - 1) ? "" : ", ")).color(plugin.isEnabled() ? TextColor.color(10) : TextColor.color(12)));
            }
            args.sendMessage(Identity.nil(), text);
            return CommandResult.success();
        }
    }

    public static Command.Parameterized build() {
        Command.Parameterized pluginsCMD = Command.builder()
                .shortDescription(Component.text("List all Bukkit plugins running"))
                .executor(new PluginsCMD())
                .addParameter(PLUGIN_OPTIONAL)
                .permission(Permissions.BONGE_PLUGINS)
                .build();

        Command.Parameterized infoCMD = Command.builder()
                .shortDescription(Component.text("Info about Bonge"))
                .executor(new InfoCMD())
                .build();

        Command.Parameterized commandShowCMD = Command.builder()
                .shortDescription(Component.text("Bukkit commands"))
                .executor(new ShowCMD())
                .addParameter(PLUGIN)
                .build();

        /*Command.Parameterized dumpCMD = Command.builder()
                .setShortDescription(Component.text("Display all loaded classes"))
                .setExecutor(new DumpCMD())
                .parameter(FLAG)
                .build();*/


        return Command.builder()
                .addChild(pluginsCMD, "plugins")
                .addChild(infoCMD, "info")
                .addChild(commandShowCMD, "show")
                /*.child(dumpCMD, "dump")*/
                .build();
    }
}
