package org.bonge.convert.inventory.item;

import org.bonge.bukkit.r1_14.inventory.item.holder.ItemHolder;
import org.bonge.bukkit.r1_14.inventory.item.holder.ItemStackSnapshotHolder;
import org.bonge.convert.Converter;
import org.bukkit.inventory.ItemStack;
import org.spongepowered.api.item.inventory.ItemStackSnapshot;

import java.io.IOException;

public class ItemStackSnapshotConverter implements Converter<ItemStack, ItemStackSnapshot> {
    @Override
    public Class<ItemStackSnapshot> getSpongeClass() {
        return ItemStackSnapshot.class;
    }

    @Override
    public Class<ItemStack> getBukkitClass() {
        return ItemStack.class;
    }

    @Override
    public ItemStackSnapshot from(ItemStack stack) throws IOException {
        ItemHolder holder = stack.getItemMeta().getHolder();
        if(holder instanceof ItemStackSnapshotHolder){
            return ((ItemStackSnapshotHolder)holder).getSpongeValue();
        }
        throw new IOException("Itemstack's holder is not a sponge ItemStackSnapshot");
    }

    @Override
    public ItemStack to(ItemStackSnapshot stack){
        return new ItemStack(stack);
    }
}
