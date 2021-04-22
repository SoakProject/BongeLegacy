package org.bonge.bukkit.r1_16.block.data.dedicated;

import org.bonge.bukkit.r1_16.block.data.BongeDirectional;
import org.bonge.bukkit.r1_16.block.data.BongePowerable;
import org.bonge.bukkit.r1_16.block.data.IBongeBlockData;
import org.bukkit.block.data.type.Lectern;
import org.spongepowered.api.data.Keys;

public interface BongeLectern extends IBongeBlockData, Lectern, BongeDirectional, BongePowerable {

    @Override
    default boolean hasBook() {
        return this.getSpongeValue().get(Keys.ITEM_STACK_SNAPSHOT).isPresent();
    }
}
