package org.bukkit.material;

import org.bonge.Bonge;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.api.data.Keys;

@Deprecated
public class Hopper extends MaterialData implements Directional, Redstone {

    public Hopper() {
        super(BlockTypes.HOPPER.get().defaultState());
    }

    public Hopper(BlockFace face) {
        this(face, true);
    }

    public Hopper(BlockFace face, boolean isActive) {
        this();
        this.setFacingDirection(face);
        this.setActive(isActive);
    }

    public Hopper(Material material) {
        super(material);
    }

    public Hopper(Material material, byte data) {
        super(material, data);
    }

    //TODO find the correct key
    public boolean isActive() {
        return this.spongeValue.get(Keys.IS_POWERED).get();
    }

    public void setActive(boolean check) {
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
