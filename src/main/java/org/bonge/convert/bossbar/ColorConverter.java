package org.bonge.convert.bossbar;

import net.kyori.adventure.bossbar.BossBar;
import org.bonge.convert.Converter;
import org.bukkit.boss.BarColor;

import java.io.IOException;

public class ColorConverter implements Converter<org.bukkit.boss.BarColor, BossBar.Color> {
    @Override
    public Class<BossBar.Color> getSpongeClass() {
        return BossBar.Color.class;
    }

    @Override
    public Class<BarColor> getBukkitClass() {
        return BarColor.class;
    }

    @Override
    public BossBar.Color from(BarColor colour) throws IOException {
        switch (colour){
            case PINK: return BossBar.Color.PINK;
            case BLUE: return BossBar.Color.BLUE;
            case RED: return BossBar.Color.RED;
            case GREEN: return BossBar.Color.GREEN;
            case YELLOW: return BossBar.Color.YELLOW;
            case PURPLE: return BossBar.Color.PURPLE;
            case WHITE: return BossBar.Color.WHITE;
            default: throw new IOException("Unknown bar colour of " + colour.name());
        }
    }

    @Override
    public BarColor to(BossBar.Color colour) throws IOException{
        if(colour.equals(BossBar.Color.BLUE)){
            return BarColor.BLUE;
        }else if(colour.equals(BossBar.Color.GREEN)){
            return BarColor.GREEN;
        }else if(colour.equals(BossBar.Color.PINK)){
            return BarColor.PINK;
        }else if(colour.equals(BossBar.Color.PURPLE)){
            return BarColor.PURPLE;
        }else if(colour.equals(BossBar.Color.RED)){
            return BarColor.RED;
        }else if(colour.equals(BossBar.Color.WHITE)){
            return BarColor.WHITE;
        }else if(colour.equals(BossBar.Color.YELLOW)){
            return BarColor.YELLOW;
        }else{
            throw new IOException("Unknown bar colour of " + colour.name());
        }
    }
}
