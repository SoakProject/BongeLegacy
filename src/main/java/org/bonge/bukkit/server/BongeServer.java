package org.bonge.bukkit.server;

import org.bonge.bukkit.block.BongeTag;
import org.bonge.bukkit.block.data.BongeAbstractBlockData;
import org.bonge.bukkit.boss.BongeServerBossBar;
import org.bonge.bukkit.command.BongeCommandManager;
import org.bonge.bukkit.entity.BongeAbstractEntity;
import org.bonge.bukkit.entity.living.human.BongePlayer;
import org.bonge.bukkit.inventory.inventory.BongeCustomInventory;
import org.bonge.bukkit.inventory.inventory.tileentity.workbench.BongeWorkbenchInventory;
import org.bonge.bukkit.inventory.inventory.tileentity.furnace.BongeFurnaceInventory;
import org.bonge.bukkit.inventory.item.BongeItemFactory;
import org.bonge.bukkit.scheduler.BongeScheduler;
import org.bonge.bukkit.scoreboard.BongeScoreboardManager;
import org.bonge.bukkit.server.messager.BongeMessenger;
import org.bonge.bukkit.server.plugin.BongePluginManager;
import org.bonge.bukkit.server.service.BongeServiceManager;
import org.bonge.bukkit.server.source.ConsoleSource;
import org.bonge.bukkit.toremove.BongeUnsafeValues;
import org.bonge.bukkit.world.BongeWorld;
import org.bonge.convert.EnumConvert;
import org.bonge.convert.InterfaceConvert;
import org.bonge.convert.InventoryConvert;
import org.bonge.launch.BongeLaunch;
import org.bonge.util.ArrayUtils;
import org.bonge.wrapper.BongeWrapper;
import org.bukkit.*;
import org.bukkit.advancement.Advancement;
import org.bukkit.block.Furnace;
import org.bukkit.block.data.BlockData;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
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
import org.spongepowered.api.Sponge;
import org.spongepowered.api.block.BlockState;
import org.spongepowered.api.boss.ServerBossBar;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.item.inventory.InventoryArchetypes;
import org.spongepowered.api.item.inventory.property.InventoryDimension;
import org.spongepowered.api.item.inventory.property.InventoryTitle;
import org.spongepowered.api.world.storage.WorldProperties;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.*;
import java.util.function.Consumer;
import java.util.logging.Logger;

public class BongeServer extends BongeWrapper<org.spongepowered.api.Server> implements Server {

    private BongePluginManager pluginManager;
    private BongeScheduler pluginScheduler;
    private BongeCommandManager commandMap;
    private BongeServiceManager serviceManager;

    private Set<BongeTag<? extends Keyed>> tags = new HashSet<>(Arrays.asList(
            new BongeTag<>(NamespacedKey.minecraft("wool"), Tag.REGISTRY_BLOCKS),
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
        this.pluginScheduler = new BongeScheduler();
        this.commandMap = new BongeCommandManager();
        this.serviceManager = new BongeServiceManager();
    }

    public BongeCommandManager getCommandManager(){
        return this.commandMap;
    }

    @Override
    public String getName() {
        return BongeLaunch.PLUGIN_NAME;
    }

    @Override
    public String getVersion() {
        return BongeLaunch.PLUGIN_NAME + ": " + BongeLaunch.PLUGIN_VERSION + " | MC: " + Sponge.getPlatform().getMinecraftVersion().getName();
    }

    @Override
    public String getBukkitVersion() {
        return BongeLaunch.PLUGIN_VERSION;
    }

    @Override
    public Collection<? extends Player> getOnlinePlayers() {
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
        return 0;
    }

    @Override
    public String getIp() {
        return this.getSpongeValue().getBoundAddress().get().getAddress().getHostAddress();
    }

    @Override
    public String getServerName() {
        return null;
    }

    @Override
    public String getServerId() {
        return null;
    }

    @Override
    public String getWorldType() {
        return this.getSpongeValue().getDefaultWorld().get().getDimensionType().getName();
    }

    @Override
    public boolean getGenerateStructures() {
        return false;
    }

    @Override
    public boolean getAllowEnd() {
        return false;
    }

    @Override
    public boolean getAllowNether() {
        return false;
    }

    @Override
    public boolean hasWhitelist() {
        return this.getSpongeValue().hasWhitelist();
    }

    @Override
    public void setWhitelist(boolean b) {

    }

    @Override
    public Set<OfflinePlayer> getWhitelistedPlayers() {
        return null;
    }

    @Override
    public void reloadWhitelist() {
    }

    @Override
    public int broadcastMessage(String s) {
        Sponge.getServer().getBroadcastChannel().send(InterfaceConvert.fromString(s));
        return Sponge.getServer().getBroadcastChannel().getMembers().size();
    }

    @Override
    public String getUpdateFolder() {
        return null;
    }

    @Override
    public File getUpdateFolderFile() {
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
    public Player getPlayer(String s) {
        Optional<org.spongepowered.api.entity.living.player.Player> opPlayer = this.getSpongeValue().getOnlinePlayers().stream().filter(p -> p.getName().equalsIgnoreCase(s)).findAny();
        return opPlayer.map(BongePlayer::new).orElse(null);
    }

    @Override
    public Player getPlayerExact(String s) {
        Optional<org.spongepowered.api.entity.living.player.Player> opPlayer = this.getSpongeValue().getOnlinePlayers().stream().filter(p -> p.getName().equals(s)).findAny();
        return opPlayer.map(BongePlayer::new).orElse(null);
    }

    @Override
    public List<Player> matchPlayer(String s) {
        return null;
    }

    @Override
    public Player getPlayer(UUID uuid) {
        Optional<org.spongepowered.api.entity.living.player.Player> opPlayer = this.getSpongeValue().getOnlinePlayers().stream().filter(p -> p.getUniqueId().equals(uuid)).findAny();
        return opPlayer.map(BongePlayer::new).orElse(null);
    }

    @Override
    public BongePluginManager getPluginManager() {
        return this.pluginManager;
    }

    @Override
    public BukkitScheduler getScheduler() {
        return this.pluginScheduler;
    }

    @Override
    public ServicesManager getServicesManager() {
        return this.serviceManager;
    }

    @Override
    public List<World> getWorlds() {
        List<World> list = new ArrayList<>();
        this.getSpongeValue().getWorlds().forEach(w -> list.add(new BongeWorld(w)));
        return list;
    }

    @Override
    public World createWorld(WorldCreator worldCreator) {
        return null;
    }

    @Override
    public boolean unloadWorld(String s, boolean b) {
        return false;
    }

    @Override
    public boolean unloadWorld(World world, boolean b) {
        return false;
    }

    @Override
    public World getWorld(String s) {
        org.spongepowered.api.world.World world = this.getSpongeValue().getWorld(s).orElse(null);
        if(world == null){
            return null;
        }
        return new BongeWorld(world);
    }

    @Override
    public World getWorld(UUID uuid) {
        org.spongepowered.api.world.World world = this.getSpongeValue().getWorld(uuid).orElse(null);
        if(world == null){
            return null;
        }
        return new BongeWorld(world);
    }

    @Override
    public MapView getMap(short id) {
        return null;
    }

    @Override
    public MapView createMap(World world) {
        return null;
    }

    @Override
    public ItemStack createExplorerMap(World world, Location location, StructureType structureType) {
        return null;
    }

    @Override
    public ItemStack createExplorerMap(World world, Location location, StructureType structureType, int i, boolean b) {
        return null;
    }

    @Override
    public void reload() {

    }

    @Override
    public void reloadData() {

    }

    @Override
    public Logger getLogger() {
        return BongeLaunch.getLogger();
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
    public boolean dispatchCommand(CommandSender commandSender, String s) throws CommandException {
        CommandResult result = Sponge.getCommandManager().process(InterfaceConvert.getSource(commandSender), s);
        return result.equals(CommandResult.empty());
    }

    @Override
    public boolean addRecipe(Recipe recipe) {
        return false;
    }

    @Override
    public List<Recipe> getRecipesFor(ItemStack itemStack) {
        return null;
    }

    @Override
    public Iterator<Recipe> recipeIterator() {
        return null;
    }

    @Override
    public void clearRecipes() {

    }

    public void resetRecipes() {

    }

    @Override
    public Map<String, String[]> getCommandAliases() {
        return null;
    }

    @Override
    public int getSpawnRadius() {
        return 0;
    }

    @Override
    public void setSpawnRadius(int i) {

    }

    @Override
    public boolean getOnlineMode() {
        return this.getSpongeValue().getOnlineMode();
    }

    @Override
    public boolean getAllowFlight() {
        return false;
    }

    @Override
    public boolean isHardcore() {
        return false;
    }

    @Override
    public void shutdown() {

    }

    @Override
    public int broadcast(String s, String s1) {
        return 0;
    }

    @Override
    public OfflinePlayer getOfflinePlayer(String s) {
        return null;
    }

    @Override
    public OfflinePlayer getOfflinePlayer(UUID uuid) {
        return null;
    }

    @Override
    public Set<String> getIPBans() {
        return null;
    }

    @Override
    public void banIP(String s) {

    }

    @Override
    public void unbanIP(String s) {

    }

    @Override
    public Set<OfflinePlayer> getBannedPlayers() {
        return null;
    }

    @Override
    public BanList getBanList(BanList.Type type) {
        return null;
    }

    @Override
    public Set<OfflinePlayer> getOperators() {
        return null;
    }

    @Override
    public GameMode getDefaultGameMode() {
        Optional<WorldProperties> opWorld = this.spongeValue.getDefaultWorld();
        return opWorld.map(worldProperties -> EnumConvert.getGamemode(worldProperties.getGameMode())).orElse(null);
    }

    @Override
    public void setDefaultGameMode(GameMode gameMode) {
        Optional<WorldProperties> opWorld = this.spongeValue.getDefaultWorld();
        if(!opWorld.isPresent()){
            return;
        }
        opWorld.get().setGameMode(EnumConvert.getGamemode(gameMode));
    }

    @Override
    public ConsoleCommandSender getConsoleSender() {
        return new ConsoleSource();
    }

    @Override
    public File getWorldContainer() {
        return null;
    }

    @Override
    public OfflinePlayer[] getOfflinePlayers() {
        return new OfflinePlayer[0];
    }

    @Override
    public Messenger getMessenger() {
        return new BongeMessenger();
    }

    @Override
    public HelpMap getHelpMap() {
        return null;
    }

    @Override
    public Inventory createInventory(InventoryHolder inventoryHolder, InventoryType inventoryType) {
        return this.createInventory(inventoryHolder, inventoryType, null);
    }

    @Override
    public Inventory createInventory(InventoryHolder inventoryHolder, InventoryType inventoryType, String s) {
        org.spongepowered.api.item.inventory.Inventory.Builder inventoryBuilder = org.spongepowered.api.item.inventory.Inventory
                .builder()
                .of(InventoryConvert.getInventoryType(inventoryType));
        org.spongepowered.api.item.inventory.Inventory inventory = inventoryBuilder.build(BongeLaunch.getInstance());
        if(inventory.getArchetype().equals(InventoryArchetypes.WORKBENCH)){
            return new BongeWorkbenchInventory((inventoryHolder instanceof Furnace ? ((Furnace)inventoryHolder) : null), (org.spongepowered.api.item.inventory.crafting.CraftingInventory) inventory);
        }
        if(inventory.getArchetype().equals(InventoryArchetypes.FURNACE)){
            return new BongeFurnaceInventory((inventoryHolder instanceof Furnace ? ((Furnace)inventoryHolder) : null), (org.spongepowered.api.item.inventory.type.TileEntityInventory<org.spongepowered.api.block.tileentity.carrier.Furnace>)inventory);
        }
        if(inventory.getArchetype().equals(InventoryArchetypes.CHEST) || inventory.getArchetype().equals(InventoryArchetypes.DOUBLE_CHEST)){
            BongeCustomInventory bci = new BongeCustomInventory(inventory);
            bci.setHolder(inventoryHolder);
            return bci;
        }
        return null;
    }

    @Override
    public Inventory createInventory(InventoryHolder inventoryHolder, int i) throws IllegalArgumentException {
        org.spongepowered.api.item.inventory.Inventory inventory = org.spongepowered.api.item.inventory.Inventory
                .builder()
                .of(InventoryArchetypes.CHEST)
                .property(InventoryDimension.of(9, i / 9))
                .build(BongeLaunch.getInstance());
        BongeCustomInventory bci = new BongeCustomInventory(inventory);
        bci.setHolder(inventoryHolder);
        return bci;
    }

    @Override
    public Inventory createInventory(InventoryHolder inventoryHolder, int i, String s) throws IllegalArgumentException {
        org.spongepowered.api.item.inventory.Inventory inventory = org.spongepowered.api.item.inventory.Inventory
                .builder()
                .of(InventoryArchetypes.CHEST)
                .property(InventoryDimension.of(9, i/9))
                .property(InventoryTitle.of(InterfaceConvert.fromString(s)))
                .build(BongeLaunch.getInstance());
        BongeCustomInventory bci = new BongeCustomInventory(inventory);
        bci.setHolder(inventoryHolder);
        return bci;
    }

    @Override
    public Merchant createMerchant(String s) {
        return null;
    }

    @Override
    public int getMonsterSpawnLimit() {
        return 0;
    }

    @Override
    public int getAnimalSpawnLimit() {
        return 0;
    }

    @Override
    public int getWaterAnimalSpawnLimit() {
        return 0;
    }

    @Override
    public int getAmbientSpawnLimit() {
        return 0;
    }

    @Override
    public boolean isPrimaryThread() {
        return false;
    }

    @Override
    public String getMotd() {
        return null;
    }

    @Override
    public String getShutdownMessage() {
        return null;
    }

    @Override
    public Warning.WarningState getWarningState() {
        return null;
    }

    @Override
    public ItemFactory getItemFactory() {
        return new BongeItemFactory();
    }

    @Override
    public ScoreboardManager getScoreboardManager() {
        return new BongeScoreboardManager();
    }

    @Override
    public CachedServerIcon getServerIcon() {
        return null;
    }

    @Override
    public CachedServerIcon loadServerIcon(File file) {
        return null;
    }

    @Override
    public CachedServerIcon loadServerIcon(BufferedImage bufferedImage) {
        return null;
    }

    @Override
    public void setIdleTimeout(int i) {

    }

    @Override
    public int getIdleTimeout() {
        return 0;
    }

    @Override
    public ChunkGenerator.ChunkData createChunkData(World world) {
        return null;
    }

    @Override
    public BossBar createBossBar(String s, BarColor barColor, BarStyle barStyle, BarFlag... barFlags) {
        return new BongeServerBossBar(ServerBossBar
                .builder()
                .name(InterfaceConvert.fromString(s))
                .color(EnumConvert.getColour(barColor))
                .overlay(EnumConvert.getStyle(barStyle))
                .createFog(containsFlag(BarFlag.CREATE_FOG, barFlags))
                .darkenSky(containsFlag(BarFlag.DARKEN_SKY, barFlags))
                .playEndBossMusic(containsFlag(BarFlag.PLAY_BOSS_MUSIC, barFlags))
                .build());
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
    public Entity getEntity(UUID uuid) {
        for (org.spongepowered.api.world.World world : this.spongeValue.getWorlds()){
            Optional<org.spongepowered.api.entity.Entity> opEntity = world.getEntity(uuid);
            if(opEntity.isPresent()){
                return BongeAbstractEntity.of(opEntity.get());
            }
        }
        return null;
    }

    @Override
    public Advancement getAdvancement(NamespacedKey namespacedKey) {
        return null;
    }

    @Override
    public Iterator<Advancement> advancementIterator() {
        return null;
    }

    @Override
    public BlockData createBlockData(Material material) {
        return BongeAbstractBlockData.of((material.getSpongeBlockValue().get()).getDefaultState());
    }

    @Override
    public BlockData createBlockData(Material material, Consumer<BlockData> consumer) {
        BlockData data = createBlockData(material);
        consumer.accept(data);
        return data;
    }

    @Override
    public BlockData createBlockData(String s) throws IllegalArgumentException {
        Optional<BlockState> opState = Sponge.getRegistry().getType(BlockState.class, s);
        return opState.map(BongeAbstractBlockData::of).orElse(null);
    }

    @Override
    public BlockData createBlockData(Material material, String s) throws IllegalArgumentException {
        return createBlockData(material.getKey().getNamespace() + "[" + s + "]");
    }

    @Override
    public <T extends Keyed> Tag<T> getTag(String s, NamespacedKey namespacedKey, Class<T> aClass) {
        return new BongeTag<>(namespacedKey, s);
        /*for (Tag<T> value : this.getTags(s, aClass)){
            if(value.getKey().equals(namespacedKey)){
                return value;
            }
        }
        throw new IllegalStateException("Unknown Tag namespace of " + namespacedKey.toString() + " in extra: " + s);*/
    }

    /*@Override
    public <T extends Keyed> Iterable<Tag<T>> getTags(String s, Class<T> aClass) {
        Set<Tag<T>> set = new HashSet<>();
        this.tags.stream().filter(t -> t.getExtra().equals(s)).forEach(t -> set.add((Tag<T>) t));
        return set;
    }*/

    @Override
    public LootTable getLootTable(NamespacedKey namespacedKey) {
        return null;
    }

    /*@Override
    public List<Entity> selectEntities(CommandSender commandSender, String s) throws IllegalArgumentException {
        return null;
    }*/

    @Override
    public UnsafeValues getUnsafe() {
        return new BongeUnsafeValues();
    }

    @Override
    public void sendPluginMessage(Plugin plugin, String s, byte[] bytes) {

    }

    @Override
    public Set<String> getListeningPluginChannels() {
        return null;
    }
}
