package org.bukkit.material;

import org.bonge.Bonge;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.api.data.Keys;

@Deprecated
public class Bed extends MaterialData implements Directional {

    public Bed() {
        super(BlockTypes.RED_BED.get().defaultState());
    }

    public Bed(Material material) {
        super(material);
    }

    public Bed(Material material, byte data) {
        super(material, data);
    }

    @Override
    public void setFacingDirection(@NotNull BlockFace face) {
        this.spongeValue = this.spongeValue.with(Keys.DIRECTION, Bonge.getInstance().convert(face)).get();
    }

    @Override
    public @NotNull BlockFace getFacing() {
        return Bonge.getInstance().convert(this.spongeValue.get(Keys.DIRECTION).get());
    }
}
