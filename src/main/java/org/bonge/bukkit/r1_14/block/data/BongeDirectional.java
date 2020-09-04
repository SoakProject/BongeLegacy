package org.bonge.bukkit.r1_14.block.data;

import org.bonge.Bonge;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Directional;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.block.BlockState;
import org.spongepowered.api.data.Keys;

import java.util.Optional;

public interface BongeDirectional extends IBongeBlockData, Directional {

    @Override
    default @NotNull BlockFace getFacing() {
        return Bonge.getInstance().convert(this.getSpongeValue().get(Keys.DIRECTION).get());
    }

    @Override
    default void setFacing(@NotNull BlockFace facing) {
        Optional<BlockState> opState = this.getSpongeValue().with(Keys.DIRECTION, Bonge.getInstance().convert(facing));
        if(!opState.isPresent()){
            return;
        }
        this.setSpongeValue(opState.get());
    }
}
