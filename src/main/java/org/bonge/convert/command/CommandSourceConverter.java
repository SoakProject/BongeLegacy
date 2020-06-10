package org.bonge.convert.command;

import org.bonge.bukkit.r1_13.entity.living.human.BongePlayer;
import org.bonge.bukkit.r1_13.server.source.ConsoleSource;
import org.bonge.convert.Converter;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.spec.CommandSpec;

import java.io.IOException;

public class CommandSourceConverter implements Converter<CommandSender, CommandSource> {
    @Override
    public Class<CommandSource> getSpongeClass() {
        return CommandSource.class;
    }

    @Override
    public Class<CommandSender> getBukkitClass() {
        return CommandSender.class;
    }

    @Override
    public CommandSource from(CommandSender sender) throws IOException{
        if(sender instanceof Player){
            return ((BongePlayer)sender).getSpongeValue();
        }
        if(sender instanceof ConsoleCommandSender){
            return Sponge.getServer().getConsole();
        }
        throw new IOException("Unknown sender of " + sender.getName());
    }

    @Override
    public CommandSender to(CommandSource source) throws IOException {
        if(source instanceof org.spongepowered.api.entity.living.player.Player){
            return BongePlayer.getPlayer((org.spongepowered.api.entity.living.player.Player)source);
        }
        if(source instanceof org.spongepowered.api.command.source.ConsoleSource){
            return new ConsoleSource();
        }
        throw new IOException("Unknown source of " + source.getName());
    }
}
