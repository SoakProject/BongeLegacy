package org.bukkit.material;

import org.bonge.Bonge;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.data.Keys;

@Deprecated
public class Ladder extends SimpleAttachableMaterialData {

    public Ladder() {
        this(Material.LADDER);
    }

    public Ladder(Material type) {
        super(type);
    }

    @Deprecated
    public Ladder(Material type, byte data) {
        super(type, data);
    }

    @Override
    public @NotNull BlockFace getAttachedFace() {
        return Bonge.getInstance().convert(this.spongeValue.get(Keys.DIRECTION).get().opposite());
    }

    @Override
    public void setFacingDirection(@NotNull BlockFace face) {
        this.spongeValue = this.spongeValue.with(Keys.DIRECTION, Bonge.getInstance().convert(face)).get();
    }
}
