package org.bukkit.command;

import org.bonge.Bonge;
import org.bonge.bukkit.r1_16.command.RawSpongeCommand;
import org.bonge.bukkit.r1_16.command.state.CommandState;
import org.spongepowered.api.command.Command;
import org.spongepowered.api.command.CommandCompletion;
import org.spongepowered.api.command.CommandExecutor;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.parameter.CommandContext;
import org.spongepowered.api.command.parameter.Parameter;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class SpongeCommandWrapping implements CommandExecutor {

    private final CommandState state;
    private final Parameter.Value<String> commandArguments;

    public SpongeCommandWrapping(CommandState state) {
        this.state = state;
        this.commandArguments = Parameter.remainingJoinedStrings().key("args").completer((context, input) -> {
            Collection<? extends String> args = context.all(SpongeCommandWrapping.this.commandArguments);
            CommandSender sender;
            try {
                sender = Bonge.getInstance().convert(CommandSender.class, context.subject());
            } catch (IOException e) {
                throw new IllegalArgumentException(e);
            }
            List<String> suggestions = SpongeCommandWrapping.this.state.getCmd().tabComplete(sender, SpongeCommandWrapping.this.state.getLabel(), args.toArray(new String[0]));
            return suggestions.stream().map(CommandCompletion::of).collect(Collectors.toList());
        }).optional().build();
    }

    public Command.Raw buildCommand() {
        org.bukkit.command.Command command = this.state.getCmd();
        if (!(command instanceof PluginCommand)) {
            throw new IllegalStateException("SpongeCommandWrapper must contain a PluginCommand within the state, found " + command.getClass().getName());
        }
        return new RawSpongeCommand(this.state);
    }

    @Override
    public CommandResult execute(CommandContext context) {
        try {
            CommandSender source2 = Bonge.getInstance().convert(CommandSender.class, context.subject());
            String[] split = context.all(this.commandArguments).toArray(new String[0]);
            boolean result = this.state.getCmd().execute(source2, this.state.getLabel(), split);
            return result ? CommandResult.success() : CommandResult.builder().build();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        } catch (Throwable e) {
            if (this.state.getCmd() instanceof PluginCommand) {
                Bonge.createCrashFile(((PluginCommand) this.state.getCmd()).getPlugin(), "Command", e);
            }
            throw e;
        }
    }

    /*
    @Override
    public boolean testPermission(CommandSource source) {
        String permission = this.state.getCmd().getPermission();
        if(permission == null){
            return true;
        }
        return source.hasPermission(permission);
    }

    @Override
    public Optional<Text> getShortDescription(CommandSource source) {
        return Optional.empty();
    }

    @Override
    public Optional<Text> getHelp(CommandSource source) {
        return Optional.empty();
    }

    @Override
    public Text getUsage(CommandSource source) {
        return TextSerializers.FORMATTING_CODE.deserialize(this.state.getCmd().usageMessage);
    }*/
}
