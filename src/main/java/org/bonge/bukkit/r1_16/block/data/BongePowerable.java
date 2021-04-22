package org.bonge.bukkit.r1_16.block.data;

import org.bukkit.block.data.Powerable;
import org.spongepowered.api.data.Keys;

public interface BongePowerable extends IBongeBlockData, Powerable {

    @Override
    default boolean isPowered(){
        return this.getSpongeValue().get(Keys.IS_POWERED).get();
    }

    @Override
    default void setPowered(boolean powered){
        this.setSpongeValue(this.getSpongeValue().with(Keys.IS_POWERED, powered).get());
    }
}
