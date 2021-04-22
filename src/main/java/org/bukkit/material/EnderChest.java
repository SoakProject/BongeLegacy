package org.bukkit.material;

import org.bukkit.Material;
import org.bukkit.block.BlockFace;

@Deprecated
public class EnderChest extends DirectionalContainer{

    public EnderChest() {
        super(Material.ENDER_CHEST);
    }

    public EnderChest(BlockFace face){
        this();
        this.setFacingDirection(face);
    }

    public EnderChest(Material material) {
        super(material);
    }

    public EnderChest(Material material, byte data) {
        super(material, data);
    }
}
