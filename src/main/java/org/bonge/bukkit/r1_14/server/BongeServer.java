package org.bonge.bukkit.r1_14.server;

import org.array.utils.ArrayUtils;
import org.bonge.Bonge;
import org.bonge.bukkit.r1_14.block.BongeTag;
import org.bonge.bukkit.r1_14.block.data.BongeAbstractBlockData;
import org.bonge.bukkit.r1_14.boss.BongeServerBossBar;
import org.bonge.bukkit.r1_14.command.BongeCommandManager;
import org.bonge.bukkit.r1_14.entity.EntityManager;
import org.bonge.bukkit.r1_14.entity.living.human.BongeOfflinePlayer;
import org.bonge.bukkit.r1_14.entity.living.human.BongePlayer;
import org.bonge.bukkit.r1_14.inventory.item.BongeItemFactory;
import org.bonge.bukkit.r1_14.scheduler.BongeScheduler;
import org.bonge.bukkit.r1_14.scoreboard.BongeScoreboardManager;
import org.bonge.bukkit.r1_14.server.help.BongeHelpMap;
import org.bonge.bukkit.r1_14.server.messager.BongeMessenger;
import org.bonge.bukkit.r1_14.server.plugin.BongePluginManager;
import org.bonge.bukkit.r1_14.server.service.BongeServiceManager;
import org.bonge.bukkit.r1_14.server.source.ConsoleSource;
import org.bonge.bukkit.r1_14.toremove.BongeUnsafeValues;
import org.bonge.bukkit.r1_14.world.BongeWorld;
import org.bonge.command.Permissions;
import org.bonge.launch.BongeLaunch;
import org.bonge.util.exception.NotImplementedException;
import org.bonge.wrapper.BongeWrapper;
import org.bukkit.*;
import org.bukkit.advancement.Advancement;
import org.bukkit.block.data.BlockData;
import org.bukkit.boss.*;
import org.bukkit.command.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.help.HelpMap;
import org.bukkit.inventory.*;
import org.bukkit.loot.LootTable;
import org.bukkit.map.MapView;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.ServicesManager;
import org.bukkit.plugin.messaging.Messenger;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.util.CachedServerIcon;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.api.ResourceKey;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.block.BlockState;
import org.spongepowered.api.entity.living.player.User;
import org.spongepowered.api.entity.living.player.server.ServerPlayer;
import org.spongepowered.api.world.server.ServerWorld;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.function.Consumer;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class BongeServer extends BongeWrapper<org.spongepowered.api.Server> implements Server {

    private final BongePluginManager pluginManager;
    private final BongeScheduler pluginScheduler;
    private final BongeCommandManager commandMap;
    private final BongeServiceManager serviceManager;
    private final EntityManager entityManager;
    private final BongeHelpMap helpMap;

    private final Set<BongeTag<? extends Keyed>> tags = new HashSet<>(Arrays.asList(
            new BongeTag<>(NamespacedKey.minecraft("wool"), Tag.REGISTRY_BLOCKS, Material.BLACK_WOOL, Material.BLUE_WOOL),
            new BongeTag<>(NamespacedKey.minecraft("planks"), Tag.REGISTRY_BLOCKS),
            new BongeTag<>(NamespacedKey.minecraft("stone_bricks"), Tag.REGISTRY_BLOCKS),
            new BongeTag<>(NamespacedKey.minecraft("wooden_buttons"), Tag.REGISTRY_BLOCKS),
            new BongeTag<>(NamespacedKey.minecraft("buttons"), Tag.REGISTRY_BLOCKS),
            new BongeTag<>(NamespacedKey.minecraft("carpets"), Tag.REGISTRY_BLOCKS),
            new BongeTag<>(NamespacedKey.minecraft("wooden_doors"), Tag.REGISTRY_BLOCKS),
            new BongeTag<>(NamespacedKey.minecraft("wooden_stairs"), Tag.REGISTRY_BLOCKS),
            new BongeTag<>(NamespacedKey.minecraft("wooden_slabs"), Tag.REGISTRY_BLOCKS),
            new BongeTag<>(NamespacedKey.minecraft("wooden_pressure_plates"), Tag.REGISTRY_BLOCKS),
            new BongeTag<>(NamespacedKey.minecraft("wooden_trapdoors"), Tag.REGISTRY_BLOCKS),
            new BongeTag<>(NamespacedKey.minecraft("doors"), Tag.REGISTRY_BLOCKS),
            new BongeTag<>(NamespacedKey.minecraft("saplings"), Tag.REGISTRY_BLOCKS),
            new BongeTag<>(NamespacedKey.minecraft("logs"), Tag.REGISTRY_BLOCKS),
            new BongeTag<>(NamespacedKey.minecraft("dark_oak_logs"), Tag.REGISTRY_BLOCKS),
            new BongeTag<>(NamespacedKey.minecraft("oak_logs"), Tag.REGISTRY_BLOCKS),
            new BongeTag<>(NamespacedKey.minecraft("birch_logs"), Tag.REGISTRY_BLOCKS),
            new BongeTag<>(NamespacedKey.minecraft("acacia_logs"), Tag.REGISTRY_BLOCKS),
            new BongeTag<>(NamespacedKey.minecraft("jungle_logs"), Tag.REGISTRY_BLOCKS),
            new BongeTag<>(NamespacedKey.minecraft("spruce_logs"), Tag.REGISTRY_BLOCKS),
            new BongeTag<>(NamespacedKey.minecraft("banners"), Tag.REGISTRY_BLOCKS),
            new BongeTag<>(NamespacedKey.minecraft("sand"), Tag.REGISTRY_BLOCKS),
            new BongeTag<>(NamespacedKey.minecraft("stairs"), Tag.REGISTRY_BLOCKS),
            new BongeTag<>(NamespacedKey.minecraft("slabs"), Tag.REGISTRY_BLOCKS),
            new BongeTag<>(NamespacedKey.minecraft("anvil"), Tag.REGISTRY_BLOCKS),
            new BongeTag<>(NamespacedKey.minecraft("rails"), Tag.REGISTRY_BLOCKS),
            new BongeTag<>(NamespacedKey.minecraft("coral_blocks"), Tag.REGISTRY_BLOCKS),
            new BongeTag<>(NamespacedKey.minecraft("corals"), Tag.REGISTRY_BLOCKS),
            new BongeTag<>(NamespacedKey.minecraft("wall_corals"), Tag.REGISTRY_BLOCKS),
            new BongeTag<>(NamespacedKey.minecraft("leaves"), Tag.REGISTRY_BLOCKS),
            new BongeTag<>(NamespacedKey.minecraft("trapdoors"), Tag.REGISTRY_BLOCKS),
            new BongeTag<>(NamespacedKey.minecraft("flower_pots"), Tag.REGISTRY_BLOCKS),
            new BongeTag<>(NamespacedKey.minecraft("enderman_holdable"), Tag.REGISTRY_BLOCKS),
            new BongeTag<>(NamespacedKey.minecraft("ice"), Tag.REGISTRY_BLOCKS),
            new BongeTag<>(NamespacedKey.minecraft("valid_spawn"), Tag.REGISTRY_BLOCKS),
            new BongeTag<>(NamespacedKey.minecraft("impermeable"), Tag.REGISTRY_BLOCKS)
            ));
    
    public BongeServer(org.spongepowered.api.Server server){
        super(server);
        this.pluginManager = new BongePluginManager();
        this.entityManager = new EntityManager();
        this.pluginScheduler = new BongeScheduler();
        this.commandMap = new BongeCommandManager();
        this.serviceManager = new BongeServiceManager();
        this.helpMap = new BongeHelpMap();
    }

    public EntityManager getEntityManager(){
        return this.entityManager;
    }

    public BongeCommandManager getCommandManager(){
        return this.commandMap;
    }

    public ServerWorld getDefaultWorld(){
        return Sponge.getServer().getWorldManager().getWorld(Sponge.getServer().getWorldManager().getDefaultProperties().get().getKey()).get();
    }

    @Override
    public @NotNull String getName() {
        return BongeLaunch.PLUGIN_NAME;
    }

    @Override
    public @NotNull String getVersion() {
        return BongeLaunch.PLUGIN_NAME + ": " + getBukkitVersion() + " | MC: " + Sponge.getPlatform().getMinecraftVersion().getName();
    }

    @Override
    public @NotNull String getBukkitVersion() {
        return BongeLaunch.IMPLEMENTATION_VERSION + " (" +BongeLaunch.PLUGIN_VERSION + ")";
    }

    @Override
    public @NotNull Collection<? extends Player> getOnlinePlayers() {
        return ArrayUtils.convert(BongePlayer::new, this.spongeValue.getOnlinePlayers());
    }

    @Override
    public int getMaxPlayers() {
        return this.getSpongeValue().getMaxPlayers();
    }

    @Override
    public int getPort() {
        return this.getSpongeValue().getBoundAddress().get().getPort();
    }

    @Override
    public int getViewDistance() {
        throw new NotImplementedException("World.getViewDistance() is not supported on Sponge");
    }

    @Override
    public @NotNull String getIp() {
        return this.getSpongeValue().getBoundAddress().get().getAddress().getHostAddress();
    }

    public String getServerName() {
        return "BongeServer";
    }

    public String getServerId() {
        return "bonge:bongeServer";
    }

    @Override
    public @NotNull String getWorldType() {
        return this.getDefaultWorld().getDimensionType().getKey().getValue();
    }

    @Override
    public boolean getGenerateStructures() {
        return false;
    }

    @Override
    public boolean getAllowEnd() {
        return Sponge.getServer().getWorldManager().getProperties(ResourceKey.builder().value("DIM-1").build()).isPresent();
    }

    @Override
    public boolean getAllowNether() {
        return Sponge.getServer().getWorldManager().getWorld(ResourceKey.builder().value("DIM1").build()).isPresent();
    }

    @Override
    public boolean hasWhitelist() {
        return this.getSpongeValue().hasWhitelist();
    }

    @Override
    public void setWhitelist(boolean b) {
        this.getSpongeValue().setHasWhitelist(b);
    }

    @Override
    public @NotNull Set<OfflinePlayer> getWhitelistedPlayers() {
        return null;
    }

    @Override
    public void reloadWhitelist() {
    }

    @Override
    public int broadcastMessage(String s) {
        Sponge.getServer().getBroadcastAudience().sendMessage(Bonge.getInstance().convertText(s));
        return Sponge.getServer().getOnlinePlayers().size();
    }

    @Override
    public @NotNull String getUpdateFolder() {
        return null;
    }

    @Override
    public @NotNull File getUpdateFolderFile() {
        return null;
    }

    @Override
    public long getConnectionThrottle() {
        return 0;
    }

    @Override
    public int getTicksPerAnimalSpawns() {
        return 0;
    }

    @Override
    public int getTicksPerMonsterSpawns() {
        return 0;
    }

    @Override
    public Player getPlayer(@NotNull String s) {
        Optional<ServerPlayer> opPlayer = this.getSpongeValue().getOnlinePlayers().stream().filter(p -> p.getName().equalsIgnoreCase(s)).findAny();
        return opPlayer.map(BongePlayer::new).orElse(null);
    }

    @Override
    public Player getPlayerExact(@NotNull String s) {
        Optional<ServerPlayer> opPlayer = this.getSpongeValue().getOnlinePlayers().stream().filter(p -> p.getName().equals(s)).findAny();
        return opPlayer.map(BongePlayer::new).orElse(null);
    }

    @Override
    public @NotNull List<Player> matchPlayer(String s) {
        return null;
    }

    @Override
    public Player getPlayer(@NotNull UUID uuid) {
        Optional<ServerPlayer> opPlayer = this.getSpongeValue().getOnlinePlayers().stream().filter(p -> p.getUniqueId().equals(uuid)).findAny();
        return opPlayer.map(BongePlayer::new).orElse(null);
    }

    @Override
    public @NotNull BongePluginManager getPluginManager() {
        return this.pluginManager;
    }

    @Override
    public @NotNull BukkitScheduler getScheduler() {
        return this.pluginScheduler;
    }

    @Override
    public @NotNull ServicesManager getServicesManager() {
        return this.serviceManager;
    }

    @Override
    public @NotNull List<World> getWorlds() {
        List<World> list = new ArrayList<>();
        this.getSpongeValue().getWorldManager().getWorlds().forEach(w -> list.add(new BongeWorld(w)));
        return list;
    }

    @Override
    public World createWorld(@NotNull WorldCreator worldCreator) {
        return null;
    }

    @Override
    public boolean unloadWorld(@NotNull String s, boolean b) {
        return false;
    }

    @Override
    public boolean unloadWorld(@NotNull World world, boolean b) {
        return false;
    }

    //this has become 10X harder
    @Override
    public World getWorld(@NotNull String s) {
        Optional<ServerWorld> opWorld = this.getSpongeValue().getWorldManager().getWorlds().stream().filter(w -> w.getKey().getValue().equalsIgnoreCase(s)).findAny();
        return opWorld.map(BongeWorld::new).orElse(null);
    }

    @Override
    public World getWorld(@NotNull UUID uuid) {
        Optional<ServerWorld> opWorld = this.getSpongeValue().getWorldManager().getWorlds().stream().filter(w -> w.getUniqueId().equals(uuid)).findAny();
        return opWorld.map(BongeWorld::new).orElse(null);
    }

    public @Nullable  MapView getMap(int id) {
        throw new NotImplementedException("Sponge does not have support for MapView");
    }

    @Override
    public @NotNull MapView createMap(@NotNull World world) {
        throw new NotImplementedException("Sponge does not have support for MapView");
    }

    @Override
    public @NotNull ItemStack createExplorerMap(World world, Location location, StructureType structureType) {
        throw new NotImplementedException("Server.createExplorerMap(World, Location, StructureType) not looked at yet");
    }

    @Override
    public @NotNull ItemStack createExplorerMap(World world, Location location, StructureType structureType, int i, boolean b) {
        throw new NotImplementedException("Server.createExplorerMap(World, Location, StructureType) not looked at yet");
    }

    @Override
    public void reload() {

    }

    @Override
    public void reloadData() {

    }

    @Override
    public @NotNull Logger getLogger() {
        return BongeLaunch.getLogger(null);
    }

    @Override
    public PluginCommand getPluginCommand(String s) {
        Command command = this.commandMap.getCommand(s);
        if(command instanceof PluginCommand){
            return (PluginCommand)command;
        }
        return null;
    }

    @Override
    public void savePlayers() {

    }

    @Override
    public boolean dispatchCommand(@NotNull CommandSender commandSender, @NotNull String s) throws CommandException {
        /*CommandSource sender;
        try {
            sender = Bonge.getInstance().convert(commandSender, CommandSource.class);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        CommandResult result = Sponge.getCommandManager().process(sender, s);
        return result.equals(CommandResult.empty());*/
        throw new NotImplementedException("Server.dispatchCommand(CommandSender, String) not looked at yet");
    }

    @Override
    public boolean addRecipe(Recipe recipe) {
        throw new NotImplementedException("Server.addRecipe(Recipe) Recipes not looked at yet");
    }

    @Override
    public @NotNull List<Recipe> getRecipesFor(@NotNull ItemStack itemStack) {
        throw new NotImplementedException("Server.getRecipeFor(ItemStack) Recipes not looked at yet");
    }

    @Override
    public @NotNull Iterator<Recipe> recipeIterator() {
        throw new NotImplementedException("Server.recipeIterator() Recipes not looked at yet");
    }

    @Override
    public void clearRecipes() {
        throw new NotImplementedException("Server.clearRecipes() Recipes not looked at yet");
    }

    public void resetRecipes() {
        throw new NotImplementedException("Server.resetRecipes() not looked at yet");
    }

    @Override
    public @NotNull Map<String, String[]> getCommandAliases() {
        throw new NotImplementedException("Server.getCommandAliases() not looked at yet");
    }

    @Override
    public int getSpawnRadius() {
        throw new NotImplementedException("Sponge does not support spawnRadius as far as im aware");
    }

    @Override
    public void setSpawnRadius(int i) {
        throw new NotImplementedException("Sponge does not support spawnRadius as far as im aware");
    }

    @Override
    public boolean getOnlineMode() {
        return this.getSpongeValue().getOnlineMode();
    }

    @Override
    public boolean getAllowFlight() {
        throw new NotImplementedException("Sponge does not support AllowFlight as far as im aware");
    }

    @Override
    public boolean isHardcore() {
        return this.getDefaultWorld().getProperties().isHardcore();
    }

    @Override
    public void shutdown() {
        Sponge.getServer().shutdown();
    }

    @Override
    public int broadcast(String s, String s1) {
        throw new NotImplementedException("Server.broadcast(String, String) has not been looked at yet");

    }

    @Override
    public @NotNull OfflinePlayer getOfflinePlayer(@NotNull String s) {
        Optional<User> opUser = this.getSpongeValue().getUserManager().get(s);
        return Objects.requireNonNull(opUser.map(BongeOfflinePlayer::new).orElse(null));
    }

    @Override
    public @NotNull OfflinePlayer getOfflinePlayer(@NotNull UUID uuid) {
        Optional<User> opUser = this.getSpongeValue().getUserManager().get(uuid);
        return Objects.requireNonNull(opUser.map(BongeOfflinePlayer::new).orElse(null));
    }

    @Override
    public @NotNull Set<String> getIPBans() {
        throw new NotImplementedException("Server.getIPBans() has not been looked at yet");
    }

    @Override
    public void banIP(String s) {
        throw new NotImplementedException("Server.banIP(String) has not been looked at yet");
    }

    @Override
    public void unbanIP(String s) {
        throw new NotImplementedException("Server.banIP(String) has not been looked at yet");
    }

    @Override
    public @NotNull Set<OfflinePlayer> getBannedPlayers() {
        throw new NotImplementedException("Server.getBannedPlayers() has not been looked at yet");
    }

    @Override
    public @NotNull BanList getBanList(BanList.@NotNull Type type) {
        throw new NotImplementedException("Server.getBanList(BanList.Type) has not been looked at yet");
    }

    @Override
    public @NotNull Set<OfflinePlayer> getOperators() {
        Set<OfflinePlayer> players = new HashSet<>();
        this.getSpongeValue().getUserManager().getAll().stream().filter(p -> this.getSpongeValue().getUserManager().get(p).isPresent()).filter(p -> this.getSpongeValue().getUserManager().get(p).get().hasPermission(Permissions.BONGE_OP)).forEach(p -> {
            players.add(new BongeOfflinePlayer(this.getSpongeValue().getUserManager().get(p).get()));
        });
        return players;
    }

    @Override
    public @NotNull GameMode getDefaultGameMode() {
        ServerWorld world = this.getDefaultWorld();
        org.spongepowered.api.entity.living.player.gamemode.GameMode mode = world.getProperties().getGameMode();
        try {
            return Bonge.getInstance().convert(GameMode.class, mode);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void setDefaultGameMode(@NotNull GameMode gameMode) {
        ServerWorld world = this.getDefaultWorld();
        try {
            org.spongepowered.api.entity.living.player.gamemode.GameMode sGamemode = Bonge.getInstance().convert(gameMode, org.spongepowered.api.entity.living.player.gamemode.GameMode.class);
            world.getProperties().setGameMode(sGamemode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public @NotNull ConsoleCommandSender getConsoleSender() {
        return new ConsoleSource();
    }

    @Override
    public @NotNull File getWorldContainer() {
        return this.getDefaultWorld().getDirectory().toFile();
    }

    @Override
    public OfflinePlayer[] getOfflinePlayers() {
        Set<OfflinePlayer> set = new HashSet<>();
        this.getSpongeValue().getUserManager().getAll().forEach(u -> this.getSpongeValue().getUserManager().get(u).ifPresent(user -> set.add(new BongeOfflinePlayer(user))));
        return set.toArray(new OfflinePlayer[set.size()]);
    }

    @Override
    public @NotNull Messenger getMessenger() {
        return new BongeMessenger();
    }

    @Override
    public @NotNull HelpMap getHelpMap() {
        return this.helpMap;
    }

    @Override
    public @NotNull Inventory createInventory(InventoryHolder inventoryHolder, @NotNull InventoryType inventoryType) {
        return this.createInventory(inventoryHolder, inventoryType, null);
    }

    @Override
    public @NotNull Inventory createInventory(InventoryHolder inventoryHolder, @NotNull InventoryType inventoryType, @NotNull String s) {
        throw new NotImplementedException("Server.createInventory(InventoryHolder, InventoryType, String) not got to yet");
    }

    @Override
    public @NotNull Inventory createInventory(InventoryHolder inventoryHolder, int i) throws IllegalArgumentException {
        throw new NotImplementedException("Server.createInventory(InventoryHolder, int) not got to yet");

    }

    @Override
    public @NotNull Inventory createInventory(InventoryHolder inventoryHolder, int i, @NotNull String s) throws IllegalArgumentException {
        throw new NotImplementedException("Server.createInventory(InventoryHolder, int, String) not got to yet");
    }

    @Override
    public @NotNull Merchant createMerchant(String s) {
        throw new NotImplementedException("Merchant has not been implemented yet");
    }

    @Override
    public int getMonsterSpawnLimit() {
        throw new NotImplementedException("Server.getMonsterSpawnLimit() has not been looked at yet");
    }

    @Override
    public int getAnimalSpawnLimit() {
        throw new NotImplementedException("Server.getAnimalSpawnLimit() has not been looked at yet");
    }

    @Override
    public int getWaterAnimalSpawnLimit() {
        throw new NotImplementedException("Server.getWaterAnimalSpawnLimit() has not been looked at yet");
    }

    @Override
    public int getAmbientSpawnLimit() {
        throw new NotImplementedException("Server.getAmbientSpawnLimit() has not been looked at yet");
    }

    @Override
    public boolean isPrimaryThread() {
        return true;
    }

    @Override
    public @NotNull String getMotd() {
        throw new NotImplementedException("Server.getMotd() has not been looked at yet");
    }

    @Override
    public String getShutdownMessage() {
        throw new NotImplementedException("Server.getShutdownMessage() has not been looked at yet");
    }

    @Override
    public Warning.@NotNull WarningState getWarningState() {
        throw new NotImplementedException("Server.getWarningState() has not been looked at yet");
    }

    @Override
    public @NotNull ItemFactory getItemFactory() {
        return new BongeItemFactory();
    }

    @Override
    public ScoreboardManager getScoreboardManager() {
        return new BongeScoreboardManager();
    }

    @Override
    public CachedServerIcon getServerIcon() {
        throw new NotImplementedException("Server.getServerIcon() has not been looked at yet");
    }

    @Override
    public @NotNull CachedServerIcon loadServerIcon(@NotNull File file) {
        throw new NotImplementedException("Server.loadServrIcon() has not been looked at yet");
    }

    @Override
    public @NotNull CachedServerIcon loadServerIcon(@NotNull BufferedImage bufferedImage) {
        throw new NotImplementedException("Server.loadServerIcon(BufferedImage) has not been looked at yet");
    }

    @Override
    public void setIdleTimeout(int i) {
        throw new NotImplementedException("Server.setIdleTimeout() has not been looked at yet");
    }

    @Override
    public int getIdleTimeout() {
        throw new NotImplementedException("Server.getIdleTimeout() has not been looked at yet");
    }

    @Override
    public ChunkGenerator.@NotNull ChunkData createChunkData(@NotNull World world) {
        throw new NotImplementedException("Server.createChunkData(World) has not been looked at yet");
    }

    @Override
    public @NotNull BossBar createBossBar(String s, @NotNull BarColor barColor, @NotNull BarStyle barStyle, BarFlag... barFlags) {
        net.kyori.adventure.bossbar.BossBar.Color colour;
        net.kyori.adventure.bossbar.BossBar.Overlay overlay;
        try {
            colour = Bonge.getInstance().convert(barColor, net.kyori.adventure.bossbar.BossBar.Color.class);
            overlay = Bonge.getInstance().convert(barStyle, net.kyori.adventure.bossbar.BossBar.Overlay.class);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        return new BongeServerBossBar(net.kyori.adventure.bossbar.BossBar.of(Bonge.getInstance().convertText(s), 0, colour, overlay));
    }

    @Override
    public @NotNull KeyedBossBar createBossBar(@NotNull NamespacedKey key, @Nullable String title, @NotNull BarColor color, @NotNull BarStyle style, @NotNull BarFlag... flags) {
        throw new NotImplementedException("Server.createBossBar(NamespacedKey, String, BarColor, BarStyle, BarFlag...) has not been looked at");
    }

    @Override
    public @NotNull Iterator<KeyedBossBar> getBossBars() {
        throw new NotImplementedException("Server.getBossBars() has not been looked at yet");
    }

    @Override
    public @Nullable KeyedBossBar getBossBar(@NotNull NamespacedKey key) {
        throw new NotImplementedException("Server.getBossBar(NamespacedKey) has not been looked at");
    }

    @Override
    public boolean removeBossBar(@NotNull NamespacedKey key) {
        throw new NotImplementedException("Server.removeBossBar(NamespacedKey) has not been looked at");
    }

    private boolean containsFlag(BarFlag check, BarFlag... flags){
        for(BarFlag flag : flags){
            if(flag.equals(check)){
                return true;
            }
        }
        return false;
    }

    @Override
    public Entity getEntity(@NotNull UUID uuid) {
        for (ServerWorld world : this.spongeValue.getWorldManager().getWorlds()){
            Optional<org.spongepowered.api.entity.Entity> opEntity = world.getEntity(uuid);
            if(opEntity.isPresent()){
                try {
                    return Bonge.getInstance().convert(Entity.class, opEntity.get());
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    public Advancement getAdvancement(@NotNull NamespacedKey namespacedKey) {
        throw new NotImplementedException("Server.getAdvancement(NamespacedKey) has not been looked at yet");
    }

    @Override
    public @NotNull Iterator<Advancement> advancementIterator() {
        throw new NotImplementedException("Server.advancementIterator() has not been looked at yet");
    }

    @Override
    public @NotNull BlockData createBlockData(Material material) {
        return material.createBlockData();
    }

    @Override
    public @NotNull BlockData createBlockData(Material material, Consumer<BlockData> consumer) {
        return material.createBlockData(consumer);
    }

    @Override
    public @NotNull BlockData createBlockData(String s) throws IllegalArgumentException {
        String[] split = s.split(":");
        Optional<BlockState> opBlock = Sponge.getRegistry().getCatalogRegistry().get(BlockState.class, ResourceKey.builder().namespace(split[0]).value(split[1]).build());
        if(opBlock.isPresent()){
            try {
                return BongeAbstractBlockData.findDynamicClass(opBlock.get());
            } catch (InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException e) {
                throw new IllegalStateException(e);
            }
        }
        throw new IllegalArgumentException("Unknown BlockState of " + s);
    }

    @Override
    public @NotNull BlockData createBlockData(Material material, String s) throws IllegalArgumentException {
        return createBlockData(material.getKey().getNamespace() + "[" + s + "]");
    }

    @Override
    public <T extends Keyed> Tag<T> getTag(@NotNull String s, @NotNull NamespacedKey namespacedKey, @NotNull Class<T> aClass) {
        //return new BongeTag<>(namespacedKey, s);
        for (Tag<T> value : this.getTags(s, aClass)){
            if(value.getKey().equals(namespacedKey)){
                return value;
            }
        }
        throw new IllegalStateException("Unknown Tag namespace of " + namespacedKey.toString() + " in extra: " + s);
    }

    @Override
    public @NotNull <T extends Keyed> Iterable<Tag<T>> getTags(@NotNull String registry, @NotNull Class<T> clazz) {
        return (Iterable<Tag<T>>) (Object) this.tags.stream().filter(t -> t.getExtra().equalsIgnoreCase(registry)).filter(t -> {
            if(t.getValues().isEmpty()){
                System.err.println(t.getKey().toString() + " (Tag) does not have any values assigned to it, ignoring");
                return false;
            }
            return clazz.isInstance(t.getValues().iterator().next());
        }).collect(Collectors.toSet());
    }

    @Override
    public LootTable getLootTable(@NotNull NamespacedKey namespacedKey) {
        throw new NotImplementedException("Server.getLootTable(NamespacedKey) has not been looked at yet");
    }

    @Override
    public @NotNull List<Entity> selectEntities(@NotNull CommandSender sender, @NotNull String selector) throws IllegalArgumentException {
        throw new NotImplementedException("Server.selectEntities(CommandSender, String) has not been looked at");
    }

    @Override
    @Deprecated
    public @NotNull UnsafeValues getUnsafe() {
        return new BongeUnsafeValues();
    }

    @Override
    public void sendPluginMessage(@NotNull Plugin plugin, @NotNull String s, byte[] bytes) {
        throw new NotImplementedException("Server.sendPluginMessage(Plugin, String, byte[]) has not been looked at yet");
    }

    @Override
    public @NotNull Set<String> getListeningPluginChannels() {
        throw new NotImplementedException("Server.getListeningPluginChannels() has not been looked at yet");
    }
}
