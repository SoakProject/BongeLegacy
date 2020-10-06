package org.bonge.bukkit.r1_15.block.data.dedicated;

import org.bonge.bukkit.r1_15.block.data.BongeAgeable;
import org.bonge.bukkit.r1_15.block.data.IBongeBlockData;
import org.bonge.util.exception.NotImplementedException;
import org.bukkit.block.data.type.Bamboo;
import org.jetbrains.annotations.NotNull;

public interface BongeBamboo extends IBongeBlockData, BongeAgeable, BongeSapling, Bamboo {

    @Override
    default @NotNull Leaves getLeaves(){
        throw new NotImplementedException("Bamboo.getLeaves() not got to yet");
    }

    @Override
    default void setLeaves(@NotNull Leaves leaves){
        throw new NotImplementedException("Bamboo.setLeaves(Leaves) not got to yet");
    }
}
