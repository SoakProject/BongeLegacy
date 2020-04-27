package org.bonge.convert.inventory.item;

import org.bonge.Bonge;
import org.bonge.convert.Converter;
import org.bonge.convert.text.TextConverter;
import org.bonge.util.ArrayUtils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.item.ItemType;
import org.spongepowered.api.item.inventory.ItemStackSnapshot;

import java.io.IOException;
import java.util.Optional;

public class ItemStackSnapshotConverter implements Converter<ItemStack, ItemStackSnapshot> {
    @Override
    public Class<ItemStackSnapshot> getSpongeClass() {
        return null;
    }

    @Override
    public Class<ItemStack> getBukkitClass() {
        return null;
    }

    @Override
    public ItemStackSnapshot from(ItemStack stack) {
        ItemType type;
        try {
            type = Bonge.getInstance().convert(stack.getType(), ItemType.class);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        org.spongepowered.api.item.inventory.ItemStack.Builder item = org.spongepowered.api.item.inventory.ItemStack.builder()
                .itemType(type)
                .quantity(stack.getAmount());
        ItemMeta meta = stack.getItemMeta();
        item.add(Keys.UNBREAKABLE, meta.isUnbreakable());
        if (meta.hasDisplayName()){
            item.add(Keys.DISPLAY_NAME, TextConverter.CONVERTER.from(meta.getDisplayName()));
        }
        if(meta.hasLore()){
            item.add(Keys.ITEM_LORE, ArrayUtils.convert(s -> TextConverter.CONVERTER.from(s), meta.getLore()));
        }
        return item.build().createSnapshot();
    }

    @Override
    public ItemStack to(ItemStackSnapshot stack) {
        Material type;
        try {
            type = Bonge.getInstance().convert(Material.class, stack.getType());
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        ItemStack stack2 = new ItemStack(type, stack.getQuantity());
        ItemMeta meta = stack2.getItemMeta();
        stack.get(Keys.DISPLAY_NAME).ifPresent(name -> {
            meta.setDisplayName(TextConverter.CONVERTER.to(name));
        });
        stack.get(Keys.UNBREAKABLE).ifPresent(meta::setUnbreakable);
        stack.get(Keys.ITEM_LORE).ifPresent(t -> meta.setLore(ArrayUtils.convert(s -> TextConverter.CONVERTER.to(s), t)));
        stack2.setItemMeta(meta);
        return stack2;
    }
}
