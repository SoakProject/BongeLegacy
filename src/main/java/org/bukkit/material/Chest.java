package org.bukkit.material;

import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.spongepowered.api.block.BlockState;
import org.spongepowered.api.block.BlockTypes;

@Deprecated
public class Chest extends DirectionalContainer {
    public Chest(BlockState value) {
        super(value);
    }

    public Chest(Material material) {
        super(material);
    }

    public Chest(Material material, byte data) {
        super(material, data);
    }

    public Chest() {
        this(BlockTypes.CHEST.get().defaultState());
    }

    public Chest(BlockFace face) {
        this();
        this.setFacingDirection(face);
    }


}
