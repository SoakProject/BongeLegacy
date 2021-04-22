package org.bukkit.material;

import org.bukkit.Material;
import org.bukkit.TreeSpecies;
import org.spongepowered.api.data.Keys;
import org.spongepowered.api.data.type.SlabPortions;

@Deprecated
public class WoodenStep extends Wood {

    public WoodenStep() {
        super(Material.OAK_SLAB);
    }

    public WoodenStep(TreeSpecies species) {
        this(Material.OAK_SLAB, species);
    }

    public WoodenStep(TreeSpecies species, boolean inv){
        this(species);
        this.setInverted(inv);
    }

    public WoodenStep(Material material) {
        super(material);
    }

    public WoodenStep(Material material, TreeSpecies species) {
        super(material, species);
    }

    public WoodenStep(Material material, byte data) {
        super(material, data);
    }

    public boolean isInverted(){
        return this.spongeValue.get(Keys.SLAB_PORTION).get().equals(SlabPortions.TOP.get());
    }

    public void setInverted(boolean portion){
        this.spongeValue = this.spongeValue.with(Keys.SLAB_PORTION, portion ? SlabPortions.TOP.get() : SlabPortions.BOTTOM.get()).get();
    }


}
