package org.bonge.bukkit.block.data;

import org.spongepowered.api.block.BlockState;

public class BongeSimpleBlockData extends BongeAbstractBlockData {

    public BongeSimpleBlockData(BlockState value) {
        super(value);
    }

    @Override
    public BongeAbstractBlockData newInstance(BlockState state) {
        return new BongeSimpleBlockData(state);
    }
}
