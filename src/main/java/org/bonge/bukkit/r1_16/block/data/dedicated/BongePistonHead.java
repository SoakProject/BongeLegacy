package org.bonge.bukkit.r1_16.block.data.dedicated;

import org.bonge.bukkit.r1_16.block.data.IBongeBlockData;
import org.bukkit.block.data.type.PistonHead;
import org.spongepowered.api.data.Keys;

public interface BongePistonHead extends IBongeBlockData, PistonHead, BongeTechnicalPiston {

    @Override
    default boolean isShort() {
        return !this.getSpongeValue().get(Keys.IS_EXTENDED).get();
    }

    @Override
    default void setShort(boolean _short) {
        this.setSpongeValue(this.getSpongeValue().with(Keys.IS_EXTENDED, !_short).get());
    }
}
