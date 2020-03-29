package org.bonge.bukkit.entity.living.human;

import org.bonge.bukkit.entity.living.BongeAbstractLivingEntity;
import org.bonge.bukkit.inventory.inventory.entity.living.human.BongePlayerInventory;
import org.bonge.bukkit.world.BongeLocation;
import org.bonge.convert.EnumConvert;
import org.bonge.convert.InventoryConvert;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.data.type.HandPreference;
import org.spongepowered.api.data.type.HandPreferences;
import org.spongepowered.api.data.type.HandTypes;

import java.util.Collection;
import java.util.Optional;

public abstract class BongeAbstractHuman<T extends org.spongepowered.api.entity.living.Humanoid> extends BongeAbstractLivingEntity<T> implements HumanEntity {

    public BongeAbstractHuman(T entity) {
        super(entity);
    }

    @Override
    public double getEyeHeight() {
        return 1;
    }

    @Override
    public double getEyeHeight(boolean ignorePose) {
        return 1;
    }

    @Override
    public @NotNull Location getEyeLocation() {
        return new BongeLocation(this.getLocation().add(0, 1, 0));
    }

    @Override
    public @NotNull PlayerInventory getInventory() {
        return new BongePlayerInventory(this.spongeValue);
    }

    @Override
    public @NotNull Inventory getEnderChest() {
        return null;
    }

    @Override
    public @NotNull MainHand getMainHand() {
        HandPreference pref = this.spongeValue.get(Keys.DOMINANT_HAND).get();
        if(pref.equals(HandPreferences.LEFT)){
            return MainHand.LEFT;
        }
        return MainHand.RIGHT;
    }

    @Override
    public boolean setWindowProperty(InventoryView.@NotNull Property prop, int value) {
        return false;
    }

    @Override
    public @NotNull ItemStack getItemInHand() {
        Optional<org.spongepowered.api.item.inventory.ItemStack> opItem = this.spongeValue.getItemInHand(HandTypes.MAIN_HAND);
        return opItem.map(InventoryConvert::getItemStack).orElseGet(() -> new ItemStack(Material.AIR));
    }

    @Override
    public void setItemInHand(@Nullable ItemStack item) {

    }

    @Override
    public @NotNull ItemStack getItemOnCursor() {
        return null;
    }

    @Override
    public void setItemOnCursor(@Nullable ItemStack item) {

    }

    @Override
    public boolean hasCooldown(@NotNull Material material) {
        return false;
    }

    @Override
    public int getCooldown(@NotNull Material material) {
        return 0;
    }

    @Override
    public void setCooldown(@NotNull Material material, int ticks) {

    }

    @Override
    public boolean isSleeping() {
        return false;
    }

    @Override
    public int getSleepTicks() {
        return 0;
    }

    @Override
    public @NotNull GameMode getGameMode() {
        return EnumConvert.getGamemode(this.spongeValue.get(Keys.GAME_MODE).get());
    }

    @Override
    public void setGameMode(@NotNull GameMode mode) {
        this.spongeValue.offer(Keys.GAME_MODE, EnumConvert.getGamemode(mode));
    }

    @Override
    public boolean isBlocking() {
        return false;
    }

    @Override
    public boolean isHandRaised() {
        return false;
    }

    @Override
    public int getExpToLevel() {
        return 0;
    }

    @Override
    public boolean discoverRecipe(@NotNull NamespacedKey recipe) {
        return false;
    }

    @Override
    public int discoverRecipes(@NotNull Collection<NamespacedKey> recipes) {
        return 0;
    }

    @Override
    public boolean undiscoverRecipe(@NotNull NamespacedKey recipe) {
        return false;
    }

    @Override
    public int undiscoverRecipes(@NotNull Collection<NamespacedKey> recipes) {
        return 0;
    }

    @Override
    public @Nullable Entity getShoulderEntityLeft() {
        return null;
    }

    @Override
    public void setShoulderEntityLeft(@Nullable Entity entity) {

    }

    @Override
    public @Nullable Entity getShoulderEntityRight() {
        return null;
    }

    @Override
    public void setShoulderEntityRight(@Nullable Entity entity) {

    }
}
