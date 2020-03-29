package org.bonge.convert;

import org.bonge.bukkit.inventory.item.meta.BongeItemMeta;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.spongepowered.api.CatalogType;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.item.ItemType;

import java.util.Optional;

public class InventoryConvert {

    public static Optional<org.spongepowered.api.item.inventory.ItemStack> getItemStack(ItemStack stack){
        Optional<CatalogType> opItemType = stack.getType().getSpongeValue();
        if(!opItemType.isPresent()){
            return Optional.empty();
        }
        org.spongepowered.api.item.inventory.ItemStack item = org.spongepowered.api.item.inventory.ItemStack.builder()
                .itemType((ItemType)opItemType.get())
                .quantity(stack.getAmount())
                .build();
        ItemMeta meta = stack.getItemMeta();
        if(meta != null) {
            item.offer(Keys.UNBREAKABLE, meta.isUnbreakable());
        }
        return Optional.of(item);
    }

    public static ItemStack getItemStack(org.spongepowered.api.item.inventory.ItemStack stack){
        ItemStack stack2 = new ItemStack(Material.getMaterial(stack.getType()), stack.getQuantity());
        ItemMeta meta = new BongeItemMeta(stack);
        meta.setDisplayName(InterfaceConvert.toString(stack.get(Keys.DISPLAY_NAME).orElse(null)));
        stack.get(Keys.UNBREAKABLE).ifPresent(meta::setUnbreakable);
        stack2.setItemMeta(meta);
        return stack2;
    }

    public static ItemStack getItemStack(org.spongepowered.api.item.inventory.ItemStackSnapshot stack){
        return getItemStack(stack.createStack());
    }
}
