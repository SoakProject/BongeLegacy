package org.bonge.bukkit.r1_15.block.data;

import org.bonge.Bonge;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Rotatable;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.data.Keys;

public interface BongeRotatable extends IBongeBlockData, Rotatable {

    @Override
    default @NotNull BlockFace getRotation(){
        return Bonge.getInstance().convert(this.getSpongeValue().get(Keys.DIRECTION).get());
    }

    @Override
    default void setRotation(@NotNull BlockFace rotation){
        this.setSpongeValue(this.getSpongeValue().with(Keys.DIRECTION, Bonge.getInstance().convert(rotation)).get());
    }
}
