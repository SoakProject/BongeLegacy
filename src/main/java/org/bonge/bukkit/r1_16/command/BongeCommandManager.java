package org.bonge.bukkit.r1_16.command;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import org.bonge.Bonge;
import org.bonge.launch.BongeLaunch;
import org.bukkit.Location;
import org.bukkit.command.*;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.event.lifecycle.RegisterCommandEvent;
import org.spongepowered.api.service.permission.Subject;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class BongeCommandManager implements CommandMap {

    private final Set<CommandState> commands = new HashSet<>();

    public Set<PluginCommand> getCommands() {
        return this.commands
                .stream()
                .filter(c -> c.getCmd() instanceof PluginCommand)
                .map(c -> (PluginCommand) c.getCmd()).collect(Collectors.toSet());
    }

    public Set<PluginCommand> getCommands(Plugin plugin) {
        Set<PluginCommand> commands = new HashSet<>();
        this.commands
                .stream()
                .filter(c -> c.getCmd() instanceof PluginCommand)
                .filter(c -> ((PluginCommand) c.getCmd()).getPlugin().equals(plugin))
                .forEach(c -> commands.add((PluginCommand) c.getCmd()));
        return commands;
    }

    public void registerWithSponge(RegisterCommandEvent<org.spongepowered.api.command.Command.Raw> event) {
        this.commands.forEach(c -> registerWithSponge(event, c));
    }

    public void registerWithSponge(RegisterCommandEvent<org.spongepowered.api.command.Command.Raw> event, CommandState state) {
        @NotNull List<String> list = new ArrayList<>(state.getCmd().getAliases());
        for (int A = list.size() - 1; A >= 0; A--) {
            String alis = list.get(A);
            if (Sponge.server().commandManager().commandMapping(alis).isPresent()) {
                list.remove(A);
            }
        }
        list.add(0, state.getLabel());
        //list.add(1, state.getPluginTag() + ":" + state.getLabel());
        String[] commands = new String[list.size()];
        for (int A = 0; A < list.size(); A++) {
            String command = list.get(A);
            String modified = command.replaceAll("-", "");
            if (command.length() != modified.length()) {
                System.err.println("Bonge detected invalid command of '" + command + "'. This has been changed to '" + modified + "'");
            }
            commands[A] = modified;
        }
        try {
            //TODO alis
            event.register(BongeLaunch.getContainer(), new SpongeCommandWrapping(state).buildCommand(), commands[0]);
        } catch (IllegalArgumentException e) {
            System.err.println("Command could not be registered: " + state.getPluginTag() + ":" + state.getLabel() + " due to another command being registered with the same label");
        }
    }

    public void register(PluginCommand command) {
        this.commands.add(new CommandState(command));
    }

    @Override
    public void registerAll(@NotNull String fallbackPrefix, @NotNull List<Command> commands) {
        commands.forEach(c -> this.register(fallbackPrefix, c));
    }

    @Override
    public boolean register(@NotNull String label, @NotNull String fallbackPrefix, @NotNull Command command) {
        return this.commands.add(new CommandState(label, fallbackPrefix, command));
    }

    @Override
    public boolean register(@NotNull String fallbackPrefix, @NotNull Command command) {
        return this.commands.add(new CommandState(command.getLabel(), fallbackPrefix, command));
    }

    @Override
    public boolean dispatch(@NotNull CommandSender sender, @NotNull String cmdLine) throws CommandException {
        CommandResult result;
        try {
            Subject subject = Bonge.getInstance().convert(sender, Subject.class);
            try {
                result = Sponge.server().commandManager().process(subject, ((Audience) subject), cmdLine);
            } catch (org.spongepowered.api.command.exception.CommandException e) {
                Component text = e.componentMessage();
                if (text == null) {
                    return false;
                }
                ((Audience) subject).sendMessage(text);
                return false;
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        return !result.equals(CommandResult.empty());
    }

    @Override
    public void clearCommands() {
        //Sponge.getCommandManager().getStandardRegistrar().reset();
        //this.commands.clear();
    }

    @Override
    public @Nullable Command getCommand(@NotNull String name) {
        Optional<CommandState> opcmd = this.commands.stream().filter(c -> c.getLabel().equalsIgnoreCase(name)).findAny();
        if (opcmd.isPresent()) {
            return opcmd.get().getCmd();
        }
        Optional<CommandState> opAlis = this.commands.stream().filter(c -> c.getCmd().getAliases().stream().anyMatch(s -> s.equalsIgnoreCase(name))).findAny();
        return opAlis.map(CommandState::getCmd).orElse(null);
    }

    @Override
    public @Nullable List<String> tabComplete(@NotNull CommandSender sender, @NotNull String cmdLine) throws IllegalArgumentException {
        return tabComplete(sender, cmdLine, null);
    }

    @Override
    public @Nullable List<String> tabComplete(@NotNull CommandSender sender, @NotNull String cmdLine, @Nullable Location location) throws IllegalArgumentException {
        //TODO give location?
        try {
            Subject subject = Bonge.getInstance().convert(sender, Subject.class);

            return Sponge.server().commandManager().suggest(subject, ((Audience) subject), cmdLine);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
