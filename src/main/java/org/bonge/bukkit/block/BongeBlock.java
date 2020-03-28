package org.bonge.bukkit.block;

import org.bonge.bukkit.block.data.BongeAbstractBlockData;
import org.bonge.bukkit.block.data.BongeSimpleBlockData;
import org.bonge.bukkit.world.BongeLocation;
import org.bonge.bukkit.world.BongeWorld;
import org.bonge.bukkit.world.chunk.BongeChunk;
import org.bonge.convert.EnumConvert;
import org.bonge.wrapper.BongeWrapper;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.block.*;
import org.bukkit.block.data.BlockData;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.world.BlockChangeFlags;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class BongeBlock extends BongeWrapper<Location<org.spongepowered.api.world.World>> implements Block {

    public BongeBlock(Location<World> value) {
        super(value);
    }

    @Override
    @Deprecated
    public byte getData() {
        //NOT SUPPORTED BY BUKKIT NOR SPONGE - NOT SUPPORTED BY BONGE
        return 0;
    }

    @NotNull
    @Override
    public BlockData getBlockData() {
        return BongeAbstractBlockData.of(this.spongeValue.getBlock());
    }

    @NotNull
    @Override
    public Block getRelative(int modX, int modY, int modZ) {
        return new BongeBlock(this.spongeValue.add(modX, modY, modZ));
    }

    @NotNull
    @Override
    public Block getRelative(@NotNull BlockFace face) {
        return new BongeBlock(this.spongeValue.add(EnumConvert.getDirection(face).asBlockOffset()));
    }

    @NotNull
    @Override
    public Block getRelative(@NotNull BlockFace face, int distance) {
        return new BongeBlock(this.spongeValue.add(EnumConvert.getDirection(face).asBlockOffset().mul(distance)));
    }

    @NotNull
    @Override
    public Material getType() {
        return Material.getMaterial(this.spongeValue.getBlockType());
    }

    @Override
    public byte getLightLevel() {
        //TODO
        return 0;
    }

    @Override
    public byte getLightFromSky() {
        return 0;
    }

    @Override
    public byte getLightFromBlocks() {
        return 0;
    }

    @NotNull
    @Override
    public org.bukkit.World getWorld() {
        return new BongeWorld(this.spongeValue.getExtent());
    }

    @Override
    public int getX() {
        return this.spongeValue.getBlockX();
    }

    @Override
    public int getY() {
        return this.spongeValue.getBlockY();
    }

    @Override
    public int getZ() {
        return this.spongeValue.getBlockZ();
    }

    @NotNull
    @Override
    public org.bukkit.Location getLocation() {
        return new BongeLocation(this.spongeValue);
    }

    @Nullable
    @Override
    public org.bukkit.Location getLocation(@Nullable org.bukkit.Location loc) {
        return null;
    }

    @NotNull
    @Override
    public Chunk getChunk() {
        //TODO - UNLOADED CHUNK
        Optional<org.spongepowered.api.world.Chunk> opChunk = this.spongeValue.getExtent().getChunk(this.spongeValue.getChunkPosition());
        if(!opChunk.isPresent()){
        }
        return new BongeChunk(opChunk.get());
    }

    @Override
    public void setBlockData(@NotNull BlockData data) {
        this.setBlockData(data, true);
    }

    @Override
    public void setBlockData(@NotNull BlockData data, boolean applyPhysics) {
        this.spongeValue.setBlock(((BongeAbstractBlockData)data).getSpongeValue(), (applyPhysics ? BlockChangeFlags.ALL : BlockChangeFlags.NONE));
    }

    @Override
    public void setType(@NotNull Material type) {
        this.setBlockData(type.createBlockData(), true);
    }

    @Override
    public void setType(@NotNull Material type, boolean applyPhysics) {
        this.setBlockData(type.createBlockData(), applyPhysics);
    }

    @Nullable
    @Override
    public BlockFace getFace(@NotNull Block block) {
        return null;
    }

    @NotNull
    @Override
    public BlockState getState() {
        return null;
    }

    @NotNull
    @Override
    public Biome getBiome() {
        return null;
    }

    @Override
    public void setBiome(@NotNull Biome bio) {

    }

    @Override
    public boolean isBlockPowered() {
        return this.spongeValue.get(Keys.POWERED).get();
    }

    @Override
    public boolean isBlockIndirectlyPowered() {
        return false;
    }

    @Override
    public boolean isBlockFacePowered(@NotNull BlockFace face) {
        return false;
    }

    @Override
    public boolean isBlockFaceIndirectlyPowered(@NotNull BlockFace face) {
        return false;
    }

    @Override
    public int getBlockPower(@NotNull BlockFace face) {
        return 0;
    }

    @Override
    public int getBlockPower() {
        return this.spongeValue.get(Keys.POWER).get();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean isLiquid() {
        return false;
    }

    @Override
    public double getTemperature() {
        return 0;
    }

    @Override
    public double getHumidity() {
        return 0;
    }

    @NotNull
    @Override
    public PistonMoveReaction getPistonMoveReaction() {
        return null;
    }

    @Override
    public boolean breakNaturally() {
        //TODO - drops
        return this.spongeValue.removeBlock();
    }

    @Override
    public boolean breakNaturally(@NotNull ItemStack tool) {
        //TODO - tool check
        return breakNaturally();
    }

    @NotNull
    @Override
    public Collection<ItemStack> getDrops() {
        return null;
    }

    @NotNull
    @Override
    public Collection<ItemStack> getDrops(@NotNull ItemStack tool) {
        return null;
    }

    @Override
    public boolean isPassable() {
        return false;
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

    @Override
    public String toString(){
        return "BongeBlock[X: " + this.getX() + " Y: " + this.getY() + " Z: " + this.getZ() + " World: " + this.getWorld().getName() + " State:" + this.spongeValue.getBlock().getId() + "]";
    }
}
