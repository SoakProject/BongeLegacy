package org.bonge.convert;

import org.bonge.bukkit.r1_13.entity.BongeAbstractEntity;
import org.bonge.bukkit.r1_13.entity.living.human.BongePlayer;
import org.bonge.bukkit.r1_13.server.source.ConsoleSource;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.projectiles.ProjectileSource;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.scoreboard.critieria.Criteria;
import org.spongepowered.api.scoreboard.critieria.Criterion;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.action.TextActions;
import org.spongepowered.api.text.serializer.TextSerializers;

import java.net.MalformedURLException;
import java.net.URL;

public class InterfaceConvert {

    public static Criterion getCriteria(String criteria){
        switch (criteria){
            case "dummy": return Criteria.DUMMY;
            case "deathCount": return Criteria.DEATHS;
            case "playerKillCount": return Criteria.PLAYER_KILLS;
            case "totalKillCount": return Criteria.TOTAL_KILLS;
            case "health": return Criteria.HEALTH;
            case "trigger": return Criteria.TRIGGER;
            default: throw new IllegalStateException("Unknown criteria of '" + criteria + "'");
        }
    }

    public static String getCriteria(Criterion criterion){
        if(criterion.equals(Criteria.DEATHS)){
            return "deathCount";
        }
        if(criterion.equals(Criteria.DUMMY)){
            return "dummy";
        }
        if(criterion.equals(Criteria.HEALTH)){
            return "health";
        }
        if(criterion.equals(Criteria.PLAYER_KILLS)){
            return "playerKillCount";
        }
        if(criterion.equals(Criteria.TOTAL_KILLS)){
            return "totalKillCount";
        }
        if(criterion.equals(Criteria.TRIGGER)){
            return "trigger";
        }
        throw new IllegalStateException("Unknown criteria of " + criterion.getId());
    }

    public static Text fromString(String message){
        Text text = TextSerializers.FORMATTING_CODE.deserialize(message);
        try{
            URL url = new URL(ChatColor.stripColor(message));
            return text.toBuilder().onClick(TextActions.openUrl(url)).build();
        } catch (MalformedURLException e){
            return text;
        }
    }

    public static String toString(Text text){
        if(text == null){
            return null;
        }
        return TextSerializers.FORMATTING_CODE.serialize(text);
    }

    public static org.spongepowered.api.entity.projectile.source.ProjectileSource getProjectile(ProjectileSource source){
        if(source instanceof Entity){
            return (org.spongepowered.api.entity.projectile.source.ProjectileSource)((BongeAbstractEntity<?>)source).getSpongeValue();
        }
        return null;
    }

    public static ProjectileSource getProjectile(org.spongepowered.api.entity.projectile.source.ProjectileSource projectile){
        if(projectile instanceof org.spongepowered.api.entity.Entity){
            return (ProjectileSource) BongeAbstractEntity.of((org.spongepowered.api.entity.Entity)projectile);
        }
        return null;
    }

    public static CommandSender getSender(org.spongepowered.api.command.CommandSource source){
        if(source instanceof org.spongepowered.api.entity.living.player.Player){
            return BongePlayer.getPlayer((org.spongepowered.api.entity.living.player.Player)source);
        }
        if(source instanceof org.spongepowered.api.command.source.ConsoleSource){
            return new ConsoleSource();
        }
        throw new IllegalArgumentException("Unknown source of " + source.getName());
    }

    public static CommandSource getSource(CommandSender sender){
        if(sender instanceof Player){
            return ((BongePlayer)sender).getSpongeValue();
        }
        if(sender instanceof ConsoleCommandSender){
            return Sponge.getServer().getConsole();
        }
        throw new IllegalStateException("Unknown sender of " + sender.getName());
    }
}
