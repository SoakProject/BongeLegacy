package org.bonge.bukkit.r1_14.block.data.blocks;

import org.bonge.bukkit.r1_14.block.data.IBongeBlockData;
import org.bukkit.block.data.Waterlogged;

public interface BongeWaterlogged extends IBongeBlockData, Waterlogged {

    @Override
    default boolean isWaterlogged() {
        return false;
    }

    @Override
    default void setWaterlogged(boolean waterlogged) {

    }
}
