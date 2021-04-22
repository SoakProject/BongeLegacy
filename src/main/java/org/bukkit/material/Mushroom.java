package org.bukkit.material;

import org.bonge.Bonge;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.material.types.MushroomBlockTexture;
import org.spongepowered.api.block.BlockState;
import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.api.data.Keys;
import org.spongepowered.api.data.type.MooshroomType;

import java.io.IOException;

@Deprecated
public class Mushroom extends MaterialData {

    public Mushroom(Material material) {
        super(material);
    }

    public Mushroom(Material material, BlockFace face) {
        super(material);
    }

    public Mushroom(Material material, MushroomBlockTexture texture) {
        super(material);
    }

    public Mushroom(Material material, byte data) {
        super(material, data);
    }

    public boolean isStem() {
        return this.spongeValue.type().equals(BlockTypes.MUSHROOM_STEM.get());
    }

    public void setStem() {
        this.spongeValue = BlockState.builder().from(this.spongeValue).blockType(BlockTypes.MUSHROOM_STEM).build();
    }

    public MushroomBlockTexture getBlockTexture() {
        try {
            return Bonge.getInstance().convert(MushroomBlockTexture.class, this.spongeValue.get(Keys.MOOSHROOM_TYPE).get());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public void setBlockTexture(MushroomBlockTexture texture) {
        try {
            MooshroomType type = Bonge.getInstance().convert(texture, MooshroomType.class);
            this.spongeValue = this.spongeValue.with(Keys.MOOSHROOM_TYPE, type).get();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
