package org.bonge.bukkit.r1_16.server;

import org.bonge.Bonge;
import org.bonge.bukkit.r1_16.block.BongeTag;
import org.bonge.bukkit.r1_16.block.data.BongeAbstractBlockData;
import org.bonge.bukkit.r1_16.boss.BongeServerBossBar;
import org.bonge.bukkit.r1_16.command.BongeCommandManager;
import org.bonge.bukkit.r1_16.entity.EntityManager;
import org.bonge.bukkit.r1_16.entity.living.human.BongeOfflinePlayer;
import org.bonge.bukkit.r1_16.entity.living.human.BongePlayer;
import org.bonge.bukkit.r1_16.inventory.item.BongeItemFactory;
import org.bonge.bukkit.r1_16.scheduler.BongeScheduler;
import org.bonge.bukkit.r1_16.scoreboard.BongeScoreboardManager;
import org.bonge.bukkit.r1_16.server.help.BongeHelpMap;
import org.bonge.bukkit.r1_16.server.messager.BongeMessenger;
import org.bonge.bukkit.r1_16.server.plugin.BongePluginManager;
import org.bonge.bukkit.r1_16.server.service.BongeServiceManager;
import org.bonge.bukkit.r1_16.server.source.ConsoleSource;
import org.bonge.bukkit.r1_16.toremove.BongeUnsafeValues;
import org.bonge.bukkit.r1_16.world.BongeWorld;
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
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class BongeServer extends BongeWrapper<org.spongepowered.api.Server> implements Server {

    private final Set<BongeTag<? extends Keyed>> tags = new HashSet<>();

    private final BongePluginManager pluginManager;
    private final BongeScheduler pluginScheduler;
    private final BongeCommandManager commandMap;
    private final BongeServiceManager serviceManager;
    private final EntityManager entityManager;
    private final BongeHelpMap helpMap;

    public BongeServer() {
        this(null);
    }

    public BongeServer(org.spongepowered.api.Server server) {
        super(server);
        this.pluginManager = new BongePluginManager();
        this.entityManager = new EntityManager();
        this.pluginScheduler = new BongeScheduler();
        this.commandMap = new BongeCommandManager();
        this.serviceManager = new BongeServiceManager();
        this.helpMap = new BongeHelpMap();
    }

    public EntityManager getEntityManager() {
        return this.entityManager;
    }

    public void setSpongeServer(org.spongepowered.api.Server server) {
        this.spongeValue = server;
        if (this.tags.isEmpty()) {
            List<BongeTag<? extends Keyed>> list = Arrays.asList(
                    new BongeTag<>(NamespacedKey.minecraft("wool"),
                            Tag.REGISTRY_BLOCKS,
                            Material.BLACK_WOOL,
                            Material.BLUE_WOOL,
                            Material.BROWN_WOOL),
                    new BongeTag<>(NamespacedKey.minecraft("planks"),
                            Tag.REGISTRY_BLOCKS,
                            Material.ACACIA_PLANKS,
                            Material.BIRCH_PLANKS,
                            Material.CRIMSON_PLANKS,
                            Material.OAK_PLANKS,
                            Material.JUNGLE_PLANKS,
                            Material.SPRUCE_PLANKS,
                            Material.WARPED_PLANKS),
                    new BongeTag<>(NamespacedKey.minecraft("stone_bricks"),
                            Tag.REGISTRY_BLOCKS,
                            Material.STONE_BRICKS),
                    new BongeTag<>(NamespacedKey.minecraft("wooden_buttons"), Tag.REGISTRY_BLOCKS, Material.ACACIA_BUTTON),
                    new BongeTag<>(NamespacedKey.minecraft("buttons"), Tag.REGISTRY_BLOCKS, Material.STONE_BUTTON),
                    new BongeTag<>(NamespacedKey.minecraft("carpets"), Tag.REGISTRY_BLOCKS, Material.BLACK_CARPET),
                    new BongeTag<>(NamespacedKey.minecraft("wooden_doors"), Tag.REGISTRY_BLOCKS, Material.ACACIA_DOOR),
                    new BongeTag<>(NamespacedKey.minecraft("wooden_stairs"), Tag.REGISTRY_BLOCKS, Material.ACACIA_STAIRS),
                    new BongeTag<>(NamespacedKey.minecraft("wooden_fences"), Tag.REGISTRY_BLOCKS, Material.ACACIA_FENCE),
                    new BongeTag<>(NamespacedKey.minecraft("wooden_slabs"), Tag.REGISTRY_BLOCKS, Material.SEA_LANTERN),
                    new BongeTag<>(NamespacedKey.minecraft("wooden_pressure_plates"), Tag.REGISTRY_BLOCKS, Material.ACACIA_PRESSURE_PLATE),
                    new BongeTag<>(NamespacedKey.minecraft("pressure_plates"), Tag.REGISTRY_BLOCKS, Material.ACACIA_PRESSURE_PLATE),
                    new BongeTag<>(NamespacedKey.minecraft("stone_pressure_plates"), Tag.REGISTRY_BLOCKS, Material.STONE_PRESSURE_PLATE),
                    new BongeTag<>(NamespacedKey.minecraft("beds"), Tag.REGISTRY_BLOCKS, Material.BLACK_BED),
                    new BongeTag<>(NamespacedKey.minecraft("fences"), Tag.REGISTRY_BLOCKS, Material.ACACIA_FENCE),
                    new BongeTag<>(NamespacedKey.minecraft("tall_flowers"), Tag.REGISTRY_BLOCKS, Material.SUGAR_CANE),
                    new BongeTag<>(NamespacedKey.minecraft("flowers"), Tag.REGISTRY_BLOCKS, Material.SUGAR_CANE),
                    new BongeTag<>(NamespacedKey.minecraft("piglin_repellents"), Tag.REGISTRY_BLOCKS, Material.GOLD_NUGGET),
                    new BongeTag<>(NamespacedKey.minecraft("gold_ores"), Tag.REGISTRY_BLOCKS, Material.GOLD_ORE, Material.NETHER_GOLD_ORE),
                    new BongeTag<>(NamespacedKey.minecraft("non_flammable_wood"), Tag.REGISTRY_BLOCKS, Material.WARPED_PLANKS),
                    new BongeTag<>(NamespacedKey.minecraft("wooden_trapdoors"), Tag.REGISTRY_BLOCKS, Material.ACACIA_TRAPDOOR),
                    new BongeTag<>(NamespacedKey.minecraft("doors"), Tag.REGISTRY_BLOCKS, Material.ACACIA_DOOR),
                    new BongeTag<>(NamespacedKey.minecraft("saplings"), Tag.REGISTRY_BLOCKS, Material.ACACIA_SAPLING),
                    new BongeTag<>(NamespacedKey.minecraft("logs_that_burn"), Tag.REGISTRY_BLOCKS, Material.ACACIA_PLANKS),
                    new BongeTag<>(NamespacedKey.minecraft("logs"), Tag.REGISTRY_BLOCKS, Material.ACACIA_LOG),
                    new BongeTag<>(NamespacedKey.minecraft("dark_oak_logs"), Tag.REGISTRY_BLOCKS, Material.DARK_OAK_LOG),
                    new BongeTag<>(NamespacedKey.minecraft("oak_logs"), Tag.REGISTRY_BLOCKS, Material.OAK_LOG),
                    new BongeTag<>(NamespacedKey.minecraft("birch_logs"), Tag.REGISTRY_BLOCKS, Material.BIRCH_LOG),
                    new BongeTag<>(NamespacedKey.minecraft("acacia_logs"), Tag.REGISTRY_BLOCKS, Material.ACACIA_LOG),
                    new BongeTag<>(NamespacedKey.minecraft("jungle_logs"), Tag.REGISTRY_BLOCKS, Material.JUNGLE_LOG),
                    new BongeTag<>(NamespacedKey.minecraft("spruce_logs"), Tag.REGISTRY_BLOCKS, Material.SPRUCE_LOG),
                    new BongeTag<>(NamespacedKey.minecraft("crimson_stems"), Tag.REGISTRY_BLOCKS, Material.CRIMSON_PLANKS),
                    new BongeTag<>(NamespacedKey.minecraft("warped_stems"), Tag.REGISTRY_BLOCKS, Material.CRIMSON_PLANKS),
                    new BongeTag<>(NamespacedKey.minecraft("banners"), Tag.REGISTRY_BLOCKS, Material.BLACK_BANNER),
                    new BongeTag<>(NamespacedKey.minecraft("sand"), Tag.REGISTRY_BLOCKS, Material.SAND),
                    new BongeTag<>(NamespacedKey.minecraft("stairs"), Tag.REGISTRY_BLOCKS, Material.ACACIA_STAIRS),
                    new BongeTag<>(NamespacedKey.minecraft("slabs"), Tag.REGISTRY_BLOCKS, Material.ACACIA_SLAB),
                    new BongeTag<>(NamespacedKey.minecraft("walls"), Tag.REGISTRY_BLOCKS, Material.BRICK_WALL),
                    new BongeTag<>(NamespacedKey.minecraft("anvil"), Tag.REGISTRY_BLOCKS, Material.ANVIL, Material.CHIPPED_ANVIL, Material.DAMAGED_ANVIL),
                    new BongeTag<>(NamespacedKey.minecraft("rails"), Tag.REGISTRY_BLOCKS, Material.RAIL, Material.ACTIVATOR_RAIL, Material.DETECTOR_RAIL, Material.POWERED_RAIL),
                    new BongeTag<>(NamespacedKey.minecraft("coral_blocks"), Tag.REGISTRY_BLOCKS, Material.BRAIN_CORAL_BLOCK),
                    new BongeTag<>(NamespacedKey.minecraft("corals"), Tag.REGISTRY_BLOCKS, Material.BRAIN_CORAL),
                    new BongeTag<>(NamespacedKey.minecraft("wall_corals"), Tag.REGISTRY_BLOCKS, Material.BUBBLE_CORAL_WALL_FAN),
                    new BongeTag<>(NamespacedKey.minecraft("leaves"), Tag.REGISTRY_BLOCKS, Material.ACACIA_LEAVES),
                    new BongeTag<>(NamespacedKey.minecraft("trapdoors"), Tag.REGISTRY_BLOCKS, Material.ACACIA_TRAPDOOR),
                    new BongeTag<>(NamespacedKey.minecraft("flower_pots"), Tag.REGISTRY_BLOCKS, Material.FLOWER_POT),
                    new BongeTag<>(NamespacedKey.minecraft("enderman_holdable"), Tag.REGISTRY_BLOCKS, Material.GRASS_BLOCK),
                    new BongeTag<>(NamespacedKey.minecraft("ice"), Tag.REGISTRY_BLOCKS, Material.ICE),
                    new BongeTag<>(NamespacedKey.minecraft("small_flowers"), Tag.REGISTRY_BLOCKS, Material.CHORUS_FLOWER),
                    new BongeTag<>(NamespacedKey.minecraft("impermeable"), Tag.REGISTRY_BLOCKS, Material.ACACIA_PLANKS),
                    new BongeTag<>(NamespacedKey.minecraft("underwater_bonemeals"), Tag.REGISTRY_BLOCKS, Material.SEAGRASS),
                    new BongeTag<>(NamespacedKey.minecraft("coral_plants"), Tag.REGISTRY_BLOCKS, Material.BRAIN_CORAL),
                    new BongeTag<>(NamespacedKey.minecraft("corals"), Tag.REGISTRY_BLOCKS, Material.BRAIN_CORAL),
                    new BongeTag<>(NamespacedKey.minecraft("bamboo_plantable_on"), Tag.REGISTRY_BLOCKS, Material.BAMBOO_SAPLING),
                    new BongeTag<>(NamespacedKey.minecraft("standing_signs"), Tag.REGISTRY_BLOCKS, Material.ACACIA_SIGN),
                    new BongeTag<>(NamespacedKey.minecraft("wall_signs"), Tag.REGISTRY_BLOCKS, Material.ACACIA_SIGN),
                    new BongeTag<>(NamespacedKey.minecraft("signs"), Tag.REGISTRY_BLOCKS, Material.ACACIA_SIGN),
                    new BongeTag<>(NamespacedKey.minecraft("dragon_immune"), Tag.REGISTRY_BLOCKS, Material.DRAGON_EGG),
                    new BongeTag<>(NamespacedKey.minecraft("wither_immune"), Tag.REGISTRY_BLOCKS, Material.DRAGON_EGG),
                    new BongeTag<>(NamespacedKey.minecraft("wither_summon_base_blocks"), Tag.REGISTRY_BLOCKS, Material.WITHER_SKELETON_SKULL),
                    new BongeTag<>(NamespacedKey.minecraft("beehives"), Tag.REGISTRY_BLOCKS, Material.BEEHIVE),
                    new BongeTag<>(NamespacedKey.minecraft("crops"), Tag.REGISTRY_BLOCKS, Material.SUGAR_CANE),
                    new BongeTag<>(NamespacedKey.minecraft("bee_growables"), Tag.REGISTRY_BLOCKS, Material.SUGAR_CANE),
                    new BongeTag<>(NamespacedKey.minecraft("portals"), Tag.REGISTRY_BLOCKS, Material.NETHER_PORTAL, Material.END_PORTAL),
                    new BongeTag<>(NamespacedKey.minecraft("fire"), Tag.REGISTRY_BLOCKS, Material.FIRE),
                    new BongeTag<>(NamespacedKey.minecraft("nylium"), Tag.REGISTRY_BLOCKS, Material.CRIMSON_NYLIUM),
                    new BongeTag<>(NamespacedKey.minecraft("wart_blocks"), Tag.REGISTRY_BLOCKS, Material.NETHER_WART),
                    new BongeTag<>(NamespacedKey.minecraft("beacon_base_blocks"), Tag.REGISTRY_BLOCKS, Material.IRON_BLOCK),
                    new BongeTag<>(NamespacedKey.minecraft("soul_speed_blocks"), Tag.REGISTRY_BLOCKS, Material.SOUL_SAND),
                    new BongeTag<>(NamespacedKey.minecraft("wall_post_override"), Tag.REGISTRY_BLOCKS, Material.ACACIA_SLAB),
                    new BongeTag<>(NamespacedKey.minecraft("climbable"), Tag.REGISTRY_BLOCKS, Material.LADDER),
                    new BongeTag<>(NamespacedKey.minecraft("shulker_boxes"), Tag.REGISTRY_BLOCKS, Material.BLACK_SHULKER_BOX),
                    new BongeTag<>(NamespacedKey.minecraft("hoglin_repellents"), Tag.REGISTRY_BLOCKS, Material.WATER),
                    new BongeTag<>(NamespacedKey.minecraft("soul_fire_base_blocks"), Tag.REGISTRY_BLOCKS, Material.NETHERRACK),
                    new BongeTag<>(NamespacedKey.minecraft("strider_warm_blocks"), Tag.REGISTRY_BLOCKS, Material.NETHERRACK),
                    new BongeTag<>(NamespacedKey.minecraft("campfires"), Tag.REGISTRY_BLOCKS, Material.CAMPFIRE),
                    new BongeTag<>(NamespacedKey.minecraft("guarded_by_piglins"), Tag.REGISTRY_BLOCKS, Material.CHEST),
                    new BongeTag<>(NamespacedKey.minecraft("prevent_mob_spawning_inside"), Tag.REGISTRY_BLOCKS, Material.ACACIA_SIGN),
                    new BongeTag<>(NamespacedKey.minecraft("fence_gates"), Tag.REGISTRY_BLOCKS, Material.ACACIA_FENCE_GATE),
                    new BongeTag<>(NamespacedKey.minecraft("unstable_bottom_center"), Tag.REGISTRY_BLOCKS, Material.DIRT),
                    new BongeTag<>(NamespacedKey.minecraft("infiniburn_overworld"), Tag.REGISTRY_BLOCKS, Material.NETHERRACK),
                    new BongeTag<>(NamespacedKey.minecraft("infiniburn_nether"), Tag.REGISTRY_BLOCKS, Material.NETHERRACK),
                    new BongeTag<>(NamespacedKey.minecraft("infiniburn_end"), Tag.REGISTRY_BLOCKS, Material.NETHERRACK),
                    new BongeTag<>(NamespacedKey.minecraft("piglin_loved"), Tag.REGISTRY_ITEMS, Material.GOLD_NUGGET),
                    new BongeTag<>(NamespacedKey.minecraft("banners"), Tag.REGISTRY_ITEMS, Material.BLACK_BANNER),
                    new BongeTag<>(NamespacedKey.minecraft("boats"), Tag.REGISTRY_ITEMS, Material.BIRCH_BOAT),
                    new BongeTag<>(NamespacedKey.minecraft("fishes"), Tag.REGISTRY_ITEMS, Material.COD),
                    new BongeTag<>(NamespacedKey.minecraft("music_discs"), Tag.REGISTRY_ITEMS, Material.MUSIC_DISC_CHIRP),
                    new BongeTag<>(NamespacedKey.minecraft("creeper_drop_music_discs"), Tag.REGISTRY_ITEMS, Material.MUSIC_DISC_13),
                    new BongeTag<>(NamespacedKey.minecraft("piglin_loved"), Tag.REGISTRY_ITEMS, Material.MUSIC_DISC_13),
                    new BongeTag<>(NamespacedKey.minecraft("coals"), Tag.REGISTRY_ITEMS, Material.COAL),
                    new BongeTag<>(NamespacedKey.minecraft("arrows"), Tag.REGISTRY_ITEMS, Material.ARROW),
                    new BongeTag<>(NamespacedKey.minecraft("lectern_books"), Tag.REGISTRY_ITEMS, Material.KNOWLEDGE_BOOK),
                    new BongeTag<>(NamespacedKey.minecraft("beacon_payment_items"), Tag.REGISTRY_ITEMS, Material.GOLDEN_SWORD),
                    new BongeTag<>(NamespacedKey.minecraft("stone_tool_materials"), Tag.REGISTRY_ITEMS, Material.STONE_AXE),
                    new BongeTag<>(NamespacedKey.minecraft("furnace_materials"), Tag.REGISTRY_ITEMS, Material.COAL),
                    new BongeTag<>(NamespacedKey.minecraft("lava"), Tag.REGISTRY_FLUIDS, Material.LAVA),
                    new BongeTag<>(NamespacedKey.minecraft("water"), Tag.REGISTRY_FLUIDS, Material.WATER),


                    new BongeTag<>(NamespacedKey.minecraft("valid_spawn"), Tag.REGISTRY_BLOCKS, Material.DIRT),
                    new BongeTag<>(NamespacedKey.minecraft("impermeable"), Tag.REGISTRY_BLOCKS, Material.DIRT)
            );
            this.tags.addAll(list);
        }
    }

    public BongeCommandManager getCommandManager() {
        return this.commandMap;
    }

    public ServerWorld getDefaultWorld() {
        return Sponge.server().worldManager().defaultWorld();
    }

    @Override
    public @NotNull String getName() {
        return BongeLaunch.PLUGIN_NAME;
    }

    @Override
    public @NotNull String getVersion() {
        return BongeLaunch.PLUGIN_NAME + ": " + getBukkitVersion() + " | MC: " + Sponge.platform().minecraftVersion().name();
    }

    @Override
    public @NotNull String getBukkitVersion() {
        return BongeLaunch.IMPLEMENTATION_VERSION + " (" + BongeLaunch.PLUGIN_VERSION + ")";
    }

    @Override
    public @NotNull Collection<? extends Player> getOnlinePlayers() {
        return this.spongeValue.onlinePlayers().stream().map(player -> Bonge.getInstance().convert(player)).collect(Collectors.toSet());
    }

    @Override
    public int getMaxPlayers() {
        return this.getSpongeValue().maxPlayers();
    }

    @Override
    public int getPort() {
        return this.getSpongeValue().boundAddress().orElseThrow(() -> new IllegalStateException("Unknown Bound Address of server")).getPort();
    }

    @Override
    public int getViewDistance() {
        throw new NotImplementedException("World.getViewDistance() is not supported on Sponge");
    }

    @Override
    public @NotNull String getIp() {
        return this.getSpongeValue().boundAddress().get().getAddress().getHostAddress();
    }

    public String getServerName() {
        return "BongeServer";
    }

    public String getServerId() {
        return "bonge:bongeServer";
    }

    @Override
    public @NotNull String getWorldType() {
        throw new NotImplementedException("Server.getWorldType() Not got to yet");
    }

    @Override
    public boolean getGenerateStructures() {
        throw new NotImplementedException("Server.getGenerateStructures() Not got to yet");
    }

    @Override
    public int getMaxWorldSize() {
        throw new NotImplementedException("Server.getMaxWorldSize() Not got to yet");
    }

    @Override
    public boolean getAllowEnd() {
        return Sponge.server().worldManager().worldExists(ResourceKey.minecraft("the_end"));
    }

    @Override
    public boolean getAllowNether() {
        return Sponge.server().worldManager().worldExists(ResourceKey.minecraft("the_nether"));
    }

    @Override
    public boolean hasWhitelist() {
        return this.getSpongeValue().isWhitelistEnabled();
    }

    @Override
    public void setWhitelist(boolean b) {
        this.getSpongeValue().setHasWhitelist(b);
    }

    @Override
    public @NotNull Set<OfflinePlayer> getWhitelistedPlayers() {
        throw new NotImplementedException("Server.getWhitelistedPlayers() Not got to yet");

    }

    @Override
    public void reloadWhitelist() {
    }

    @Override
    public int broadcastMessage(@NotNull String s) {
        Sponge.server().broadcastAudience().sendMessage(Bonge.getInstance().convertText(s));
        return Sponge.server().onlinePlayers().size();
    }

    @Override
    public @NotNull String getUpdateFolder() {
        throw new NotImplementedException("Server.getUpdateFolder() Not got to yet");
    }

    @Override
    public @NotNull File getUpdateFolderFile() {
        throw new NotImplementedException("Server.getUpdateFolderFile() Not got to yet");
    }

    @Override
    public long getConnectionThrottle() {
        throw new NotImplementedException("Server.getConnectionThrottle() Not got to yet");

    }

    @Override
    public int getTicksPerAnimalSpawns() {
        throw new NotImplementedException("Server.getTicksPerAnimalSpawns() Not got to yet");

    }

    @Override
    public int getTicksPerMonsterSpawns() {
        throw new NotImplementedException("Server.getTicksPerMonsterSpawns() Not got to yet");

    }

    @Override
    public int getTicksPerWaterSpawns() {
        throw new NotImplementedException("Server.getTicksPerWaterSpawns() Not got to yet");
    }

    @Override
    public int getTicksPerWaterAmbientSpawns() {
        throw new NotImplementedException("Server.getTicksPerWaterAmbientSpawns() Not got to yet");
    }

    @Override
    public int getTicksPerAmbientSpawns() {
        throw new NotImplementedException("Server.getTicksPerAmbientSpawns() Not got to yet");
    }

    @Override
    public Player getPlayer(@NotNull String s) {
        Optional<ServerPlayer> opPlayer = this.getSpongeValue().onlinePlayers().stream().filter(p -> p.name().equalsIgnoreCase(s)).findAny();
        return opPlayer.map(BongePlayer::new).orElse(null);
    }

    @Override
    public Player getPlayerExact(@NotNull String s) {
        Optional<ServerPlayer> opPlayer = this.getSpongeValue().onlinePlayers().stream().filter(p -> p.name().equals(s)).findAny();
        return opPlayer.map(BongePlayer::new).orElse(null);
    }

    @Override
    public @NotNull List<Player> matchPlayer(@NotNull String s) {
        throw new NotImplementedException("Server.matchPlayer(String s) Not got to yet");

    }

    @Override
    public Player getPlayer(@NotNull UUID uuid) {
        Optional<ServerPlayer> opPlayer = this.getSpongeValue().onlinePlayers().stream().filter(p -> p.uniqueId().equals(uuid)).findAny();
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
        this.getSpongeValue().worldManager().worlds().forEach(w -> list.add(new BongeWorld(w)));
        return list;
    }

    @Override
    public World createWorld(@NotNull WorldCreator worldCreator) {
        throw new NotImplementedException("Server.createWorld(WorldCreator worldCreator) Not got to yet");
    }

    @Override
    public boolean unloadWorld(@NotNull String s, boolean b) {
        throw new NotImplementedException("Server.unloadWorld(String, boolean) Not got to yet");
    }

    @Override
    public boolean unloadWorld(@NotNull World world, boolean b) {
        throw new NotImplementedException("Server.unloadWorld(World, boolean) Not got to yet");

    }

    //this has become 10X harder
    @Override
    public World getWorld(@NotNull String s) {
        Optional<ServerWorld> opWorld = this.getSpongeValue().worldManager().worlds().stream().filter(w -> w.key().value().equalsIgnoreCase(s)).findAny();
        return opWorld.map(BongeWorld::new).orElse(null);
    }

    @Override
    public World getWorld(@NotNull UUID uuid) {
        Optional<ServerWorld> opWorld = this.getSpongeValue().worldManager().worlds().stream().filter(w -> w.uniqueId().equals(uuid)).findAny();
        return opWorld.map(BongeWorld::new).orElse(null);
    }

    public @Nullable MapView getMap(int id) {
        throw new NotImplementedException("Sponge does not have support for MapView");
    }

    @Override
    public @NotNull MapView createMap(@NotNull World world) {
        throw new NotImplementedException("Sponge does not have support for MapView");
    }

    @Override
    public @NotNull ItemStack createExplorerMap(@NotNull World world, @NotNull Location location, @NotNull StructureType structureType) {
        throw new NotImplementedException("Server.createExplorerMap(World, Location, StructureType) not looked at yet");
    }

    @Override
    public @NotNull ItemStack createExplorerMap(@NotNull World world, @NotNull Location location, @NotNull StructureType structureType, int i, boolean b) {
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
    public PluginCommand getPluginCommand(@NotNull String s) {
        Command command = this.commandMap.getCommand(s);
        if (command instanceof PluginCommand) {
            return (PluginCommand) command;
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

    @Nullable
    @Override
    public Recipe getRecipe(@NotNull NamespacedKey recipeKey) {
        return null;
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
    public boolean removeRecipe(@NotNull NamespacedKey key) {
        return false;
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
        return this.getSpongeValue().isOnlineModeEnabled();
    }

    @Override
    public boolean getAllowFlight() {
        throw new NotImplementedException("Sponge does not support AllowFlight as far as im aware");
    }

    @Override
    public boolean isHardcore() {
        return this.getDefaultWorld().properties().hardcore();
    }

    @Override
    public void shutdown() {
        Sponge.server().shutdown();
    }

    @Override
    public int broadcast(@NotNull String s, @NotNull String s1) {
        throw new NotImplementedException("Server.broadcast(String, String) has not been looked at yet");
    }

    @Override
    public @NotNull OfflinePlayer getOfflinePlayer(@NotNull String s) {
        CompletableFuture<Optional<User>> future = this.getSpongeValue().userManager().load(s);
        Optional<User> opUser = future.getNow(Optional.empty());
        return opUser.map(BongeOfflinePlayer::new).orElseThrow(() -> new NotImplementedException("Couldnt find offline user with that name"));
    }

    @Override
    public @NotNull OfflinePlayer getOfflinePlayer(@NotNull UUID uuid) {
        CompletableFuture<User> future = this.getSpongeValue().userManager().loadOrCreate(uuid);
        try {
            return new BongeOfflinePlayer(future.get());
        } catch (InterruptedException | ExecutionException e) {
            throw new IllegalStateException("Bukkits inefficiencies got in the way", e);
        }
    }

    @Override
    public @NotNull Set<String> getIPBans() {
        throw new NotImplementedException("Server.getIPBans() has not been looked at yet");
    }

    @Override
    public void banIP(@NotNull String s) {
        throw new NotImplementedException("Server.banIP(String) has not been looked at yet");
    }

    @Override
    public void unbanIP(@NotNull String s) {
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
        return this
                .getSpongeValue()
                .userManager()
                .streamAll()
                .map(p -> {
                    try {
                        return this.getSpongeValue().userManager().loadOrCreate(p.uuid()).get();
                    } catch (InterruptedException | ExecutionException e) {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .filter(p -> p.hasPermission(Permissions.BONGE_OP))
                .map(BongeOfflinePlayer::new)
                .collect(Collectors.toSet());
    }

    @Override
    public @NotNull GameMode getDefaultGameMode() {
        ServerWorld world = this.getDefaultWorld();
        org.spongepowered.api.entity.living.player.gamemode.GameMode mode = world.properties().gameMode();
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
            world.properties().setGameMode(sGamemode);
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
        return this.getDefaultWorld().directory().toFile();
    }

    @Override
    public OfflinePlayer[] getOfflinePlayers() {
        return this
                .getSpongeValue()
                .userManager()
                .streamAll()
                .map(u -> {
                    try {
                        return this.getSpongeValue().userManager().loadOrCreate(u.uuid()).get();
                    } catch (InterruptedException | ExecutionException e) {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .map(BongeOfflinePlayer::new)
                .toArray(BongeOfflinePlayer[]::new);
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
    public int getWaterAmbientSpawnLimit() {
        return 0;
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
    public @NotNull Warning.WarningState getWarningState() {
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
    public @NotNull ChunkGenerator.ChunkData createChunkData(@NotNull World world) {
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
        return new BongeServerBossBar(net.kyori.adventure.bossbar.BossBar.bossBar(Bonge.getInstance().convertText(s), 0, colour, overlay));
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

    private boolean containsFlag(BarFlag check, BarFlag... flags) {
        for (BarFlag flag : flags) {
            if (flag.equals(check)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Entity getEntity(@NotNull UUID uuid) {
        for (ServerWorld world : this.spongeValue.worldManager().worlds()) {
            Optional<org.spongepowered.api.entity.Entity> opEntity = world.entity(uuid);
            if (opEntity.isPresent()) {
                try {
                    return Bonge.getInstance().convert(Entity.class, opEntity.get());
                } catch (IOException e) {
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
        try {

            return BongeAbstractBlockData.findDynamicClass(BlockState.fromString(s));
        } catch (InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException e) {
            throw new IllegalStateException(e);
        } catch (Throwable e) {
            throw new IllegalArgumentException("Unknown BlockState of " + s);
        }
    }

    @Override
    public @NotNull BlockData createBlockData(Material material, String s) throws IllegalArgumentException {
        return createBlockData(material.getKey().getNamespace() + "[" + s + "]");
    }

    @Override
    public <T extends Keyed> Tag<T> getTag(@NotNull String s, @NotNull NamespacedKey namespacedKey, @NotNull Class<T> aClass) {
        //return new BongeTag<>(namespacedKey, s);
        for (Tag<T> value : this.getTags(namespacedKey.toString(), aClass)) {
            if (value.getKey().equals(namespacedKey)) {
                return value;
            }
        }
        throw new IllegalStateException("Unknown Tag namespace of " + namespacedKey + " in extra: " + s);
    }

    @Override
    public @NotNull <T extends Keyed> Iterable<Tag<T>> getTags(@NotNull String registry, @NotNull Class<T> clazz) {
        return this.tags.stream()
                .filter(t -> t.getKey().toString().equalsIgnoreCase(registry))
                .filter(t -> {
                    try {
                        @NotNull Set<T> values = (Set<T>) t.getValues();
                        return true;
                    } catch (ClassCastException e) {
                        return false;
                    }
                })
                .map(t -> (BongeTag<T>) t)
                .collect(Collectors.toSet());
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
