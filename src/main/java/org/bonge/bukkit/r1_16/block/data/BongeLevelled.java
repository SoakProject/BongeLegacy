package org.bonge.bukkit.r1_16.block.data;

import org.bonge.util.exception.NotImplementedException;
import org.bukkit.block.data.Levelled;
import org.spongepowered.api.block.BlockState;
import org.spongepowered.api.data.Keys;

import java.util.Optional;

public interface BongeLevelled extends IBongeBlockData, Levelled {

    @Override
    default int getLevel() {
        return this.getSpongeValue().get(Keys.FLUID_LEVEL).get();
    }

    @Override
    default void setLevel(int level) {
        Optional<BlockState> opState = this.getSpongeValue().with(Keys.FLUID_LEVEL, level);
        if(!opState.isPresent()){
            return;
        }
        this.setSpongeValue(opState.get());
    }

    @Override
    default int getMaximumLevel() {
        //return this.getSpongeValue().getValue(Keys.FLUID_LEVEL).get().getMaxValue();
        throw new NotImplementedException("Levelled.getMaximumLevel() no Sponge alternative");
    }
}
