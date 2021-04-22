package org.bukkit.material;

import org.bonge.Bonge;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.api.data.Keys;

@Deprecated
public class Lever extends MaterialData implements Redstone {

    public Lever() {
        super(BlockTypes.LEVER.get().defaultState());
    }

    public Lever(Material material) {
        super(material);
    }

    public Lever(Material material, byte data) {
        super(material, data);
    }

    @Override
    public boolean isPowered() {
        return this.spongeValue.get(Keys.IS_POWERED).get();
    }

    public void setPowered(boolean check) {
        this.spongeValue = this.spongeValue.with(Keys.IS_POWERED, check).get();
    }

    public BlockFace getAttachedFace() {
        return Bonge.getInstance().convert(this.spongeValue.get(Keys.DIRECTION).get().opposite());
    }

    public void setFacingDirection(BlockFace face) {
        this.spongeValue = this.spongeValue.with(Keys.DIRECTION, Bonge.getInstance().convert(face)).get();
    }


}
