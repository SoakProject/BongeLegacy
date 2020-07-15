package org.bonge.bukkit.r1_14.inventory.item.meta;

import com.google.common.collect.Multimap;
import org.bonge.Bonge;
import org.bonge.bukkit.r1_14.inventory.item.enchantment.EnchantmentData;
import org.bonge.bukkit.r1_14.inventory.item.holder.ItemHolder;
import org.bonge.util.ArrayUtils;
import org.bonge.util.exception.NotImplementedException;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.tags.CustomItemTagContainer;
import org.bukkit.persistence.PersistentDataContainer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.api.data.Keys;
import org.spongepowered.api.item.enchantment.EnchantmentType;
import org.spongepowered.api.text.Text;

import java.io.IOException;
import java.util.*;

public abstract class AbstractItemMeta implements ItemMeta, Damageable {

    protected ItemHolder stack;

    public AbstractItemMeta(ItemHolder stack){
        this.stack = stack;
    }

    public abstract ItemMetaBuilder<? extends AbstractItemMeta> createBuilder();
    public abstract ItemHolder deserialize(Map<String, Object> map);
    public abstract void setHolder(ItemHolder holder);

    public ItemHolder getHolder() {
        return this.stack;
    }

    @Override
    public boolean hasCustomModelData() {
        throw new NotImplementedException("ItemMeta.hasCustomModelData() not got to yet");
    }

    @Override
    public int getCustomModelData() {
        throw new NotImplementedException("ItemMeta.getCustomModelData() not got to yet");
    }

    @Override
    public void setCustomModelData(@Nullable Integer data) {
        throw new NotImplementedException("ItemMeta.setCustomModelData(Integer) not got to yet");
    }

    @Override
    public void setVersion(int version) {
        throw new NotImplementedException("ItemMeta.setVersion(int) not got to yet");
    }

    @Override
    public @NotNull CustomItemTagContainer getCustomTagContainer() {
        throw new NotImplementedException("ItemMeta.getCustomTagContainer() not looked at yet");
    }

    @Override
    public boolean hasDisplayName() {
        return this.stack.get(Keys.DISPLAY_NAME.get()).isPresent();
    }

    @Override
    public String getDisplayName() {
        Optional<Text> opName = this.stack.get(Keys.DISPLAY_NAME.get());
        if(!opName.isPresent()){
            return null;
        }
        return Bonge.getInstance().convert(opName.get());
    }

    @Override
    public void setDisplayName(String name) {
        this.stack.offer(Keys.DISPLAY_NAME.get(), Bonge.getInstance().convertText(name));
    }

    @Override
    public boolean hasLocalizedName() {
        return false;
    }

    @Override
    public String getLocalizedName() {
        throw new NotImplementedException("ItemMeta.getLocalizedName() has not been looked at");
    }

    @Override
    public void setLocalizedName(String name) {
        throw new NotImplementedException("ItemMeta.setLocalizedName(String) has not been looked at");
    }

    @Override
    public boolean hasLore() {
        return !this.stack.get(Keys.LORE).get().isEmpty();
    }

    @Override
    public List<String> getLore() {
        return ArrayUtils.convert(e -> Bonge.getInstance().convert(e), this.stack.get(Keys.LORE).get());
    }

    @Override
    public void setLore(List<String> lore) {
        List<Text> list = ArrayUtils.convert(e -> Bonge.getInstance().convertText(e), lore);
        this.stack.offer(Keys.LORE, list);
    }

    @Override
    public boolean hasEnchants() {
        return !this.stack.get(Keys.STORED_ENCHANTMENTS).get().isEmpty();
    }

    @Override
    public boolean hasEnchant(Enchantment ench) {
        EnchantmentType type;
        try {
            type = Bonge.getInstance().convert(ench, EnchantmentType.class);
        } catch (IOException e) {
            return false;
        }
        return this.stack.get(Keys.STORED_ENCHANTMENTS).get().stream().anyMatch(e -> e.getType().equals(type));
    }

    @Override
    public int getEnchantLevel(Enchantment ench) {
        EnchantmentType type;
        try {
            type = Bonge.getInstance().convert(ench, EnchantmentType.class);
        } catch (IOException e) {
            return -1;
        }
        Optional<org.spongepowered.api.item.enchantment.Enchantment> opEnchantment = this.stack.get(Keys.STORED_ENCHANTMENTS).get().stream().filter(e -> e.getType().equals(type)).findAny();
        if(opEnchantment.isPresent()){
            return opEnchantment.get().getLevel();
        }
        return -1;
    }

    @Override
    public Map<Enchantment, Integer> getEnchants() {
        Map<Enchantment, Integer> map = new HashMap<>();
        this.stack.get(Keys.STORED_ENCHANTMENTS).get().forEach(e -> {
            Enchantment enchantment;
            try {
                enchantment = Bonge.getInstance().convert(Enchantment.class, e.getType());
            } catch (IOException ioException) {
                throw new IllegalStateException(ioException);
            }
            map.put(enchantment, e.getLevel());
        });
        return Collections.unmodifiableMap(map);
    }

    @Override
    public boolean addEnchant(Enchantment ench, int level, boolean ignoreLevelRestriction) {
        if(!ignoreLevelRestriction && level > ench.getMaxLevel()){
            return false;
        }
        EnchantmentType enchantment;
        try {
            enchantment = Bonge.getInstance().convert(ench, EnchantmentType.class);
        } catch (IOException e) {
            return false;
        }

        List<org.spongepowered.api.item.enchantment.Enchantment> enchant = this.stack.get(Keys.STORED_ENCHANTMENTS).get();
        enchant.add(org.spongepowered.api.item.enchantment.Enchantment.builder().type(enchantment).level(level).build());
        this.stack.offer(Keys.STORED_ENCHANTMENTS, enchant);
        return true;
    }

    @Override
    public boolean removeEnchant(Enchantment ench) {
        EnchantmentType enchantment;
        try {
            enchantment = Bonge.getInstance().convert(ench, EnchantmentType.class);
        } catch (IOException e) {
            return false;
        }

        List<org.spongepowered.api.item.enchantment.Enchantment> enchant = this.stack.get(Keys.STORED_ENCHANTMENTS).get();
        enchant.removeIf(e -> e.getType().equals(enchantment));
        this.stack.offer(Keys.STORED_ENCHANTMENTS, enchant);
        return true;
    }

    @Override
    public boolean hasConflictingEnchant(Enchantment ench) {
        return false;
    }

    @Override
    public void addItemFlags(ItemFlag... itemFlags) {
        throw new NotImplementedException("ItemMeta.addItemFlags(ItemFlag...) not looked at yet");
    }

    @Override
    public void removeItemFlags(ItemFlag... itemFlags) {
        throw new NotImplementedException("ItemMeta.removeItemFlags(ItemFlag...) not looked at yet");
    }

    @Override
    public Set<ItemFlag> getItemFlags() {
        throw new NotImplementedException("ItemMeta.getItemFlags() not looked at yet");
    }

    @Override
    public boolean hasItemFlag(ItemFlag flag) {
        throw new NotImplementedException("ItemMeta.hasItemFlags(ItemFlag) not looked at yet");
    }

    @Override
    public boolean isUnbreakable() {
        return this.stack.get(Keys.IS_UNBREAKABLE).get();
    }

    @Override
    public void setUnbreakable(boolean unbreakable) {
        this.stack.offer(Keys.IS_UNBREAKABLE, unbreakable);
    }

    @Override
    public boolean hasAttributeModifiers() {
        throw new NotImplementedException("ItemMeta.hasAttributeModifiers() not looked at yet");
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers() {
        throw new NotImplementedException("ItemMeta.getAttributeModifiers() not looked at yet");
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot) {
        throw new NotImplementedException("ItemMeta.getAttributeModifiers(EquipmentSlot) not looked at yet");
    }

    @Override
    public Collection<AttributeModifier> getAttributeModifiers(Attribute attribute) {
        throw new NotImplementedException("ItemMeta.getAttributeModifiers(Attribute) not looked at yet");
    }

    @Override
    public boolean addAttributeModifier(Attribute attribute, AttributeModifier modifier) {
        throw new NotImplementedException("ItemMeta.addAttributeModifiers(Attribute, AttributeModifier) not looked at yet");
    }

    @Override
    public void setAttributeModifiers(Multimap<Attribute, AttributeModifier> attributeModifiers) {
        throw new NotImplementedException("ItemMeta.setAttributeModifiers(Multimap<Attribute, AttributeModifier>) not looked at yet");
    }

    @Override
    public boolean removeAttributeModifier(Attribute attribute) {
        throw new NotImplementedException("ItemMeta.removeAttributeModifier(Attribute) not looked at yet");
    }

    @Override
    public boolean removeAttributeModifier(EquipmentSlot slot) {
        throw new NotImplementedException("ItemMeta.removeAttributeModifier(EquipmentSlot) not looked at yet");
    }

    @Override
    public boolean removeAttributeModifier(Attribute attribute, AttributeModifier modifier) {
        throw new NotImplementedException("ItemMeta.removeAttributeModifier(Attribute, AttributeModifier) not looked at yet");

    }

    @Override
    public boolean hasDamage() {
        return this.stack.get(Keys.ATTACK_DAMAGE).isPresent();
    }

    @Override
    public int getDamage() {
        return this.stack.get(Keys.ATTACK_DAMAGE).orElse(0.0).intValue();
    }

    @Override
    public void setDamage(int damage) {
        this.stack.offer(Keys.ATTACK_DAMAGE, (double)damage);
    }

    @Override
    public AbstractItemMeta clone(){
        return this.createBuilder().build(this.stack.copy());
    }

    @Override
    public @NotNull PersistentDataContainer getPersistentDataContainer() {
        throw new NotImplementedException("ItemMeta.getPersistentDataContainer() not looked at");
    }
}
