package org.bukkit.material;

import org.bonge.util.exception.NotImplementedException;
import org.bukkit.Material;
import org.bukkit.SandstoneType;
import org.spongepowered.api.block.BlockState;
import org.spongepowered.api.block.BlockTypes;

@Deprecated
public class Sandstone extends MaterialData {
    public Sandstone() {
        super(BlockTypes.SANDSTONE.get().defaultState());
    }

    public Sandstone(SandstoneType type){
        this();
        this.setType(type);
    }

    public Sandstone(Material material) {
        super(material);
    }

    public Sandstone(Material material, byte data) {
        super(material, data);
    }

    public SandstoneType getType(){
        throw new NotImplementedException("Sandstone.getType() not got to yet");
    }

    public void setType(SandstoneType type){
        throw new NotImplementedException("Sandstone.setType(SandstoneType) not got to yet");
    }
}
