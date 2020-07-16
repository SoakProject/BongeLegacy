package org.bonge.convert.entity;

import org.bonge.convert.Converter;
import org.spongepowered.api.entity.living.player.gamemode.GameMode;
import org.spongepowered.api.entity.living.player.gamemode.GameModes;

public class GameModeConverter implements Converter<org.bukkit.GameMode, GameMode> {
    @Override
    public Class<GameMode> getSpongeClass() {
        return GameMode.class;
    }

    @Override
    public Class<org.bukkit.GameMode> getBukkitClass() {
        return org.bukkit.GameMode.class;
    }

    @Override
    public GameMode from(org.bukkit.GameMode mode) {
        switch (mode){
            case ADVENTURE: return GameModes.ADVENTURE.get();
            case CREATIVE: return GameModes.CREATIVE.get();
            case SPECTATOR: return GameModes.SPECTATOR.get();
            default: return GameModes.SURVIVAL.get();
        }
    }

    @Override
    public org.bukkit.GameMode to(GameMode gamemode){
        if (gamemode == GameModes.ADVENTURE.get()){
            return org.bukkit.GameMode.ADVENTURE;
        }
        if (gamemode == GameModes.CREATIVE.get()){
            return org.bukkit.GameMode.CREATIVE;
        }
        if (gamemode == GameModes.SPECTATOR.get()){
            return org.bukkit.GameMode.SPECTATOR;
        }
        return org.bukkit.GameMode.SURVIVAL;
    }
}
