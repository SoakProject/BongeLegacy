package org.bukkit.material;

import org.bonge.Bonge;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.api.data.Keys;
import org.spongepowered.api.util.Direction;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.Set;

@Deprecated
public class Vine extends MaterialData {

    public Vine() {
        super(BlockTypes.VINE.get().defaultState());
    }

    public Vine(byte value) {
        this(Material.VINE, value);
    }

    public Vine(BlockFace... faces) {
        this(EnumSet.copyOf(Arrays.asList(faces)));
    }

    public Vine(EnumSet<BlockFace> set) {
        this();
    }

    public Vine(Material material) {
        super(material);
    }

    public Vine(Material material, byte data) {
        super(material, data);
    }

    public boolean isOnFace(BlockFace face) {
        Direction dir = Bonge.getInstance().convert(face);
        return this.spongeValue.get(Keys.CONNECTED_DIRECTIONS).get().stream().anyMatch(d -> dir.equals(d));
    }

    public void putOnFace(BlockFace face) {
        Direction dir = Bonge.getInstance().convert(face);
        Set<Direction> directions = this.spongeValue.get(Keys.CONNECTED_DIRECTIONS).get();
        directions.add(dir);
        this.spongeValue = this.spongeValue.with(Keys.CONNECTED_DIRECTIONS, directions).get();
    }

    public void removeOnFace(BlockFace face) {
        Direction dir = Bonge.getInstance().convert(face);
        Set<Direction> directions = this.spongeValue.get(Keys.CONNECTED_DIRECTIONS).get();
        directions.remove(dir);
        this.spongeValue = this.spongeValue.with(Keys.CONNECTED_DIRECTIONS, directions).get();
    }

}
