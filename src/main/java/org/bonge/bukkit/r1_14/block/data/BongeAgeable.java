package org.bonge.bukkit.r1_14.block.data;

import org.bonge.util.exception.NotImplementedException;
import org.bukkit.block.data.Ageable;
import org.spongepowered.api.block.BlockState;
import org.spongepowered.api.data.Keys;

import java.util.Optional;

public interface BongeAgeable extends IBongeBlockData, Ageable {

    @Override
    default int getAge(){
        return this.getSpongeValue().get(Keys.AGE).get();
    }

    @Override
    default void setAge(int age){
        if(age > this.getMaximumAge()){
            return;
        }
        Optional<BlockState> opState = this.getSpongeValue().with(Keys.AGE, age);
        if(!opState.isPresent()){
            return;
        }
        this.setSpongeValue(opState.get());
    }

    @Override
    default int getMaximumAge(){
        //return this.getSpongeValue().getValue(Keys.AGE).get().getMaxValue();
        throw new NotImplementedException("Ageable.getMaximumLevel() no Sponge alternative");
    }
}
