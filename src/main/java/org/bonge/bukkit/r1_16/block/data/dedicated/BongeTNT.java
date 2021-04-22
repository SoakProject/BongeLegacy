package org.bonge.bukkit.r1_16.block.data.dedicated;

import org.bonge.bukkit.r1_16.block.data.IBongeBlockData;
import org.bukkit.block.data.type.TNT;
import org.spongepowered.api.data.Keys;

public interface BongeTNT extends IBongeBlockData, TNT {

    @Override
    default boolean isUnstable() {
        return this.getSpongeValue().get(Keys.UNSTABLE).get();
    }

    @Override
    default void setUnstable(boolean unstable) {
        this.setSpongeValue(this.getSpongeValue().with(Keys.UNSTABLE, unstable).get());
    }
}
