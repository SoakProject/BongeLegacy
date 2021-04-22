package org.bukkit.material;

import org.bonge.Bonge;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.api.data.Keys;

@Deprecated
public class Banner extends MaterialData implements Attachable {

    public Banner() {
        super(BlockTypes.WHITE_BANNER.get().defaultState());
    }

    public Banner(Material material) {
        super(material);
    }

    public Banner(Material material, byte data) {
        super(material, data);
    }

    @Override
    public @NotNull BlockFace getAttachedFace() {
        return Bonge.getInstance().convert(this.spongeValue.get(Keys.DIRECTION).get().opposite());
    }

    @Override
    public void setFacingDirection(@NotNull BlockFace face) {
        this.spongeValue = this.spongeValue.with(Keys.DIRECTION, Bonge.getInstance().convert(face).opposite()).get();
    }

    @Override
    public @NotNull BlockFace getFacing() {
        return getAttachedFace().getOppositeFace();
    }
}
