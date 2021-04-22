package org.bonge.bukkit.r1_16.block.data.dedicated;

import org.bonge.bukkit.r1_16.block.data.BongeLightable;
import org.bonge.bukkit.r1_16.block.data.BongeWaterLogged;
import org.bonge.util.exception.NotImplementedException;
import org.bukkit.block.data.type.Campfire;

public interface BongeCampfire extends Campfire, BongeLightable, BongeWaterLogged {

    @Override
    default boolean isSignalFire() {
        throw new NotImplementedException("Campfire.isSignalFire() not got to yet");
    }

    @Override
    default void setSignalFire(boolean signalFire) {
        throw new NotImplementedException("Campfire.setSignalFire(boolean) not got to yet");
    }
}
