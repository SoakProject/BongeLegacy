package org.bonge.bukkit.r1_15.block.data.dedicated;

import org.bonge.bukkit.r1_15.block.data.BongeWaterLogged;
import org.bonge.bukkit.r1_15.block.data.IBongeBlockData;
import org.bonge.util.exception.NotImplementedException;
import org.bukkit.block.data.type.Scaffolding;

public interface BongeScaffolding extends IBongeBlockData, Scaffolding, BongeWaterLogged {

    @Override
    default boolean isBottom() {
        throw new NotImplementedException("Scaffolding.isBottom() Not looked at yet");
    }

    @Override
    default void setBottom(boolean bottom) {
        throw new NotImplementedException("Scaffolding.setBottom(boolean) Not looked at yet");
    }

    @Override
    default int getDistance() {
        throw new NotImplementedException("Scaffolding.getDistance() Not looked at yet");
    }

    @Override
    default void setDistance(int distance) {
        throw new NotImplementedException("Scaffolding.setDistance(int) Not looked at yet");
    }

    @Override
    default int getMaximumDistance() {
        throw new NotImplementedException("Scaffolding.getMaximumDistance() Not looked at yet");
    }
}
