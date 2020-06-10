package org.bonge.bukkit.r1_13.block.data.blocks.directional;

import org.bonge.bukkit.r1_13.block.data.IBongeBlockData;
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
