package org.bonge.bukkit.r1_14.entity.living.human;

import org.bonge.Bonge;
import org.bonge.bukkit.r1_14.entity.EntityManager;
import org.bonge.bukkit.r1_14.entity.living.BongeAbstractLivingEntity;
import org.bonge.bukkit.r1_14.inventory.BongeInventorySnapshot;
import org.bonge.bukkit.r1_14.inventory.BongeInventoryView;
import org.bonge.bukkit.r1_14.inventory.entity.living.player.BongePlayerInventory;
import org.bonge.bukkit.r1_14.inventory.entity.living.player.PlayerInventorySnapshot;
import org.bonge.bukkit.r1_14.world.BongeLocation;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.api.data.Keys;
import org.spongepowered.api.data.type.HandPreference;
import org.spongepowered.api.data.type.HandPreferences;
import org.spongepowered.api.data.type.HandTypes;

import java.io.IOException;
import java.util.Collection;

public abstract class BongeAbstractHuman<T extends org.spongepowered.api.entity.living.Humanoid> extends BongeAbstractLivingEntity<T> implements HumanEntity {

    public BongeAbstractHuman(T entity) {
        super(entity);
    }

    @Override
    public abstract @NotNull BongeInventoryView getOpenInventory();

    @Override
    public abstract BongeInventoryView openInventory(@NotNull Inventory inv);

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
        BongeInventorySnapshot<? extends org.spongepowered.api.item.inventory.Inventory> inventory = this.getData().get(EntityManager.INVENTORY);
        if(inventory != null){
            return (PlayerInventorySnapshot)inventory;
        }
        return new BongePlayerInventory((org.spongepowered.api.item.inventory.entity.PlayerInventory) this.spongeValue.getInventory());
    }

    @Override
    public @NotNull Inventory getEnderChest() {
        return null;
    }

    @Override
    public @NotNull MainHand getMainHand() {
        HandPreference pref = this.spongeValue.get(Keys.DOMINANT_HAND).get();
        if(pref.equals(HandPreferences.LEFT.get())){
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
        org.spongepowered.api.item.inventory.ItemStack item = this.spongeValue.getItemInHand(HandTypes.MAIN_HAND);
        try {
            return Bonge.getInstance().convert(ItemStack.class, item);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void setItemInHand(@Nullable ItemStack item) {

    }

    @Override
    public @NotNull ItemStack getItemOnCursor() {
        BongeInventoryView view = getOpenInventory();
        if(view == null){
            throw new IllegalArgumentException("Inventory is not open");
        }
        return view.getCursor0();
    }

    @Override
    public void setItemOnCursor(@Nullable ItemStack item) {
        BongeInventoryView view = getOpenInventory();
        if(view == null){
            return;
        }
        view.setCursor0(item);
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
        try {
            return Bonge.getInstance().convert(GameMode.class, this.spongeValue.get(Keys.GAME_MODE).get());
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void setGameMode(@NotNull GameMode mode) {
        try {
            this.spongeValue.offer(Keys.GAME_MODE, Bonge.getInstance().convert(mode, org.spongepowered.api.entity.living.player.gamemode.GameMode.class));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
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
