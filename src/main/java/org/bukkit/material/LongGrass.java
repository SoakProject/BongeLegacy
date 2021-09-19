package org.bukkit.material;

import org.bonge.util.exception.NotImplementedException;
import org.bukkit.GrassSpecies;
import org.bukkit.Material;
import org.spongepowered.api.block.BlockType;
import org.spongepowered.api.block.BlockTypes;

@Deprecated
public class LongGrass extends MaterialData {

    public LongGrass() {
        super(BlockTypes.TALL_GRASS.get().defaultState());
    }

    public LongGrass(Material material) {
        super(material);
    }

    public LongGrass(Material material, byte data) {
        super(material, data);
    }

    public GrassSpecies getSpecies(){
        BlockType type = this.spongeValue.type();
        throw new NotImplementedException("LongGrass.getSpecies() there are a lot of types of grass ....");
    }

    public void setSpecies(GrassSpecies species){
        throw new NotImplementedException("LongGrass.setSpecies(GrassSpecies) there are a lot of types of grass...");
    }
}
