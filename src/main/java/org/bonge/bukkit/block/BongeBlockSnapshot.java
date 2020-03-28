package org.bonge.bukkit.block;

import org.bonge.bukkit.block.data.BongeAbstractBlockData;
import org.bonge.bukkit.block.data.BongeSimpleBlockData;
import org.bonge.bukkit.world.BongeLocation;
import org.bonge.bukkit.world.BongeWorld;
import org.bonge.convert.EnumConvert;
import org.bonge.wrapper.BongeWrapper;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.*;
import org.bukkit.block.data.BlockData;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.block.BlockSnapshot;

import java.util.Collection;
import java.util.List;

public class BongeBlockSnapshot extends BongeWrapper<org.spongepowered.api.block.BlockSnapshot> implements Block {

    public BongeBlockSnapshot(BlockSnapshot value) {
        super(value);
    }

    public org.spongepowered.api.world.Location<org.spongepowered.api.world.World> getSpongeLocation(){
        return this.spongeValue.getLocation().get();
    }

    @Override
    @Deprecated
    public byte getData() {
        return 0;
    }

    @Override
    public BlockData getBlockData() {
        //TODO - other blockstates
        return new BongeSimpleBlockData(this.spongeValue.getExtendedState());
    }

    @NotNull
    @Override
    public Block getRelative(int modX, int modY, int modZ) {
        return new BongeBlock(this.getSpongeLocation().add(modX, modY, modZ));
    }

    @NotNull
    @Override
    public Block getRelative(@NotNull BlockFace face) {
        return new BongeBlock(this.getSpongeLocation().add(EnumConvert.getDirection(face).asBlockOffset()));
    }

    @NotNull
    @Override
    public Block getRelative(@NotNull BlockFace face, int distance) {
        return new BongeBlock(this.getSpongeLocation().add(EnumConvert.getDirection(face).asBlockOffset().mul(distance)));
    }

    @Override
    public Material getType() {
        return Material.getMaterial(this.spongeValue.getExtendedState().getType());
    }

    @Override
    public byte getLightLevel() {
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

    @Override
    public World getWorld() {
        return new BongeWorld(this.getSpongeLocation().getExtent());
    }

    @Override
    public int getX() {
        return this.spongeValue.getPosition().getX();
    }

    @Override
    public int getY() {
        return this.spongeValue.getPosition().getY();
    }

    @Override
    public int getZ() {
        return this.spongeValue.getPosition().getZ();
    }

    @Override
    public Location getLocation() {
        return new BongeLocation(this.getSpongeLocation());
    }

    @Override
    public Location getLocation(Location loc) {
        return null;
    }

    @Override
    public Chunk getChunk() {
        return null;
    }

    @Override
    public void setBlockData(BlockData data) {
        this.setBlockData(data, true);
    }

    @Override
    public void setBlockData(BlockData data, boolean applyPhysics) {
        this.spongeValue.withState(((BongeAbstractBlockData)data).getSpongeValue());
    }

    @Override
    public void setType(Material type) {
        this.setBlockData(type.createBlockData(), true);
    }

    @Override
    public void setType(Material type, boolean applyPhysics) {
        this.setBlockData(type.createBlockData(), applyPhysics);
    }

    @Override
    public BlockFace getFace(Block block) {
        return null;
    }

    @Override
    public BlockState getState() {
        return null;
    }

    @Override
    public Biome getBiome() {
        return null;
    }

    @Override
    public void setBiome(Biome bio) {

    }

    @Override
    public boolean isBlockPowered() {
        return false;
    }

    @Override
    public boolean isBlockIndirectlyPowered() {
        return false;
    }

    @Override
    public boolean isBlockFacePowered(BlockFace face) {
        return false;
    }

    @Override
    public boolean isBlockFaceIndirectlyPowered(BlockFace face) {
        return false;
    }

    @Override
    public int getBlockPower(BlockFace face) {
        return 0;
    }

    @Override
    public int getBlockPower() {
        return 0;
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

    @Override
    public PistonMoveReaction getPistonMoveReaction() {
        return null;
    }

    @Override
    public boolean breakNaturally() {
        return false;
    }

    @Override
    public boolean breakNaturally(ItemStack tool) {
        return false;
    }

    @Override
    public Collection<ItemStack> getDrops() {
        return null;
    }

    @Override
    public Collection<ItemStack> getDrops(ItemStack tool) {
        return null;
    }

    @Override
    public boolean isPassable() {
        return false;
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
