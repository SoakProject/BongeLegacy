package org.bonge.convert.world;

import org.bonge.convert.Converter;
import org.bukkit.WorldType;
import org.spongepowered.api.world.gen.GeneratorModifierType;
import org.spongepowered.api.world.gen.GeneratorModifierTypes;

import java.io.IOException;

public class GeneratorTypeConverter implements Converter<WorldType, GeneratorModifierType> {
    @Override
    public Class<GeneratorModifierType> getSpongeClass() {
        return GeneratorModifierType.class;
    }

    @Override
    public Class<WorldType> getBukkitClass() {
        return WorldType.class;
    }

    @Override
    public GeneratorModifierType from(WorldType value) throws IOException {
        switch (value){
            case NORMAL: return GeneratorModifierTypes.NONE.get();
            case FLAT: return GeneratorModifierTypes.FLAT.get();
            case VERSION_1_1: break;
            case LARGE_BIOMES: return GeneratorModifierTypes.LARGE_BIOMES.get();
            case AMPLIFIED: return GeneratorModifierTypes.AMPLIFIED.get();
            case CUSTOMIZED: break;
            case BUFFET: break;
            default: throw new IOException("Unknown Generator Type");
        }
        throw new IOException("Unknown Generator Type");
    }

    @Override
    public WorldType to(GeneratorModifierType value) throws IOException{
        if(value.equals(GeneratorModifierTypes.AMPLIFIED.get())){
            return WorldType.AMPLIFIED;
        }
        if(value.equals(GeneratorModifierTypes.NONE.get())){
            return WorldType.NORMAL;
        }
        if(value.equals(GeneratorModifierTypes.FLAT.get())){
            return WorldType.FLAT;
        }
        if(value.equals(GeneratorModifierTypes.LARGE_BIOMES.get())){
            return WorldType.LARGE_BIOMES;
        }
        throw new IOException("Unknown GeneratorType");
    }
}
