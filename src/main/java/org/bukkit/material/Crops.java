package org.bukkit.material;

import org.bonge.util.exception.NotImplementedException;
import org.bukkit.CropState;
import org.bukkit.Material;
import org.spongepowered.api.block.BlockTypes;

@Deprecated
public class Crops extends MaterialData {

    //TODO check this is correct
    public Crops() {
        super(BlockTypes.GRASS.get().defaultState());
    }

    public Crops(CropState state){
        this(Material.GRASS, state);
    }

    public Crops(Material material, CropState state){
        super(material);
        setState(state);
    }

    public Crops(Material material) {
        super(material);
    }

    public Crops(Material material, byte data) {
        super(material, data);
    }

    public CropState getState(){
        throw new NotImplementedException("Crops.getState() not got to yet");
    }

    public void setState(CropState state){
        throw new NotImplementedException("Crops.setState(CropState) not got to yet");
    }
}
