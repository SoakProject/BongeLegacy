package org.bonge.bukkit.r1_15.block.data;

import org.bukkit.block.data.Attachable;
import org.spongepowered.api.data.Keys;

public interface BongeAttachable extends IBongeBlockData, Attachable {

    @Override
    default boolean isAttached() {
        return this.getSpongeValue().get(Keys.IS_ATTACHED).get();
    }

    @Override
    default void setAttached(boolean attached) {
        this.setSpongeValue(this.getSpongeValue().with(Keys.IS_ATTACHED, attached).get());
    }
}
