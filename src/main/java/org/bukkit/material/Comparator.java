package org.bukkit.material;


import org.bonge.Bonge;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.api.data.Keys;
import org.spongepowered.api.data.type.ComparatorModes;

@Deprecated
public class Comparator extends MaterialData implements Directional, Redstone {

    public Comparator() {
        super(BlockTypes.COMPARATOR.get().defaultState());
    }

    public Comparator(BlockFace face) {
        this(face, false);
    }

    public Comparator(BlockFace face, boolean isSubtraction) {
        this(face, isSubtraction, true);
    }

    public Comparator(BlockFace face, boolean isSubtraction, boolean state) {
        this();
        this.setFacingDirection(face);
        this.setSubtractionMode(isSubtraction);
        this.setPowered(state);
    }

    public Comparator(Material material) {
        super(material);
    }

    public Comparator(Material material, byte data) {
        super(material, data);
    }

    public boolean isSubtractionMode() {
        return this.spongeValue.get(Keys.COMPARATOR_MODE).get().equals(ComparatorModes.SUBTRACT);
    }

    public void setSubtractionMode(boolean sub) {
        this.spongeValue = this.spongeValue.with(Keys.COMPARATOR_MODE, sub ? ComparatorModes.SUBTRACT.get() : ComparatorModes.COMPARE.get()).get();
    }

    public void setPowered(boolean state) {
        this.spongeValue = this.spongeValue.with(Keys.IS_POWERED, state).get();
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
