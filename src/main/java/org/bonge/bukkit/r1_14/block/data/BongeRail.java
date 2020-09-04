package org.bonge.bukkit.r1_14.block.data;

import org.bonge.Bonge;
import org.bukkit.block.data.Rail;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.data.Keys;
import org.spongepowered.api.data.type.RailDirection;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public interface BongeRail extends IBongeBlockData, Rail {

    @Override
    default @NotNull Shape getShape(){
        try {
            return Bonge.getInstance().convert(Shape.class, this.getSpongeValue().get(Keys.RAIL_DIRECTION.get()));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    default void setShape(@NotNull Shape shape){
        try {
            RailDirection direction = Bonge.getInstance().convert(shape, RailDirection.class);
            this.setSpongeValue(this.getSpongeValue().with(Keys.RAIL_DIRECTION, direction).get());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    default @NotNull Set<Shape> getShapes(){
        Set<Shape> shapes = new HashSet<>();
        for(Shape shape : Shape.values()){
            try {
                RailDirection direction = Bonge.getInstance().convert(shape, RailDirection.class);
                if (this.getSpongeValue().with(Keys.RAIL_DIRECTION, direction).isPresent()){
                    shapes.add(shape);
                }
            } catch (IOException ignore) {
            }
        }
        return shapes;
    }
}
