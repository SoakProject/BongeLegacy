package org.bukkit.material;

import org.bukkit.Material;
import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.api.data.Keys;

@Deprecated
public class Cauldron extends MaterialData {

    public Cauldron() {
        super(BlockTypes.CAULDRON.get().defaultState());
    }

    public Cauldron(Material material) {
        super(material);
    }

    public Cauldron(Material material, byte data) {
        super(material, data);
    }

    public boolean isFull() {
        return this.spongeValue.get(Keys.FLUID_LEVEL).get() == 3;
    }

    public boolean isEmpty() {
        return this.spongeValue.get(Keys.FLUID_LEVEL).get() == 0;
    }
}
