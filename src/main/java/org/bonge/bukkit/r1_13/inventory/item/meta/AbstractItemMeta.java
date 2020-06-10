package org.bonge.bukkit.r1_13.inventory.item.meta;

import com.google.common.collect.Multimap;
import org.bonge.bukkit.r1_13.inventory.item.enchantment.EnchantmentData;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public abstract class AbstractItemMeta implements ItemMeta, Damageable {

    protected String displayName;
    protected List<String> lore = new ArrayList<>();
    protected Integer damage;
    protected Set<EnchantmentData> enchantment = new HashSet<>();
    protected boolean unbreakable;
    protected Material[] acceptable;
    protected Set<ItemFlag> flags = new HashSet<>();

    public AbstractItemMeta(Material... material){
        this.acceptable = material;
    }

    public boolean isAcceptableMaterial(Material material){
        for(Material ma : this.acceptable){
            if(ma.equals(material)){
                return true;
            }
        }
        return false;
    }

    public Set<EnchantmentData> getEnchantments(){
        return this.enchantment;
    }

    @Override
    public boolean hasDisplayName() {
        return this.displayName != null;
    }

    @Override
    public String getDisplayName() {
        return this.displayName;
    }

    @Override
    public void setDisplayName(String name) {
        this.displayName = name;
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
        return !this.lore.isEmpty();
    }

    @Override
    public List<String> getLore() {
        return this.lore;
    }

    @Override
    public void setLore(List<String> lore) {
        this.lore.clear();
        this.lore.addAll(lore);
    }

    @Override
    public boolean hasEnchants() {
        return !this.enchantment.isEmpty();
    }

    @Override
    public boolean hasEnchant(Enchantment ench) {
        return this.enchantment.stream().anyMatch(d -> d.getEnchantment().equals(ench));
    }

    @Override
    public int getEnchantLevel(Enchantment ench) {
        for(EnchantmentData enchantment : this.enchantment){
            if(enchantment.getEnchantment().equals(ench)){
                return enchantment.getLevel();
            }
        }
        return 0;
    }

    @Override
    public Map<Enchantment, Integer> getEnchants() {
        Map<Enchantment, Integer> map = new HashMap<>();
        this.enchantment.forEach(e -> map.put(e.getEnchantment(), e.getLevel()));
        return map;
    }

    @Override
    public boolean addEnchant(Enchantment ench, int level, boolean ignoreLevelRestriction) {
        for(EnchantmentData data : this.enchantment){
            if(data.getEnchantment().equals(ench)){
                data.setLevel(level);
                data.setIgnore(ignoreLevelRestriction);
                return true;
            }
        }
        return this.enchantment.add(new EnchantmentData(ench, level, ignoreLevelRestriction));
    }

    @Override
    public boolean removeEnchant(Enchantment ench) {
        EnchantmentData data1 = null;
        for(EnchantmentData data : this.enchantment){
            if(data.getEnchantment().equals(ench)) {
                data1 = data;
                break;
            }
        }
        if(data1 == null){
            return false;
        }
        return this.enchantment.remove(data1);
    }

    @Override
    public boolean hasConflictingEnchant(Enchantment ench) {
        return false;
    }

    @Override
    public void addItemFlags(ItemFlag... itemFlags) {
        this.flags.addAll(Arrays.asList(itemFlags));
    }

    @Override
    public void removeItemFlags(ItemFlag... itemFlags) {
        this.flags.removeAll(Arrays.asList(itemFlags));
    }

    @Override
    public Set<ItemFlag> getItemFlags() {
        return this.flags;
    }

    @Override
    public boolean hasItemFlag(ItemFlag flag) {
        return this.flags.contains(flag);
    }

    @Override
    public boolean isUnbreakable() {
        return this.unbreakable;
    }

    @Override
    public void setUnbreakable(boolean unbreakable) {
        this.unbreakable = unbreakable;
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
    public boolean hasDamage() {
        return this.damage != null;
    }

    @Override
    public int getDamage() {
        return (this.damage == null) ? -1 : this.damage;
    }

    @Override
    public void setDamage(int damage) {
        this.damage = damage;
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();
        return map;
    }

    @Override
    public AbstractItemMeta clone(){
        throw new IllegalArgumentException("Not implemented");
    }
}
