package org.bonge.bukkit.r1_16.block.state;

import org.bonge.util.exception.NotImplementedException;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.data.BlockData;
import org.bukkit.material.MaterialData;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class BongeBasicBlockState implements BlockState {

    private final Block block;

    public BongeBasicBlockState(Block block) {
        this.block = block;
    }

    @Override
    public @NotNull Block getBlock() {
        return this.block;
    }

    @Override
    @Deprecated
    public @NotNull MaterialData getData() {
        throw new NotImplementedException("Method BlockState.getData() should only be called from legacy plugins which Bonge does not support");
    }

    @Override
    public @NotNull BlockData getBlockData() {
        return this.block.getBlockData();
    }

    @Override
    public @NotNull Material getType() {
        return this.block.getType();
    }

    @Override
    public byte getLightLevel() {
        return this.block.getLightLevel();
    }

    @Override
    public @NotNull World getWorld() {
        return this.block.getWorld();
    }

    @Override
    public int getX() {
        return this.block.getX();
    }

    @Override
    public int getY() {
        return this.block.getY();
    }

    @Override
    public int getZ() {
        return this.block.getZ();
    }

    @Override
    public @NotNull Location getLocation() {
        return this.block.getLocation();
    }

    @Override
    public Location getLocation(Location loc) {
        return this.block.getLocation(loc);
    }

    @Override
    public @NotNull Chunk getChunk() {
        return this.block.getChunk();
    }

    @Override
    @Deprecated
    public void setData(@NotNull MaterialData data) {
        throw new NotImplementedException("Method BlockState.setData(MaterialData) should only be called from legacy plugins which Bonge does not support");
    }

    @Override
    public void setBlockData(@NotNull BlockData data) {
        this.block.setBlockData(data);
    }

    @Override
    public void setType(@NotNull Material type) {
        this.block.setType(type);
    }

    @Override
    public boolean update() {
        return update(false, true);
    }

    @Override
    public boolean update(boolean force) {
        return update(force, false);
    }

    @Override
    public boolean update(boolean force, boolean applyPhysics) {
        return false;
    }

    @Override
    @Deprecated
    public byte getRawData() {
        throw new NotImplementedException("Method BlockState.getRawData() has not yet been looked at");

    }

    @Override
    @Deprecated
    public void setRawData(byte data) {
        throw new NotImplementedException("Method BlockState.setRawData() has not yet been looked at");

    }

    @Override
    public boolean isPlaced() {
        return true;
    }

    @Override
    public void setMetadata(@NotNull String metadataKey, @NotNull MetadataValue newMetadataValue) {
        throw new NotImplementedException("Method BlockState.setMetadata(String, MetadataValue) has not yet been looked at");

    }

    @Override
    public @NotNull List<MetadataValue> getMetadata(@NotNull String metadataKey) {
        throw new NotImplementedException("Method BlockState.getMetadata(String) has not yet been looked at");
    }

    @Override
    public boolean hasMetadata(@NotNull String metadataKey) {
        return false;
    }

    @Override
    public void removeMetadata(@NotNull String metadataKey, @NotNull Plugin owningPlugin) {
        throw new NotImplementedException("Method BlockState.removeMetadata(String, Plugin) has not yet been looked at");
    }
}
