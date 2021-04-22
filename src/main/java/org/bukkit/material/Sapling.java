package org.bukkit.material;

import org.bonge.util.exception.NotImplementedException;
import org.bukkit.Material;
import org.bukkit.TreeSpecies;

@Deprecated
public class Sapling extends Wood {

    public Sapling() {
        this(Material.OAK_SAPLING);
    }

    public Sapling(TreeSpecies species) {
        super(Material.OAK_SAPLING, species);
    }

    public Sapling(TreeSpecies species, boolean grow) {
        super(Material.OAK_SAPLING, species);
    }

    public Sapling(Material material) {
        super(material);
    }

    public Sapling(Material material, TreeSpecies species) {
        super(material, species);
    }

    public Sapling(Material material, TreeSpecies species, boolean grow) {
        super(material, species);
    }

    public Sapling(Material material, byte data) {
        super(material, data);
    }

    public boolean isInstantGrowable(){
        throw new NotImplementedException("Sapling.isInstantGrowable() Not got to yet");
    }

    public void setIsInstantGrowable(boolean growable){
        throw new NotImplementedException("Sapling.setIsInstantGrowable(boolean) Not got to yet");
    }
}
