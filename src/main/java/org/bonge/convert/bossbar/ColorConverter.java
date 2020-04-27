package org.bonge.convert.bossbar;

import org.bonge.convert.Converter;
import org.bukkit.boss.BarColor;
import org.spongepowered.api.boss.BossBarColor;
import org.spongepowered.api.boss.BossBarColors;

public class ColorConverter implements Converter<org.bukkit.boss.BarColor, org.spongepowered.api.boss.BossBarColor> {
    @Override
    public Class<BossBarColor> getSpongeClass() {
        return BossBarColor.class;
    }

    @Override
    public Class<BarColor> getBukkitClass() {
        return BarColor.class;
    }

    @Override
    public BossBarColor from(BarColor colour) {
        switch (colour){
            case PINK: return BossBarColors.PINK;
            case BLUE: return BossBarColors.BLUE;
            case RED: return BossBarColors.RED;
            case GREEN: return BossBarColors.GREEN;
            case YELLOW: return BossBarColors.YELLOW;
            case PURPLE: return BossBarColors.PURPLE;
            case WHITE: return BossBarColors.WHITE;
            default: throw new IllegalStateException("Unknown bar colour of " + colour.name());
        }
    }

    @Override
    public BarColor to(BossBarColor colour) {
        if(colour.equals(BossBarColors.BLUE)){
            return BarColor.BLUE;
        }else if(colour.equals(BossBarColors.GREEN)){
            return BarColor.GREEN;
        }else if(colour.equals(BossBarColors.PINK)){
            return BarColor.PINK;
        }else if(colour.equals(BossBarColors.PURPLE)){
            return BarColor.PURPLE;
        }else if(colour.equals(BossBarColors.RED)){
            return BarColor.RED;
        }else if(colour.equals(BossBarColors.WHITE)){
            return BarColor.WHITE;
        }else if(colour.equals(BossBarColors.YELLOW)){
            return BarColor.YELLOW;
        }else{
            throw new IllegalStateException("Unknown bar colour of " + colour.getId());
        }
    }
}
