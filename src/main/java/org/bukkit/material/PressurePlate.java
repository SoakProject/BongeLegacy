package org.bukkit.material;

import org.bukkit.Material;
import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.api.data.Keys;

@Deprecated
public class PressurePlate extends MaterialData implements PressureSensor {

    public PressurePlate() {
        super(BlockTypes.OAK_PRESSURE_PLATE.get().defaultState());
    }

    public PressurePlate(Material material) {
        super(material);
    }

    public PressurePlate(Material material, byte data) {
        super(material, data);
    }

    @Override
    public boolean isPressed() {
        return this.spongeValue.get(Keys.IS_POWERED).get();
    }
}
