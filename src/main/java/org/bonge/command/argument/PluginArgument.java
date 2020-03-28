package org.bonge.command.argument;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.ArgumentParseException;
import org.spongepowered.api.command.args.CommandArgs;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.args.CommandElement;
import org.spongepowered.api.text.Text;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class PluginArgument extends CommandElement {

    public PluginArgument(@Nullable Text key) {
        super(key);
    }

    @Nullable
    @Override
    protected Object parseValue(CommandSource source, CommandArgs args) throws ArgumentParseException {
        String peek = args.next();
        Optional<Plugin> opPlugin = Stream.of(Bukkit.getServer().getPluginManager().getPlugins()).filter(p -> p.getName().startsWith(peek)).findAny();
        if(opPlugin.isPresent()){
            return opPlugin.get();
        }
        throw args.createError(Text.of("Unknown bukkit plugin"));
    }

    @Override
    public List<String> complete(CommandSource src, CommandArgs args, CommandContext context) {
        try {
            String peek = args.peek();
            List<String> ret = new ArrayList<>();
            Stream.of(Bukkit.getServer().getPluginManager().getPlugins()).filter(p -> p.getName().startsWith(peek)).forEach(p -> ret.add(p.getName()));
            return ret;
        } catch (ArgumentParseException e) {
            return new ArrayList<>();
        }
    }
}
