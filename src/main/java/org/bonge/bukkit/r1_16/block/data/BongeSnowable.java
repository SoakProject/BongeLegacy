package org.bonge.bukkit.r1_16.block.data;

import org.bukkit.block.data.Snowable;
import org.spongepowered.api.data.Keys;

public interface BongeSnowable extends IBongeBlockData, Snowable {

    @Override
    default boolean isSnowy(){
        return this.getSpongeValue().get(Keys.IS_SNOWY).get();
    }

    @Override
    default void setSnowy(boolean snowy){
        this.setSpongeValue(this.getSpongeValue().with(Keys.IS_SNOWY, snowy).get());
    }
}
