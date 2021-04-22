package org.bonge.convert.inventory.item;

import org.bonge.bukkit.r1_16.inventory.item.holder.ItemHolder;
import org.bonge.bukkit.r1_16.inventory.item.holder.ItemStackHolder;
import org.bonge.convert.Converter;
import org.bukkit.inventory.ItemStack;

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
    public org.spongepowered.api.item.inventory.ItemStack from(ItemStack value) throws IOException{
        ItemHolder holder = value.getItemMeta().getHolder();
        if(holder instanceof ItemStackHolder){
            return ((ItemStackHolder)holder).getSpongeValue();
        }
        throw new IOException("ItemStack's holder is not a Sponge itemstack");
    }

    @Override
    public ItemStack to(org.spongepowered.api.item.inventory.ItemStack value){
        return new ItemStack(value);
    }
}
