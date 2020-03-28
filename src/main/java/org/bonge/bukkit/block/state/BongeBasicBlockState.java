package org.bonge.bukkit.block.state;

import org.bonge.bukkit.block.BongeBlock;
import org.bonge.wrapper.BongeWrapper;
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
        return null;
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
        return false;
    }

    @Override
    public boolean update(boolean force) {
        return false;
    }

    @Override
    public boolean update(boolean force, boolean applyPhysics) {
        return false;
    }

    @Override
    public byte getRawData() {
        return 0;
    }

    @Override
    public void setRawData(byte data) {

    }

    @Override
    public boolean isPlaced() {
        return true;
    }

    @Override
    public void setMetadata(String metadataKey, MetadataValue newMetadataValue) {

    }

    @Override
    public List<MetadataValue> getMetadata(String metadataKey) {
        return null;
    }

    @Override
    public boolean hasMetadata(String metadataKey) {
        return false;
    }

    @Override
    public void removeMetadata(String metadataKey, Plugin owningPlugin) {

    }
}
