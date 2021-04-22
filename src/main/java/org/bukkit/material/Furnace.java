package org.bukkit.material;

import org.bukkit.Material;
import org.bukkit.block.BlockFace;

@Deprecated
public class Furnace extends FurnaceAndDispenser{

    public Furnace(){
        this(Material.FURNACE);
    }

    public Furnace(BlockFace face){
        this();
        this.setFacingDirection(face);
    }

    public Furnace(Material type) {
        super(type);
    }

    public Furnace(Material type, byte data) {
        super(type, data);
    }
}
