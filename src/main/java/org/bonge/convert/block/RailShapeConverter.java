package org.bonge.convert.block;

import org.bonge.convert.Converter;
import org.bukkit.block.data.Rail;
import org.spongepowered.api.data.type.RailDirection;
import org.spongepowered.api.data.type.RailDirections;

import java.io.IOException;

public class RailShapeConverter implements Converter<Rail.Shape, RailDirection> {

    @Override
    public Class<RailDirection> getSpongeClass() {
        return RailDirection.class;
    }

    @Override
    public Class<Rail.Shape> getBukkitClass() {
        return Rail.Shape.class;
    }

    @Override
    public RailDirection from(Rail.Shape value) throws IOException {
        switch (value){
            case NORTH_SOUTH: return RailDirections.NORTH_SOUTH.get();
            case EAST_WEST: return RailDirections.EAST_WEST.get();
            case ASCENDING_EAST: return RailDirections.ASCENDING_EAST.get();
            case ASCENDING_WEST: return RailDirections.ASCENDING_WEST.get();
            case ASCENDING_NORTH: return RailDirections.ASCENDING_NORTH.get();
            case ASCENDING_SOUTH: return RailDirections.ASCENDING_SOUTH.get();
            case SOUTH_EAST: return RailDirections.SOUTH_EAST.get();
            case SOUTH_WEST: return RailDirections.SOUTH_WEST.get();
            case NORTH_WEST: return RailDirections.NORTH_WEST.get();
            case NORTH_EAST: return RailDirections.NORTH_EAST.get();
            default: throw new IOException("Unknown convention for Bukkit '" + value.name() + "'");
        }
    }

    @Override
    public Rail.Shape to(RailDirection value) throws IOException {
        if(value.equals(RailDirections.SOUTH_EAST.get())){
            return Rail.Shape.SOUTH_EAST;
        }
        if(value.equals(RailDirections.SOUTH_WEST.get())){
            return Rail.Shape.SOUTH_WEST;
        }
        if(value.equals(RailDirections.NORTH_EAST.get())){
            return Rail.Shape.NORTH_EAST;
        }
        if(value.equals(RailDirections.NORTH_WEST.get())){
            return Rail.Shape.NORTH_WEST;
        }
        if(value.equals(RailDirections.EAST_WEST.get())){
            return Rail.Shape.EAST_WEST;
        }
        if(value.equals(RailDirections.NORTH_SOUTH.get())){
            return Rail.Shape.NORTH_SOUTH;
        }
        if(value.equals(RailDirections.ASCENDING_SOUTH.get())){
            return Rail.Shape.ASCENDING_SOUTH;
        }
        if(value.equals(RailDirections.ASCENDING_NORTH.get())){
            return Rail.Shape.ASCENDING_NORTH;
        }
        if(value.equals(RailDirections.ASCENDING_WEST.get())){
            return Rail.Shape.ASCENDING_WEST;
        }
        if(value.equals(RailDirections.ASCENDING_EAST.get())){
            return Rail.Shape.ASCENDING_EAST;
        }
        throw new IOException("Unknown conversion for Sponge '" + value.toString() + "'");
    }
}
