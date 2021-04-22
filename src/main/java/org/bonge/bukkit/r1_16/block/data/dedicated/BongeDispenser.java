package org.bonge.bukkit.r1_16.block.data.dedicated;

import org.bonge.bukkit.r1_16.block.data.BongeDirectional;
import org.bonge.util.exception.NotImplementedException;
import org.bukkit.block.data.type.Dispenser;

public interface BongeDispenser extends Dispenser, BongeDirectional {

    @Override
    default boolean isTriggered() {
        throw new NotImplementedException("Dispenser.isTriggered() Not got to yet");
    }

    @Override
    default void setTriggered(boolean triggered) {
        throw new NotImplementedException("Dispenser.setTriggered(boolean) Not got to yet");
    }
}
