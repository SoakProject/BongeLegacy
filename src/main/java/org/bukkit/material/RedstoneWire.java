package org.bukkit.material;

import org.bukkit.Material;
import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.api.data.Keys;

@Deprecated
public class RedstoneWire extends MaterialData implements Redstone {

    public RedstoneWire() {
        super(BlockTypes.REDSTONE_WIRE.get().defaultState());
    }

    public RedstoneWire(Material material) {
        super(material);
    }

    public RedstoneWire(Material material, byte data) {
        super(material, data);
    }

    @Override
    public boolean isPowered() {
        return this.spongeValue.get(Keys.IS_POWERED).get();
    }
}
