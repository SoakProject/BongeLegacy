package org.bonge.bukkit.r1_16.entity.living.human;

import org.bonge.Bonge;
import org.bonge.bukkit.r1_16.entity.living.ILivingEntity;
import org.bonge.bukkit.r1_16.inventory.BongeInventoryView;
import org.bonge.bukkit.r1_16.world.BongeLocation;
import org.bonge.util.exception.NotImplementedException;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MainHand;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.api.data.Keys;
import org.spongepowered.api.data.type.HandPreference;
import org.spongepowered.api.data.type.HandPreferences;
import org.spongepowered.api.data.type.HandTypes;

import java.io.IOException;
import java.util.Collection;
import java.util.UUID;

public interface IHuman<T extends org.spongepowered.api.entity.living.Humanoid> extends ILivingEntity<T>, HumanEntity {

    @Override
    default @NotNull UUID getUniqueId() {
        return ILivingEntity.super.getUniqueId();
    }

    @Override
    @NotNull BongeInventoryView getOpenInventory();

    @Override
    BongeInventoryView openInventory(@NotNull Inventory inv);

    @Override
    default double getEyeHeight() {
        return 1;
    }

    @Override
    default double getEyeHeight(boolean ignorePose) {
        return 1;
    }

    @Override
    default @NotNull Location getEyeLocation() {
        return new BongeLocation(this.getLocation().add(0, 1, 0));
    }

    @Override
    default @NotNull Inventory getEnderChest() {
        return null;
    }

    @Override
    default @NotNull MainHand getMainHand() {
        HandPreference pref = this.getSpongeValue().get(Keys.DOMINANT_HAND).get();
        if (pref.equals(HandPreferences.LEFT.get())) {
            return MainHand.LEFT;
        }
        return MainHand.RIGHT;
    }

    @Override
    default boolean setWindowProperty(InventoryView.@NotNull Property prop, int value) {
        throw new NotImplementedException("yet to look at");

    }

    @Override
    @Deprecated
    default @NotNull ItemStack getItemInHand() {
        org.spongepowered.api.item.inventory.ItemStack item = this.getSpongeValue().itemInHand(HandTypes.MAIN_HAND);
        try {
            return Bonge.getInstance().convert(ItemStack.class, item);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    @Deprecated
    default void setItemInHand(@Nullable ItemStack item) {
        throw new NotImplementedException("yet to look at");

    }

    @Override
    default @NotNull ItemStack getItemOnCursor() {
        BongeInventoryView view = getOpenInventory();
        if (view == null) {
            throw new IllegalArgumentException("Inventory is not open");
        }
        return view.getCursor0();
    }

    @Override
    default void setItemOnCursor(@Nullable ItemStack item) {
        BongeInventoryView view = getOpenInventory();
        if (view == null) {
            return;
        }
        view.setCursor0(item);
    }

    @Override
    default boolean hasCooldown(@NotNull Material material) {
        throw new NotImplementedException("yet to look at");

    }

    @Override
    default int getCooldown(@NotNull Material material) {
        throw new NotImplementedException("yet to look at");

    }

    @Override
    default void setCooldown(@NotNull Material material, int ticks) {
        throw new NotImplementedException("yet to look at");

    }

    @Override
    default boolean isSleeping() {
        throw new NotImplementedException("yet to look at");

    }

    @Override
    default int getSleepTicks() {
        throw new NotImplementedException("yet to look at");

    }

    @Override
    default @NotNull GameMode getGameMode() {
        try {
            return Bonge.getInstance().convert(GameMode.class, this.getSpongeValue().get(Keys.GAME_MODE).get());
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    default void setGameMode(@NotNull GameMode mode) {
        try {
            this.getSpongeValue().offer(Keys.GAME_MODE, Bonge.getInstance().convert(mode, org.spongepowered.api.entity.living.player.gamemode.GameMode.class));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    default boolean isBlocking() {
        throw new NotImplementedException("yet to look at");

    }

    @Override
    default boolean isHandRaised() {
        throw new NotImplementedException("yet to look at");

    }

    @Override
    default int getExpToLevel() {
        throw new NotImplementedException("yet to look at");

    }

    @Override
    default boolean discoverRecipe(@NotNull NamespacedKey recipe) {
        throw new NotImplementedException("yet to look at");

    }

    @Override
    default int discoverRecipes(@NotNull Collection<NamespacedKey> recipes) {
        throw new NotImplementedException("yet to look at");

    }

    @Override
    default boolean undiscoverRecipe(@NotNull NamespacedKey recipe) {
        throw new NotImplementedException("yet to look at");

    }

    @Override
    default int undiscoverRecipes(@NotNull Collection<NamespacedKey> recipes) {
        throw new NotImplementedException("yet to look at");

    }

    @Override
    @Deprecated
    default @Nullable Entity getShoulderEntityLeft() {
        throw new NotImplementedException("yet to look at");

    }

    @Override
    @Deprecated
    default void setShoulderEntityLeft(@Nullable Entity entity) {
        throw new NotImplementedException("yet to look at");

    }

    @Override
    @Deprecated
    default @Nullable Entity getShoulderEntityRight() {
        throw new NotImplementedException("yet to look at");

    }

    @Override
    @Deprecated
    default void setShoulderEntityRight(@Nullable Entity entity) {
        throw new NotImplementedException("yet to look at");

    }

    @Override
    default @NotNull String getName() {
        return ILivingEntity.super.getName();
    }
}
