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
            case ADVENTURE: return GameModes.ADVENTURE;
            case CREATIVE: return GameModes.CREATIVE;
            case SPECTATOR: return GameModes.SPECTATOR;
            default: return GameModes.SURVIVAL;
        }
    }

    @Override
    public org.bukkit.GameMode to(GameMode gamemode) {
        if (gamemode == GameModes.ADVENTURE){
            return org.bukkit.GameMode.ADVENTURE;
        }
        if (gamemode == GameModes.CREATIVE){
            return org.bukkit.GameMode.CREATIVE;
        }
        if (gamemode == GameModes.SPECTATOR){
            return org.bukkit.GameMode.SPECTATOR;
        }
        return org.bukkit.GameMode.SURVIVAL;
    }
}
