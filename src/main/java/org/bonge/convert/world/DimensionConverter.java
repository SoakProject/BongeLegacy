package org.bonge.convert.world;

import org.bonge.convert.Converter;
import org.bukkit.World;
import org.spongepowered.api.world.dimension.DimensionType;
import org.spongepowered.api.world.dimension.DimensionTypes;

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
            return DimensionTypes.OVERWORLD.get();
        }else if(value.equals(World.Environment.NETHER)){
            return DimensionTypes.THE_NETHER.get();
        }else if(value.equals(World.Environment.THE_END)){
            return DimensionTypes.THE_END.get();
        }
        throw new IOException("Unknown dimension");
    }

    @Override
    public World.Environment to(DimensionType value) throws IOException{
        if(value.equals(DimensionTypes.OVERWORLD.get())){
            return World.Environment.NORMAL;
        }else if(value.equals(DimensionTypes.THE_NETHER.get())){
            return World.Environment.NETHER;
        }else if(value.equals(DimensionTypes.THE_END.get())){
            return World.Environment.THE_END;
        }
        throw new IOException("Unknown dimension");
    }
}
