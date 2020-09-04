package org.bonge.bukkit.r1_14.block.data.dedicated;

import org.bonge.bukkit.r1_14.block.data.BongeDirectional;
import org.bonge.bukkit.r1_14.block.data.IBongeBlockData;
import org.bukkit.block.data.type.DaylightDetector;
import org.spongepowered.api.data.Keys;

public interface BongeDaylightDetector extends IBongeBlockData, DaylightDetector, BongeDirectional {

    @Override
    default boolean isInverted() {
        return this.getSpongeValue().get(Keys.INVERTED).get();
    }

    @Override
    default void setInverted(boolean inverted) {
        this.setSpongeValue(this.getSpongeValue().with(Keys.INVERTED, inverted).get());
    }
}
