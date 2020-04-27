package org.bonge.convert.inventory.item;

import org.bonge.Bonge;
import org.bonge.convert.Converter;
import org.bukkit.inventory.ItemStack;
import org.spongepowered.api.item.inventory.ItemStackSnapshot;

import java.io.IOException;

public class ItemStackConverter implements Converter<ItemStack, org.spongepowered.api.item.inventory.ItemStack> {
    @Override
    public Class<org.spongepowered.api.item.inventory.ItemStack> getSpongeClass() {
        return org.spongepowered.api.item.inventory.ItemStack.class;
    }

    @Override
    public Class<ItemStack> getBukkitClass() {
        return ItemStack.class;
    }

    @Override
    public org.spongepowered.api.item.inventory.ItemStack from(ItemStack value) {
        try {
            return Bonge.getInstance().convert(ItemStackSnapshot.class, value).createStack();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public ItemStack to(org.spongepowered.api.item.inventory.ItemStack value) {
        try {
            return Bonge.getInstance().convert(value.createSnapshot(), ItemStack.class);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
