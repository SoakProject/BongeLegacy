package org.bonge.bukkit.r1_13.world;

import com.flowpowered.math.vector.Vector3d;
import com.flowpowered.math.vector.Vector3i;
import org.bonge.Bonge;
import org.bonge.bukkit.r1_13.block.BongeBlock;
import org.bonge.bukkit.r1_13.block.data.BongeAbstractBlockData;
import org.bonge.bukkit.r1_13.entity.BongeAbstractEntity;
import org.bonge.bukkit.r1_13.entity.living.human.BongePlayer;
import org.bonge.bukkit.r1_13.entity.other.arrow.BongeAbstractArrowEntity;
import org.bonge.bukkit.r1_13.entity.other.arrow.BongeTippedArrowEntity;
import org.bonge.bukkit.r1_13.entity.other.item.BongeItem;
import org.bonge.bukkit.r1_13.world.chunk.BongeChunk;
import org.bonge.convert.InventoryConvert;
import org.bonge.util.ArrayUtils;
import org.bonge.util.WrappedArrayList;
import org.bonge.util.exception.NotImplementedException;
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
import org.spongepowered.api.Sponge;
import org.spongepowered.api.block.BlockState;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.entity.EntityTypes;
import org.spongepowered.api.item.inventory.ItemStackSnapshot;
import org.spongepowered.api.world.GeneratorTypes;
import org.spongepowered.api.world.explosion.Explosion;
import org.spongepowered.api.world.weather.Weathers;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        return opChunk.map(BongeChunk::new).orElse(null);
    }

    @Override
    public Chunk getChunkAt(Location location) {
        Optional<org.spongepowered.api.world.Chunk> opChunk = this.spongeValue.getChunkAtBlock(new BongeLocation(location).getSpongeLocation().getBlockPosition());
        return opChunk.map(BongeChunk::new).orElse(null);
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
        throw new NotImplementedException("Not got to yet");
    }

    @Override
    public boolean isChunkInUse(int i, int i1) {
        throw new NotImplementedException("Not got to yet");
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
        Optional<org.spongepowered.api.world.Chunk> opChunk = this.spongeValue.getChunk(i, 0, i1);
        return opChunk.filter(chunk -> this.spongeValue.unloadChunk(chunk)).isPresent();
    }

    @Override
    public boolean unloadChunk(int i, int i1, boolean b) {
        throw new NotImplementedException("Not got to yet");
    }

    @Override
    public boolean unloadChunk(int x, int z, boolean save, boolean safe) {
        throw new NotImplementedException("Not got to yet");
    }

    @Override
    public boolean unloadChunkRequest(int i, int i1) {
        throw new NotImplementedException("Not got to yet");
    }

    @Override
    public boolean unloadChunkRequest(int x, int z, boolean safe) {
        throw new NotImplementedException("Not got to yet");
    }

    @Override
    public boolean regenerateChunk(int i, int i1) {
        return this.spongeValue.regenerateChunk(i, 0, i1).isPresent();
    }

    @Override
    public boolean refreshChunk(int i, int i1) {
        if (!this.unloadChunk(i, i1)){
            return false;
        }
        this.loadChunk(i, i1);
        return true;
    }

    @Override
    public BongeItem dropItem(Location location, ItemStack itemStack) {
        org.spongepowered.api.entity.Item item = (org.spongepowered.api.entity.Item)this.spongeValue.createEntity(EntityTypes.ITEM, new Vector3i(location.getBlockX(), location.getBlockY(), location.getBlockZ()));
        try {
            item.offer(Keys.REPRESENTED_ITEM, Bonge.getInstance().convert(itemStack, ItemStackSnapshot.class));
        } catch (IOException e) {
        }
        this.spongeValue.spawnEntity(item);
        return new BongeItem(item);
    }

    @Override
    public BongeItem dropItemNaturally(Location location, ItemStack itemStack) {
        BongeItem item = this.dropItem(location, itemStack);
        item.getSpongeValue().offer(Keys.VELOCITY, new Vector3d(0.3, 0.7, 0.1));
        return item;
    }

    @Override
    public Arrow spawnArrow(Location location, Vector vector, float v, float v1) {
        return this.spawnArrow(location, vector, v, v1, TippedArrow.class);
    }

    @Override
    public <T extends Arrow> T spawnArrow(Location location, Vector direction, float speed, float spread, Class<T> clazz) {
        BongeAbstractArrowEntity<?> arrow = null;
        if(clazz.isAssignableFrom(SpectralArrow.class)){
            //TODO
        }else{
            arrow = new BongeTippedArrowEntity((org.spongepowered.api.entity.projectile.arrow.TippedArrow) this.spongeValue.createEntity(EntityTypes.TIPPED_ARROW, new Vector3d(location.getX(), location.getY(), location.getZ())));
        }
        arrow.getSpongeValue().setRotation(new Vector3d(direction.getX(), direction.getY(), direction.getZ()));
        arrow.getSpongeValue().setVelocity(new Vector3d(direction.getX(), direction.getY(), direction.getZ()).mul(speed));
        //TODO SPREAD
        return (T)arrow;
    }

    @Override
    public boolean generateTree(Location location, TreeType treeType) {
        throw new NotImplementedException("Not got to yet");
    }

    @Override
    public boolean generateTree(Location location, TreeType treeType, BlockChangeDelegate blockChangeDelegate) {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    public Entity spawnEntity(Location location, EntityType entityType) {
        try {
            org.spongepowered.api.world.Location<org.spongepowered.api.world.World> loc = Bonge.getInstance().convert(location, org.spongepowered.api.world.Location.class);
            org.spongepowered.api.entity.Entity entity = loc.createEntity(Bonge.getInstance().convert(entityType, org.spongepowered.api.entity.EntityType.class));
            loc.spawnEntity(entity);
            return Bonge.getInstance().convert(Entity.class, entity);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public LightningStrike strikeLightning(Location location) {
        throw new NotImplementedException("Not got to yet");
    }

    @Override
    public LightningStrike strikeLightningEffect(Location location) {
        throw new NotImplementedException("Not got to yet");
    }

    @Override
    public List<Entity> getEntities() {
        return new WrappedArrayList.Direct<>(e ->{
            if(e == null){
                return null;
            }
          return (((BongeAbstractEntity<?>)e).getSpongeValue());
        }, e -> {
            try {
                return Bonge.getInstance().convert(Entity.class, e);
            } catch (IOException ioException) {
                throw new IllegalArgumentException(ioException);
            }
        }, this.spongeValue.getEntities());
    }

    @Override
    public List<LivingEntity> getLivingEntities() {
        return new ArrayList<>(this.getEntitiesByClass(LivingEntity.class));
    }

    @SafeVarargs
    @Override
    public final <T extends Entity> Collection<T> getEntitiesByClass(Class<T>... classes) {
        return ArrayUtils.convert(e -> (T)e, this.getEntitiesByClasses(classes));
    }

    @Override
    public <T extends Entity> Collection<T> getEntitiesByClass(Class<T> aClass) {
        return ArrayUtils.convert(e -> (T)e, this.getEntities().stream().filter(aClass::isInstance).collect(Collectors.toSet()));
    }

    @Override
    public Collection<Entity> getEntitiesByClasses(Class<?>... classes) {
        return this.getEntities().stream().filter(e -> {
            for(Class<?> clazz : classes){
                if(clazz.isInstance(e)){
                    return true;
                }
            }
            return false;
        }).collect(Collectors.toSet());
    }

    @Override
    public List<Player> getPlayers() {
        return new WrappedArrayList.Direct<>(p -> (((BongePlayer)p).getSpongeValue()), BongePlayer::new, this.spongeValue.getPlayers());
    }

    @Override
    public Collection<Entity> getNearbyEntities(Location location, double v, double v1, double v2) {
        if(v == v1 && v1 == v2){
            return new WrappedArrayList.Direct<>(e -> {
                try {
                    return Bonge.getInstance().convert(e, org.spongepowered.api.entity.Entity.class);
                } catch (IOException ioException) {
                    throw new IllegalArgumentException(ioException);
                }
            }, e -> {
                try {
                    return Bonge.getInstance().convert(Entity.class, e);
                } catch (IOException ioException) {
                    throw new IllegalArgumentException(ioException);
                }
            }, this.spongeValue.getNearbyEntities(new Vector3d(location.getX(), location.getY(), location.getZ()), v));
        }
        throw new NotImplementedException("Not got to yet");
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
        }else if(this.spongeValue.getWeather().equals(Weathers.RAIN)){
            this.spongeValue.getProperties().setRainTime(i);
        }
    }

    @Override
    public boolean isThundering() {
        return this.spongeValue.getWeather().equals(Weathers.THUNDER_STORM);
    }

    @Override
    public void setThundering(boolean b) {
        if(b){
            this.spongeValue.setWeather(Weathers.THUNDER_STORM);
            return;
        }
        this.spongeValue.setWeather(Weathers.CLEAR);

    }

    @Override
    public int getThunderDuration() {
        return (int)this.spongeValue.getRemainingDuration();
    }

    @Override
    public void setThunderDuration(int i) {
        this.spongeValue.setWeather(Weathers.THUNDER_STORM, i);
    }

    @Override
    public boolean createExplosion(double x, double y, double z, float power) {
        return this.createExplosion(x, y, z, power, true, true);
    }

    @Override
    public boolean createExplosion(double x, double y, double z, float power, boolean setFire) {
        return this.createExplosion(x, y, z, power, setFire, true);
    }

    @Override
    public boolean createExplosion(double x, double y, double z, float power, boolean setFire, boolean breakBlocks) {
        Explosion exp = Explosion.builder().canCauseFire(setFire).shouldBreakBlocks(breakBlocks).radius(power).location(this.spongeValue.getLocation(x, y, z)).build();
        this.spongeValue.triggerExplosion(exp);
        return true;
    }

    @Override
    public boolean createExplosion(Location location, float power) {
        return this.createExplosion(location, power, true);
    }

    @Override
    public boolean createExplosion(Location location, float power, boolean setFire) {
        return this.createExplosion(location.getX(), location.getY(), location.getZ(), power, setFire);
    }

    @Override
    public Environment getEnvironment() {
        try {
            return Bonge.getInstance().convert(Environment.class, this.spongeValue.getDimension().getType());
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
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
        throw new NotImplementedException("Not got to yet");
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
        throw new NotImplementedException("Not got to yet");
    }

    @Override
    public <T extends Entity> T spawn(Location location, Class<T> aClass) throws IllegalArgumentException {
        throw new NotImplementedException("Not got to yet");
    }

    @Override
    public <T extends Entity> T spawn(Location location, Class<T> aClass, Consumer<T> consumer) throws IllegalArgumentException {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    @Deprecated
    public FallingBlock spawnFallingBlock(Location location, MaterialData materialData) throws IllegalArgumentException {
        return this.spawnFallingBlock(location, materialData.getSpongeValue());
    }

    @Override
    public FallingBlock spawnFallingBlock(Location location, BlockData blockData) throws IllegalArgumentException {
        try {
            return this.spawnFallingBlock(location, Bonge.getInstance().convert(blockData, BlockState.class));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public FallingBlock spawnFallingBlock(Location loc, BlockState state){
        org.spongepowered.api.entity.FallingBlock block = (org.spongepowered.api.entity.FallingBlock) this.spongeValue.createEntity(EntityTypes.FALLING_BLOCK, new Vector3d(loc.getX(), loc.getY(), loc.getZ()));
        block.offer(Keys.REPRESENTED_BLOCK, state);
        try {
            return (FallingBlock) Bonge.getInstance().convert(Entity.class, block);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Deprecated
    @Override
    public FallingBlock spawnFallingBlock(Location location, Material material, byte b) throws IllegalArgumentException {
        return this.spawnFallingBlock(location, material.createBlockData());
    }

    @Override
    public void playEffect(Location location, Effect effect, int i) {
        //throw new NotImplementedException("Not got to yet");
    }

    @Override
    public void playEffect(Location location, Effect effect, int i, int i1) {
        //throw new NotImplementedException("Not got to yet");
    }

    @Override
    public <T> void playEffect(Location location, Effect effect, T t) {
        //throw new NotImplementedException("Not got to yet");
    }

    @Override
    public <T> void playEffect(Location location, Effect effect, T t, int i) {
        //throw new NotImplementedException("Not got to yet");
    }

    @Override
    public ChunkSnapshot getEmptyChunkSnapshot(int i, int i1, boolean b, boolean b1) {
        throw new NotImplementedException("Not got to yet");
    }

    @Override
    public void setSpawnFlags(boolean b, boolean b1) {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    public boolean getAllowAnimals() {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    public boolean getAllowMonsters() {
        throw new NotImplementedException("Not got to yet");
    }

    @Override
    public Biome getBiome(int i, int i1) {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    public void setBiome(int i, int i1, Biome biome) {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    public double getTemperature(int i, int i1) {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    public double getHumidity(int i, int i1) {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    public int getMaxHeight() {
        return this.spongeValue.getBlockMax().getY();
    }

    @Override
    public int getSeaLevel() {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    public boolean getKeepSpawnInMemory() {
        return this.spongeValue.getProperties().doesKeepSpawnLoaded();
    }

    @Override
    public void setKeepSpawnInMemory(boolean b) {
        this.spongeValue.getProperties().setKeepSpawnLoaded(b);
    }

    @Override
    public boolean isAutoSave() {
        throw new NotImplementedException("Not got to yet");
    }

    @Override
    public void setAutoSave(boolean b) {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    public void setDifficulty(Difficulty difficulty) {
        try {
            this.spongeValue.getProperties().setDifficulty(Bonge.getInstance().convert(difficulty, org.spongepowered.api.world.difficulty.Difficulty.class));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public Difficulty getDifficulty() {
        try {
            return Bonge.getInstance().convert(Difficulty.class, this.spongeValue.getDifficulty());
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

    }

    @Override
    public File getWorldFolder() {
        return this.spongeValue.getDirectory().toFile();
    }

    @Override
    public WorldType getWorldType(){
        try {
            return Bonge.getInstance().convert(WorldType.class, this.spongeValue.getDimension().getGeneratorType());
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public boolean canGenerateStructures() {
        //TODO NOT SURE IF THIS IS CORRECT
        return this.spongeValue.getWorldGenerator().getGenerationPopulators().isEmpty();
    }

    @Override
    public long getTicksPerAnimalSpawns() {
        throw new NotImplementedException("Not got to yet");
    }

    @Override
    public void setTicksPerAnimalSpawns(int i) {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    public long getTicksPerMonsterSpawns() {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    public void setTicksPerMonsterSpawns(int i) {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    public int getMonsterSpawnLimit() {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    public void setMonsterSpawnLimit(int i) {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    public int getAnimalSpawnLimit() {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    public void setAnimalSpawnLimit(int i) {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    public int getWaterAnimalSpawnLimit(){
        throw new NotImplementedException("Not got to yet");
    }

    @Override
    public void setWaterAnimalSpawnLimit(int i) {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    public int getAmbientSpawnLimit() {
        throw new NotImplementedException("Not got to yet");
    }

    @Override
    public void setAmbientSpawnLimit(int i) {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    public void playSound(Location location, Sound sound, float v, float v1) {
        //throw new NotImplementedException("Not got to yet");
    }

    @Override
    public void playSound(Location location, String s, float v, float v1) {
        //throw new NotImplementedException("Not got to yet");
    }

    @Override
    public void playSound(Location location, Sound sound, SoundCategory soundCategory, float v, float v1) {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    public void playSound(Location location, String s, SoundCategory soundCategory, float v, float v1) {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    public String[] getGameRules() {
        return this.spongeValue.getGameRules().keySet().toArray(new String[0]);
    }

    @Override
    public String getGameRuleValue(String s) {
        return this.spongeValue.getGameRule(s).orElse(null);
    }

    @Override
    public boolean setGameRuleValue(String s, String s1) {
        if(this.spongeValue.getGameRules().containsKey(s)) {
            this.spongeValue.getGameRules().replace(s, s1);
            return true;
        }
        this.spongeValue.getGameRules().put(s, s1);
        return true;
    }

    @Override
    public boolean isGameRule(String s) {
        return Stream.of(GameRule.values()).anyMatch(v -> v.getName().equalsIgnoreCase(s));
    }

    @Override
    public <T> T getGameRuleValue(GameRule<T> gameRule) {
        throw new NotImplementedException("Not got to yet");
    }

    @Override
    public <T> T getGameRuleDefault(GameRule<T> gameRule) {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    public <T> boolean setGameRule(GameRule<T> gameRule, T t) {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    public WorldBorder getWorldBorder() {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    public void spawnParticle(Particle particle, Location location, int i) {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    public void spawnParticle(Particle particle, double v, double v1, double v2, int i) {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    public <T> void spawnParticle(Particle particle, Location location, int i, T t) {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    public <T> void spawnParticle(Particle particle, double v, double v1, double v2, int i, T t) {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    public void spawnParticle(Particle particle, Location location, int i, double v, double v1, double v2) {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    public void spawnParticle(Particle particle, double v, double v1, double v2, int i, double v3, double v4, double v5) {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    public <T> void spawnParticle(Particle particle, Location location, int i, double v, double v1, double v2, T t) {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    public <T> void spawnParticle(Particle particle, double v, double v1, double v2, int i, double v3, double v4, double v5, T t) {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    public void spawnParticle(Particle particle, Location location, int i, double v, double v1, double v2, double v3) {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    public void spawnParticle(Particle particle, double v, double v1, double v2, int i, double v3, double v4, double v5, double v6) {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    public <T> void spawnParticle(Particle particle, Location location, int i, double v, double v1, double v2, double v3, T t) {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    public <T> void spawnParticle(Particle particle, double v, double v1, double v2, int i, double v3, double v4, double v5, double v6, T t) {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    public <T> void spawnParticle(Particle particle, Location location, int i, double v, double v1, double v2, double v3, T t, boolean b) {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    public <T> void spawnParticle(Particle particle, double v, double v1, double v2, int i, double v3, double v4, double v5, double v6, T t, boolean b) {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    public Location locateNearestStructure(Location location, StructureType structureType, int i, boolean b) {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    public void setMetadata(String s, MetadataValue metadataValue) {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    public List<MetadataValue> getMetadata(String s) {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    public boolean hasMetadata(String s) {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    public void removeMetadata(String s, Plugin plugin) {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    public void sendPluginMessage(Plugin plugin, String s, byte[] bytes) {
        throw new NotImplementedException("Not got to yet");
    }

    @Override
    public Set<String> getListeningPluginChannels() {
        throw new NotImplementedException("Not got to yet");

    }
}
