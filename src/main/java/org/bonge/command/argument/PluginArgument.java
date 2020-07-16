package org.bonge.command.argument;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.spongepowered.api.command.exception.ArgumentParseException;
import org.spongepowered.api.command.parameter.ArgumentReader;
import org.spongepowered.api.command.parameter.CommandContext;
import org.spongepowered.api.command.parameter.Parameter;
import org.spongepowered.api.command.parameter.managed.ValueParser;

import java.util.Optional;
import java.util.stream.Stream;

public class PluginArgument implements ValueParser<Plugin> {

    @Override
    public Optional<? extends Plugin> getValue(Parameter.Key<? super Plugin> parameterKey, ArgumentReader.Mutable reader, CommandContext.Builder context) throws ArgumentParseException {
        String pluginName = reader.parseString();
        return Stream.of(Bukkit.getServer().getPluginManager().getPlugins()).filter(p -> p.getName().equalsIgnoreCase(pluginName)).findAny();
    }

    /*@Override
    public List<String> complete(CommandContext context) {
        try {
            String peek = context.peek();
            List<String> ret = new ArrayList<>();
            Stream.of(Bukkit.getServer().getPluginManager().getPlugins()).filter(p -> p.getName().startsWith(peek)).forEach(p -> ret.add(p.getName()));
            return ret;
        } catch (ArgumentParseException e) {
            return new ArrayList<>();
        }
    }*/
}
