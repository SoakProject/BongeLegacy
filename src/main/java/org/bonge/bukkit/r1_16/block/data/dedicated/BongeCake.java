package org.bonge.bukkit.r1_16.block.data.dedicated;

import org.bonge.bukkit.r1_16.block.data.IBongeBlockData;
import org.bonge.util.exception.NotImplementedException;
import org.bukkit.block.data.type.Cake;

public interface BongeCake extends IBongeBlockData, Cake {

    @Override
    default int getBites() {
        throw new NotImplementedException("Cake.getBites() Not got to yet");
    }

    @Override
    default void setBites(int bites) {
        throw new NotImplementedException("Cake.setBites(int) Not got to yet");
    }

    @Override
    default int getMaximumBites() {
        throw new NotImplementedException("Cake.getMaximumBites() Not got to yet");
    }
}
