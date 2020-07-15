package org.bonge.convert.command;

import org.bonge.bukkit.r1_14.command.rcon.RconCommandSource;
import org.bonge.bukkit.r1_14.entity.living.human.BongePlayer;
import org.bonge.bukkit.r1_14.server.source.ConsoleSource;
import org.bonge.convert.Converter;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.SystemSubject;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.entity.living.player.server.ServerPlayer;
import org.spongepowered.api.network.RconConnection;
import org.spongepowered.api.service.permission.Subject;

import java.io.IOException;

public class CommandSourceConverter implements Converter<CommandSender, Subject> {
    @Override
    public Class<Subject> getSpongeClass() {
        return Subject.class;
    }

    @Override
    public Class<CommandSender> getBukkitClass() {
        return CommandSender.class;
    }

    @Override
    public Subject from(CommandSender sender) throws IOException{
        if(sender instanceof BongePlayer){
            Player player = ((BongePlayer)sender).getSpongeValue();
            if(player instanceof ServerPlayer){
                return (ServerPlayer)player;
            }
            throw new IllegalStateException("CommandSource only works on a server");
        }
        if(sender instanceof ConsoleCommandSender){
            return Sponge.getSystemSubject();
        }
        if(sender instanceof RconCommandSource){
            return ((RconCommandSource)sender).getSpongeValue();
        }
        throw new IOException("Unknown sender of " + sender.getName());
    }

    @Override
    public CommandSender to(Subject source) throws IOException {
        if(source instanceof org.spongepowered.api.entity.living.player.Player){
            return BongePlayer.getPlayer((org.spongepowered.api.entity.living.player.Player)source);
        }
        if(source instanceof SystemSubject){
            return new ConsoleSource();
        }
        if(source instanceof RconConnection){
            return new RconCommandSource((RconConnection) source);
        }
        throw new IOException("Unknown source of " + source.getFriendlyIdentifier().orElse(source.getIdentifier()));
    }
}
