package org.bonge.bukkit.r1_14.block.data.dedicated;

import org.bonge.bukkit.r1_14.block.data.IBongeBlockData;
import org.bonge.util.exception.NotImplementedException;
import org.bukkit.block.data.type.BrewingStand;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public interface BongeBrewingStand extends IBongeBlockData, BrewingStand {

    @Override
    default boolean hasBottle(int bottle) {
        throw new NotImplementedException("BongeBrewingStand.hasBottle(int) not got to yet");
    }

    @Override
    default void setBottle(int bottle, boolean has) {
        throw new NotImplementedException("BongeBrewingStand.setBottle(int, boolean) not got to yet");
    }

    @Override
    default @NotNull Set<Integer> getBottles() {
        throw new NotImplementedException("BongeBrewingStand.getBottles() not got to yet");
    }

    @Override
    default int getMaximumBottles() {
        throw new NotImplementedException("BongeBrewingStand.getMaximumBottles() not got to yet");
    }
}
