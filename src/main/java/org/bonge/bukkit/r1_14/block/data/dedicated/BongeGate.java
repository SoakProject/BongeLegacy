package org.bonge.bukkit.r1_14.block.data.dedicated;

import org.bonge.bukkit.r1_14.block.data.BongeDirectional;
import org.bonge.bukkit.r1_14.block.data.BongeOpenable;
import org.bonge.bukkit.r1_14.block.data.BongePowerable;
import org.bonge.bukkit.r1_14.block.data.IBongeBlockData;
import org.bukkit.block.data.type.Gate;
import org.spongepowered.api.data.Keys;

public interface BongeGate extends IBongeBlockData, Gate, BongeDirectional, BongeOpenable, BongePowerable {

    @Override
    default boolean isInWall() {
        return this.getSpongeValue().get(Keys.IN_WALL).get();
    }

    @Override
    default void setInWall(boolean inWall) {
        this.setSpongeValue(this.getSpongeValue().with(Keys.IN_WALL, inWall).get());
    }
}
