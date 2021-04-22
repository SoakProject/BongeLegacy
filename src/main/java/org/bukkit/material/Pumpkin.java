package org.bukkit.material;


import org.bonge.Bonge;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.block.BlockState;
import org.spongepowered.api.data.Keys;

@Deprecated
public class Pumpkin extends MaterialData implements Directional {

    public Pumpkin(BlockState value) {
        super(value);
    }

    public Pumpkin(Material material) {
        super(material);
    }

    public Pumpkin(Material material, byte data) {
        super(material, data);
    }

    public boolean isLit(){
        return this.spongeValue.get(Keys.IS_LIT).get();
    }

    @Override
    public void setFacingDirection(@NotNull BlockFace face) {
        this.spongeValue = this.spongeValue.with(Keys.DIRECTION, Bonge.getInstance().convert(face)).get();
    }

    @Override
    public @NotNull BlockFace getFacing() {
        return Bonge.getInstance().convert(this.spongeValue.get(Keys.DIRECTION).get());
    }
}
