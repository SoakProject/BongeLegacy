package org.bonge.bukkit.world;

import com.flowpowered.math.vector.Vector3i;
import org.bonge.bukkit.block.BongeBlock;
import org.bonge.bukkit.entity.BongeAbstractEntity;
import org.bonge.bukkit.entity.living.human.BongePlayer;
import org.bonge.bukkit.world.chunk.BongeChunk;
import org.bonge.util.WrappedArrayList;
import org.bonge.wrapper.BongeWrapper;
import org.bukkit.*;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.*;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Consumer;
import org.bukkit.util.Vector;
import org.spongepowered.api.world.weather.Weathers;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class BongeWorld extends BongeWrapper<org.spongepowered.api.world.World> implements World {

    public BongeWorld(org.spongepowered.api.world.World world){
        super(world);
    }

    @Override
    public Block getBlockAt(int i, int i1, int i2) {
        return new BongeBlock(this.spongeValue.getLocation(i, i1, i2));
    }

    @Override
    public Block getBlockAt(Location location) {
        return new BongeBlock(new BongeLocation(location).getSpongeLocation());
    }

    @Override
    public Block getHighestBlockAt(int i, int i1) {
        return this.getBlockAt(i, this.getHighestBlockYAt(i, i1), i1);
    }

    @Override
    public Block getHighestBlockAt(Location location) {
        return new BongeBlock(new BongeLocation(location).getSpongeLocation().asHighestLocation());
    }

    @Override
    public int getHighestBlockYAt(int i, int i1) {
        return this.getHighestBlockAt(new BongeLocation(this, i, 0, i1)).getY();
    }

    @Override
    public int getHighestBlockYAt(Location location) {
        return this.getHighestBlockAt(location).getY();
    }

    @Override
    public Chunk getChunkAt(int i, int i1) {
        Optional<org.spongepowered.api.world.Chunk> opChunk = this.spongeValue.getChunk(i, 0, i1);
        if(!opChunk.isPresent()){
            return null;
        }
        return new BongeChunk(opChunk.get());
    }

    @Override
    public Chunk getChunkAt(Location location) {
        Optional<org.spongepowered.api.world.Chunk> opChunk = this.spongeValue.getChunkAtBlock(new BongeLocation(location).getSpongeLocation().getBlockPosition());
        if(!opChunk.isPresent()){
            return null;
        }
        return new BongeChunk(opChunk.get());
    }

    @Override
    public Chunk getChunkAt(Block block) {
        return this.getChunkAt(block.getLocation());
    }

    @Override
    public boolean isChunkLoaded(Chunk chunk) {
        return chunk.isLoaded();
    }

    @Override
    public Chunk[] getLoadedChunks() {
        Iterable<org.spongepowered.api.world.Chunk> chunks = this.spongeValue.getLoadedChunks();
        int count = 0;
        Iterator<org.spongepowered.api.world.Chunk> iter = chunks.iterator();
        while (iter.hasNext()){
            iter.next();
            count++;
        }
        Chunk[] bChunk = new Chunk[count];
        count = 0;
        iter = chunks.iterator();
        while (iter.hasNext()){
            bChunk[count] = new BongeChunk(iter.next());
            count++;
        }
        return bChunk;
    }

    @Override
    public void loadChunk(Chunk chunk) {
        chunk.load();
    }

    @Override
    public boolean isChunkLoaded(int i, int i1) {
        return this.spongeValue.getChunk(i, 0, i1).isPresent();
    }

    @Override
    public boolean isChunkGenerated(int i, int i1) {
        return false;
    }

    @Override
    public boolean isChunkInUse(int i, int i1) {
        return false;
    }

    @Override
    public void loadChunk(int i, int i1) {
        this.loadChunk(i, i1, false);
    }

    @Override
    public boolean loadChunk(int i, int i1, boolean b) {
        return this.spongeValue.loadChunk(i, 0, i1, b).isPresent();
    }

    @Override
    public boolean unloadChunk(Chunk chunk) {
        return chunk.unload();
    }

    @Override
    public boolean unloadChunk(int i, int i1) {
        return false;
    }

    @Override
    public boolean unloadChunk(int i, int i1, boolean b) {
        return false;
    }

    @Override
    public boolean unloadChunk(int x, int z, boolean save, boolean safe) {
        return false;
    }

    @Override
    public boolean unloadChunkRequest(int i, int i1) {
        return false;
    }

    @Override
    public boolean unloadChunkRequest(int x, int z, boolean safe) {
        return false;
    }

    @Override
    public boolean regenerateChunk(int i, int i1) {
        return false;
    }

    @Override
    public boolean refreshChunk(int i, int i1) {
        return false;
    }

    @Override
    public Item dropItem(Location location, ItemStack itemStack) {
        return null;
    }

    @Override
    public Item dropItemNaturally(Location location, ItemStack itemStack) {
        return null;
    }

    @Override
    public Arrow spawnArrow(Location location, Vector vector, float v, float v1) {
        return null;
    }

    @Override
    public <T extends Arrow> T spawnArrow(Location location, Vector direction, float speed, float spread, Class<T> clazz) {
        return null;
    }

    @Override
    public boolean generateTree(Location location, TreeType treeType) {
        return false;
    }

    @Override
    public boolean generateTree(Location location, TreeType treeType, BlockChangeDelegate blockChangeDelegate) {
        return false;
    }

    @Override
    public Entity spawnEntity(Location location, EntityType entityType) {
        return null;
    }

    @Override
    public LightningStrike strikeLightning(Location location) {
        return null;
    }

    @Override
    public LightningStrike strikeLightningEffect(Location location) {
        return null;
    }

    @Override
    public List<Entity> getEntities() {
        return new WrappedArrayList.Direct<>(e ->{
            if(e == null){
                return null;
            }
          return (((BongeAbstractEntity<?>)e).getSpongeValue());
        } , t -> BongeAbstractEntity.of(t), this.spongeValue.getEntities());
    }

    @Override
    public List<LivingEntity> getLivingEntities() {
        return null;
    }

    @Override
    public <T extends Entity> Collection<T> getEntitiesByClass(Class<T>... classes) {
        return null;
    }

    @Override
    public <T extends Entity> Collection<T> getEntitiesByClass(Class<T> aClass) {
        return null;
    }

    @Override
    public Collection<Entity> getEntitiesByClasses(Class<?>... classes) {
        return null;
    }

    @Override
    public List<Player> getPlayers() {
        return new WrappedArrayList.Direct<>(p -> (((BongePlayer)p).getSpongeValue()), t -> new BongePlayer(t), this.spongeValue.getPlayers());
    }

    @Override
    public Collection<Entity> getNearbyEntities(Location location, double v, double v1, double v2) {
        return null;
    }

    @Override
    public String getName() {
        return this.spongeValue.getName();
    }

    @Override
    public UUID getUID() {
        return this.spongeValue.getUniqueId();
    }

    @Override
    public Location getSpawnLocation() {
        return new BongeLocation(this.spongeValue.getSpawnLocation());
    }

    @Override
    public boolean setSpawnLocation(Location location) {
        this.spongeValue.getProperties().setSpawnPosition(new BongeLocation(location).getSpongeLocation().getBlockPosition());
        return true;
    }

    @Override
    public boolean setSpawnLocation(int i, int i1, int i2) {
        this.spongeValue.getProperties().setSpawnPosition(new Vector3i(i, i1, i2));
        return true;
    }

    @Override
    public long getTime() {
        return this.spongeValue.getProperties().getWorldTime();
    }

    @Override
    public void setTime(long l) {
        this.spongeValue.getProperties().setWorldTime(l);
    }

    @Override
    public long getFullTime() {
        return this.spongeValue.getProperties().getTotalTime();
    }

    @Override
    public void setFullTime(long l) {
        this.spongeValue.getProperties().setWorldTime(l);
    }

    @Override
    public boolean hasStorm() {
        return this.spongeValue.getProperties().isThundering();
    }

    @Override
    public void setStorm(boolean b) {
        this.spongeValue.getProperties().setThundering(b);
    }

    @Override
    public int getWeatherDuration() {
        if(this.spongeValue.getWeather().equals(Weathers.THUNDER_STORM)){
            return this.spongeValue.getProperties().getThunderTime();
        }else if(this.spongeValue.getWeather().equals(Weathers.RAIN)){
            return this.spongeValue.getProperties().getRainTime();
        }
        return 0;
    }

    @Override
    public void setWeatherDuration(int i) {
        if(this.spongeValue.getWeather().equals(Weathers.THUNDER_STORM)){
            this.spongeValue.getProperties().setThunderTime(i);
            return;
        }else if(this.spongeValue.getWeather().equals(Weathers.RAIN)){
            this.spongeValue.getProperties().setRainTime(i);
            return;
        }
    }

    @Override
    public boolean isThundering() {
        return false;
    }

    @Override
    public void setThundering(boolean b) {

    }

    @Override
    public int getThunderDuration() {
        return 0;
    }

    @Override
    public void setThunderDuration(int i) {

    }

    @Override
    public boolean createExplosion(double v, double v1, double v2, float v3) {
        return false;
    }

    @Override
    public boolean createExplosion(double v, double v1, double v2, float v3, boolean b) {
        return false;
    }

    @Override
    public boolean createExplosion(double v, double v1, double v2, float v3, boolean b, boolean b1) {
        return false;
    }

    @Override
    public boolean createExplosion(Location location, float v) {
        return false;
    }

    @Override
    public boolean createExplosion(Location location, float v, boolean b) {
        return false;
    }

    @Override
    public Environment getEnvironment() {
        return null;
    }

    @Override
    public long getSeed() {
        return this.spongeValue.getProperties().getSeed();
    }

    @Override
    public boolean getPVP() {
        return this.spongeValue.getProperties().isPVPEnabled();
    }

    @Override
    public void setPVP(boolean b) {
        this.spongeValue.getProperties().setPVPEnabled(b);
    }

    @Override
    public ChunkGenerator getGenerator() {
        return null;
    }

    @Override
    public void save() {
        try {
            this.spongeValue.save();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<BlockPopulator> getPopulators() {
        return null;
    }

    @Override
    public <T extends Entity> T spawn(Location location, Class<T> aClass) throws IllegalArgumentException {
        return null;
    }

    @Override
    public <T extends Entity> T spawn(Location location, Class<T> aClass, Consumer<T> consumer) throws IllegalArgumentException {
        return null;
    }

    @Override
    public FallingBlock spawnFallingBlock(Location location, MaterialData materialData) throws IllegalArgumentException {
        return null;
    }

    @Override
    public FallingBlock spawnFallingBlock(Location location, BlockData blockData) throws IllegalArgumentException {
        return null;
    }

    @Override
    public FallingBlock spawnFallingBlock(Location location, Material material, byte b) throws IllegalArgumentException {
        return null;
    }

    @Override
    public void playEffect(Location location, Effect effect, int i) {

    }

    @Override
    public void playEffect(Location location, Effect effect, int i, int i1) {

    }

    @Override
    public <T> void playEffect(Location location, Effect effect, T t) {

    }

    @Override
    public <T> void playEffect(Location location, Effect effect, T t, int i) {

    }

    @Override
    public ChunkSnapshot getEmptyChunkSnapshot(int i, int i1, boolean b, boolean b1) {
        return null;
    }

    @Override
    public void setSpawnFlags(boolean b, boolean b1) {

    }

    @Override
    public boolean getAllowAnimals() {
        return false;
    }

    @Override
    public boolean getAllowMonsters() {
        return false;
    }

    @Override
    public Biome getBiome(int i, int i1) {
        return null;
    }

    @Override
    public void setBiome(int i, int i1, Biome biome) {

    }

    @Override
    public double getTemperature(int i, int i1) {
        return 0;
    }

    @Override
    public double getHumidity(int i, int i1) {
        return 0;
    }

    @Override
    public int getMaxHeight() {
        return 0;
    }

    @Override
    public int getSeaLevel() {
        return 0;
    }

    @Override
    public boolean getKeepSpawnInMemory() {
        return false;
    }

    @Override
    public void setKeepSpawnInMemory(boolean b) {

    }

    @Override
    public boolean isAutoSave() {
        return false;
    }

    @Override
    public void setAutoSave(boolean b) {

    }

    @Override
    public void setDifficulty(Difficulty difficulty) {

    }

    @Override
    public Difficulty getDifficulty() {
        return null;
    }

    @Override
    public File getWorldFolder() {
        return null;
    }

    @Override
    public WorldType getWorldType() {
        return null;
    }

    @Override
    public boolean canGenerateStructures() {
        return false;
    }

    @Override
    public long getTicksPerAnimalSpawns() {
        return 0;
    }

    @Override
    public void setTicksPerAnimalSpawns(int i) {

    }

    @Override
    public long getTicksPerMonsterSpawns() {
        return 0;
    }

    @Override
    public void setTicksPerMonsterSpawns(int i) {

    }

    @Override
    public int getMonsterSpawnLimit() {
        return 0;
    }

    @Override
    public void setMonsterSpawnLimit(int i) {

    }

    @Override
    public int getAnimalSpawnLimit() {
        return 0;
    }

    @Override
    public void setAnimalSpawnLimit(int i) {

    }

    @Override
    public int getWaterAnimalSpawnLimit() {
        return 0;
    }

    @Override
    public void setWaterAnimalSpawnLimit(int i) {

    }

    @Override
    public int getAmbientSpawnLimit() {
        return 0;
    }

    @Override
    public void setAmbientSpawnLimit(int i) {

    }

    @Override
    public void playSound(Location location, Sound sound, float v, float v1) {

    }

    @Override
    public void playSound(Location location, String s, float v, float v1) {

    }

    @Override
    public void playSound(Location location, Sound sound, SoundCategory soundCategory, float v, float v1) {

    }

    @Override
    public void playSound(Location location, String s, SoundCategory soundCategory, float v, float v1) {

    }

    @Override
    public String[] getGameRules() {
        return new String[0];
    }

    @Override
    public String getGameRuleValue(String s) {
        return null;
    }

    @Override
    public boolean setGameRuleValue(String s, String s1) {
        return false;
    }

    @Override
    public boolean isGameRule(String s) {
        return false;
    }

    @Override
    public <T> T getGameRuleValue(GameRule<T> gameRule) {
        return null;
    }

    @Override
    public <T> T getGameRuleDefault(GameRule<T> gameRule) {
        return null;
    }

    @Override
    public <T> boolean setGameRule(GameRule<T> gameRule, T t) {
        return false;
    }

    @Override
    public WorldBorder getWorldBorder() {
        return null;
    }

    @Override
    public void spawnParticle(Particle particle, Location location, int i) {

    }

    @Override
    public void spawnParticle(Particle particle, double v, double v1, double v2, int i) {

    }

    @Override
    public <T> void spawnParticle(Particle particle, Location location, int i, T t) {

    }

    @Override
    public <T> void spawnParticle(Particle particle, double v, double v1, double v2, int i, T t) {

    }

    @Override
    public void spawnParticle(Particle particle, Location location, int i, double v, double v1, double v2) {

    }

    @Override
    public void spawnParticle(Particle particle, double v, double v1, double v2, int i, double v3, double v4, double v5) {

    }

    @Override
    public <T> void spawnParticle(Particle particle, Location location, int i, double v, double v1, double v2, T t) {

    }

    @Override
    public <T> void spawnParticle(Particle particle, double v, double v1, double v2, int i, double v3, double v4, double v5, T t) {

    }

    @Override
    public void spawnParticle(Particle particle, Location location, int i, double v, double v1, double v2, double v3) {

    }

    @Override
    public void spawnParticle(Particle particle, double v, double v1, double v2, int i, double v3, double v4, double v5, double v6) {

    }

    @Override
    public <T> void spawnParticle(Particle particle, Location location, int i, double v, double v1, double v2, double v3, T t) {

    }

    @Override
    public <T> void spawnParticle(Particle particle, double v, double v1, double v2, int i, double v3, double v4, double v5, double v6, T t) {

    }

    @Override
    public <T> void spawnParticle(Particle particle, Location location, int i, double v, double v1, double v2, double v3, T t, boolean b) {

    }

    @Override
    public <T> void spawnParticle(Particle particle, double v, double v1, double v2, int i, double v3, double v4, double v5, double v6, T t, boolean b) {

    }

    @Override
    public Location locateNearestStructure(Location location, StructureType structureType, int i, boolean b) {
        return null;
    }

    @Override
    public void setMetadata(String s, MetadataValue metadataValue) {

    }

    @Override
    public List<MetadataValue> getMetadata(String s) {
        return null;
    }

    @Override
    public boolean hasMetadata(String s) {
        return false;
    }

    @Override
    public void removeMetadata(String s, Plugin plugin) {

    }

    @Override
    public void sendPluginMessage(Plugin plugin, String s, byte[] bytes) {

    }

    @Override
    public Set<String> getListeningPluginChannels() {
        return null;
    }
}
