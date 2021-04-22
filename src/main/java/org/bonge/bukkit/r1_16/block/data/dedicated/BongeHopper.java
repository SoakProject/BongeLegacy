package org.bonge.bukkit.r1_16.block.data.dedicated;

import org.bonge.bukkit.r1_16.block.data.BongeDirectional;
import org.bonge.bukkit.r1_16.block.data.IBongeBlockData;
import org.bonge.util.exception.NotImplementedException;
import org.bukkit.block.data.type.Hopper;

public interface BongeHopper extends IBongeBlockData, Hopper, BongeDirectional {

    @Override
    default boolean isEnabled() {
        throw new NotImplementedException("Hopper.isEnabled() Not got to yet");
    }

    @Override
    default void setEnabled(boolean enabled) {
        throw new NotImplementedException("Hopper.setEnabled(boolean) Not got to yet");
    }
}
