package org.bonge.bukkit.block.data.block.impl;

import org.bonge.bukkit.block.data.IBongeBlockData;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Directional;

import java.util.Set;

public interface BongeDirectional extends IBongeBlockData, Directional {

    @Override
    default BlockFace getFacing() {
        return null;
    }

    @Override
    default void setFacing(BlockFace facing) {

    }

    @Override
    default Set<BlockFace> getFaces() {
        return null;
    }
}
