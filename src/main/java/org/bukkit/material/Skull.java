package org.bukkit.material;

import org.bonge.Bonge;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.api.data.Keys;

@Deprecated
public class Skull extends MaterialData implements Directional {

    public Skull() {
        super(BlockTypes.SKELETON_SKULL.get().defaultState());
    }

    public Skull(BlockFace face) {
        this();
        this.setFacingDirection(face);
    }

    public Skull(Material material) {
        super(material);
    }

    public Skull(Material material, byte data) {
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
