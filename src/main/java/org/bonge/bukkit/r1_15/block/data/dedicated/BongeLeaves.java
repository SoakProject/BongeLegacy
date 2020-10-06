package org.bonge.bukkit.r1_15.block.data.dedicated;

import org.bonge.bukkit.r1_15.block.data.IBongeBlockData;
import org.bukkit.block.data.type.Leaves;
import org.spongepowered.api.data.Keys;

public interface BongeLeaves extends IBongeBlockData, Leaves {

    @Override
    default boolean isPersistent() {
        return this.getSpongeValue().get(Keys.IS_PERSISTENT).get();
    }

    @Override
    default void setPersistent(boolean persistent) {
        this.setSpongeValue(this.getSpongeValue().with(Keys.IS_PERSISTENT, persistent).get());
    }

    @Override
    default int getDistance() {
        return this.getSpongeValue().get(Keys.DECAY_DISTANCE).get();
    }

    @Override
    default void setDistance(int distance) {
        this.setSpongeValue(this.getSpongeValue().with(Keys.DECAY_DISTANCE, distance).get());
    }
}
