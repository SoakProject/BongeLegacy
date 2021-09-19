package org.bonge.bukkit.r1_16.world.chunk;

import org.bonge.Bonge;
import org.bonge.bukkit.r1_16.world.BongeWorld;
import org.bonge.util.exception.NotImplementedException;
import org.bukkit.Chunk;
import org.bukkit.ChunkSnapshot;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Entity;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.util.AABB;
import org.spongepowered.api.util.Ticks;
import org.spongepowered.api.world.chunk.WorldChunk;
import org.spongepowered.math.vector.Vector3i;

import java.io.IOException;
import java.util.Collection;
import java.util.Objects;

public class BongeChunk implements Chunk {

    private final Vector3i location;
    private final BongeWorld world;

    public BongeChunk(@NotNull Chunk chunk) {
        this(new Vector3i(chunk.getX(), 0, chunk.getZ()), (BongeWorld) chunk.getWorld());
    }

    public BongeChunk(@NotNull WorldChunk chunk) {
        this(chunk.chunkPosition(), (BongeWorld) Bonge.getInstance().convert(chunk.world()));
    }

    public BongeChunk(@NotNull Vector3i location, @NotNull BongeWorld world) {
        this.location = location;
        this.world = world;
    }

    @Override
    public int getX() {
        return this.location.x();
    }

    @Override
    public int getZ() {
        return this.location.y();
    }

    @Override
    public @NotNull World getWorld() {
        return this.world;
    }

    @Override
    public @NotNull Block getBlock(int x, int y, int z) {
        throw new NotImplementedException("Not got to yet");
    }

    @Override
    public @NotNull ChunkSnapshot getChunkSnapshot() {
        throw new NotImplementedException("Not got to yet");
    }

    @Override
    public @NotNull ChunkSnapshot getChunkSnapshot(boolean includeMaxblocky, boolean includeBiome, boolean includeBiomeTempRain) {
        throw new NotImplementedException("Not got to yet");
    }

    @Override
    public Entity[] getEntities() {
        WorldChunk chunk = this.world.getSpongeValue().chunk(this.location.x(), 0, this.location.y());
        Collection<? extends org.spongepowered.api.entity.Entity> sEntities = chunk.entities(AABB.of(chunk.min(), chunk.max()));
        return sEntities.stream().map(sEntity -> {
            try {
                return Bonge.getInstance().convert(sEntity);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }).filter(Objects::nonNull).toArray(Entity[]::new);
    }

    @Override
    public BlockState[] getTileEntities() {
        throw new NotImplementedException("Not got to yet");
        /*WorldChunk chunk = this.world.getSpongeValue().chunk(this.location.x(), 0, this.location.y());
        Collection<? extends BlockEntity> blockEntity = chunk.blockEntities();
        return blockEntity.stream().map(entity -> Bonge.getInstance().convert(entity)).toArray(Object[]::new);*/
    }

    @Override
    public boolean isLoaded() {
        return this.world.getSpongeValue().isChunkLoaded(this.location.x(), 0, this.location.y(), true);
    }

    @Override
    public boolean load(boolean generate) {
        return this.world.getSpongeValue().loadChunk(this.location, generate).isPresent();
    }

    @Override
    public boolean load() {
        return this.world.getSpongeValue().loadChunk(this.location, false).isPresent();
    }

    @Override
    public boolean unload(boolean save) {
        return unload();
    }

    @Override
    public boolean unload() {
        throw new NotImplementedException("Not implemented yet");
    }

    @Override
    public boolean isSlimeChunk() {
        throw new NotImplementedException("Not got to yet");
    }

    @Override
    public boolean isForceLoaded() {
        throw new NotImplementedException("Chunk.isForceLoaded() does not have a Sponge equivalent");
    }

    @Override
    public void setForceLoaded(boolean forced) {
        throw new NotImplementedException("Chunk.setForcedLoaded(boolean) does not have a Sponge equivalent");
    }

    @Override
    public boolean addPluginChunkTicket(@NotNull Plugin plugin) {
        throw new NotImplementedException("Chunk.addPluginChunkTicket(Plugin) not looked at");
    }

    @Override
    public boolean removePluginChunkTicket(@NotNull Plugin plugin) {
        throw new NotImplementedException("Chunk.removePluginChunkTicket(Plugin) not looked at");
    }

    @Override
    public @NotNull Collection<Plugin> getPluginChunkTickets() {
        throw new NotImplementedException("Chunk.getPluginChunkTickets() not looked at");
    }

    @Override
    public long getInhabitedTime() {
        WorldChunk chunk = this.world.getSpongeValue().chunk(this.location);
        return chunk.inhabitedTime().ticks();
    }

    @Override
    public void setInhabitedTime(long ticks) {
        WorldChunk chunk = this.world.getSpongeValue().chunk(this.location);
        chunk.setInhabitedTime(Ticks.of(ticks));
    }

    @Override
    public boolean contains(@NotNull BlockData block) {
        throw new NotImplementedException("Not got to yet");
    }

    @NotNull
    @Override
    public PersistentDataContainer getPersistentDataContainer() {
        throw new NotImplementedException("yet to look at");
    }
}
