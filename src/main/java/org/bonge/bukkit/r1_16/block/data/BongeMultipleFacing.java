package org.bonge.bukkit.r1_16.block.data;

import org.bonge.Bonge;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.MultipleFacing;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.api.data.Keys;
import org.spongepowered.api.util.Direction;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

public interface BongeMultipleFacing extends IBongeBlockData, MultipleFacing {

    @Override
    default boolean hasFace(@NotNull BlockFace face) {
        return this.getFaces().parallelStream().anyMatch(f -> f.equals(face));
    }

    @Override
    default void setFace(@Nullable BlockFace face, boolean has) {
        Set<Direction> set = this.getSpongeValue().get(Keys.CONNECTED_DIRECTIONS).get();
        Direction direction = Bonge.getInstance().convert(face);
        if (has) {
            set.add(direction);
        } else {
            set.remove(direction);
        }
        this.setSpongeValue(this.getSpongeValue().with(Keys.CONNECTED_DIRECTIONS, set).get());
    }

    @Override
    default @NotNull Set<BlockFace> getFaces() {
        return this
                .getSpongeValue()
                .get(Keys.CONNECTED_DIRECTIONS)
                .map(directions -> directions
                        .stream()
                        .map(direction -> Bonge.getInstance().convert(direction))
                        .collect(Collectors.toSet()))
                .orElseGet(Collections::emptySet);
    }

    @Override
    default @NotNull Set<BlockFace> getAllowedFaces() {
        return this
                .getSpongeValue()
                .getValue(Keys.CONNECTED_DIRECTIONS)
                .map(directions -> directions
                        .all()
                        .stream()
                        .map(direction -> Bonge.getInstance().convert(direction))
                        .collect(Collectors.toSet()))
                .orElseGet(Collections::emptySet);
    }
}
