package org.bukkit.material;

import org.bonge.Bonge;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.block.BlockState;
import org.spongepowered.api.data.Keys;
import org.spongepowered.api.util.Direction;

import java.io.IOException;

@Deprecated
public class DirectionalContainer extends MaterialData implements Directional {

    public DirectionalContainer(BlockState value) {
        super(value);
    }

    public DirectionalContainer(Material material) {
        super(material);
    }

    public DirectionalContainer(Material material, byte data) {
        super(material, data);
    }

    @Override
    public void setFacingDirection(@NotNull BlockFace face) {
        try {
            this.spongeValue = this.spongeValue.with(Keys.DIRECTION, Bonge.getInstance().convert(face, Direction.class)).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public @NotNull BlockFace getFacing() {
        try {
            return Bonge.getInstance().convert(BlockFace.class, this.spongeValue.get(Keys.DIRECTION).get());
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
