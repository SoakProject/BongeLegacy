package org.bonge.bukkit.r1_16.block.data;

import org.bukkit.block.data.Openable;
import org.spongepowered.api.data.Keys;

public interface BongeOpenable extends IBongeBlockData, Openable {

    @Override
    default boolean isOpen(){
        return this.getSpongeValue().get(Keys.IS_OPEN).get();
    }

    @Override
    default void setOpen(boolean open){
        this.setSpongeValue(this.getSpongeValue().with(Keys.IS_OPEN, open).get());
    }
}
