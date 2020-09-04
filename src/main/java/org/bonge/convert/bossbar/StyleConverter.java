package org.bonge.convert.bossbar;

import net.kyori.adventure.bossbar.BossBar;
import org.bonge.convert.Converter;
import org.bukkit.boss.BarStyle;

import java.io.IOException;

public class StyleConverter implements Converter<org.bukkit.boss.BarStyle, BossBar.Overlay> {
    @Override
    public Class<BossBar.Overlay> getSpongeClass() {
        return BossBar.Overlay.class;
    }

    @Override
    public Class<BarStyle> getBukkitClass() {
        return BarStyle.class;
    }

    @Override
    public BossBar.Overlay from(BarStyle value) throws IOException {
        switch (value){
            case SOLID: return BossBar.Overlay.PROGRESS;
            case SEGMENTED_6: return BossBar.Overlay.NOTCHED_6;
            case SEGMENTED_10: return BossBar.Overlay.NOTCHED_10;
            case SEGMENTED_12: return BossBar.Overlay.NOTCHED_12;
            case SEGMENTED_20: return BossBar.Overlay.NOTCHED_20;
            default: throw new IOException("Unknown style of " + value.name());
        }
    }

    @Override
    public BarStyle to(BossBar.Overlay overlay) throws IOException{
        if(overlay.equals(BossBar.Overlay.NOTCHED_6)){
            return BarStyle.SEGMENTED_6;
        }else if(overlay.equals(BossBar.Overlay.NOTCHED_10)){
            return BarStyle.SEGMENTED_10;
        }else if(overlay.equals(BossBar.Overlay.NOTCHED_12)){
            return BarStyle.SEGMENTED_12;
        }else if(overlay.equals(BossBar.Overlay.NOTCHED_20)) {
            return BarStyle.SEGMENTED_20;
        }else if(overlay.equals(BossBar.Overlay.PROGRESS)){
            return BarStyle.SOLID;
        }else{
            throw new IOException("Unknown style of " + overlay.name());
        }
    }
}
