package org.bonge.bukkit.r1_16.block.data.dedicated;

import org.bonge.bukkit.r1_16.block.data.IBongeBlockData;
import org.bonge.util.exception.NotImplementedException;
import org.bukkit.block.data.type.Bed;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.data.Keys;

public interface BongeBed extends IBongeBlockData, Bed {

    @Override
    @NotNull
    default Part getPart() {
        throw new NotImplementedException("Bed.getPart() not got to yet");
    }

    @Override
    default void setPart(@NotNull Part part) {
        throw new NotImplementedException("Bed.setPart(Part) not got to yet");
    }

    @Override
    default boolean isOccupied() {
        return this.getSpongeValue().get(Keys.IS_OCCUPIED).get();
    }
}
