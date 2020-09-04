package org.bonge.convert.block;

import org.bonge.convert.Converter;
import org.bukkit.block.data.Bisected;
import org.spongepowered.api.data.type.SlabPortion;
import org.spongepowered.api.data.type.SlabPortions;

import java.io.IOException;

public class BlockHalfConverter implements Converter<Bisected.Half, SlabPortion> {
    @Override
    public Class<SlabPortion> getSpongeClass() {
        return SlabPortion.class;
    }

    @Override
    public Class<Bisected.Half> getBukkitClass() {
        return Bisected.Half.class;
    }

    @Override
    public SlabPortion from(Bisected.Half value) throws IOException {
        switch (value){
            case TOP:
                return SlabPortions.TOP.get();
            case BOTTOM:
                return SlabPortions.BOTTOM.get();
            default: throw new IOException("Unknown Bukkit Half as " + value);
        }
    }

    @Override
    public Bisected.Half to(SlabPortion value) throws IOException {
        if(value.equals(SlabPortions.TOP.get())){
            return Bisected.Half.TOP;
        }else if(value.equals(SlabPortions.BOTTOM.get())){
            return Bisected.Half.BOTTOM;
        }else if(value.equals(SlabPortions.FULL.get())){
            throw new IOException("Have not got round to converting Full slab to Bisected.Half");
        }
        throw new IOException("Unknown half");
    }
}
