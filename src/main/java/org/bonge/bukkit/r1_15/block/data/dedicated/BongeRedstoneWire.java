package org.bonge.bukkit.r1_15.block.data.dedicated;

import org.bonge.Bonge;
import org.bonge.bukkit.r1_15.block.data.BongeAnaloguePowerable;
import org.bonge.bukkit.r1_15.block.data.IBongeBlockData;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.type.RedstoneWire;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.data.Keys;
import org.spongepowered.api.data.type.WireAttachmentType;
import org.spongepowered.api.util.Direction;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public interface BongeRedstoneWire extends IBongeBlockData, RedstoneWire, BongeAnaloguePowerable {

    @Override
    @NotNull
    default Connection getFace(@NotNull BlockFace face) {
        try {
            return Bonge.getInstance().convert(Connection.class, this.getSpongeValue().get(Keys.WIRE_ATTACHMENTS).get().get(Bonge.getInstance().convert(face)));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    default void setFace(@NotNull BlockFace face, @NotNull Connection connection) {
        Direction direction = Bonge.getInstance().convert(face);
        try {
            this.getSpongeValue().get(Keys.WIRE_ATTACHMENTS).get().replace(direction, Bonge.getInstance().convert(connection, WireAttachmentType.class));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    default @NotNull Set<BlockFace> getAllowedFaces() {
        Set<BlockFace> set = new HashSet<>();
        this.getSpongeValue().get(Keys.WIRE_ATTACHMENTS).get().keySet().stream().forEach(b -> set.add(Bonge.getInstance().convert(b)));
        return set;
    }
}
