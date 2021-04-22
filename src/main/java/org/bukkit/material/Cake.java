package org.bukkit.material;

import org.bukkit.Material;
import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.api.data.Keys;

@Deprecated
public class Cake extends MaterialData {

    public Cake() {
        super(BlockTypes.CAKE.get().defaultState());
    }

    public Cake(Material material) {
        super(material);
    }

    public Cake(Material material, byte data) {
        super(material, data);
        this.setSlicesEaten(data);
    }

    public int getSlicesEaten() {
        return 6 - this.getSlicesRemaining();
    }

    public int getSlicesRemaining() {
        return this.spongeValue.get(Keys.LAYER).get();
    }

    public void setSlicesEaten(int slice) {
        this.setSlicesRemaining(6 - slice);
    }

    public void setSlicesRemaining(int slice) {
        this.spongeValue = this.spongeValue.with(Keys.LAYER, slice).get();
    }
}
