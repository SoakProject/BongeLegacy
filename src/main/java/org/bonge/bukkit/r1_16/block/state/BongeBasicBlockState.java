package org.bonge.bukkit.r1_16.block.state;

import org.bonge.bukkit.r1_16.block.BongeBlock;
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

import java.util.List;

public class BongeBasicBlockState implements BlockState {

    private BongeBlock block;

    public BongeBasicBlockState(BongeBlock block){
        this.block = block;
    }

    @Override
    public Block getBlock() {
        return this.block;
    }

    @Override
    public MaterialData getData() {
        throw new NotImplementedException("Method BlockState.getData() should only be called from legacy plugins which Bonge does not support");
    }

    @Override
    public BlockData getBlockData() {
        return this.block.getBlockData();
    }

    @Override
    public Material getType() {
        return this.block.getType();
    }

    @Override
    public byte getLightLevel() {
        return this.block.getLightLevel();
    }

    @Override
    public World getWorld() {
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
    public Location getLocation() {
        return this.block.getLocation();
    }

    @Override
    public Location getLocation(Location loc) {
        return this.block.getLocation(loc);
    }

    @Override
    public Chunk getChunk() {
        return this.block.getChunk();
    }

    @Override
    public void setData(MaterialData data) {
        throw new NotImplementedException("Method BlockState.setData(MaterialData) should only be called from legacy plugins which Bonge does not support");
    }

    @Override
    public void setBlockData(BlockData data) {
        this.block.setBlockData(data);
    }

    @Override
    public void setType(Material type) {
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
    public byte getRawData() {
        throw new NotImplementedException("Method BlockState.getRawData() has not yet been looked at");

    }

    @Override
    public void setRawData(byte data) {
        throw new NotImplementedException("Method BlockState.setRawData() has not yet been looked at");

    }

    @Override
    public boolean isPlaced() {
        return true;
    }

    @Override
    public void setMetadata(String metadataKey, MetadataValue newMetadataValue) {
        throw new NotImplementedException("Method BlockState.setMetadata(String, MetadataValue) has not yet been looked at");

    }

    @Override
    public List<MetadataValue> getMetadata(String metadataKey) {
        throw new NotImplementedException("Method BlockState.getMetadata(String) has not yet been looked at");
    }

    @Override
    public boolean hasMetadata(String metadataKey) {
        return false;
    }

    @Override
    public void removeMetadata(String metadataKey, Plugin owningPlugin) {
        throw new NotImplementedException("Method BlockState.removeMetadata(String, Plugin) has not yet been looked at");
    }
}
