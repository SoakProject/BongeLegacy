package org.bonge.command;

import org.bonge.bukkit.server.BongeServer;
import org.bonge.bukkit.server.plugin.loader.BongePluginLoader;
import org.bonge.command.argument.PluginArgument;
import org.bonge.util.ArrayUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

import java.util.*;

public class BongeCommand {

    private static final Text PLUGIN = Text.of("plugin");

    private static class DumpCMD implements CommandExecutor {

        @Override
        public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
            ((BongeServer) Bukkit.getServer()).getPluginManager().getBongePlugins().stream().filter(l -> l instanceof BongePluginLoader).forEach(l -> {
                src.sendMessage(Text.of(((BongePluginLoader) l).getYaml().getFullName()));
                ((BongePluginLoader) l).getClasses().stream().forEach(c -> {
                    src.sendMessage(Text.of(" - " + c.getName()));
                });
            });
            return CommandResult.success();
        }
    }

    private interface CommandsCMD {

        class ShowCMD implements CommandExecutor {

            @Override
            public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
                Plugin plugin = args.<Plugin>getOne(PLUGIN).get();
                ((BongeServer) Bukkit.getServer()).getCommandManager().getCommands(plugin).stream().forEach(c -> {
                    src.sendMessage(Text.join(Text.builder("Command: " + c.getName() + " ").color(TextColors.AQUA).build(), Text.builder(c.getDescription()).build()));
                });
                return CommandResult.success();
            }
        }

    }

    private static class InfoCMD implements CommandExecutor {

        @Override
        public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
            src.sendMessage(Text.join(Text.builder("version: ").build(), Text.builder(Bukkit.getServer().getVersion()).build()));
            return CommandResult.success();
        }
    }

    private static class PluginsCMD implements CommandExecutor {

        @Override
        public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
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
            plugins.sort(Comparator.comparing(c -> c.getName(), Comparator.naturalOrder()));
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
                .build();
        CommandSpec infoCMD = CommandSpec.builder()
                .description(Text.builder("Info about bonge").build())
                .executor(new InfoCMD())
                .build();
        CommandSpec commandShowCMD = CommandSpec.builder()
                .description(Text.builder("Bukkit commands").build())
                .arguments(new PluginArgument(PLUGIN))
                .executor(new CommandsCMD.ShowCMD())
                .build();
        CommandSpec dumpCMD = CommandSpec.builder()
                .description(Text.builder("Display all loaded classes").build())
                .executor(new DumpCMD())
                .build();
        return CommandSpec.builder()
                .child(pluginsCMD, "plugins")
                .child(infoCMD, "info")
                .child(commandShowCMD, "show")
                .child(dumpCMD, "dump")
                .permission("bonge.cmd.plugins")
                .build();
    }
}
