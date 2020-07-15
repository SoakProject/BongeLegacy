package org.bonge.bukkit.r1_14.inventory.item.enchantment;

import org.bukkit.enchantments.Enchantment;

public class EnchantmentData {

    private Enchantment enchantment;
    private int level;
    private boolean ignore;

    public EnchantmentData(Enchantment enchantment, int level, boolean ignore){
        this.enchantment = enchantment;
        this.level = level;
        this.ignore = ignore;
    }

    public Enchantment getEnchantment() {
        return enchantment;
    }

    public void setLevel(int level){
        this.level = level;
    }

    public int getLevel() {
        return this.level;
    }

    public void setIgnore(boolean ignore){
        this.ignore = ignore;
    }

    public boolean isIgnore() {
        return this.ignore;
    }
}
