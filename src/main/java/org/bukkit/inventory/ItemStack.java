package org.bukkit.inventory;

import org.apache.commons.lang.NotImplementedException;
import org.bonge.Bonge;
import org.bonge.bukkit.r1_16.inventory.item.BongeItemFactory;
import org.bonge.bukkit.r1_16.inventory.item.holder.ItemHolder;
import org.bonge.bukkit.r1_16.inventory.item.holder.ItemStackHolder;
import org.bonge.bukkit.r1_16.inventory.item.holder.ItemStackSnapshotHolder;
import org.bonge.bukkit.r1_16.inventory.item.meta.AbstractItemMeta;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Utility;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.api.data.Keys;
import org.spongepowered.api.item.ItemType;
import org.spongepowered.api.item.inventory.ItemStackSnapshot;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ItemStack implements Cloneable, ConfigurationSerializable {

    private AbstractItemMeta meta;

    private static final String SERIALIZATION_TYPE = "Type";
    private static final String SERIALIZATION_AMOUNT = "Amount";
    private static final String SERIALIZATION_META = "Meta";

    public ItemStack(ItemStackSnapshot stack) {
        this(new ItemStackSnapshotHolder(stack));
    }

    public ItemStack(org.spongepowered.api.item.inventory.ItemStack stack) {
        this(new ItemStackHolder(stack));
    }

    public ItemStack(ItemHolder stack) {
        this.meta = ((BongeItemFactory) Bukkit.getItemFactory()).createItemMeta(stack);
    }

    public ItemStack(AbstractItemMeta meta) {
        this.meta = meta;
    }

    public ItemStack(@NotNull final Material type) {
        this(type, 1);
    }

    public ItemStack(@NotNull final Material type, final int amount) {
        this(type, amount, (short) 0);
    }

    public ItemStack(@NotNull final Material type, final int amount, final short damage) {
        this(type, amount, damage, null);
    }

    @Deprecated
    public ItemStack(@NotNull Material type, final int amount, final short damage, @Nullable final Byte ignore) {
        Optional<ItemType> opType = Bonge.getInstance().convertItem(type);
        if (!opType.isPresent()) {
            throw new IllegalArgumentException("Material is not an Item");
        }
        ((BongeItemFactory) Bukkit.getItemFactory()).createItemMeta(new ItemStackHolder(org.spongepowered.api.item.inventory.ItemStack.builder()
                .itemType(opType.get())
                .quantity(amount)
                .add(Keys.ATTACK_DAMAGE, (double) damage)
                .build()
        ));
    }

    public ItemStack(@NotNull final ItemStack stack) {
        this.meta = stack.meta.clone();
    }

    public @NotNull Material getType() {
        return Material.valueOf(this.meta.getHolder().getType());
    }

    public void setType(@NotNull Material type) {
        Optional<ItemType> opItem = Bonge.getInstance().convertItem(type);
        if (!opItem.isPresent()) {
            throw new IllegalArgumentException("Material is not an item");
        }
        if (!this.meta.createBuilder().isAcceptable(type)) {
            throw new IllegalArgumentException("ItemMeta cannot apply to " + type.name());
        }
        this.meta.setHolder(new ItemStackHolder(org.spongepowered.api.item.inventory.ItemStack.builder()
                .fromContainer(this.meta.getHolder().toContainer())
                .itemType(opItem.get())
                .build()));
    }

    public int getAmount() {
        return this.meta.getHolder().getAmount();
    }

    public void setAmount(int amount) {
        this.meta.setHolder(this.meta.getHolder().copy(amount));
    }

    @Deprecated
    public MaterialData getData() {
        return this.getType().getNewData((byte) 0);
    }

    @Deprecated
    public void setData(@Nullable MaterialData data) {
        throw new NotImplementedException("ItemStack.getDurability() is legacy code");
    }

    @Deprecated
    public void setDurability(final short durability) {
        throw new NotImplementedException("ItemStack.getDurability() is legacy code");
    }

    @Deprecated
    public short getDurability() {
        throw new NotImplementedException("ItemStack.getDurability() is legacy code");
    }

    public int getMaxStackSize() {
        return this.meta.getHolder().getMaxStackSize();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ItemStack)) {
            return false;
        }
        return this.meta.equals(((ItemStack) obj).meta);
    }

    public boolean isSimilar(@Nullable ItemStack stack) {
        return this.equals(stack);
    }

    @NotNull
    @Override
    public ItemStack clone() {
        return new ItemStack(this);
    }

    public boolean containsEnchantment(@NotNull Enchantment ench) {
        return this.meta.hasEnchant(ench);
    }

    public int getEnchantmentLevel(@NotNull Enchantment ench) {
        return this.meta.getEnchantLevel(ench);
    }

    @NotNull
    public Map<Enchantment, Integer> getEnchantments() {
        return this.meta.getEnchants();
    }

    public void addEnchantments(@NotNull Map<Enchantment, Integer> enchantments) {
        enchantments.forEach((key, value) -> this.meta.addEnchant(key, value, false));
    }

    public void addEnchantment(@NotNull Enchantment ench, int level) {
        this.meta.addEnchant(ench, level, false);
    }

    @Utility
    public void addUnsafeEnchantments(@NotNull Map<Enchantment, Integer> enchantments) {
        for (Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()) {
            this.meta.addEnchant(entry.getKey(), entry.getValue(), true);
        }
    }

    public void addUnsafeEnchantment(@NotNull Enchantment ench, int level) {
        this.meta.addEnchant(ench, level, true);
    }

    public int removeEnchantment(@NotNull Enchantment ench) {
        if (this.meta.hasEnchant(ench)) {
            return -1;
        }
        int value = this.meta.getEnchants().get(ench);
        this.meta.removeEnchant(ench);
        return value;
    }

    @Override
    @NotNull
    public Map<String, Object> serialize() {
        return this.meta.serialize();
    }

    @NotNull
    public static ItemStack deserialize(@NotNull Map<String, Object> args) {
        Material material = Material.getMaterial(args.get(SERIALIZATION_TYPE).toString());
        int amount = (int) args.get(SERIALIZATION_AMOUNT);
        Map<String, Object> map = new HashMap<>();
        args.entrySet().stream().filter(e -> e.getKey().startsWith(SERIALIZATION_META)).forEach(e -> map.put(e.getKey().substring(SERIALIZATION_META.length()), e.getValue()));
        ItemStack stack = new ItemStack(material, amount);
        stack.meta.deserialize(map);
        return stack;
    }

    public AbstractItemMeta getItemMeta() {
        if (this.meta == null) {
            this.meta = (AbstractItemMeta) Bukkit.getItemFactory().getItemMeta(this.getType()).clone();
        }
        return this.meta;
    }

    public boolean hasItemMeta() {
        return this.meta != null;
    }

    public boolean setItemMeta(@Nullable ItemMeta itemMeta) {
        if (!Bukkit.getItemFactory().isApplicable(itemMeta, this)) {
            return false;
        }
        if (itemMeta instanceof AbstractItemMeta) {
            this.meta = (AbstractItemMeta) itemMeta;
            return true;
        }
        return false;
    }
}