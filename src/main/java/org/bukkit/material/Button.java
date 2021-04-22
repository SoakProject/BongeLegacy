package org.bukkit.material;

import org.bukkit.Material;
import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.api.data.Keys;

@Deprecated
public class Button extends MaterialData implements Redstone {
    public Button() {
        super(BlockTypes.STONE_BUTTON.get().defaultState());
    }

    public Button(Material material) {
        super(material);
    }

    public Button(Material material, byte data) {
        super(material, data);
    }

    @Override
    public boolean isPowered() {
        return this.spongeValue.get(Keys.IS_POWERED).get();
    }
}
