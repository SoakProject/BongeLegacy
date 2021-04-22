package org.bukkit.material;

import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.spongepowered.api.data.Keys;

@Deprecated
public class RedstoneTorch extends Torch implements Redstone{

    public RedstoneTorch() {
        this(Material.REDSTONE_TORCH);
    }

    public RedstoneTorch(Material type, BlockFace direction) {
        super(type, direction);
    }

    public RedstoneTorch(Material type) {
        super(type);
    }

    public RedstoneTorch(Material type, byte data) {
        super(type, data);
    }

    @Override
    public boolean isPowered() {
        return this.spongeValue.get(Keys.IS_POWERED).get();
    }
}
