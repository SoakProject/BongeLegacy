package org.bonge.convert.block;

import org.bonge.convert.Converter;
import org.bukkit.block.data.type.Stairs;
import org.spongepowered.api.data.type.StairShape;
import org.spongepowered.api.data.type.StairShapes;

import java.io.IOException;

public class BongeStairsShapeConverter implements Converter<Stairs.Shape, StairShape> {
    @Override
    public Class<StairShape> getSpongeClass() {
        return StairShape.class;
    }

    @Override
    public Class<Stairs.Shape> getBukkitClass() {
        return Stairs.Shape.class;
    }

    @Override
    public StairShape from(Stairs.Shape value) throws IOException {
        switch (value){
            case STRAIGHT: return StairShapes.STRAIGHT.get();
            case INNER_LEFT: return StairShapes.INNER_LEFT.get();
            case INNER_RIGHT: return StairShapes.INNER_RIGHT.get();
            case OUTER_LEFT: return StairShapes.OUTER_LEFT.get();
            case OUTER_RIGHT: return StairShapes.OUTER_RIGHT.get();
            default: throw new IOException("Unknown Bukkit Stairs.Shape." + value.name());
        }
    }

    @Override
    public Stairs.Shape to(StairShape value) throws IOException {
        if(value.equals(StairShapes.INNER_LEFT.get())){
            return Stairs.Shape.INNER_LEFT;
        }
        if(value.equals(StairShapes.INNER_RIGHT.get())){
            return Stairs.Shape.INNER_RIGHT;
        }
        if(value.equals(StairShapes.OUTER_LEFT.get())){
            return Stairs.Shape.OUTER_LEFT;
        }
        if(value.equals(StairShapes.OUTER_RIGHT.get())){
            return Stairs.Shape.OUTER_RIGHT;
        }
        throw new IOException("Unknown Sponge StairShape." + value.toString());
    }
}
