package org.bonge.bukkit.inventory.item.meta;

import com.google.common.collect.Multimap;
import org.bonge.convert.InterfaceConvert;
import org.bonge.convert.InventoryConvert;
import org.bonge.util.ArrayUtils;
import org.bonge.wrapper.BongeWrapper;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.item.inventory.ItemStack;

import java.util.*;

public class BongeItemMeta extends BongeWrapper<org.spongepowered.api.item.inventory.ItemStack> implements ItemMeta, Damageable {

    public BongeItemMeta(ItemStack value) {
        super(value);
    }

    @Override
    public boolean hasDisplayName() {
        return this.spongeValue.get(Keys.DISPLAY_NAME).isPresent();
    }

    @Override
    public String getDisplayName() {
        return InterfaceConvert.toString(this.spongeValue.get(Keys.DISPLAY_NAME).get());
    }

    @Override
    public void setDisplayName(String name) {
        if(name == null){
            this.spongeValue.remove(Keys.DISPLAY_NAME);
            return;
        }
        this.spongeValue.offer(Keys.DISPLAY_NAME, InterfaceConvert.fromString(name));
    }

    @Override
    public boolean hasLocalizedName() {
        return false;
    }

    @Override
    public String getLocalizedName() {
        return null;
    }

    @Override
    public void setLocalizedName(String name) {

    }

    @Override
    public boolean hasLore() {
        return !this.spongeValue.get(Keys.ITEM_LORE).get().isEmpty();
    }

    @Override
    public List<String> getLore() {
        return ArrayUtils.convert((t) -> InterfaceConvert.toString(t), this.spongeValue.get(Keys.ITEM_LORE).get());
    }

    @Override
    public void setLore(List<String> lore) {
        this.spongeValue.offer(Keys.ITEM_LORE, ArrayUtils.convert((s) -> InterfaceConvert.fromString(s), lore));
    }

    @Override
    public boolean hasEnchants() {
        return false;
    }

    @Override
    public boolean hasEnchant(Enchantment ench) {
        return false;
    }

    @Override
    public int getEnchantLevel(Enchantment ench) {
        return 0;
    }

    @Override
    public Map<Enchantment, Integer> getEnchants() {
        return new HashMap<>();
    }

    @Override
    public boolean addEnchant(Enchantment ench, int level, boolean ignoreLevelRestriction) {
        return false;
    }

    @Override
    public boolean removeEnchant(Enchantment ench) {
        return false;
    }

    @Override
    public boolean hasConflictingEnchant(Enchantment ench) {
        return false;
    }

    @Override
    public void addItemFlags(ItemFlag... itemFlags) {

    }

    @Override
    public void removeItemFlags(ItemFlag... itemFlags) {

    }

    @Override
    public Set<ItemFlag> getItemFlags() {
        return new HashSet<>();
    }

    @Override
    public boolean hasItemFlag(ItemFlag flag) {
        return false;
    }

    @Override
    public boolean isUnbreakable() {
        return false;
    }

    @Override
    public void setUnbreakable(boolean unbreakable) {

    }

    @Override
    public boolean hasAttributeModifiers() {
        return false;
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers() {
        return null;
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot) {
        return null;
    }

    @Override
    public Collection<AttributeModifier> getAttributeModifiers(Attribute attribute) {
        return null;
    }

    @Override
    public boolean addAttributeModifier(Attribute attribute, AttributeModifier modifier) {
        return false;
    }

    @Override
    public void setAttributeModifiers(Multimap<Attribute, AttributeModifier> attributeModifiers) {

    }

    @Override
    public boolean removeAttributeModifier(Attribute attribute) {
        return false;
    }

    @Override
    public boolean removeAttributeModifier(EquipmentSlot slot) {
        return false;
    }

    @Override
    public boolean removeAttributeModifier(Attribute attribute, AttributeModifier modifier) {
        return false;
    }

    @Override
    public BongeItemMeta clone() {
        return new BongeItemMeta(this.spongeValue.copy());
    }

    @Override
    public boolean hasDamage() {
        return this.spongeValue.get(Keys.ITEM_DURABILITY).isPresent();
    }

    @Override
    public int getDamage() {
        return this.spongeValue.get(Keys.ITEM_DURABILITY).orElse(0);
    }

    @Override
    public void setDamage(int damage) {
        this.spongeValue.offer(Keys.ITEM_DURABILITY, damage);
    }

    @Override
    public Map<String, Object> serialize() {
        return null;
    }
}
