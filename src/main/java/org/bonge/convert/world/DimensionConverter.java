package org.bonge.convert.world;

import org.bonge.convert.Converter;
import org.bukkit.World;
import org.spongepowered.api.world.DimensionType;
import org.spongepowered.api.world.DimensionTypes;

import java.io.IOException;

public class DimensionConverter implements Converter<World.Environment, DimensionType> {
    @Override
    public Class<DimensionType> getSpongeClass() {
        return DimensionType.class;
    }

    @Override
    public Class<World.Environment> getBukkitClass() {
        return World.Environment.class;
    }

    @Override
    public DimensionType from(World.Environment value) throws IOException {
        if(value.equals(World.Environment.NORMAL)){
            return DimensionTypes.OVERWORLD;
        }else if(value.equals(World.Environment.NETHER)){
            return DimensionTypes.NETHER;
        }else if(value.equals(World.Environment.THE_END)){
            return DimensionTypes.THE_END;
        }
        throw new IOException("Unknown dimension");
    }

    @Override
    public World.Environment to(DimensionType value) throws IOException{
        if(value.equals(DimensionTypes.OVERWORLD)){
            return World.Environment.NORMAL;
        }else if(value.equals(DimensionTypes.NETHER)){
            return World.Environment.NETHER;
        }else if(value.equals(DimensionTypes.THE_END)){
            return World.Environment.THE_END;
        }
        throw new IOException("Unknown dimension");
    }
}
