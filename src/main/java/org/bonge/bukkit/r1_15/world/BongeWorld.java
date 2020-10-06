package org.bonge.bukkit.r1_15.world;

import org.array.utils.ArrayUtils;
import org.bonge.Bonge;
import org.bonge.bukkit.r1_15.block.BongeBlock;
import org.bonge.bukkit.r1_15.entity.living.BongeAbstractLivingEntity;
import org.bonge.bukkit.r1_15.entity.other.arrow.BongeAbstractArrowEntity;
import org.bonge.bukkit.r1_15.entity.other.arrow.BongeTippedArrowEntity;
import org.bonge.bukkit.r1_15.entity.other.item.BongeItem;
import org.bonge.bukkit.r1_15.world.chunk.BongeChunk;
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
import org.bukkit.util.BoundingBox;
import org.bukkit.util.Consumer;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.api.block.BlockState;
import org.spongepowered.api.data.Keys;
import org.spongepowered.api.entity.EntityTypes;
import org.spongepowered.api.entity.explosive.Explosive;
import org.spongepowered.api.entity.living.Living;
import org.spongepowered.api.item.inventory.ItemStackSnapshot;
import org.spongepowered.api.world.explosion.Explosion;
import org.spongepowered.api.world.server.ServerWorld;
import org.spongepowered.api.world.weather.Weathers;
import org.spongepowered.math.vector.Vector3d;
import org.spongepowered.math.vector.Vector3i;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BongeWorld extends BongeWrapper<org.spongepowered.api.world.World<? extends org.spongepowered.api.world.World>> implements World {

    public BongeWorld(org.spongepowered.api.world.World<? extends org.spongepowered.api.world.World> world){
        super(world);
    }

    @Override
    public @NotNull Block getBlockAt(int i, int i1, int i2) {
        return new BongeBlock(this.spongeValue.getLocation(i, i1, i2));
    }

    @Override
    public @NotNull Block getBlockAt(Location location) {
        return new BongeBlock(new BongeLocation(location).getSpongeLocation());
    }

    @Override
    public @NotNull Block getHighestBlockAt(int i, int i1) {
        return this.getBlockAt(i, this.getHighestBlockYAt(i, i1), i1);
    }

    @Override
    public @NotNull Block getHighestBlockAt(@NotNull Location location) {
        Vector3i vector = this.getSpongeValue().getHighestPositionAt(Bonge.getInstance().convert(location).getBlockPosition());
        return location.getWorld().getBlockAt(vector.getX(), vector.getY(), vector.getZ());
    }

    @Override
    public int getHighestBlockYAt(int i, int i1) {
        return this.getHighestBlockAt(new BongeLocation(this, i, 0, i1)).getY();
    }

    @Override
    public int getHighestBlockYAt(@NotNull Location location) {
        return this.getHighestBlockAt(location).getY();
    }

    @Override
    public @NotNull Chunk getChunkAt(int i, int i1) {
        org.spongepowered.api.world.chunk.Chunk chunk = this.spongeValue.getChunk(i, 0, i1);
        try {
            return Bonge.getInstance().convert(Chunk.class, chunk);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public @NotNull Chunk getChunkAt(@NotNull Location location) {
        org.spongepowered.api.world.chunk.Chunk chunk = this.spongeValue.getChunkAtBlock(new BongeLocation(location).getSpongeLocation().getBlockPosition());
        try {
            return Bonge.getInstance().convert(Chunk.class, chunk);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public @NotNull Chunk getChunkAt(Block block) {
        return this.getChunkAt(block.getLocation());
    }

    @Override
    public boolean isChunkLoaded(Chunk chunk) {
        return chunk.isLoaded();
    }

    @Override
    public Chunk[] getLoadedChunks() {
        Iterable<org.spongepowered.api.world.chunk.Chunk> chunks = this.spongeValue.getLoadedChunks();
        int count = 0;
        Iterator<org.spongepowered.api.world.chunk.Chunk> iter = chunks.iterator();
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
        return this.spongeValue.isChunkLoaded(i, 0, i1, false);
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
        org.spongepowered.api.world.chunk.Chunk chunk = this.spongeValue.getChunk(i, 0, i1);
        return chunk.unloadChunk();
    }

    @Override
    public boolean unloadChunk(int i, int i1, boolean b) {
        throw new NotImplementedException("Not got to yet");
    }

    @Override
    public boolean unloadChunkRequest(int i, int i1) {
        throw new NotImplementedException("Not got to yet");
    }


    @Override
    public boolean regenerateChunk(int i, int i1) {
        if(this.getSpongeValue() instanceof ServerWorld){
            return ((ServerWorld) this.getSpongeValue()).regenerateChunk(i, 0, i1).isPresent();
        }
        return false;
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
    public boolean isChunkForceLoaded(int x, int z) {
        throw new NotImplementedException("World.isChunkForceLoaded(int, int) has no equivalent");
    }

    @Override
    public void setChunkForceLoaded(int x, int z, boolean forced) {
        throw new NotImplementedException("World.isChunkForceLoaded(int, int, boolean) has no equivalent");

    }

    @Override
    public @NotNull Collection<Chunk> getForceLoadedChunks() {
        throw new NotImplementedException("World.getForceLoadedChunks() has no equivalent");
    }

    @Override
    public boolean addPluginChunkTicket(int x, int z, @NotNull Plugin plugin) {
        throw new NotImplementedException("World.addPluginChunkTicket(int, int, Plugin) has no equivalent");
    }

    @Override
    public boolean removePluginChunkTicket(int x, int z, @NotNull Plugin plugin) {
        throw new NotImplementedException("World.removePluginChunkTicket(int, int, Plugin) has no equivalent");
    }

    @Override
    public void removePluginChunkTickets(@NotNull Plugin plugin) {
        throw new NotImplementedException("World.removePluginChunkTickets(Plugin) has no equivalent");
    }

    @Override
    public @NotNull Collection<Plugin> getPluginChunkTickets(int x, int z) {
        throw new NotImplementedException("World.getPluginChunkTickets(int, int) has no equivalent");
    }

    @Override
    public @NotNull Map<Plugin, Collection<Chunk>> getPluginChunkTickets() {
        throw new NotImplementedException("World.getPluginChunkTickets(int, int) has no equivalent");
    }

    @Override
    public @NotNull BongeItem dropItem(Location location, @NotNull ItemStack itemStack) {
        org.spongepowered.api.entity.Item item = (org.spongepowered.api.entity.Item)this.spongeValue.createEntity(EntityTypes.ITEM.get(), new Vector3i(location.getBlockX(), location.getBlockY(), location.getBlockZ()));
        try {
            item.offer(Keys.ITEM_STACK_SNAPSHOT, Bonge.getInstance().convert(itemStack, ItemStackSnapshot.class));
        } catch (IOException e) {
        }
        this.spongeValue.spawnEntity(item);
        return new BongeItem(item);
    }

    @Override
    public @NotNull BongeItem dropItemNaturally(@NotNull Location location, @NotNull ItemStack itemStack) {
        BongeItem item = this.dropItem(location, itemStack);
        item.getSpongeValue().offer(Keys.VELOCITY, new Vector3d(0.3, 0.7, 0.1));
        return item;
    }

    @Override
    public @NotNull Arrow spawnArrow(@NotNull Location location, @NotNull Vector vector, float v, float v1) {
        return this.spawnArrow(location, vector, v, v1, TippedArrow.class);
    }

    @Override
    public <T extends AbstractArrow> @NotNull T spawnArrow(@NotNull Location location, @NotNull Vector direction, float speed, float spread, @NotNull Class<T> clazz) {
        BongeAbstractArrowEntity<?> arrow;
        if(clazz.isAssignableFrom(SpectralArrow.class)){
            throw new NotImplementedException("World.spawnArrow(Location, Vector, float, float, Class<T>) does not currently have support for SpecialArrow");
        }else{
            arrow = new BongeTippedArrowEntity((org.spongepowered.api.entity.projectile.arrow.Arrow) this.spongeValue.createEntity(EntityTypes.ARROW.get(), new Vector3d(location.getX(), location.getY(), location.getZ())));
        }
        arrow.getSpongeValue().setRotation(new Vector3d(direction.getX(), direction.getY(), direction.getZ()));
        arrow.getSpongeValue().offer(Keys.VELOCITY, new Vector3d(direction.getX(), direction.getY(), direction.getZ()).mul(speed));
        //TODO SPREAD
        return (T)arrow;
    }

    @Override
    public boolean generateTree(@NotNull Location location, @NotNull TreeType treeType) {
        throw new NotImplementedException("Not got to yet");
    }

    @Override
    public boolean generateTree(@NotNull Location location, @NotNull TreeType treeType, @NotNull BlockChangeDelegate blockChangeDelegate) {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    public @NotNull Entity spawnEntity(@NotNull Location location, @NotNull EntityType entityType) {
        try {
            org.spongepowered.api.world.Location<? extends org.spongepowered.api.world.World> loc = Bonge.getInstance().convert(location, org.spongepowered.api.world.Location.class);
            org.spongepowered.api.entity.Entity entity = this.spongeValue.createEntity(Bonge.getInstance().convert(entityType, org.spongepowered.api.entity.EntityType.class), loc.getPosition());
            this.spongeValue.spawnEntity(entity);
            return Bonge.getInstance().convert(Entity.class, entity);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public @NotNull LightningStrike strikeLightning(@NotNull Location location) {
        throw new NotImplementedException("Not got to yet");
    }

    @Override
    public @NotNull LightningStrike strikeLightningEffect(@NotNull Location location) {
        throw new NotImplementedException("Not got to yet");
    }

    @Override
    public @NotNull List<Entity> getEntities() {
        if(!(this.spongeValue instanceof ServerWorld)){
            return Collections.emptyList();
        }
        ServerWorld sWorld = (ServerWorld)this.spongeValue;
        return ArrayUtils.convert(e -> {
            try {
                return Bonge.getInstance().convert(Entity.class, e);
            } catch (IOException ioException) {
                throw new IllegalArgumentException(ioException);
            }
        }, sWorld.getEntities());
    }

    @Override
    public @NotNull List<LivingEntity> getLivingEntities() {
        return new ArrayList<>(this.getEntitiesByClass(LivingEntity.class));
    }

    @SafeVarargs
    @Override
    public final <T extends Entity> @NotNull Collection<T> getEntitiesByClass(Class<T>... classes) {
        return ArrayUtils.convert(e -> (T)e, this.getEntitiesByClasses(classes));
    }

    @Override
    public <T extends Entity> @NotNull Collection<T> getEntitiesByClass(Class<T> aClass) {
        return ArrayUtils.convert(e -> (T)e, this.getEntities().stream().filter(aClass::isInstance).collect(Collectors.toSet()));
    }

    @Override
    public @NotNull Collection<Entity> getEntitiesByClasses(Class<?>... classes) {
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
    public @NotNull List<Player> getPlayers() {
        return ArrayUtils.convert(p -> Bonge.getInstance().convert(p), this.spongeValue.getPlayers());
    }

    @Override
    public @NotNull Collection<Entity> getNearbyEntities(@NotNull Location location, double v, double v1, double v2) {
        return this.getNearbyEntities(location, v, v1, v2, (e) -> true);
    }

    @Override
    public @NotNull Collection<Entity> getNearbyEntities(@NotNull Location location, double x, double y, double z, @Nullable Predicate<Entity> filter) {
        if(x == y && y == z){
            return ArrayUtils.convert((e) -> {
                try {
                    return Bonge.getInstance().convert(Entity.class, e);
                } catch (IOException ioException) {
                    throw new IllegalArgumentException(ioException);
                }
            }, this.spongeValue.getNearbyEntities(new Vector3d(location.getX(), location.getY(), location.getZ()), x));
        }
        throw new NotImplementedException("Not got to yet");
    }

    @Override
    public @NotNull Collection<Entity> getNearbyEntities(@NotNull BoundingBox boundingBox) {
        throw new NotImplementedException("World.getNearbyEntities(BoundingBox) has not been looked at yet");
    }

    @Override
    public @NotNull Collection<Entity> getNearbyEntities(@NotNull BoundingBox boundingBox, @Nullable Predicate<Entity> filter) {
        throw new NotImplementedException("World.getNearbyEntities(BoundingBox, Predicate<Entity>) has not been looked at yet");
    }

    @Override
    public @Nullable RayTraceResult rayTraceEntities(@NotNull Location start, @NotNull Vector direction, double maxDistance) {
        throw new NotImplementedException("World.rayTraceEntities(Location, Vector, double) has not been looked at yet");
    }

    @Override
    public @Nullable RayTraceResult rayTraceEntities(@NotNull Location start, @NotNull Vector direction, double maxDistance, double raySize) {
        throw new NotImplementedException("World.rayTraceEntities(Location, Vector, double, double raySize) has not been looked at yet");
    }

    @Override
    public @Nullable RayTraceResult rayTraceEntities(@NotNull Location start, @NotNull Vector direction, double maxDistance, @Nullable Predicate<Entity> filter) {
        throw new NotImplementedException("World.rayTraceEntities(Location, Vector, double, Predicate<Entity>) has not been looked at yet");

    }

    @Override
    public @Nullable RayTraceResult rayTraceEntities(@NotNull Location start, @NotNull Vector direction, double maxDistance, double raySize, @Nullable Predicate<Entity> filter) {
        throw new NotImplementedException("World.rayTraceEntities(Location, Vector, double, double, Predicate<Entity>) has not been looked at yet");

    }

    @Override
    public @Nullable RayTraceResult rayTraceBlocks(@NotNull Location start, @NotNull Vector direction, double maxDistance) {
        throw new NotImplementedException("World.rayTraceBlocks(Location, Vector, double) has not been looked at yet");

    }

    @Override
    public @Nullable RayTraceResult rayTraceBlocks(@NotNull Location start, @NotNull Vector direction, double maxDistance, @NotNull FluidCollisionMode fluidCollisionMode) {
        throw new NotImplementedException("World.rayTraceBlocks(Location, Vector, double, FluidCollisionMode) has not been looked at yet");
    }

    @Override
    public @Nullable RayTraceResult rayTraceBlocks(@NotNull Location start, @NotNull Vector direction, double maxDistance, @NotNull FluidCollisionMode fluidCollisionMode, boolean ignorePassableBlocks) {
        throw new NotImplementedException("World.rayTraceEntities(Location, Vector, double, FluidCollisionMode, boolean) has not been looked at yet");
    }

    @Override
    public @Nullable RayTraceResult rayTrace(@NotNull Location start, @NotNull Vector direction, double maxDistance, @NotNull FluidCollisionMode fluidCollisionMode, boolean ignorePassableBlocks, double raySize, @Nullable Predicate<Entity> filter) {
        throw new NotImplementedException("World.rayTraceEntities(Location, Vector, double, FluidCollisionMode, boolean, double, Predicate<Entity>) has not been looked at yet");
    }

    @Override
    public @NotNull String getName() {
        if(this.spongeValue instanceof ServerWorld){
            return ((ServerWorld)this.spongeValue).getKey().getValue();
        }
        return this.spongeValue.getContext().getValue();
    }

    @Override
    public @NotNull UUID getUID() {
        if(this.spongeValue instanceof ServerWorld){
            return ((ServerWorld)this.spongeValue).getUniqueId();
        }
        throw new IllegalStateException("World UUID is not present in client mode");
    }

    @Override
    public @NotNull Location getSpawnLocation() {
        if(this.spongeValue instanceof ServerWorld){
            return Bonge.getInstance().convert(this.spongeValue.getLocation(((ServerWorld) this.spongeValue).getProperties().getSpawnPosition()));
        }
        throw new NotImplementedException("World.getSpawnLocation() doesn't work on client");
    }

    @Override
    public boolean setSpawnLocation(Location location) {
        return this.setSpawnLocation(location.getBlockX(), location.getBlockY(), location.getBlockZ());
    }

    @Override
    public boolean setSpawnLocation(int i, int i1, int i2) {
        if(!(this.spongeValue instanceof ServerWorld)){
            return false;
        }
        ((ServerWorld) this.spongeValue).getProperties().setSpawnPosition(new Vector3i(i, i1, i2));
        return true;
    }

    @Override
    public long getTime() {
        if(this.spongeValue instanceof ServerWorld){
            return ((ServerWorld) this.spongeValue).getProperties().getDayTime().toNanos();
        }
        return 0;
    }

    @Override
    public void setTime(long l) {
        if(this.spongeValue instanceof ServerWorld){
            ((ServerWorld) this.spongeValue).getProperties().setDayTime(Duration.ofNanos(l));
        }
    }

    @Override
    public long getFullTime() {
        if(this.spongeValue instanceof ServerWorld){
            return ((ServerWorld) this.spongeValue).getProperties().getGameTime().toNanos();
        }
        return 0;
    }

    @Override
    public void setFullTime(long l) {
        /*if(this.spongeValue instanceof ServerWorld){
            ((ServerWorld) this.spongeValue).getProperties().setGameTime(Duration.ofNanos(l));
        }*/
    }

    @Override
    public boolean hasStorm() {
        if(!(this.spongeValue instanceof ServerWorld)){
            return false;
        }
        return ((ServerWorld)this.spongeValue).getWeather().equals(Weathers.THUNDER);
    }

    @Override
    public void setStorm(boolean b) {
        ((ServerWorld)this.spongeValue).setWeather(b ? Weathers.THUNDER.get() : Weathers.CLEAR.get());
    }

    @Override
    public int getWeatherDuration() {
        if(!(this.spongeValue instanceof ServerWorld)){
            return 0;
        }
        return (int)((ServerWorld)this.spongeValue).getRunningWeatherDuration().toNanos();
    }

    @Override
    public void setWeatherDuration(int i) {
        if(!(this.spongeValue instanceof ServerWorld)){
            return;
        }
        ((ServerWorld)this.spongeValue).setWeather(((ServerWorld)this.spongeValue).getWeather(), Duration.ofNanos(i));
    }

    @Override
    public boolean isThundering() {
        return hasStorm();
    }

    @Override
    public void setThundering(boolean b) {
        setStorm(b);
    }

    @Override
    public int getThunderDuration() {
        if(!(this.spongeValue instanceof ServerWorld)){
            throw new NotImplementedException("Weather is not supported on Client");
        }
        return (int)((ServerWorld)this.spongeValue).getRemainingWeatherDuration().toNanos();
    }

    @Override
    public void setThunderDuration(int i) {
        if(!(this.spongeValue instanceof ServerWorld)){
            throw new NotImplementedException("Weather is not supported on Client");
        }
        ((ServerWorld)this.spongeValue).setWeather(Weathers.THUNDER.get(), Duration.ofNanos(i));
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
        if(!(this.spongeValue instanceof ServerWorld)){
            return false;
        }
        ServerWorld world = (ServerWorld) this.spongeValue;
        Explosion exp = Explosion.builder().canCauseFire(setFire).shouldBreakBlocks(breakBlocks).radius(power).location(this.spongeValue.getLocation(x, y, z)).build();
        world.triggerExplosion(exp);
        return true;
    }

    @Override
    public boolean createExplosion(double x, double y, double z, float power, boolean setFire, boolean breakBlocks, @Nullable Entity source) {
        if(source == null){
            return createExplosion(x, y, z, power, setFire, breakBlocks);
        }
        if(!(this.spongeValue instanceof ServerWorld)){
            return false;
        }
        ServerWorld world = (ServerWorld) this.spongeValue;
        Explosion exp = Explosion.builder().sourceExplosive((Explosive) ((BongeAbstractLivingEntity<Living>)source).getSpongeValue()).canCauseFire(setFire).shouldBreakBlocks(breakBlocks).radius(power).location(this.spongeValue.getLocation(x, y, z)).build();
        world.triggerExplosion(exp);
        return true;
    }

    @Override
    public boolean createExplosion(@NotNull Location location, float power) {
        return this.createExplosion(location, power, true);
    }

    @Override
    public boolean createExplosion(Location location, float power, boolean setFire) {
        return this.createExplosion(location.getX(), location.getY(), location.getZ(), power, setFire);
    }

    @Override
    public boolean createExplosion(@NotNull Location loc, float power, boolean setFire, boolean breakBlocks) {
        return false;
    }

    @Override
    public boolean createExplosion(@NotNull Location loc, float power, boolean setFire, boolean breakBlocks, @Nullable Entity source) {
        return false;
    }

    @Override
    public @NotNull Environment getEnvironment() {
        try {
            return Bonge.getInstance().convert(Environment.class, this.spongeValue.getDimensionType());
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public long getSeed() {
        return this.spongeValue.getSeed();
    }

    @Override
    public boolean getPVP() {
        if(this.spongeValue instanceof ServerWorld){
            return ((ServerWorld) this.spongeValue).getProperties().isPVPEnabled();
        }
        return true;
    }

    @Override
    public void setPVP(boolean b) {
        if(!(this.spongeValue instanceof ServerWorld)){
            return;
        }
        ServerWorld sWorld = (ServerWorld) this.spongeValue;
        sWorld.getProperties().setPVPEnabled(b);
    }

    @Override
    public ChunkGenerator getGenerator() {
        throw new NotImplementedException("Not got to yet");
    }

    @Override
    public void save() {
        if(!(this.spongeValue instanceof ServerWorld)){
            return;
        }
        try {
            ((ServerWorld)this.spongeValue).save();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public @NotNull List<BlockPopulator> getPopulators() {
        throw new NotImplementedException("Not got to yet");
    }

    @Override
    public <T extends Entity> @NotNull T spawn(@NotNull Location location, @NotNull Class<T> aClass) throws IllegalArgumentException {
        throw new NotImplementedException("Not got to yet");
    }

    @Override
    public <T extends Entity> @NotNull T spawn(@NotNull Location location, @NotNull Class<T> aClass, Consumer<T> consumer) throws IllegalArgumentException {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    @Deprecated
    public @NotNull FallingBlock spawnFallingBlock(@NotNull Location location, MaterialData materialData) throws IllegalArgumentException {
        return this.spawnFallingBlock(location, materialData.getSpongeValue());
    }

    @Override
    public @NotNull FallingBlock spawnFallingBlock(@NotNull Location location, @NotNull BlockData blockData) throws IllegalArgumentException {
        try {
            return this.spawnFallingBlock(location, Bonge.getInstance().convert(blockData, BlockState.class));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public FallingBlock spawnFallingBlock(Location loc, BlockState state){
        org.spongepowered.api.entity.FallingBlock block = (org.spongepowered.api.entity.FallingBlock) this.spongeValue.createEntity(EntityTypes.FALLING_BLOCK.get(), new Vector3d(loc.getX(), loc.getY(), loc.getZ()));
        block.offer(Keys.BLOCK_STATE, state);
        try {
            return (FallingBlock) Bonge.getInstance().convert(Entity.class, block);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Deprecated
    @Override
    public @NotNull FallingBlock spawnFallingBlock(@NotNull Location location, Material material, byte b) throws IllegalArgumentException {
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
    public @NotNull ChunkSnapshot getEmptyChunkSnapshot(int i, int i1, boolean b, boolean b1) {
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
    public @NotNull Biome getBiome(int i, int i1) {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    public @NotNull Biome getBiome(int x, int y, int z) {
        throw new NotImplementedException("Not got to yet");
    }

    @Override
    public void setBiome(int i, int i1, @NotNull Biome biome) {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    public void setBiome(int x, int y, int z, @NotNull Biome bio) {

    }

    @Override
    public double getTemperature(int i, int i1) {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    public double getTemperature(int x, int y, int z) {
        return 0;
    }

    @Override
    public double getHumidity(int i, int i1) {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    public double getHumidity(int x, int y, int z) {
        return 0;
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
        if(this.spongeValue instanceof ServerWorld){
            return ((ServerWorld) this.spongeValue).getProperties().doesKeepSpawnLoaded();
        }
        return true;
    }

    @Override
    public void setKeepSpawnInMemory(boolean b) {
        if(!(this.spongeValue instanceof ServerWorld)){
            return;
        }
        ((ServerWorld) this.spongeValue).getProperties().setKeepSpawnLoaded(b);
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
    public void setDifficulty(@NotNull Difficulty difficulty) {
        if(!(this.spongeValue instanceof ServerWorld)){
            return;
        }
        ServerWorld world = (ServerWorld)this.spongeValue;
        try {
            world.getProperties().setDifficulty(Bonge.getInstance().convert(difficulty, org.spongepowered.api.world.difficulty.Difficulty.class));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public @NotNull Difficulty getDifficulty() {
        try {
            return Bonge.getInstance().convert(Difficulty.class, this.spongeValue.getDifficulty());
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

    }

    @Override
    public @NotNull File getWorldFolder() {
        if(!(this.spongeValue instanceof ServerWorld)){
            //TODO find out default world name
            return new File("saves/");
        }
        ServerWorld world = (ServerWorld) this.spongeValue;
        return world.getDirectory().toFile();
    }

    @Override
    public WorldType getWorldType(){
        try {
            return Bonge.getInstance().convert(WorldType.class, this.spongeValue.getDimensionType());
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public boolean canGenerateStructures() {
        throw new NotImplementedException("Not got to yet");
    }

    @Override
    public boolean isHardcore() {
        return false;
    }

    @Override
    public void setHardcore(boolean hardcore) {

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
        if(!(this.spongeValue instanceof ServerWorld)){
            return new String[0];
        }
        ServerWorld world = (ServerWorld) this.spongeValue;
        Map<org.spongepowered.api.world.gamerule.GameRule<?>, ?> map = world.getProperties().getGameRules();
        String[] rules = new String[map.size()];
        List<Map.Entry<org.spongepowered.api.world.gamerule.GameRule<?>, ?>> sRules = new ArrayList<>(map.entrySet());
        for(int A = 0; A < sRules.size(); A++){
            rules[A] = sRules.get(A).getKey().getName() + ":" + sRules.get(A).getValue().toString();
        }
        return rules;
    }

    @Override
    public String getGameRuleValue(String s) {
        if(!(this.spongeValue instanceof ServerWorld)){
            return null;
        }
        ServerWorld world = (ServerWorld) this.spongeValue;
        try {
            org.spongepowered.api.world.gamerule.GameRule<?> rule = Bonge.getInstance().convert(s, org.spongepowered.api.world.gamerule.GameRule.class);
            Object sRule = world.getProperties().getGameRules().get(rule);
            if(sRule == null){
                return null;
            }
            return sRule.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean setGameRuleValue(@NotNull String s, @NotNull String s1) {
        if(!(this.spongeValue instanceof ServerWorld)){
            return false;
        }
        ServerWorld world = (ServerWorld) this.spongeValue;
        if(!isGameRule(s)){
            return false;
        }
        try {
            org.spongepowered.api.world.gamerule.GameRule<?> rule = Bonge.getInstance().convert(s, org.spongepowered.api.world.gamerule.GameRule.class);
            Object def = rule.getDefaultValue();
            if(def instanceof Boolean){
                world.getProperties().setGameRule((org.spongepowered.api.world.gamerule.GameRule<? super Boolean>) rule, Boolean.parseBoolean(s1));
            }else if(def instanceof Integer){
                world.getProperties().setGameRule((org.spongepowered.api.world.gamerule.GameRule<? super Integer>) rule, Integer.parseInt(s1));
            }else{
                throw new NotImplementedException("World.setGameRuleValue(String, String) Cannot work out value parse for gamerule '" + rule.getName() + "' with valuetype of '" + def.getClass().getName() + "'");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean isGameRule(String s) {
        return Stream.of(GameRule.values()).anyMatch(v -> v.getName().equalsIgnoreCase(s));
    }

    @Override
    public <T> T getGameRuleValue(@NotNull GameRule<T> gameRule) {
        throw new NotImplementedException("Not got to yet");
    }

    @Override
    public <T> T getGameRuleDefault(@NotNull GameRule<T> gameRule) {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    public <T> boolean setGameRule(@NotNull GameRule<T> gameRule, @NotNull T t) {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    public @NotNull WorldBorder getWorldBorder() {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    public void spawnParticle(@NotNull Particle particle, @NotNull Location location, int i) {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    public void spawnParticle(@NotNull Particle particle, double v, double v1, double v2, int i) {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    public <T> void spawnParticle(@NotNull Particle particle, @NotNull Location location, int i, T t) {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    public <T> void spawnParticle(@NotNull Particle particle, double v, double v1, double v2, int i, T t) {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    public void spawnParticle(@NotNull Particle particle, @NotNull Location location, int i, double v, double v1, double v2) {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    public void spawnParticle(@NotNull Particle particle, double v, double v1, double v2, int i, double v3, double v4, double v5) {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    public <T> void spawnParticle(@NotNull Particle particle, @NotNull Location location, int i, double v, double v1, double v2, T t) {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    public <T> void spawnParticle(@NotNull Particle particle, double v, double v1, double v2, int i, double v3, double v4, double v5, T t) {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    public void spawnParticle(@NotNull Particle particle, @NotNull Location location, int i, double v, double v1, double v2, double v3) {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    public void spawnParticle(@NotNull Particle particle, double v, double v1, double v2, int i, double v3, double v4, double v5, double v6) {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    public <T> void spawnParticle(@NotNull Particle particle, @NotNull Location location, int i, double v, double v1, double v2, double v3, T t) {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    public <T> void spawnParticle(@NotNull Particle particle, double v, double v1, double v2, int i, double v3, double v4, double v5, double v6, T t) {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    public <T> void spawnParticle(@NotNull Particle particle, @NotNull Location location, int i, double v, double v1, double v2, double v3, T t, boolean b) {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    public <T> void spawnParticle(@NotNull Particle particle, double v, double v1, double v2, int i, double v3, double v4, double v5, double v6, T t, boolean b) {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    public Location locateNearestStructure(@NotNull Location location, @NotNull StructureType structureType, int i, boolean b) {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    public @Nullable Raid locateNearestRaid(@NotNull Location location, int radius) {
        return null;
    }

    @Override
    public @NotNull List<Raid> getRaids() {
        return null;
    }

    @Override
    public void setMetadata(@NotNull String s, @NotNull MetadataValue metadataValue) {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    public @NotNull List<MetadataValue> getMetadata(@NotNull String s) {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    public boolean hasMetadata(@NotNull String s) {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    public void removeMetadata(@NotNull String s, @NotNull Plugin plugin) {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    public void sendPluginMessage(@NotNull Plugin plugin, @NotNull String s, byte[] bytes) {
        throw new NotImplementedException("Not got to yet");
    }

    @Override
    public @NotNull Set<String> getListeningPluginChannels() {
        throw new NotImplementedException("Not got to yet");

    }
}
