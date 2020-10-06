package org.bonge.bukkit.r1_15.block.data;

import org.bukkit.block.data.Lightable;
import org.spongepowered.api.data.Keys;

public interface BongeLightable extends IBongeBlockData, Lightable {

    @Override
    default boolean isLit() {
        return this.getSpongeValue().get(Keys.IS_LIT).get();
    }

    @Override
    default void setLit(boolean lit) {
        this.setSpongeValue(this.getSpongeValue().with(Keys.IS_LIT, lit).get());
    }
}
