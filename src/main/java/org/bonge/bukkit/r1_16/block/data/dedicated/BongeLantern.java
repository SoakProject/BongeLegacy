package org.bonge.bukkit.r1_16.block.data.dedicated;

import org.bonge.bukkit.r1_16.block.data.IBongeBlockData;
import org.bukkit.block.data.type.Lantern;
import org.spongepowered.api.data.Keys;

public interface BongeLantern extends IBongeBlockData, Lantern {

    @Override
    default boolean isHanging() {
        return this.getSpongeValue().get(Keys.IS_ATTACHED).get();
    }

    @Override
    default void setHanging(boolean hanging) {
        this.setSpongeValue(this.getSpongeValue().with(Keys.IS_ATTACHED, hanging).get());
    }
}
