package org.bukkit.material;

import org.bonge.util.exception.NotImplementedException;
import org.bukkit.Material;
import org.bukkit.TreeSpecies;
import org.spongepowered.api.block.BlockTypes;

@Deprecated
public class Wood extends MaterialData {

    public Wood() {
        super(BlockTypes.OAK_PLANKS.get().defaultState());
    }

    public Wood(TreeSpecies species){
        this(Material.OAK_PLANKS, species);
    }

    public Wood(Material material) {
        super(material);
    }

    public Wood(Material material, TreeSpecies species){
        super(material);
        this.setSpecies(species);
    }

    public Wood(Material material, byte data) {
        super(material, data);
    }

    public TreeSpecies getSpecies(){
        throw new NotImplementedException("Wood.getSpecies() not got to yet");
    }

    public void setSpecies(TreeSpecies species){
        throw new NotImplementedException("Wood.setSpecies() not got to yet");
    }
}
