package org.bonge.bukkit.r1_14.block.data.dedicated;

import org.bonge.bukkit.r1_14.block.data.IBongeBlockData;
import org.bukkit.block.data.type.Jukebox;
import org.spongepowered.api.data.Keys;

public interface BongeJukebox extends IBongeBlockData, Jukebox {

    @Override
    default boolean hasRecord() {
        return this.getSpongeValue().get(Keys.MUSIC_DISC).isPresent();
    }
}
