package org.bonge.bukkit.r1_16.entity.other.item;

import org.bonge.Bonge;
import org.bonge.bukkit.r1_16.entity.BongeAbstractEntity;
import org.bonge.util.exception.NotImplementedException;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.api.data.Keys;
import org.spongepowered.api.util.Ticks;

import java.io.IOException;
import java.util.UUID;

public class BongeItem extends BongeAbstractEntity<org.spongepowered.api.entity.Item> implements Item {

    public BongeItem(org.spongepowered.api.entity.Item entity) {
        super(entity);
    }

    @Override
    public @NotNull ItemStack getItemStack() {
        try {
            return Bonge.getInstance().convert(ItemStack.class, this.spongeValue.get(Keys.ACTIVE_ITEM).get());
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void setItemStack(ItemStack stack) {
        throw new NotImplementedException("BongeItem.setItemStack(ItemStack)");
    }

    @Override
    public int getPickupDelay() {
        return (int)this.spongeValue.get(Keys.PICKUP_DELAY).get().ticks();
    }

    @Override
    public void setPickupDelay(int delay) {
        this.spongeValue.offer(Keys.PICKUP_DELAY, Ticks.of(delay));
    }

    @Override
    public void setOwner(@Nullable UUID owner) {
        throw new NotImplementedException("Not got to yet");

    }

    @Nullable
    @Override
    public UUID getOwner() {
        throw new NotImplementedException("Not got to yet");
    }

    @Override
    public void setThrower(@Nullable UUID uuid) {
        throw new NotImplementedException("Not got to yet");

    }

    @Nullable
    @Override
    public UUID getThrower() {
        throw new NotImplementedException("Not got to yet");
    }

    @Override
    public @NotNull EntityType getType() {
        return EntityType.DROPPED_ITEM;
    }

    @Override
    public void sendMessage(@Nullable UUID sender, @NotNull String message) {

    }

    @Override
    public void sendMessage(@Nullable UUID sender, @NotNull String[] messages) {

    }
}
