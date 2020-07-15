package org.bonge.convert.bossbar;

import org.bonge.convert.Converter;
import org.bukkit.boss.BarColor;
import org.spongepowered.api.boss.BossBarColor;
import org.spongepowered.api.boss.BossBarColors;

import java.io.IOException;

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
    public BossBarColor from(BarColor colour) throws IOException {
        switch (colour){
            case PINK: return BossBarColors.PINK.get();
            case BLUE: return BossBarColors.BLUE.get();
            case RED: return BossBarColors.RED.get();
            case GREEN: return BossBarColors.GREEN.get();
            case YELLOW: return BossBarColors.YELLOW.get();
            case PURPLE: return BossBarColors.PURPLE.get();
            case WHITE: return BossBarColors.WHITE.get();
            default: throw new IOException("Unknown bar colour of " + colour.name());
        }
    }

    @Override
    public BarColor to(BossBarColor colour) throws IOException{
        if(colour.equals(BossBarColors.BLUE.get())){
            return BarColor.BLUE;
        }else if(colour.equals(BossBarColors.GREEN.get())){
            return BarColor.GREEN;
        }else if(colour.equals(BossBarColors.PINK.get())){
            return BarColor.PINK;
        }else if(colour.equals(BossBarColors.PURPLE.get())){
            return BarColor.PURPLE;
        }else if(colour.equals(BossBarColors.RED.get())){
            return BarColor.RED;
        }else if(colour.equals(BossBarColors.WHITE.get())){
            return BarColor.WHITE;
        }else if(colour.equals(BossBarColors.YELLOW.get())){
            return BarColor.YELLOW;
        }else{
            throw new IOException("Unknown bar colour of " + colour.getKey().getFormatted());
        }
    }
}
