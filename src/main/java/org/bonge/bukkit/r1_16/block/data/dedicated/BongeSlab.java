package org.bonge.bukkit.r1_16.block.data.dedicated;

import org.bonge.Bonge;
import org.bonge.bukkit.r1_16.block.data.BongeWaterLogged;
import org.bonge.bukkit.r1_16.block.data.IBongeBlockData;
import org.bukkit.block.data.type.Slab;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.data.Keys;
import org.spongepowered.api.data.type.PortionType;

import java.io.IOException;

public interface BongeSlab extends IBongeBlockData, Slab, BongeWaterLogged {

    @Override
    @NotNull
    default Type getType() {
        try {
            return Bonge.getInstance().convert(Type.class, this.getSpongeValue().get(Keys.PORTION_TYPE).get());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    default void setType(@NotNull Type type) {
        try {
            this.setSpongeValue(this.getSpongeValue().with(Keys.PORTION_TYPE, Bonge.getInstance().convert(type, PortionType.class)).get());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
