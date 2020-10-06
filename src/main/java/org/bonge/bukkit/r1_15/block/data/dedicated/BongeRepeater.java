package org.bonge.bukkit.r1_15.block.data.dedicated;

import org.bonge.bukkit.r1_15.block.data.BongeDirectional;
import org.bonge.bukkit.r1_15.block.data.BongePowerable;
import org.bonge.bukkit.r1_15.block.data.IBongeBlockData;
import org.bonge.util.exception.NotImplementedException;
import org.bukkit.block.data.type.Repeater;
import org.spongepowered.api.data.Keys;

public interface BongeRepeater extends IBongeBlockData, Repeater, BongeDirectional, BongePowerable {

    @Override
    default int getDelay() {
        return this.getSpongeValue().get(Keys.REDSTONE_DELAY).get();
    }

    @Override
    default void setDelay(int delay) {
        if(delay > this.getMaximumDelay()){
            return;
        }
        if(delay < this.getMinimumDelay()){
            return;
        }
        this.setSpongeValue(this.getSpongeValue().with(Keys.REDSTONE_DELAY, delay).get());
    }

    @Override
    default int getMinimumDelay() {
        //return this.getSpongeValue().getValue(Keys.REDSTONE_DELAY).get().getMinValue();
        throw new NotImplementedException("Powerable.getMinimumLevel() no Sponge alternative");
    }

    @Override
    default int getMaximumDelay() {
        //return this.getSpongeValue().getValue(Keys.REDSTONE_DELAY).get().getMaxValue();
        throw new NotImplementedException("Levelled.getMaximumLevel() no Sponge alternative");
    }

    @Override
    default boolean isLocked() {
        throw new NotImplementedException("Repeater.isLocked() Not got to yet");
    }

    @Override
    default void setLocked(boolean locked) {
        throw new NotImplementedException("Repeater.setLocked(boolean) Not got to yet");
    }
}
