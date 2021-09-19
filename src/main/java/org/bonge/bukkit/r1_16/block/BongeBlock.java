package org.bonge.bukkit.r1_16.block;

import org.bonge.Bonge;
import org.bonge.bukkit.r1_16.block.data.BongeAbstractBlockData;
import org.bonge.bukkit.r1_16.block.state.BongeBasicBlockState;
import org.bonge.bukkit.r1_16.block.state.BongeBlockState;
import org.bonge.bukkit.r1_16.inventory.item.holder.ItemHolder;
import org.bonge.bukkit.r1_16.inventory.item.holder.ItemStackHolder;
import org.bonge.bukkit.r1_16.inventory.item.holder.ItemStackSnapshotHolder;
import org.bonge.bukkit.r1_16.world.BongeLocation;
import org.bonge.bukkit.r1_16.world.BongeWorld;
import org.bonge.bukkit.r1_16.world.chunk.BongeChunk;
import org.bonge.util.exception.NotImplementedException;
import org.bonge.wrapper.BongeWrapper;
import org.bukkit.Chunk;
import org.bukkit.FluidCollisionMode;
import org.bukkit.Material;
import org.bukkit.block.*;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.block.entity.BlockEntity;
import org.spongepowered.api.data.Keys;
import org.spongepowered.api.profile.GameProfile;
import org.spongepowered.api.util.Direction;
import org.spongepowered.api.world.BlockChangeFlags;
import org.spongepowered.api.world.LightTypes;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;
import org.spongepowered.api.world.server.ServerWorld;
import org.spongepowered.math.vector.Vector3i;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class BongeBlock extends BongeWrapper<Location<? extends World, ? extends Location<?, ?>>> implements Block {

    public BongeBlock(Location<? extends World<?, ?>, ? extends Location<?, ?>> value) {
        super(value);
    }

    public boolean isPreferredTool(@NotNull ItemStack stack) {
        throw new NotImplementedException("Not got to yet");
    }

    @Override
    @Deprecated
    public byte getData() {
        throw new NotImplementedException("Method Block.getData() is for legacy plugin which Bonge does not support");
    }

    @NotNull
    @Override
    public BlockData getBlockData() {
        try {
            return BongeAbstractBlockData.findDynamicClass(this.spongeValue.block());
        } catch (InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException e) {
            throw new IllegalStateException("This should not happen. Is a mod/plugin failing to interact with Bonge correctly? If not report to Bonge", e);
        }
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
            return Bonge.getInstance().convert(Material.class, this.spongeValue.blockType());
        } catch (IOException e) {
            return Material.UNKNOWN;
        }
    }

    @Override
    public byte getLightLevel() {
        return (byte) this.spongeValue.world().light(this.getX(), this.getY(), this.getZ());
    }

    @Override
    public byte getLightFromSky() {
        return (byte) this.spongeValue.world().light(LightTypes.SKY, this.getX(), this.getY(), this.getZ());
    }

    @Override
    public byte getLightFromBlocks() {
        return (byte) this.spongeValue.world().light(LightTypes.BLOCK, this.getX(), this.getY(), this.getZ());
    }

    @NotNull
    @Override
    public org.bukkit.World getWorld() {
        return new BongeWorld(this.spongeValue.world());
    }

    @Override
    public int getX() {
        return this.spongeValue.blockX();
    }

    @Override
    public int getY() {
        return this.spongeValue.blockY();
    }

    @Override
    public int getZ() {
        return this.spongeValue.blockZ();
    }

    @NotNull
    @Override
    public org.bukkit.Location getLocation() {
        return new BongeLocation(this.spongeValue.world().location(this.spongeValue.blockX(), this.spongeValue.blockY(), this.spongeValue.blockZ()));
    }

    @Nullable
    @Override
    public org.bukkit.Location getLocation(@Nullable org.bukkit.Location loc) {
        throw new NotImplementedException("BongeBlock.getLocation(Location) has not been looked at yet");
    }

    @NotNull
    @Override
    public Chunk getChunk() {
        return new BongeChunk(this.spongeValue.chunkPosition(), new BongeWorld(this.spongeValue.world()));
    }

    @Override
    public void setBlockData(@NotNull BlockData data) {
        this.setBlockData(data, true);
    }

    @Override
    public void setBlockData(@NotNull BlockData data, boolean applyPhysics) {
        this.spongeValue.setBlock(((BongeAbstractBlockData) data).getSpongeValue(), (applyPhysics ? BlockChangeFlags.ALL : BlockChangeFlags.NONE));
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
        Optional<? extends BlockEntity> opTile = this.spongeValue.blockEntity();
        if (!opTile.isPresent()) {
            return new BongeBasicBlockState(this);
        }
        Optional<BongeBlockState<BlockEntity>> opState;
        try {
            opState = BongeBlockState.of(opTile.get());
        } catch (IOException e) {
            throw new IllegalStateException("Found the conversion, however could not supply a Snapshot version of the Inventory", e);
        }
        if (opState.isPresent()) {
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
        return this.spongeValue.block().get(Keys.IS_POWERED).get();
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
        return this.spongeValue.block().get(Keys.POWER).get();
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
        World<?, ?> world = this.spongeValue.world();
        return world.destroyBlock(new Vector3i(this.getX(), this.getY(), this.getZ()), true);
    }

    @Override
    public boolean breakNaturally(@NotNull ItemStack tool) {
        World<?, ?> world = this.spongeValue.world();
        if (!(world instanceof ServerWorld)) {
            return false;
        }
        ServerWorld sWorld = (ServerWorld) world;
        //The following gets a sacrifice for the block interaction
        Collection<GameProfile> profiles = Sponge.server().gameProfileManager().cache().all();
        if (profiles.isEmpty()) {
            return false;
        }
        ItemHolder holder = tool.getItemMeta().getHolder();
        org.spongepowered.api.item.inventory.ItemStack stack;
        if (holder instanceof ItemStackHolder) {
            stack = ((ItemStackHolder) holder).getSpongeValue();
        } else {
            stack = ((ItemStackSnapshotHolder) holder).getSpongeValue().createStack();
        }
        return sWorld.digBlockWith(this.getX(), this.getY(), this.getZ(), stack, profiles.iterator().next());
    }

    @Override
    public boolean applyBoneMeal(@NotNull BlockFace face) {
        throw new NotImplementedException("yet to look at");

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

    @NotNull
    @Override
    public Collection<ItemStack> getDrops(@NotNull ItemStack tool, @Nullable Entity entity) {
        throw new NotImplementedException("yet to look at");
    }

    @Override
    public boolean isPassable() {
        return !this.spongeValue.block().get(Keys.IS_SOLID).orElse(true);
    }

    @Override
    public @Nullable RayTraceResult rayTrace(org.bukkit.@NotNull Location start, @NotNull Vector direction, double maxDistance, @NotNull FluidCollisionMode fluidCollisionMode) {
        throw new NotImplementedException("Block.rayTrace(Location, Vector, double, FluidCollisionMode) has not been looked at");
    }

    @Override
    public @NotNull BoundingBox getBoundingBox() {
        throw new NotImplementedException("Block.getBoundingBox() has not been looked at");
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
    public String toString() {
        return "BongeBlock[X: " + this.getX() + " Y: " + this.getY() + " Z: " + this.getZ() + " World: " + this.getWorld().getName() + " State:" + this.spongeValue.block().toString() + "]";
    }
}
