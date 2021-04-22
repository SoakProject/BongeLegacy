package org.bukkit.material;

import org.bukkit.Material;
import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.api.data.Keys;

@Deprecated
public class Command extends MaterialData implements Redstone {

    public Command() {
        super(BlockTypes.COMMAND_BLOCK.get().defaultState());
    }

    public Command(Material material) {
        super(material);
    }

    public Command(Material material, byte data) {
        super(material, data);
    }

    public void setPowered(boolean powered) {
        this.spongeValue = this.spongeValue.with(Keys.IS_POWERED, powered).get();
    }

    @Override
    public boolean isPowered() {
        return this.spongeValue.get(Keys.IS_POWERED).get();
    }
}
