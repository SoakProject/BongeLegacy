package org.bukkit.material;

import org.bonge.util.exception.NotImplementedException;
import org.bukkit.Material;
import org.bukkit.TreeSpecies;
import org.spongepowered.api.data.Keys;

@Deprecated
public class Leaves extends Wood {

    public Leaves() {
    }

    public Leaves(TreeSpecies species) {
        super(species);
    }

    public Leaves(TreeSpecies species, boolean decay) {
        super(species);
    }

    public Leaves(Material material) {
        super(material);
    }

    public Leaves(Material material, TreeSpecies species) {
        super(material, species);
    }

    public Leaves(Material material, TreeSpecies species, boolean decay) {
        super(material, species);
    }

    public Leaves(Material material, byte data) {
        super(material, data);
    }

    //TODO check this is correct
    public boolean isDecayable(){
        return this.spongeValue.get(Keys.DECAY_DISTANCE).get() != 0;
    }

    //TODO find default decay distance
    public void setDecayable(boolean check){
        int distance = (check) ? 4 : 0;
        this.spongeValue = this.spongeValue.with(Keys.DECAY_DISTANCE, distance).get();
    }

    public boolean isDecaying(){
        throw new NotImplementedException("Leaves.isDecaying() not got to yet");
    }

    public void setDecaying(boolean check){
        throw new NotImplementedException("Leaves.setDecaying(boolean) not got to yet");
    }


}
