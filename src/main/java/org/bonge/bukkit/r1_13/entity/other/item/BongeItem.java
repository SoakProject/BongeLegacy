package org.bonge.bukkit.r1_13.entity.other.item;

import org.bonge.Bonge;
import org.bonge.bukkit.r1_13.entity.BongeAbstractEntity;
import org.bonge.convert.InventoryConvert;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.spongepowered.api.data.key.Keys;

import java.io.IOException;

public class BongeItem extends BongeAbstractEntity<org.spongepowered.api.entity.Item> implements Item {

    public BongeItem(org.spongepowered.api.entity.Item entity) {
        super(entity);
    }

    @Override
    public ItemStack getItemStack() {
        try {
            return Bonge.getInstance().convert(ItemStack.class, this.spongeValue.get(Keys.REPRESENTED_ITEM).get());
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
}
