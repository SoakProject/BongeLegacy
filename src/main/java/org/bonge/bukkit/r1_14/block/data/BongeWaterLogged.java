package org.bonge.bukkit.r1_14.block.data;

import org.bukkit.block.data.Waterlogged;
import org.spongepowered.api.data.Keys;

public interface BongeWaterLogged extends IBongeBlockData, Waterlogged {

    @Override
    default boolean isWaterlogged(){
        return this.getSpongeValue().get(Keys.IS_WATERLOGGED).get();
    }

    @Override
    default void setWaterlogged(boolean waterlogged){
        this.setSpongeValue(this.getSpongeValue().with(Keys.IS_WATERLOGGED, waterlogged).get());
    }
}
