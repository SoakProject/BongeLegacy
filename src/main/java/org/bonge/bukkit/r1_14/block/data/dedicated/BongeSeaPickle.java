package org.bonge.bukkit.r1_14.block.data.dedicated;

import org.bonge.bukkit.r1_14.block.data.BongeWaterLogged;
import org.bonge.bukkit.r1_14.block.data.IBongeBlockData;
import org.bonge.util.exception.NotImplementedException;
import org.bukkit.block.data.type.SeaPickle;

public interface BongeSeaPickle extends IBongeBlockData, SeaPickle, BongeWaterLogged {

    @Override
    default int getPickles() {
        throw new NotImplementedException("SeaPickle.getPickles() Not looked at yet");
    }

    @Override
    default void setPickles(int pickles) {
        throw new NotImplementedException("SeaPickle.setPickles(int) Not looked at yet");
    }

    @Override
    default int getMinimumPickles() {
        throw new NotImplementedException("SeaPickle.getMinimumPickles() Not looked at yet");
    }

    @Override
    default int getMaximumPickles() {
        throw new NotImplementedException("SeaPickle.getMaximumPickles() Not looked at yet");
    }
}
