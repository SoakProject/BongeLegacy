package org.bukkit.material;

import org.bonge.Bonge;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.data.Keys;

@Deprecated
public class PistonBaseMaterial extends MaterialData implements Directional, Redstone {

    public PistonBaseMaterial(Material material) {
        super(material);
    }

    public PistonBaseMaterial(Material material, byte data) {
        super(material, data);
    }

    public boolean isSticky() {
        return this.spongeValue.type().equals(Material.STICKY_PISTON);
    }

    public void setPowered(boolean check) {
        this.spongeValue = this.spongeValue.with(Keys.IS_POWERED, check).get();
    }

    @Override
    public void setFacingDirection(@NotNull BlockFace face) {
        this.spongeValue = this.spongeValue.with(Keys.DIRECTION, Bonge.getInstance().convert(face)).get();
    }

    @Override
    public @NotNull BlockFace getFacing() {
        return Bonge.getInstance().convert(this.spongeValue.get(Keys.DIRECTION).get());
    }

    @Override
    public boolean isPowered() {
        return this.spongeValue.get(Keys.IS_POWERED).get();
    }
}
