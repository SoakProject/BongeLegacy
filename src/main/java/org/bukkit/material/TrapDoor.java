package org.bukkit.material;

import org.bonge.Bonge;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.data.Keys;
import org.spongepowered.api.data.type.SlabPortions;

@Deprecated
public class TrapDoor extends SimpleAttachableMaterialData implements Openable{

    public TrapDoor(){
        this(Material.OAK_TRAPDOOR);
    }

    public TrapDoor(Material type, BlockFace direction) {
        super(type, direction);
    }

    public TrapDoor(Material type) {
        super(type);
    }

    public TrapDoor(Material type, byte data) {
        super(type, data);
    }

    public boolean isInverted(){
        return this.spongeValue.get(Keys.SLAB_PORTION).get().equals(SlabPortions.TOP.get());
    }

    public void setInverted(boolean test){
        this.spongeValue = this.spongeValue.with(Keys.SLAB_PORTION, test ? SlabPortions.TOP.get() : SlabPortions.BOTTOM.get()).get();
    }

    @Override
    public @NotNull BlockFace getAttachedFace() {
        return Bonge.getInstance().convert(this.spongeValue.get(Keys.DIRECTION).get()).getOppositeFace();
    }

    @Override
    public void setFacingDirection(@NotNull BlockFace face) {
        this.spongeValue = this.spongeValue.with(Keys.DIRECTION, Bonge.getInstance().convert(face)).get();
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
