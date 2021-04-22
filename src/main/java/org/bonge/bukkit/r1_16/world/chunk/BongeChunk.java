package org.bonge.bukkit.r1_16.world.chunk;

import org.bonge.Bonge;
import org.bonge.bukkit.r1_16.block.BongeBlock;
import org.bonge.bukkit.r1_16.world.BongeWorld;
import org.bonge.util.exception.NotImplementedException;
import org.bonge.wrapper.BongeWrapper;
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
import org.spongepowered.api.world.server.ServerWorld;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class BongeChunk extends BongeWrapper<org.spongepowered.api.world.chunk.Chunk> implements Chunk {

    public BongeChunk(org.spongepowered.api.world.chunk.Chunk value) {
        super(value);
    }

    @Override
    public int getX() {
        return this.spongeValue.chunkPosition().getX();
    }

    @Override
    public int getZ() {
        return this.spongeValue.chunkPosition().getZ();
    }

    @Override
    public @NotNull World getWorld() {
        return new BongeWorld(this.spongeValue.world());
    }

    @Override
    public @NotNull Block getBlock(int x, int y, int z) {
        return new BongeBlock(this.spongeValue.world().location(this.spongeValue.blockMin().add(x, y, z)));
    }

    @Override
    public @NotNull ChunkSnapshot getChunkSnapshot() {
        return null;
    }

    @Override
    public @NotNull ChunkSnapshot getChunkSnapshot(boolean includeMaxblocky, boolean includeBiome, boolean includeBiomeTempRain) {
        return null;
    }

    @Override
    public Entity[] getEntities() {
        if(!(this.spongeValue.world() instanceof ServerWorld)){
            return new Entity[0];
        }
        Collection<org.spongepowered.api.entity.Entity> sEntities = ((ServerWorld)this.spongeValue.world()).entities().stream().filter(e -> this.spongeValue.containsBlock(e.blockPosition())).collect(Collectors.toSet());
        Entity[] entities = new Entity[sEntities.size()];
        int count = 0;
        for (org.spongepowered.api.entity.Entity sEntity : sEntities) {
            try {
                entities[count] = Bonge.getInstance().convert(Entity.class, sEntity);
                count++;
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return entities;
    }

    @Override
    public BlockState[] getTileEntities() {
        List<BlockState> array = new ArrayList<>();
        this.spongeValue.blockEntities().forEach(t -> {
            try {
                array.add(Bonge.getInstance().convert(BlockState.class, t));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return array.toArray(new BlockState[0]);
    }

    @Override
    public boolean isLoaded() {
        return this.spongeValue.world().isChunkLoaded(this.spongeValue.chunkPosition(), true);
    }

    @Override
    public boolean load(boolean generate) {
        return this.spongeValue.loadChunk(generate);
    }

    @Override
    public boolean load() {
        return this.spongeValue.loadChunk(false);
    }

    @Override
    public boolean unload(boolean save) {
        return unload();
    }

    @Override
    public boolean unload() {
        return this.spongeValue.unloadChunk();
    }

    @Override
    public boolean isSlimeChunk() {
        return false;
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
        return this.spongeValue.inhabitedTime();
    }

    @Override
    public void setInhabitedTime(long ticks) {
        this.spongeValue.setInhabitedTime(ticks);
    }

    @Override
    public boolean contains(@NotNull BlockData block) {
        return false;
    }

    @NotNull
    @Override
    public PersistentDataContainer getPersistentDataContainer() {
        throw new NotImplementedException("yet to look at");
    }
}
