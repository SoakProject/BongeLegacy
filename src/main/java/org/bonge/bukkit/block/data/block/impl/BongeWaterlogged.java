package org.bonge.bukkit.block.data.block.impl;

import org.bonge.bukkit.block.data.IBongeBlockData;
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
