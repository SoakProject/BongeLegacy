package org.bonge.convert.inventory.item;

import org.bonge.Bonge;
import org.bonge.bukkit.r1_13.entity.living.human.BongeOfflinePlayer;
import org.bonge.convert.Converter;
import org.bonge.convert.text.TextConverter;
import org.bonge.util.ArrayUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.data.type.SkullTypes;
import org.spongepowered.api.entity.living.player.User;
import org.spongepowered.api.item.ItemType;
import org.spongepowered.api.item.enchantment.Enchantment;
import org.spongepowered.api.item.enchantment.EnchantmentType;
import org.spongepowered.api.item.inventory.ItemStackSnapshot;
import org.spongepowered.api.service.user.UserStorageService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
    public ItemStackSnapshot from(ItemStack stack) throws IOException{
        ItemType type = Bonge.getInstance().convert(stack.getType(), ItemType.class);
        org.spongepowered.api.item.inventory.ItemStack.Builder item = org.spongepowered.api.item.inventory.ItemStack.builder()
                .itemType(type)
                .quantity(stack.getAmount());
        ItemMeta meta = stack.getItemMeta();
        item.add(Keys.UNBREAKABLE, meta.isUnbreakable());
        if(meta.hasItemFlag(ItemFlag.HIDE_ENCHANTS)){
            item.add(Keys.HIDE_ENCHANTMENTS, true);
        }
        if(meta.hasItemFlag(ItemFlag.HIDE_DESTROYS)){
            item.add(Keys.HIDE_CAN_DESTROY, true);
        }
        if(meta.hasItemFlag(ItemFlag.HIDE_ATTRIBUTES)){
            item.add(Keys.HIDE_ATTRIBUTES, true);
        }
        if(meta.hasItemFlag(ItemFlag.HIDE_UNBREAKABLE)){
            item.add(Keys.HIDE_UNBREAKABLE, true);
        }
        if(meta.hasItemFlag(ItemFlag.HIDE_PLACED_ON)){
            item.add(Keys.HIDE_CAN_PLACE, true);
        }
        if(meta.hasItemFlag(ItemFlag.HIDE_POTION_EFFECTS)){
            item.add(Keys.HIDE_MISCELLANEOUS, true);
        }
        if (meta.hasDisplayName()){
            item.add(Keys.DISPLAY_NAME, TextConverter.CONVERTER.from(meta.getDisplayName()));
        }
        if(meta.hasLore()){
            item.add(Keys.ITEM_LORE, ArrayUtils.convert(TextConverter.CONVERTER::from, meta.getLore()));
        }
        if (meta.hasEnchants()) {
            List<Enchantment> enchantments = new ArrayList<>();
            meta.getEnchants().forEach((key, value) -> {
                try {
                    EnchantmentType enchType = Bonge.getInstance().convert(key, EnchantmentType.class);
                    enchantments.add(Enchantment.builder().type(enchType).level(value).build());
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            });
            item.add(Keys.ITEM_ENCHANTMENTS, enchantments);
        }
        if(meta instanceof SkullMeta){
            SkullMeta skull = (SkullMeta)meta;
            if(skull.hasOwner()){
                item.add(Keys.SKULL_TYPE, SkullTypes.PLAYER);
                item.add(Keys.REPRESENTED_PLAYER, ((BongeOfflinePlayer)skull.getOwningPlayer()).getSpongeValue().getProfile());
            }
        }
        return item.build().createSnapshot();
    }

    @Override
    public ItemStack to(ItemStackSnapshot stack) throws IOException{
        Material type = Bonge.getInstance().convert(Material.class, stack.getType());
        ItemStack stack2 = new ItemStack(type, stack.getQuantity());
        ItemMeta meta = stack2.getItemMeta();
        stack.get(Keys.DISPLAY_NAME).ifPresent(name -> meta.setDisplayName(TextConverter.CONVERTER.to(name)));
        stack.get(Keys.UNBREAKABLE).ifPresent(meta::setUnbreakable);
        stack.get(Keys.ITEM_LORE).ifPresent(t -> meta.setLore(ArrayUtils.convert(TextConverter.CONVERTER::to, t)));
        stack.get(Keys.ITEM_ENCHANTMENTS).ifPresent(c -> c.forEach(e -> {
            try {
                org.bukkit.enchantments.Enchantment enchantment = Bonge.getInstance().convert(org.bukkit.enchantments.Enchantment.class, e.getType());
                meta.addEnchant(enchantment, e.getLevel(), false);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }));
        if (stack.get(Keys.HIDE_ATTRIBUTES).orElse(false)){
            meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        }
        if (stack.get(Keys.HIDE_CAN_DESTROY).orElse(false)){
            meta.addItemFlags(ItemFlag.HIDE_DESTROYS);
        }
        if (stack.get(Keys.HIDE_CAN_PLACE).orElse(false)){
            meta.addItemFlags(ItemFlag.HIDE_PLACED_ON);
        }
        if (stack.get(Keys.HIDE_ENCHANTMENTS).orElse(false)){
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        if (stack.get(Keys.HIDE_MISCELLANEOUS).orElse(false)){
            meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        }
        if (stack.get(Keys.HIDE_UNBREAKABLE).orElse(false)){
            meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        }
        if(meta instanceof SkullMeta){
            stack.get(Keys.REPRESENTED_PLAYER).ifPresent(gp -> ((SkullMeta) meta).setOwningPlayer(Bukkit.getOfflinePlayer(gp.getUniqueId())));
        }
        stack2.setItemMeta(meta);
        return stack2;
    }
}
