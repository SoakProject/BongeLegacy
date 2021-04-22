package org.bukkit.material;

import org.bonge.Bonge;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.data.Keys;

@Deprecated
public class Torch extends SimpleAttachableMaterialData {

    public Torch(){
        this(Material.TORCH);
    }

    public Torch(Material type, BlockFace direction) {
        super(type, direction);
    }

    public Torch(Material type) {
        super(type);
    }

    public Torch(Material type, byte data) {
        super(type, data);
    }

    @Override
    public @NotNull BlockFace getAttachedFace() {
        return Bonge.getInstance().convert(this.spongeValue.get(Keys.DIRECTION).get());
    }

    @Override
    public void setFacingDirection(@NotNull BlockFace face) {
        this.spongeValue = this.spongeValue.with(Keys.DIRECTION, Bonge.getInstance().convert(face)).get();
    }
}
