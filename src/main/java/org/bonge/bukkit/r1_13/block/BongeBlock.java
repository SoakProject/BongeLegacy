package org.bonge.bukkit.r1_13.block;

import org.bonge.Bonge;
import org.bonge.bukkit.r1_13.block.data.BongeAbstractBlockData;
import org.bonge.bukkit.r1_13.block.state.BongeBasicBlockState;
import org.bonge.bukkit.r1_13.block.state.BongeBlockState;
import org.bonge.bukkit.r1_13.world.BongeLocation;
import org.bonge.bukkit.r1_13.world.BongeWorld;
import org.bonge.bukkit.r1_13.world.chunk.BongeChunk;
import org.bonge.util.exception.NotImplementedException;
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
import org.spongepowered.api.block.tileentity.TileEntity;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.util.Direction;
import org.spongepowered.api.world.BlockChangeFlags;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

import java.io.IOException;
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
        throw new NotImplementedException("Method Block.getData() is for legacy plugin which Bonge does not support");
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
        Direction dir;
        try {
            dir = Bonge.getInstance().convert(face, Direction.class);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        return new BongeBlock(this.spongeValue.add(dir.asBlockOffset()));
    }

    @NotNull
    @Override
    public Block getRelative(@NotNull BlockFace face, int distance) {
        Direction dir;
        try {
            dir = Bonge.getInstance().convert(face, Direction.class);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        return new BongeBlock(this.spongeValue.add(dir.asBlockOffset().mul(distance)));
    }

    @NotNull
    @Override
    public Material getType() {
        try {
            return Bonge.getInstance().convert(Material.class, this.spongeValue.getBlockType());
        } catch (IOException e) {
            return Material.UNKNOWN;
        }
    }

    @Override
    public byte getLightLevel() {
        throw new NotImplementedException("Sponge does not support LightLevel as far as im aware");

    }

    @Override
    public byte getLightFromSky() {
        throw new NotImplementedException("Sponge does not support LightLevel as far as im aware");

    }

    @Override
    public byte getLightFromBlocks() {
        throw new NotImplementedException("Sponge does not support LightLevel as far as im aware");
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
        return new BongeLocation(this.spongeValue.getExtent().getLocation(this.spongeValue.getBlockX(), this.spongeValue.getBlockY(), this.spongeValue.getBlockZ()));
    }

    @Nullable
    @Override
    public org.bukkit.Location getLocation(@Nullable org.bukkit.Location loc) {
        throw new NotImplementedException("BongeBlock.getLocation(Location) has not been looked at yet");
    }

    @NotNull
    @Override
    public Chunk getChunk() {
        Optional<org.spongepowered.api.world.Chunk> opChunk = this.spongeValue.getExtent().getChunk(this.spongeValue.getChunkPosition());
        if(!opChunk.isPresent()){
            opChunk = this.spongeValue.getExtent().loadChunk(this.spongeValue.getChunkPosition(), true);
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
        throw new NotImplementedException("Block.getFace(Block) is not a priority");
    }

    @NotNull
    @Override
    public BlockState getState() {
        Optional<TileEntity> opTile = this.spongeValue.getTileEntity();
        if(!opTile.isPresent()){
            return new BongeBasicBlockState(this);
        }
        Optional<BongeBlockState<TileEntity>> opState = BongeBlockState.of(opTile.get());
        if(opState.isPresent()){
            return opState.get();
        }
        throw new NotImplementedException("BlockState not being implemented until Sponge API 8");

    }

    @NotNull
    @Override
    public Biome getBiome() {
        throw new NotImplementedException("Biomes not being implemented");

    }

    @Override
    public void setBiome(@NotNull Biome bio) {
        throw new NotImplementedException("Biomes not being implements");

    }

    @Override
    public boolean isBlockPowered() {
        return this.spongeValue.get(Keys.POWERED).get();
    }

    @Override
    public boolean isBlockIndirectlyPowered() {
        throw new NotImplementedException("Sponge does not support checking if the block was powered indirectly, need to check manually");
    }

    @Override
    public boolean isBlockFacePowered(@NotNull BlockFace face) {
        throw new NotImplementedException("Block.isBlockFacePowered(BlockFace) has not been looked at");
    }

    @Override
    public boolean isBlockFaceIndirectlyPowered(@NotNull BlockFace face) {
        throw new NotImplementedException("Sponge does not support checking if the block was powered indirectly, need to check manually");
    }

    @Override
    public int getBlockPower(@NotNull BlockFace face) {
        throw new NotImplementedException("Block.isBlockPower(BlockFace) has not been looked at");
    }

    @Override
    public int getBlockPower() {
        return this.spongeValue.get(Keys.POWER).get();
    }

    @Override
    public boolean isEmpty() {
        throw new NotImplementedException("Block.isEmpty() has not been looked at");
    }

    @Override
    public boolean isLiquid() {
        throw new NotImplementedException("Block.isLiquid() has not been looked at");
    }

    @Override
    public double getTemperature() {
        throw new NotImplementedException("Block.getTemperature() has not been looked at");
    }

    @Override
    public double getHumidity() {
        throw new NotImplementedException("Block.getHumidity() has not been looked at");
    }

    @NotNull
    @Override
    public PistonMoveReaction getPistonMoveReaction() {
        throw new NotImplementedException("Block.getPistonMoveReaction() has not been looked at");
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
        throw new NotImplementedException("Sponge does not yet support getting drops of blocks as far as im aware");
    }

    @NotNull
    @Override
    public Collection<ItemStack> getDrops(@NotNull ItemStack tool) {
        return getDrops();
    }

    @Override
    public boolean isPassable() {
        throw new NotImplementedException("Block.isPassable() has not been looked at");
    }

    @Override
    public void setMetadata(@NotNull String metadataKey, @NotNull MetadataValue newMetadataValue) {
        throw new NotImplementedException("Block.setMetadata(String, MetadataValue) has not been looked at");
    }

    @NotNull
    @Override
    public List<MetadataValue> getMetadata(@NotNull String metadataKey) {
        throw new NotImplementedException("Block.getMetadata(String) has not been looked at");
    }

    @Override
    public boolean hasMetadata(@NotNull String metadataKey) {
        return false;
    }

    @Override
    public void removeMetadata(@NotNull String metadataKey, @NotNull Plugin owningPlugin) {
        throw new NotImplementedException("Block.removeMetadata has not been looked at");
    }

    @Override
    public String toString(){
        return "BongeBlock[X: " + this.getX() + " Y: " + this.getY() + " Z: " + this.getZ() + " World: " + this.getWorld().getName() + " State:" + this.spongeValue.getBlock().getId() + "]";
    }
}
