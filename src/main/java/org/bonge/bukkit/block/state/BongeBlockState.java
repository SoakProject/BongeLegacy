package org.bonge.bukkit.block.state;

import org.bonge.bukkit.block.BongeBlock;
import org.bonge.bukkit.world.BongeLocation;
import org.bonge.bukkit.world.BongeWorld;
import org.bonge.convert.EnumConvert;
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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.api.block.tileentity.TileEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public abstract class BongeBlockState<T extends TileEntity> extends BongeWrapper<T> implements BlockState {

    protected Map<String, Object> changes = new HashMap<>();

    public BongeBlockState(T value) {
        super(value);
    }

    public <T> Optional<T> getChanges(String key){
        Object obj = this.changes.get(key);
        if(obj == null){
            return Optional.empty();
        }
        return Optional.of((T)obj);
    }

    public void setChange(String key, Object value){
        if (this.changes.containsKey(key)){
            this.changes.replace(key, value);
            return;
        }
        this.changes.put(key, value);
    }

    public void removeChange(String key){
        this.changes.remove(key);
    }

    @NotNull
    @Override
    public Block getBlock() {
        return new BongeBlock(this.spongeValue.getLocation());
    }

    @NotNull
    @Override
    @Deprecated
    public MaterialData getData() {
        return null;
    }

    @NotNull
    @Override
    public BlockData getBlockData() {
        //TODO - blockdata
        return null;
    }

    @NotNull
    @Override
    public Material getType() {
        return Material.getMaterial(this.spongeValue.getLocation().getBlockType());
    }

    @Override
    public byte getLightLevel() {
        return 0;
    }

    @NotNull
    @Override
    public World getWorld() {
        return new BongeWorld(this.spongeValue.getWorld());
    }

    @Override
    public int getX() {
        return this.spongeValue.getLocation().getBlockX();
    }

    @Override
    public int getY() {
        return this.spongeValue.getLocation().getBlockY();
    }

    @Override
    public int getZ() {
        return this.spongeValue.getLocation().getBlockZ();
    }

    @NotNull
    @Override
    public Location getLocation() {
        return new BongeLocation(this.spongeValue.getLocation());
    }

    @Nullable
    @Override
    public Location getLocation(@Nullable Location loc) {
        return null;
    }

    @NotNull
    @Override
    public Chunk getChunk() {
        return null;
    }

    @Override
    @Deprecated
    public void setData(@NotNull MaterialData data) {

    }

    @Override
    public void setBlockData(@NotNull BlockData data) {

    }

    @Override
    public void setType(@NotNull Material type) {

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
        return this.changes.isEmpty();
    }

    @Override
    public void setMetadata(@NotNull String metadataKey, @NotNull MetadataValue newMetadataValue) {

    }

    @NotNull
    @Override
    public List<MetadataValue> getMetadata(@NotNull String metadataKey) {
        return null;
    }

    @Override
    public boolean hasMetadata(@NotNull String metadataKey) {
        return false;
    }

    @Override
    public void removeMetadata(@NotNull String metadataKey, @NotNull Plugin owningPlugin) {

    }

    public static <T extends org.spongepowered.api.block.tileentity.TileEntity> Optional<BongeBlockState<T>> of(T tile){
        return Optional.empty();
    }
}
