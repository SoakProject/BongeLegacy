package org.bonge.bukkit.r1_16.command;

import net.kyori.adventure.text.Component;
import org.bukkit.command.PluginCommand;
import org.spongepowered.api.command.Command;
import org.spongepowered.api.command.CommandCause;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.exception.CommandException;
import org.spongepowered.api.command.parameter.ArgumentReader;

import java.util.List;
import java.util.Optional;

public class RawSpongeCommand implements Command.Raw {

    private final CommandState commandState;

    public RawSpongeCommand(PluginCommand command) {
        this(new CommandState(command));
    }

    public RawSpongeCommand(CommandState state) {
        this.commandState = state;
    }

    @Override
    public CommandResult process(CommandCause cause, ArgumentReader.Mutable arguments) throws CommandException {
        return null;
    }

    @Override
    public List<String> suggestions(CommandCause cause, ArgumentReader.Mutable arguments) throws CommandException {
        return null;
    }

    @Override
    public boolean canExecute(CommandCause cause) {
        return false;
    }

    @Override
    public Optional<Component> shortDescription(CommandCause cause) {
        return Optional.empty();
    }

    @Override
    public Optional<Component> extendedDescription(CommandCause cause) {
        return Optional.empty();
    }

    @Override
    public Component usage(CommandCause cause) {
        return null;
    }
}
