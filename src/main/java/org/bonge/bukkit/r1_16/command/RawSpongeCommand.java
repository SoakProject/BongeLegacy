package org.bonge.bukkit.r1_16.command;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bonge.Bonge;
import org.bonge.bukkit.r1_16.command.state.CommandState;
import org.bonge.bukkit.r1_16.command.state.PluginCommandState;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.command.Command;
import org.spongepowered.api.command.CommandCause;
import org.spongepowered.api.command.CommandCompletion;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.exception.CommandException;
import org.spongepowered.api.command.parameter.ArgumentReader;
import org.spongepowered.api.event.EventContextKeys;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class RawSpongeCommand implements Command.Raw {

    private final CommandState commandState;

    public RawSpongeCommand(@NotNull PluginCommand command) {
        this(new PluginCommandState(command));
    }

    public RawSpongeCommand(@NotNull CommandState state) {
        this.commandState = state;
    }

    @Override
    public CommandResult process(CommandCause cause, ArgumentReader.Mutable arguments) throws CommandException {
        CommandSender sender = Bonge.getInstance().convert(cause.audience());
        String cmdLabel = cause.context().get(EventContextKeys.COMMAND).orElse(this.commandState.getLabel());
        String[] cmdArguments = arguments.input().split(" ");
        if (cmdArguments.length == 1) {
            if (cmdArguments[0].length() == 0) {
                cmdArguments = new String[0];
            }
        }
        try {
            boolean bukkitResult = this.commandState.getCmd().execute(sender, cmdLabel, cmdArguments);
            if (bukkitResult) {
                return CommandResult.success();
            }
        } catch (Throwable e) {
            System.out.println("===============[Bonge]===============");
            System.out.println("A error occur in the following plugin, its likely Bonge that is the issue, however could be the plugin itself.");
            System.out.println("Plugin: " + this.commandState.getLabel());
            System.out.println("CommandLauncher: " + this.commandState.getCmd().getClass().getSimpleName());
            System.out.println("Error: " + e.getMessage());
            System.out.println("===============[]===============");
            e.printStackTrace();
        }
        return CommandResult.builder().build();
    }

    @Override
    public List<CommandCompletion> complete(CommandCause cause, ArgumentReader.Mutable arguments) throws CommandException {
        CommandSender sender = Bonge.getInstance().convert(cause.audience());
        String cmdLabel = cause.context().get(EventContextKeys.COMMAND).orElse(this.commandState.getLabel());
        String[] cmdArguments = arguments.input().split(" ");
        if (cmdArguments.length == 0) {
            cmdArguments = new String[]{""};
        }
        return this.commandState.getCmd().tabComplete(sender, cmdLabel, cmdArguments).stream().map(CommandCompletion::of).collect(Collectors.toList());
    }

    @Override
    public boolean canExecute(CommandCause cause) {
        String permission = this.commandState.getCmd().getPermission();
        if (permission == null) {
            return true;
        }
        return cause.hasPermission(permission);
    }

    @Override
    public Optional<Component> shortDescription(CommandCause cause) {
        String description = this.commandState.getCmd().getDescription();
        @NonNull TextComponent component = LegacyComponentSerializer.legacySection().deserialize(description);
        return Optional.of(component);
    }

    @Override
    public Optional<Component> extendedDescription(CommandCause cause) {
        String description = this.commandState.getCmd().getDescription();
        @NonNull TextComponent component = LegacyComponentSerializer.legacySection().deserialize(description);
        return Optional.of(component);
    }

    @Override
    public Component usage(CommandCause cause) {
        String usage = this.commandState.getCmd().getUsage();
        @NonNull TextComponent component = LegacyComponentSerializer.legacySection().deserialize(usage);
        return component;
    }
}
