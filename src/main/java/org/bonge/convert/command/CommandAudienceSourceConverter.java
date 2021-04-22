package org.bonge.convert.command;

import net.kyori.adventure.audience.Audience;
import org.bonge.bukkit.r1_16.entity.living.human.BongePlayer;
import org.bonge.bukkit.r1_16.server.source.ConsoleSource;
import org.bonge.convert.Converter;
import org.bonge.wrapper.BongeWrapper;
import org.bukkit.command.CommandSender;
import org.spongepowered.api.SystemSubject;
import org.spongepowered.api.entity.living.player.Player;

import java.io.IOException;

public class CommandAudienceSourceConverter implements Converter<CommandSender, Audience> {
    @Override
    public Class<Audience> getSpongeClass() {
        return Audience.class;
    }

    @Override
    public Class<CommandSender> getBukkitClass() {
        return CommandSender.class;
    }

    @Override
    public Audience from(CommandSender value) throws IOException {
        if(!(value instanceof BongeWrapper)){
            throw new IOException("Unknown command sender of " + value.getClass().getName());
        }
        Object spongeValue = ((BongeWrapper<?>)value).getSpongeValue();
        if(spongeValue instanceof Audience){
            return (Audience) spongeValue;
        }
        throw new IOException("Unknown command sender of " + value.getClass().getName());
    }

    @Override
    public CommandSender to(Audience value) throws IOException {
        if(value instanceof Player){
            return BongePlayer.getPlayer((Player) value);
        }
        if(value instanceof SystemSubject){
            return new ConsoleSource();
        }
        throw new IOException("Unknown audience of " + value.getClass().getName());
    }
}
