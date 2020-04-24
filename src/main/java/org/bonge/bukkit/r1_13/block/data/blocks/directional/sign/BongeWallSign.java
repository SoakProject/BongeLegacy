package org.bonge.bukkit.r1_13.block.data.blocks.directional.sign;

import org.bonge.bukkit.r1_13.block.data.BongeAbstractBlockData;
import org.bonge.bukkit.r1_13.block.data.block.impl.BongeDirectional;
import org.bonge.bukkit.r1_13.block.data.block.impl.BongeWaterlogged;
import org.spongepowered.api.block.BlockState;

public class BongeWallSign extends BongeAbstractBlockData implements org.bukkit.block.data.type.WallSign, BongeWaterlogged, BongeDirectional {

    public BongeWallSign(BlockState value) {
        super(value);
    }

    @Override
    public BongeAbstractBlockData newInstance(BlockState state) {
        return new BongeWallSign(state);
    }

}
