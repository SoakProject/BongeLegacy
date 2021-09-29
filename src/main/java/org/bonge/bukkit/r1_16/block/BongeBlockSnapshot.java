package org.bonge.bukkit.r1_16.block;

import org.bonge.Bonge;
import org.bonge.bukkit.r1_16.block.data.BongeAbstractBlockData;
import org.bonge.bukkit.r1_16.block.state.BongeBasicBlockState;
import org.bonge.bukkit.r1_16.block.state.BongeBlockState;
import org.bonge.util.exception.NotImplementedException;
import org.bukkit.*;
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
import org.spongepowered.api.ResourceKey;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.block.BlockSnapshot;
import org.spongepowered.api.block.entity.BlockEntity;
import org.spongepowered.api.world.server.ServerLocation;
import org.spongepowered.api.world.server.ServerWorld;
import org.spongepowered.math.vector.Vector3i;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class BongeBlockSnapshot implements Block {

    private final BlockSnapshot snapshot;
    private final BlockEntity entity;

    public BongeBlockSnapshot(ServerLocation location) {
        this(location.createSnapshot(), location.blockEntity().orElse(null));
    }

    public BongeBlockSnapshot(@NotNull BlockSnapshot snapshot, @Nullable BlockEntity entity) {
        this.snapshot = snapshot;
        this.entity = entity;
    }

    @Override
    public boolean isPreferredTool(@NotNull ItemStack stack) {
        throw new NotImplementedException("Not got to yet");
    }

    @Override
    @Deprecated
    public byte getData() {
        throw new NotImplementedException("Method Block.getData() is for legacy plugin which Bonge does not support");
    }

    @Override
    public @NotNull BlockData getBlockData() {
        try {
            return BongeAbstractBlockData.findDynamicClass(this.snapshot.state());
        } catch (InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException e) {
            throw new IllegalStateException("This should not happen. Is a mod/plugin failing to interact with Bonge correctly? If not report to Bonge", e);
        }
    }

    @Override
    public @NotNull Block getRelative(int modX, int modY, int modZ) {
        ServerLocation loc = this.snapshot.location().orElseThrow(() -> new IllegalStateException("Couldn't get location of block")).add(modX, modY, modZ);
        return new BongeBlockSnapshot(loc.createSnapshot(), this.entity == null ? null : this.entity.copy());

    }

    @Override
    public @NotNull Block getRelative(@NotNull BlockFace face) {
        return this.getRelative(face.getModX(), face.getModY(), face.getModZ());
    }

    @Override
    public @NotNull Block getRelative(@NotNull BlockFace face, int distance) {
        Vector3i vector3i = new Vector3i(face.getModX(), face.getModY(), face.getModZ());
        vector3i = vector3i.mul(distance);
        return this.getRelative(vector3i.x(), vector3i.y(), vector3i.z());
    }

    @Override
    public @NotNull Material getType() {
        return Material.valueOf(Bonge.getInstance().convert(this.snapshot.state().type()));
    }

    @Override
    public byte getLightLevel() {
        throw new NotImplementedException("BlockSnapshot.getLightLevel() Not got to yet");
    }

    @Override
    public byte getLightFromSky() {
        throw new NotImplementedException("BlockSnapshot.getLightFromSky() Not got to yet");
    }

    @Override
    public byte getLightFromBlocks() {
        throw new NotImplementedException("BlockSnapshot.getLightFromBlocks() Not got to yet");
    }

    @Override
    public @NotNull World getWorld() {
        ResourceKey key = this.snapshot.world();
        Optional<ServerWorld> opWorld = Sponge.server().worldManager().world(key);
        ServerWorld world = opWorld.orElseThrow(() -> new IllegalStateException("Could not get world with key of " + key.asString()));
        return Bonge.getInstance().convert(world);
    }

    @Override
    public int getX() {
        return this.snapshot.position().x();
    }

    @Override
    public int getY() {
        return this.snapshot.position().y();

    }

    @Override
    public int getZ() {
        return this.snapshot.position().z();

    }

    @Override
    public @NotNull Location getLocation() {
        Optional<ServerLocation> opLocation = this.snapshot.location();
        if(opLocation.isPresent()){
            return Bonge.getInstance().convert(opLocation.get());
        }
        Vector3i pos = this.snapshot.position();
        return Bonge.LOCATION.to(this.snapshot.world(), pos);
    }

    @Override
    public @Nullable Location getLocation(@Nullable Location loc) {
        throw new NotImplementedException("BlockSnapshot.getLocation(Location) Not got to yet");
    }

    @Override
    public @NotNull Chunk getChunk() {
        throw new NotImplementedException("BlockSnapshot.getChunk() Not got to yet");
    }

    @Override
    public void setBlockData(@NotNull BlockData data) {
        throw new NotImplementedException("BlockSnapshot.setBlockData(BlockData) Not got to yet");
    }

    @Override
    public void setBlockData(@NotNull BlockData data, boolean applyPhysics) {
        throw new NotImplementedException("BlockSnapshot.setBlockData(BlockData, boolean) Not got to yet");
    }

    @Override
    public void setType(@NotNull Material type) {
        throw new NotImplementedException("BlockSnapshot.setType(Material) Not got to yet");
    }

    @Override
    public void setType(@NotNull Material type, boolean applyPhysics) {
        throw new NotImplementedException("BlockSnapshot.setType(Material, boolean) Not got to yet");
    }

    @Override
    public @Nullable BlockFace getFace(@NotNull Block block) {
        throw new NotImplementedException("BlockSnapshot.getFace(Block) Not got to yet");
    }

    @Override
    public @NotNull BlockState getState() {
        if (this.entity == null) {
            return new BongeBasicBlockState(this);
        }
        try {
            BongeBlockState.of(this.entity);
        } catch (IOException e) {
            e.printStackTrace();
        }


        throw new NotImplementedException("BlockSnapshot.getState() Not got to yet");
    }

    @Override
    public @NotNull Biome getBiome() {
        throw new NotImplementedException("BlockSnapshot.getBiome() Not got to yet");
    }

    @Override
    public void setBiome(@NotNull Biome bio) {
        throw new NotImplementedException("BlockSnapshot.setBiome(Biome) Not got to yet");
    }

    @Override
    public boolean isBlockPowered() {
        throw new NotImplementedException("BlockSnapshot.isBlockPowered() Not got to yet");
    }

    @Override
    public boolean isBlockIndirectlyPowered() {
        throw new NotImplementedException("BlockSnapshot.isBlockIndirectlyPowered() Not got to yet");
    }

    @Override
    public boolean isBlockFacePowered(@NotNull BlockFace face) {
        throw new NotImplementedException("BlockSnapshot.isBlockFacePowered(BlockFace) Not got to yet");
    }

    @Override
    public boolean isBlockFaceIndirectlyPowered(@NotNull BlockFace face) {
        throw new NotImplementedException("BlockSnapshot.isBlockFaceIndirectlyPowered(BlockFace) Not got to yet");
    }

    @Override
    public int getBlockPower(@NotNull BlockFace face) {
        throw new NotImplementedException("BlockSnapshot.getBlockPower(BlockFace) Not got to yet");
    }

    @Override
    public int getBlockPower() {
        throw new NotImplementedException("BlockSnapshot.getBlockPower() Not got to yet");
    }

    @Override
    public boolean isEmpty() {
        throw new NotImplementedException("BlockSnapshot.isEmpty() Not got to yet");
    }

    @Override
    public boolean isLiquid() {
        throw new NotImplementedException("BlockSnapshot.isLiquid() Not got to yet");
    }

    @Override
    public double getTemperature() {
        throw new NotImplementedException("BlockSnapshot.getTemperature() Not got to yet");
    }

    @Override
    public double getHumidity() {
        throw new NotImplementedException("BlockSnapshot.getHumidity() Not got to yet");
    }

    @Override
    public @NotNull PistonMoveReaction getPistonMoveReaction() {
        throw new NotImplementedException("BlockSnapshot.getPistonMoveReaction() Not got to yet");
    }

    @Override
    public boolean breakNaturally() {
        throw new NotImplementedException("BlockSnapshot.breakNaturally() Not got to yet");
    }

    @Override
    public boolean breakNaturally(@Nullable ItemStack tool) {
        throw new NotImplementedException("BlockSnapshot.breakNaturally(ItemStack) Not got to yet");
    }

    @Override
    public boolean applyBoneMeal(@NotNull BlockFace face) {
        throw new NotImplementedException("BlockSnapshot.applyBoneMeal(BlockFace) Not got to yet");
    }

    @Override
    public @NotNull Collection<ItemStack> getDrops() {
        throw new NotImplementedException("BlockSnapshot.getDrops() Not got to yet");
    }

    @Override
    public @NotNull Collection<ItemStack> getDrops(@Nullable ItemStack tool) {
        throw new NotImplementedException("BlockSnapshot.getDrops(ItemStack) Not got to yet");
    }

    @NotNull
    @Override
    public Collection<ItemStack> getDrops(@NotNull ItemStack tool, @Nullable Entity entity) {
        throw new NotImplementedException("BlockSnapshot.getDrops(ItemStack, Entity) not got to yet");
    }

    @Override
    public boolean isPassable() {
        throw new NotImplementedException("BlockSnapshot.isPassable() Not got to yet");
    }

    @Override
    public @Nullable RayTraceResult rayTrace(@NotNull Location start, @NotNull Vector direction, double maxDistance, @NotNull FluidCollisionMode fluidCollisionMode) {
        throw new NotImplementedException("BlockSnapshot.rayTrace(Location, Vector, double, FluidCollisionMode) Not got to yet");
    }

    @Override
    public @NotNull BoundingBox getBoundingBox() {
        throw new NotImplementedException("BlockSnapshot.getBoundingBox() Not got to yet");
    }

    @Override
    public void setMetadata(@NotNull String metadataKey, @NotNull MetadataValue newMetadataValue) {
        throw new NotImplementedException("BlockSnapshot.setMetadata(String, MetadataValue) Not got to yet");
    }

    @Override
    public @NotNull List<MetadataValue> getMetadata(@NotNull String metadataKey) {
        throw new NotImplementedException("BlockSnapshot.getMetadata(String) Not got to yet");
    }

    @Override
    public boolean hasMetadata(@NotNull String metadataKey) {
        throw new NotImplementedException("BlockSnapshot.hasMetadata(String) Not got to yet");
    }

    @Override
    public void removeMetadata(@NotNull String metadataKey, @NotNull Plugin owningPlugin) {
        throw new NotImplementedException("BlockSnapshot.removeMetadata(String, Plugin) Not got to yet");
    }
}
