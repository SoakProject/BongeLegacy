package org.bonge.bukkit.r1_16.block.data.dedicated;

import org.bonge.bukkit.r1_16.block.data.BongeDirectional;
import org.bonge.bukkit.r1_16.block.data.IBongeBlockData;
import org.bukkit.block.data.type.Piston;
import org.spongepowered.api.data.Keys;

public interface BongePiston extends IBongeBlockData, Piston, BongeDirectional {

    @Override
    default boolean isExtended() {
        return this.getSpongeValue().get(Keys.IS_EXTENDED).get();
    }

    @Override
    default void setExtended(boolean extended) {
        this.setSpongeValue(this.getSpongeValue().with(Keys.IS_EXTENDED, extended).get());
    }
}
