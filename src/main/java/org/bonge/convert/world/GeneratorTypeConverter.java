package org.bonge.convert.world;

import org.bonge.convert.Converter;
import org.bukkit.WorldType;
import org.spongepowered.api.world.GeneratorType;
import org.spongepowered.api.world.GeneratorTypes;

import java.io.IOException;

public class GeneratorTypeConverter implements Converter<WorldType, GeneratorType> {
    @Override
    public Class<GeneratorType> getSpongeClass() {
        return GeneratorType.class;
    }

    @Override
    public Class<WorldType> getBukkitClass() {
        return WorldType.class;
    }

    @Override
    public GeneratorType from(WorldType value) throws IOException {
        switch (value){
            case NORMAL: return GeneratorTypes.DEFAULT;
            case FLAT: return GeneratorTypes.FLAT;
            case VERSION_1_1: break;
            case LARGE_BIOMES: return GeneratorTypes.LARGE_BIOMES;
            case AMPLIFIED: return GeneratorTypes.AMPLIFIED;
            case CUSTOMIZED: break;
            case BUFFET: break;
            default: throw new IOException("Unknown Generator Type");
        }
        throw new IOException("Unknown Generator Type");
    }

    @Override
    public WorldType to(GeneratorType value) throws IOException{
        if(value.equals(GeneratorTypes.AMPLIFIED)){
            return WorldType.AMPLIFIED;
        }
        if(value.equals(GeneratorTypes.DEFAULT)){
            return WorldType.NORMAL;
        }
        if(value.equals(GeneratorTypes.FLAT)){
            return WorldType.FLAT;
        }
        if(value.equals(GeneratorTypes.LARGE_BIOMES)){
            return WorldType.LARGE_BIOMES;
        }
        throw new IOException("Unknown GeneratorType");
    }
}
