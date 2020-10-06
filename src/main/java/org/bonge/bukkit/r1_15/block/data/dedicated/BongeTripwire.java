package org.bonge.bukkit.r1_15.block.data.dedicated;

import org.bonge.bukkit.r1_15.block.data.BongeAttachable;
import org.bonge.bukkit.r1_15.block.data.BongeMultipleFacing;
import org.bonge.bukkit.r1_15.block.data.BongePowerable;
import org.bonge.bukkit.r1_15.block.data.IBongeBlockData;
import org.bukkit.block.data.type.Tripwire;
import org.spongepowered.api.data.Keys;

public interface BongeTripwire extends IBongeBlockData, Tripwire, BongeAttachable, BongeMultipleFacing, BongePowerable {

    @Override
    default boolean isDisarmed() {
        return this.getSpongeValue().get(Keys.IS_DISARMED).get();
    }

    @Override
    default void setDisarmed(boolean disarmed) {
        this.setSpongeValue(this.getSpongeValue().with(Keys.IS_DISARMED, disarmed).get());
    }
}
