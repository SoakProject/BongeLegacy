package org.bukkit.material;

import org.bonge.Bonge;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.api.data.Keys;

@Deprecated
public class Diode extends MaterialData implements Directional, Redstone {

    public Diode() {
        super(BlockTypes.REPEATER.get().defaultState());
    }

    public Diode(BlockFace face) {
        this(face, 0);
    }

    public Diode(BlockFace face, int delay) {
        this(face, delay, false);
    }

    public Diode(BlockFace face, int delay, boolean state) {
        this();
        this.setFacingDirection(face);
        this.setDelay(delay);
        this.setPowered(state);
    }

    public Diode(Material material) {
        super(material);
    }

    public Diode(Material material, byte data) {
        super(material, data);
    }

    public void setPowered(boolean powered) {
        this.spongeValue = this.spongeValue.with(Keys.IS_POWERED, powered).get();
    }

    public void setDelay(int delay) {
        this.spongeValue = this.spongeValue.with(Keys.REDSTONE_DELAY, delay).get();
    }

    public int getDelay() {
        return this.spongeValue.get(Keys.REDSTONE_DELAY).get();
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
