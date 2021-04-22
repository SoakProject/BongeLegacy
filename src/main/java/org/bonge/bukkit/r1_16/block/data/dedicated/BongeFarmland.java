package org.bonge.bukkit.r1_16.block.data.dedicated;

import org.bonge.bukkit.r1_16.block.data.IBongeBlockData;
import org.bonge.util.exception.NotImplementedException;
import org.bukkit.block.data.type.Farmland;
import org.spongepowered.api.data.Keys;

public interface BongeFarmland extends IBongeBlockData, Farmland {

    @Override
    default int getMoisture() {
        return this.getSpongeValue().get(Keys.MOISTURE).get();
    }

    @Override
    default void setMoisture(int moisture) {
        if(moisture > this.getMaximumMoisture()){
            return;
        }
        this.setSpongeValue(this.getSpongeValue().with(Keys.MOISTURE, moisture).get());
    }

    @Override
    default int getMaximumMoisture() {
        //return this.getSpongeValue().getValue(Keys.MOISTURE).get().getMaxValue();
        throw new NotImplementedException("Moisture.getMaximumLevel() no Sponge alternative");

    }
}
