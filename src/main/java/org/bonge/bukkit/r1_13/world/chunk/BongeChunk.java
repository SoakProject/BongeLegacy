package org.bonge.bukkit.r1_13.world.chunk;

import org.bonge.Bonge;
import org.bonge.bukkit.r1_13.block.BongeBlock;
import org.bonge.bukkit.r1_13.world.BongeWorld;
import org.bonge.wrapper.BongeWrapper;
import org.bukkit.Chunk;
import org.bukkit.ChunkSnapshot;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Entity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BongeChunk extends BongeWrapper<org.spongepowered.api.world.Chunk> implements Chunk {

    public BongeChunk(org.spongepowered.api.world.Chunk value) {
        super(value);
    }

    @Override
    public int getX() {
        return this.spongeValue.getPosition().getX();
    }

    @Override
    public int getZ() {
        return this.spongeValue.getPosition().getZ();
    }

    @Override
    public World getWorld() {
        return new BongeWorld(this.spongeValue.getWorld());
    }

    @Override
    public Block getBlock(int x, int y, int z) {
        return new BongeBlock(this.spongeValue.getWorld().getLocation(this.spongeValue.getBlockMin().add(x, y, z)));
    }

    @Override
    public ChunkSnapshot getChunkSnapshot() {
        return null;
    }

    @Override
    public ChunkSnapshot getChunkSnapshot(boolean includeMaxblocky, boolean includeBiome, boolean includeBiomeTempRain) {
        return null;
    }

    @Override
    public Entity[] getEntities() {
        Collection<org.spongepowered.api.entity.Entity> sEntities = this.spongeValue.getEntities();
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
        this.spongeValue.getTileEntities().forEach(t -> {
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
        return this.spongeValue.isLoaded();
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
    public boolean unload(boolean save, boolean safe) {
        return unload();
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
}
