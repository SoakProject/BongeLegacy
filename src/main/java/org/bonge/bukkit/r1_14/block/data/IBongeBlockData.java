package org.bonge.bukkit.r1_14.block.data;

import org.bukkit.block.data.BlockData;
import org.spongepowered.api.block.BlockState;

public interface IBongeBlockData extends BlockData {

    BongeAbstractBlockData newInstance(BlockState state);
    BlockState getSpongeValue();

}
