package org.bukkit.material;

import org.bonge.Bonge;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.block.BlockState;
import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.api.data.Keys;
import org.spongepowered.api.util.Direction;

import java.io.IOException;

@Deprecated
public class Sign extends MaterialData implements Attachable {

    public Sign(BlockState value) {
        super(value);
    }

    public Sign(Material material) {
        super(material);
    }

    public Sign() {
        this(BlockTypes.OAK_SIGN.get().defaultState());
    }

    @Override
    public @NotNull BlockFace getAttachedFace() {
        if (this.getSpongeValue().type().equals(BlockTypes.OAK_SIGN.get())) {
            return BlockFace.DOWN;
        }
        return this.getFacing().getOppositeFace();
    }

    @Override
    public void setFacingDirection(@NotNull BlockFace face) {
        try {
            this.spongeValue = this.spongeValue.with(Keys.DIRECTION, Bonge.getInstance().convert(face, Direction.class)).get();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public @NotNull BlockFace getFacing() {
        try {
            return Bonge.getInstance().convert(BlockFace.class, this.getSpongeValue().get(Keys.DIRECTION).get());
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
