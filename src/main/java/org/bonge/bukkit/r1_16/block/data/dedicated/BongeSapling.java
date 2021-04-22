package org.bonge.bukkit.r1_16.block.data.dedicated;

import org.bonge.bukkit.r1_16.block.data.IBongeBlockData;
import org.bonge.util.exception.NotImplementedException;
import org.bukkit.block.data.type.Sapling;
import org.spongepowered.api.block.BlockState;
import org.spongepowered.api.data.Keys;

import java.util.Optional;

public interface BongeSapling extends IBongeBlockData, Sapling {

    @Override
    default int getStage(){
        return this.getSpongeValue().get(Keys.GROWTH_STAGE).get();
    }

    @Override
    default void setStage(int stage){
        Optional<BlockState> opState = this.getSpongeValue().with(Keys.GROWTH_STAGE, stage);
        if(!opState.isPresent()){
            return;
        }
        this.setSpongeValue(opState.get());
    }


    @Override
    default int getMaximumStage(){
        //return this.getSpongeValue().getValue(Keys.GROWTH_STAGE).get().getMaxValue();
        throw new NotImplementedException("Levelled.getMaximumLevel() no Sponge alternative");
    }
}
