package org.bukkit.material;

import org.bonge.Bonge;
import org.bukkit.Material;
import org.bukkit.TreeSpecies;
import org.bukkit.block.BlockFace;
import org.spongepowered.api.data.Keys;

@Deprecated
public class Tree extends Wood {

    public Tree() {
        super(Material.OAK_WOOD);
    }

    public Tree(TreeSpecies species) {
        super(Material.OAK_WOOD, species);
    }

    public Tree(Material material) {
        super(material);
    }

    public Tree(Material material, TreeSpecies species) {
        super(material, species);
    }

    public Tree(Material material, byte data) {
        super(material, data);
    }

    public BlockFace getDirection(){
        return Bonge.getInstance().convert(this.spongeValue.get(Keys.DIRECTION).get());
    }

    public void setDirection(BlockFace face){
        this.spongeValue = this.spongeValue.with(Keys.DIRECTION, Bonge.getInstance().convert(face)).get();
    }
}
