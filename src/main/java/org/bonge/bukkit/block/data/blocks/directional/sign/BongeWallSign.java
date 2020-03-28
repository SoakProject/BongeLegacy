package org.bonge.bukkit.block.data.blocks.directional.sign;

import org.bonge.bukkit.block.data.BongeAbstractBlockData;
import org.bonge.bukkit.block.data.block.impl.BongeDirectional;
import org.bonge.bukkit.block.data.block.impl.BongeWaterlogged;
import org.bukkit.block.BlockFace;
import org.spongepowered.api.block.BlockState;

import java.util.Set;

public class BongeWallSign extends BongeAbstractBlockData implements org.bukkit.block.data.type.WallSign, BongeWaterlogged, BongeDirectional {

    public BongeWallSign(BlockState value) {
        super(value);
    }

    @Override
    public BongeAbstractBlockData newInstance(BlockState state) {
        return new BongeWallSign(state);
    }

}
