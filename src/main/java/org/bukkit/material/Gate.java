package org.bukkit.material;

import org.bonge.Bonge;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.api.data.Keys;

@Deprecated
public class Gate extends MaterialData implements Directional, Openable {

    public Gate() {
        super(BlockTypes.OAK_FENCE_GATE.get().defaultState());
    }

    public Gate(byte data) {
        super(Material.OAK_FENCE_GATE, data);
    }

    public Gate(Material material, byte data) {
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

    @Override
    public boolean isOpen() {
        return this.spongeValue.get(Keys.IS_OPEN).get();
    }

    @Override
    public void setOpen(boolean isOpen) {
        this.spongeValue = this.spongeValue.with(Keys.IS_OPEN, isOpen).get();
    }
}
