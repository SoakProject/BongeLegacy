package org.bonge.bukkit.r1_16.block.data;

import org.bukkit.block.data.BlockData;
import org.spongepowered.api.block.BlockState;

public interface IBongeBlockData extends BlockData {

    BongeAbstractBlockData newInstance(BlockState state);
    BlockState getSpongeValue();
    IBongeBlockData setSpongeValue(BlockState state);

}
