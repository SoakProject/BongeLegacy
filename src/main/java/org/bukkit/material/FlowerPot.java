package org.bukkit.material;

import org.bonge.util.exception.NotImplementedException;
import org.bukkit.Material;
import org.spongepowered.api.block.BlockState;
import org.spongepowered.api.block.BlockType;

@Deprecated
public class FlowerPot extends MaterialData {

    public FlowerPot(BlockState value) {
        super(value);
    }

    public FlowerPot(Material material) {
        super(material);
    }

    public FlowerPot(Material material, byte data) {
        super(material, data);
    }

    public MaterialData getContents(){
        BlockType type = this.spongeValue.type();
        throw new NotImplementedException("FlowerPot.getContents() There are a lot of flower types...");
    }

    public MaterialData setContents(MaterialData data){
        throw new NotImplementedException("FlowerPot.setContents(MaterialData) There are a lot of flower types ...");
    }
}
