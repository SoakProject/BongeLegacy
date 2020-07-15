package org.bonge.bukkit.r1_14.entity.other.item;

import org.bonge.Bonge;
import org.bonge.bukkit.r1_14.entity.BongeAbstractEntity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.data.Keys;

import java.io.IOException;

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

    }

    @Override
    public int getPickupDelay() {
        return this.spongeValue.get(Keys.PICKUP_DELAY).get();
    }

    @Override
    public void setPickupDelay(int delay) {
        this.spongeValue.offer(Keys.PICKUP_DELAY, delay);
    }

    @Override
    public @NotNull EntityType getType() {
        return EntityType.DROPPED_ITEM;
    }
}
