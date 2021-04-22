package org.bonge.bukkit.r1_16.block.data;

import org.bonge.util.exception.NotImplementedException;
import org.bukkit.block.data.AnaloguePowerable;
import org.spongepowered.api.block.BlockState;
import org.spongepowered.api.data.Keys;

import java.util.Optional;

public interface BongeAnaloguePowerable extends IBongeBlockData, AnaloguePowerable {

    @Override
    default int getPower() {
        return this.getSpongeValue().get(Keys.POWER).get();
    }

    @Override
    default void setPower(int power) {
        Optional<BlockState> opState = this.getSpongeValue().with(Keys.POWER, power);
        if(!opState.isPresent()){
            return;
        }
        this.setSpongeValue(opState.get());
    }

    @Override
    default int getMaximumPower() {
        //return this.getSpongeValue().getValue(Keys.POWER).get().getMaxValue();
        throw new NotImplementedException("AnaloguePowerable.getMaximumLevel() no Sponge alternative");
    }
}
