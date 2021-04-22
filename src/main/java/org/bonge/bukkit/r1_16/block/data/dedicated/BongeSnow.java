package org.bonge.bukkit.r1_16.block.data.dedicated;

import org.bonge.bukkit.r1_16.block.data.IBongeBlockData;
import org.bonge.util.exception.NotImplementedException;
import org.bukkit.block.data.type.Snow;
import org.spongepowered.api.data.Keys;

public interface BongeSnow extends IBongeBlockData, Snow {

    @Override
    default int getLayers() {
        return this.getSpongeValue().get(Keys.LAYER).get();
    }

    @Override
    default void setLayers(int layers) {
        if(this.getMaximumLayers() < layers){
            return;
        }
        if(this.getMinimumLayers() > layers){
            return;
        }
        this.setSpongeValue(this.getSpongeValue().with(Keys.LAYER, layers).get());
    }

    @Override
    default int getMinimumLayers() {
        //return this.getSpongeValue().getValue(Keys.LAYER).get().getMaxValue();
        throw new NotImplementedException("Levelled.getMaximumLevel() no Sponge alternative");
    }

    @Override
    default int getMaximumLayers() {
        //return this.getSpongeValue().getValue(Keys.LAYER).get().getMaxValue();
        throw new NotImplementedException("Levelled.getMaximumLevel() no Sponge alternative");

    }
}
