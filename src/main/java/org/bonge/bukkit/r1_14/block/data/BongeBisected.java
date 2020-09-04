package org.bonge.bukkit.r1_14.block.data;

import org.bonge.Bonge;
import org.bukkit.block.data.Bisected;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.data.Keys;
import org.spongepowered.api.data.type.SlabPortion;

import java.io.IOException;

public interface BongeBisected extends IBongeBlockData, Bisected {

    @Override
    @NotNull
    default Half getHalf() {
        try {
            return Bonge.getInstance().convert(Half.class, this.getSpongeValue().get(Keys.SLAB_PORTION).get());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    default void setHalf(@NotNull Half half) {
        try {
            SlabPortion portion = Bonge.getInstance().convert(half, SlabPortion.class);
            this.setSpongeValue(this.getSpongeValue().with(Keys.SLAB_PORTION, portion).get());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
