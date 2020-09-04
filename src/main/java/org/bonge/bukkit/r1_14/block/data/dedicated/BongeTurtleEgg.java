package org.bonge.bukkit.r1_14.block.data.dedicated;

import org.bonge.bukkit.r1_14.block.data.IBongeBlockData;
import org.bonge.util.exception.NotImplementedException;
import org.bukkit.block.data.type.TurtleEgg;

public interface BongeTurtleEgg extends IBongeBlockData, TurtleEgg {

    @Override
    default int getEggs() {
        throw new NotImplementedException("TurtleEgg.getEggs() Not looked at yet");
    }

    @Override
    default void setEggs(int eggs) {
        throw new NotImplementedException("TurtleEgg.setEggs(int) Not looked at yet");
    }

    @Override
    default int getMinimumEggs() {
        throw new NotImplementedException("TurtleEgg.getMinimumEggs() Not looked at yet");
    }

    @Override
    default int getMaximumEggs() {
        throw new NotImplementedException("TurtleEgg.getMaximumEggs() Not looked at yet");
    }

    @Override
    default int getHatch() {
        throw new NotImplementedException("TurtleEgg.getHatch() Not looked at yet");
    }

    @Override
    default void setHatch(int hatch) {
        throw new NotImplementedException("TurtleEgg.setHatch(int) Not looked at yet");
    }

    @Override
    default int getMaximumHatch() {
        throw new NotImplementedException("TurtleEgg.getMaximumHatch() Not looked at yet");
    }
}
