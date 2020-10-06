package org.bonge.bukkit.r1_15.block.data.dedicated;

import org.bonge.Bonge;
import org.bonge.bukkit.r1_15.block.data.BongeDirectional;
import org.bonge.bukkit.r1_15.block.data.BongePowerable;
import org.bonge.bukkit.r1_15.block.data.IBongeBlockData;
import org.bukkit.block.data.type.Comparator;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.data.Keys;
import org.spongepowered.api.data.type.ComparatorMode;

import java.io.IOException;

public interface BongeComparator extends IBongeBlockData, Comparator, BongeDirectional, BongePowerable {

    @Override
    @NotNull
    default Mode getMode() {
        try {
            return Bonge.getInstance().convert(Mode.class, this.getSpongeValue().get(Keys.COMPARATOR_MODE).get());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    default void setMode(@NotNull Mode mode) {
        try {
            ComparatorMode mode2 = Bonge.getInstance().convert(mode, ComparatorMode.class);
            this.setSpongeValue(this.getSpongeValue().with(Keys.COMPARATOR_MODE, mode2).get());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
