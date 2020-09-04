package org.bonge.bukkit.r1_14.block.data.dedicated;

import org.bonge.Bonge;
import org.bonge.bukkit.r1_14.block.data.BongeBisected;
import org.bonge.bukkit.r1_14.block.data.BongeDirectional;
import org.bonge.bukkit.r1_14.block.data.BongeWaterLogged;
import org.bonge.bukkit.r1_14.block.data.IBongeBlockData;
import org.bukkit.block.data.type.Stairs;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.data.Keys;
import org.spongepowered.api.data.type.StairShape;
import org.spongepowered.api.data.type.StairShapes;

import java.io.IOException;

public interface BongeStairs extends IBongeBlockData, Stairs, BongeBisected, BongeDirectional, BongeWaterLogged {

    @Override
    @NotNull
    default Shape getShape() {
        try {
            return Bonge.getInstance().convert(Shape.class, this.getSpongeValue().get(Keys.STAIR_SHAPE).get());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    default void setShape(@NotNull Shape shape) {
        try {
            StairShape shape1 = Bonge.getInstance().convert(shape, StairShape.class);
            this.setSpongeValue(this.getSpongeValue().with(Keys.STAIR_SHAPE, shape1).get());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
