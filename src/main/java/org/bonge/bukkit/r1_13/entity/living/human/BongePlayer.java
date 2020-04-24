package org.bonge.bukkit.r1_13.entity.living.human;

import org.bonge.bukkit.r1_13.block.data.BongeAbstractBlockData;
import org.bonge.bukkit.r1_13.entity.BongeAbstractEntity;
import org.bonge.bukkit.r1_13.inventory.inventory.BongeAbstractInventory;
import org.bonge.bukkit.r1_13.inventory.inventory.BongeInventoryView;
import org.bonge.bukkit.r1_13.inventory.inventory.tileentity.workbench.BongeCustomWorkbenchInventory;
import org.bonge.bukkit.r1_13.scoreboard.BongeScoreboard;
import org.bonge.convert.InterfaceConvert;
import org.bonge.launch.BongeLaunch;
import org.bonge.util.ArrayUtils;
import org.bukkit.*;
import org.bukkit.advancement.Advancement;
import org.bukkit.advancement.AdvancementProgress;
import org.bukkit.block.data.BlockData;
import org.bukkit.conversations.Conversation;
import org.bukkit.conversations.ConversationAbandonedEvent;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.Merchant;
import org.bukkit.map.MapView;
import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.Scoreboard;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.item.inventory.InventoryArchetypes;
import org.spongepowered.api.resourcepack.ResourcePack;
import org.spongepowered.api.resourcepack.ResourcePacks;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.title.Title;

import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class BongePlayer extends BongeAbstractHuman<org.spongepowered.api.entity.living.player.Player> implements Player {

    private static Set<BongePlayer> PLAYERS = new HashSet<>();

    private BongeInventoryView view;

    public BongePlayer(org.spongepowered.api.entity.living.player.Player entity) {
        super(entity);
    }

    @Override
    public @NotNull String getDisplayName() {
        return InterfaceConvert.toString(this.spongeValue.get(Keys.DISPLAY_NAME).get());
    }

    @Override
    public void setDisplayName(@Nullable String name) {
        this.spongeValue.offer(Keys.DISPLAY_NAME, InterfaceConvert.fromString(name));
    }

    @Override
    public @NotNull String getPlayerListName() {
        return null;
    }

    @Override
    public void setPlayerListName(@Nullable String name) {

    }

    @Override
    public @Nullable String getPlayerListHeader() {
        return null;
    }

    @Override
    public @Nullable String getPlayerListFooter() {
        return null;
    }

    @Override
    public void setPlayerListHeader(@Nullable String header) {

    }

    @Override
    public void setPlayerListFooter(@Nullable String footer) {

    }

    @Override
    public void setPlayerListHeaderFooter(@Nullable String header, @Nullable String footer) {
        this.setPlayerListFooter(footer);
        this.setPlayerListHeader(header);
    }

    @Override
    public void setCompassTarget(@NotNull Location loc) {

    }

    @Override
    public @NotNull Location getCompassTarget() {
        return null;
    }

    @Override
    public @Nullable InetSocketAddress getAddress() {
        return this.spongeValue.getConnection().getAddress();
    }

    @Override
    public boolean isConversing() {
        return false;
    }

    @Override
    public void acceptConversationInput(@NotNull String input) {

    }

    @Override
    public boolean beginConversation(@NotNull Conversation conversation) {
        return false;
    }

    @Override
    public void abandonConversation(@NotNull Conversation conversation) {

    }

    @Override
    public void abandonConversation(@NotNull Conversation conversation, @NotNull ConversationAbandonedEvent details) {

    }

    @Override
    public void sendRawMessage(@NotNull String message) {

    }

    @Override
    public void sendMessage(@NotNull String message) {
        this.spongeValue.sendMessage(InterfaceConvert.fromString(message));
    }

    @Override
    public void sendMessage(@NotNull String[] messages) {
        this.spongeValue.sendMessages(ArrayUtils.convert(Text.class, InterfaceConvert::fromString, messages));
    }

    @Override
    public void kickPlayer(@Nullable String message) {
        if(message != null){
            this.spongeValue.kick(InterfaceConvert.fromString(message));
            return;
        }
        this.spongeValue.kick();
    }

    @Override
    public void chat(@NotNull String msg) {

    }

    @Override
    public boolean performCommand(@NotNull String command) {
        CommandResult result = Sponge.getCommandManager().process(this.spongeValue, command);
        return !result.equals(CommandResult.empty());
    }

    @Override
    public boolean isSneaking() {
        return this.spongeValue.get(Keys.IS_SNEAKING).get();
    }

    @Override
    public void setSneaking(boolean sneak) {
        this.spongeValue.offer(Keys.IS_SNEAKING, sneak);
    }

    @Override
    public boolean isSprinting() {
        return this.spongeValue.get(Keys.IS_SPRINTING).get();
    }

    @Override
    public void setSprinting(boolean sprinting) {
        this.spongeValue.offer(Keys.IS_SNEAKING, sprinting);
    }

    @Override
    public boolean hasPermission(String permission){
        return this.spongeValue.hasPermission(permission);
    }

    @Override
    public void saveData() {

    }

    @Override
    public void loadData() {

    }

    @Override
    public void setSleepingIgnored(boolean isSleeping) {

    }

    @Override
    public boolean isSleepingIgnored() {
        return false;
    }

    @Override
    public void playNote(@NotNull Location loc, byte instrument, byte note) {

    }

    @Override
    public void playNote(@NotNull Location loc, @NotNull Instrument instrument, @NotNull Note note) {

    }

    @Override
    public void playSound(@NotNull Location location, @NotNull Sound sound, float volume, float pitch) {

    }

    @Override
    public void playSound(@NotNull Location location, @NotNull String sound, float volume, float pitch) {

    }

    @Override
    public void playSound(@NotNull Location location, @NotNull Sound sound, @NotNull SoundCategory category, float volume, float pitch) {

    }

    @Override
    public void playSound(@NotNull Location location, @NotNull String sound, @NotNull SoundCategory category, float volume, float pitch) {

    }

    @Override
    public void stopSound(@NotNull Sound sound) {

    }

    @Override
    public void stopSound(@NotNull String sound) {

    }

    @Override
    public void stopSound(@NotNull Sound sound, @Nullable SoundCategory category) {

    }

    @Override
    public void stopSound(@NotNull String sound, @Nullable SoundCategory category) {

    }

    @Override
    public void playEffect(@NotNull Location loc, @NotNull Effect effect, int data) {

    }

    @Override
    public <T> void playEffect(@NotNull Location loc, @NotNull Effect effect, @Nullable T data) {

    }

    @Override
    @Deprecated
    public void sendBlockChange(@NotNull Location loc, @NotNull Material material, byte data) {
        this.sendBlockChange(loc, material.createBlockData());
    }

    @Override
    public void sendBlockChange(@NotNull Location loc, @NotNull BlockData block) {
        this.spongeValue.sendBlockChange(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ(), ((BongeAbstractBlockData)block).getSpongeValue());
    }

    @Override
    public boolean sendChunkChange(@NotNull Location loc, int sx, int sy, int sz, @NotNull byte[] data) {
        return false;
    }

    @Override
    public void sendSignChange(@NotNull Location loc, @Nullable String[] lines) throws IllegalArgumentException {

    }

    @Override
    public void sendMap(@NotNull MapView map) {

    }

    @Override
    public void updateInventory() {

    }

    @Override
    @Deprecated
    public void awardAchievement(Achievement achievement) {

    }

    @Override
    @Deprecated
    public void removeAchievement(Achievement achievement) {

    }

    @Override
    @Deprecated
    public boolean hasAchievement(Achievement achievement) {
        return false;
    }

    @Override
    public void incrementStatistic(@NotNull Statistic statistic) throws IllegalArgumentException {

    }

    @Override
    public void decrementStatistic(@NotNull Statistic statistic) throws IllegalArgumentException {

    }

    @Override
    public void incrementStatistic(@NotNull Statistic statistic, int amount) throws IllegalArgumentException {

    }

    @Override
    public void decrementStatistic(@NotNull Statistic statistic, int amount) throws IllegalArgumentException {

    }

    @Override
    public void setStatistic(@NotNull Statistic statistic, int newValue) throws IllegalArgumentException {

    }

    @Override
    public int getStatistic(@NotNull Statistic statistic) throws IllegalArgumentException {
        return 0;
    }

    @Override
    public void incrementStatistic(@NotNull Statistic statistic, @NotNull Material material) throws IllegalArgumentException {

    }

    @Override
    public void decrementStatistic(@NotNull Statistic statistic, @NotNull Material material) throws IllegalArgumentException {

    }

    @Override
    public int getStatistic(@NotNull Statistic statistic, @NotNull Material material) throws IllegalArgumentException {
        return 0;
    }

    @Override
    public void incrementStatistic(@NotNull Statistic statistic, @NotNull Material material, int amount) throws IllegalArgumentException {

    }

    @Override
    public void decrementStatistic(@NotNull Statistic statistic, @NotNull Material material, int amount) throws IllegalArgumentException {

    }

    @Override
    public void setStatistic(@NotNull Statistic statistic, @NotNull Material material, int newValue) throws IllegalArgumentException {

    }

    @Override
    public void incrementStatistic(@NotNull Statistic statistic, @NotNull EntityType entityType) throws IllegalArgumentException {

    }

    @Override
    public void decrementStatistic(@NotNull Statistic statistic, @NotNull EntityType entityType) throws IllegalArgumentException {

    }

    @Override
    public int getStatistic(@NotNull Statistic statistic, @NotNull EntityType entityType) throws IllegalArgumentException {
        return 0;
    }

    @Override
    public void incrementStatistic(@NotNull Statistic statistic, @NotNull EntityType entityType, int amount) throws IllegalArgumentException {

    }

    @Override
    public void decrementStatistic(@NotNull Statistic statistic, @NotNull EntityType entityType, int amount) {

    }

    @Override
    public void setStatistic(@NotNull Statistic statistic, @NotNull EntityType entityType, int newValue) {

    }

    @Override
    public void setPlayerTime(long time, boolean relative) {

    }

    @Override
    public long getPlayerTime() {
        return 0;
    }

    @Override
    public long getPlayerTimeOffset() {
        return 0;
    }

    @Override
    public boolean isPlayerTimeRelative() {
        return false;
    }

    @Override
    public void resetPlayerTime() {

    }

    @Override
    public void setPlayerWeather(@NotNull WeatherType type) {

    }

    @Override
    public @Nullable WeatherType getPlayerWeather() {
        return null;
    }

    @Override
    public void resetPlayerWeather() {

    }

    @Override
    public void giveExp(int amount) {

    }

    @Override
    public void giveExpLevels(int amount) {

    }

    @Override
    public float getExp() {
        return 0;
    }

    @Override
    public void setExp(float exp) {

    }

    @Override
    public int getLevel() {
        return 0;
    }

    @Override
    public void setLevel(int level) {

    }

    @Override
    public int getTotalExperience() {
        return 0;
    }

    @Override
    public void setTotalExperience(int exp) {

    }

    @Override
    public float getExhaustion() {
        return this.spongeValue.get(Keys.EXHAUSTION).get().floatValue();
    }

    @Override
    public void setExhaustion(float value) {
        this.spongeValue.offer(Keys.EXHAUSTION, (double)value);
    }

    @Override
    public float getSaturation() {
        return this.spongeValue.get(Keys.SATURATION).get().floatValue();
    }

    @Override
    public void setSaturation(float value) {
        this.spongeValue.offer(Keys.SATURATION, (double)value);
    }

    @Override
    public int getFoodLevel() {
        return this.spongeValue.get(Keys.FOOD_LEVEL).get();
    }

    @Override
    public void setFoodLevel(int value) {
        this.spongeValue.offer(Keys.FOOD_LEVEL, value);
    }

    @Override
    public Location getBedSpawnLocation() {
        return null;
    }

    @Override
    public void setBedSpawnLocation(Location location) {

    }

    @Override
    public void setBedSpawnLocation(Location location, boolean force) {

    }

    @Override
    public boolean getAllowFlight() {
        return this.spongeValue.get(Keys.CAN_FLY).get();
    }

    @Override
    public void setAllowFlight(boolean flight) {
        this.spongeValue.offer(Keys.CAN_FLY, flight);
    }

    @Override
    public void hidePlayer(@NotNull Player player) {

    }

    @Override
    public void hidePlayer(@NotNull Plugin plugin, @NotNull Player player) {

    }

    @Override
    public void showPlayer(@NotNull Player player) {

    }

    @Override
    public void showPlayer(@NotNull Plugin plugin, @NotNull Player player) {

    }

    @Override
    public boolean canSee(@NotNull Player player) {
        return this.spongeValue.canSee(((BongePlayer)player).spongeValue);
    }

    @Override
    public boolean isFlying() {
        return this.spongeValue.get(Keys.IS_FLYING).get();
    }

    @Override
    public void setFlying(boolean value) {
        this.spongeValue.offer(Keys.IS_FLYING, value);
    }

    @Override
    public void setFlySpeed(float value) throws IllegalArgumentException {
        this.spongeValue.offer(Keys.FLYING_SPEED, (double)value);
    }

    @Override
    public void setWalkSpeed(float value) throws IllegalArgumentException {
        this.spongeValue.offer(Keys.WALKING_SPEED, (double)value);
    }

    @Override
    public float getFlySpeed() {
        return this.spongeValue.get(Keys.FLYING_SPEED).get().floatValue();
    }

    @Override
    public float getWalkSpeed() {
        return this.spongeValue.get(Keys.WALKING_SPEED).get().floatValue();
    }

    @Override
    public void setTexturePack(@NotNull String url) {
        this.setResourcePack(url);
    }

    @Override
    public void setResourcePack(@NotNull String url) {
        try {
            ResourcePack resource = ResourcePacks.fromUriUnchecked(new URI(url));
            this.spongeValue.sendResourcePack(resource);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setResourcePack(@NotNull String url, @NotNull byte[] hash) {
        this.setResourcePack(url);
    }

    @Override
    public @NotNull Scoreboard getScoreboard() {
        return new BongeScoreboard(this.spongeValue.getScoreboard());
    }

    @Override
    public void setScoreboard(@NotNull Scoreboard scoreboard) throws IllegalArgumentException, IllegalStateException {
        this.spongeValue.setScoreboard(((BongeScoreboard)scoreboard).getSpongeValue());
    }

    @Override
    public boolean isHealthScaled() {
        return false;
    }

    @Override
    public void setHealthScaled(boolean scale) {
    }

    @Override
    public void setHealthScale(double scale) throws IllegalArgumentException {
        this.spongeValue.offer(Keys.HEALTH_SCALE, scale);
    }

    @Override
    public double getHealthScale() {
        return this.spongeValue.get(Keys.HEALTH_SCALE).get();
    }

    @Override
    public @Nullable Entity getSpectatorTarget() {
        Optional<org.spongepowered.api.entity.Entity> opTarget = this.spongeValue.getSpectatorTarget();
        return opTarget.map(BongeAbstractEntity::of).orElse(null);
    }

    @Override
    public void setSpectatorTarget(@Nullable Entity entity) {
        assert entity != null;
        this.spongeValue.setSpectatorTarget(((BongeAbstractEntity<?>)entity).getSpongeValue());
    }

    @Override
    public void sendTitle(@Nullable String title, @Nullable String subtitle) {
        sendTitle(title, subtitle, -1, -1, -1);
    }

    @Override
    public void sendTitle(@Nullable String title, @Nullable String subtitle, int fadeIn, int stay, int fadeOut) {
        Title sTitle = Title.builder()
                .fadeIn(((fadeIn == -1) ? null : fadeIn))
                .fadeOut(((fadeOut == -1) ? null : fadeOut))
                .stay(((stay == -1) ? null : stay))
                .title(InterfaceConvert.fromString(title))
                .subtitle(InterfaceConvert.fromString(subtitle))
                .build();
        this.spongeValue.sendTitle(sTitle);

    }

    @Override
    public void resetTitle() {
        this.spongeValue.resetTitle();
    }

    @Override
    public void spawnParticle(@NotNull Particle particle, @NotNull Location location, int count) {

    }

    @Override
    public void spawnParticle(@NotNull Particle particle, double x, double y, double z, int count) {

    }

    @Override
    public <T> void spawnParticle(@NotNull Particle particle, @NotNull Location location, int count, @Nullable T data) {

    }

    @Override
    public <T> void spawnParticle(@NotNull Particle particle, double x, double y, double z, int count, @Nullable T data) {

    }

    @Override
    public void spawnParticle(@NotNull Particle particle, @NotNull Location location, int count, double offsetX, double offsetY, double offsetZ) {

    }

    @Override
    public void spawnParticle(@NotNull Particle particle, double x, double y, double z, int count, double offsetX, double offsetY, double offsetZ) {

    }

    @Override
    public <T> void spawnParticle(@NotNull Particle particle, @NotNull Location location, int count, double offsetX, double offsetY, double offsetZ, @Nullable T data) {

    }

    @Override
    public <T> void spawnParticle(@NotNull Particle particle, double x, double y, double z, int count, double offsetX, double offsetY, double offsetZ, @Nullable T data) {

    }

    @Override
    public void spawnParticle(@NotNull Particle particle, @NotNull Location location, int count, double offsetX, double offsetY, double offsetZ, double extra) {

    }

    @Override
    public void spawnParticle(@NotNull Particle particle, double x, double y, double z, int count, double offsetX, double offsetY, double offsetZ, double extra) {

    }

    @Override
    public <T> void spawnParticle(@NotNull Particle particle, @NotNull Location location, int count, double offsetX, double offsetY, double offsetZ, double extra, @Nullable T data) {

    }

    @Override
    public <T> void spawnParticle(@NotNull Particle particle, double x, double y, double z, int count, double offsetX, double offsetY, double offsetZ, double extra, @Nullable T data) {

    }

    @Override
    public @NotNull AdvancementProgress getAdvancementProgress(@NotNull Advancement advancement) {
        return null;
    }

    @Override
    public @NotNull String getLocale() {
        return null;
    }

    @Override
    public void updateCommands() {

    }

    @Override
    public boolean isOnline() {
        return this.spongeValue.isOnline();
    }

    @Override
    public boolean isBanned() {
        return false;
    }

    @Override
    public boolean isWhitelisted() {
        return false;
    }

    @Override
    public void setWhitelisted(boolean value) {

    }

    @Override
    public @Nullable Player getPlayer() {
        return this;
    }

    @Override
    public long getFirstPlayed() {
        return 0;
    }

    @Override
    public long getLastPlayed() {
        return 0;
    }

    @Override
    public boolean hasPlayedBefore() {
        return false;
    }

    @Override
    public @NotNull Map<String, Object> serialize() {
        return null;
    }

    @Override
    public void sendPluginMessage(@NotNull Plugin source, @NotNull String channel, @NotNull byte[] message) {

    }

    @Override
    public @NotNull Set<String> getListeningPluginChannels() {
        return null;
    }

    @Override
    public BongeInventoryView getOpenInventory() {
        if(this.spongeValue.getOpenInventory().isPresent()){
            return this.view;
        }
        return null;
    }

    @Override
    public InventoryView openInventory(Inventory inventory) {
        this.view = new BongeInventoryView(this, inventory);
        this.spongeValue.openInventory(((BongeAbstractInventory<?>)inventory).getSpongeInventoryValue());
        return this.view;
    }

    @Override
    public InventoryView openWorkbench(Location location, boolean force) {
        return openInventory(new BongeCustomWorkbenchInventory(this, org.spongepowered.api.item.inventory.Inventory.builder().of(InventoryArchetypes.WORKBENCH).build(BongeLaunch.getInstance())));
    }

    @Override
    public InventoryView openEnchanting(Location location, boolean force) {
        return null;
    }

    @Override
    public void openInventory(InventoryView inventory) {
        this.view = (BongeInventoryView) inventory;
        this.spongeValue.openInventory(((BongeAbstractInventory<?>)inventory).getSpongeInventoryValue());
    }

    @Override
    public InventoryView openMerchant(Villager trader, boolean force) {
        return null;
    }

    @Override
    public InventoryView openMerchant(Merchant merchant, boolean force) {
        return null;
    }

    @Override
    public void closeInventory() {
        this.spongeValue.closeInventory();
    }

    private static void updatePlayerList(){
        PLAYERS = PLAYERS.stream().filter(p -> Sponge.getServer().getPlayer(p.getUniqueId()).isPresent()).collect(Collectors.toSet());
    }

    public static BongePlayer getPlayer(org.spongepowered.api.entity.living.player.Player player){
        Optional<BongePlayer> opPlayer = PLAYERS.stream().filter(p -> p.getSpongeValue().equals(player)).findAny();
        if(opPlayer.isPresent()){
            return opPlayer.get();
        }
        BongePlayer player2 = new BongePlayer(player);
        PLAYERS.add(player2);
        return player2;
    }
}
