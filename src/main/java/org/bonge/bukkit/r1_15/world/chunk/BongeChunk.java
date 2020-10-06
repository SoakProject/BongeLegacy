package org.bonge.bukkit.r1_15.world.chunk;

import org.bonge.Bonge;
import org.bonge.bukkit.r1_15.block.BongeBlock;
import org.bonge.bukkit.r1_15.world.BongeWorld;
import org.bonge.util.exception.NotImplementedException;
import org.bonge.wrapper.BongeWrapper;
import org.bukkit.Chunk;
import org.bukkit.ChunkSnapshot;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Entity;
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
        return this.spongeValue.getChunkPosition().getX();
    }

    @Override
    public int getZ() {
        return this.spongeValue.getChunkPosition().getZ();
    }

    @Override
    public @NotNull World getWorld() {
        return new BongeWorld(this.spongeValue.getWorld());
    }

    @Override
    public @NotNull Block getBlock(int x, int y, int z) {
        return new BongeBlock(this.spongeValue.getWorld().getLocation(this.spongeValue.getBlockMin().add(x, y, z)));
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
        if(!(this.spongeValue.getWorld() instanceof ServerWorld)){
            return new Entity[0];
        }
        Collection<org.spongepowered.api.entity.Entity> sEntities = ((ServerWorld)this.spongeValue.getWorld()).getEntities().stream().filter(e -> this.spongeValue.containsBlock(e.getBlockPosition())).collect(Collectors.toSet());
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
        this.spongeValue.getBlockEntities().forEach(t -> {
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
        return this.spongeValue.getWorld().isChunkLoaded(this.spongeValue.getChunkPosition(), true);
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
        return this.spongeValue.getInhabitedTime();
    }

    @Override
    public void setInhabitedTime(long ticks) {
        this.spongeValue.setInhabitedTime(ticks);
    }

    @Override
    public boolean contains(@NotNull BlockData block) {
        return false;
    }
}
