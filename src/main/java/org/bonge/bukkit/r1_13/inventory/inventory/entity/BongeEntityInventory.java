package org.bonge.bukkit.r1_13.inventory.inventory.entity;

import org.bonge.Bonge;
import org.bonge.bukkit.r1_13.entity.BongeAbstractEntity;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.spongepowered.api.data.type.HandTypes;

import java.io.IOException;
import java.util.Optional;

public interface BongeEntityInventory<T extends org.spongepowered.api.entity.ArmorEquipable & org.spongepowered.api.entity.Entity> extends EntityEquipment {

    T getSpongeValue();

    @Override
    default ItemStack getItemInMainHand() {
        org.spongepowered.api.item.inventory.ItemStack stack = this.getSpongeValue().getItemInHand(HandTypes.MAIN_HAND).get();
        try {
            return Bonge.getInstance().convert(ItemStack.class, stack);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    default void setItemInMainHand(ItemStack item) {
        org.spongepowered.api.item.inventory.ItemStack is;
        try {
            is = Bonge.getInstance().convert(item, org.spongepowered.api.item.inventory.ItemStack.class);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        this.getSpongeValue().setItemInHand(HandTypes.MAIN_HAND, is);
    }

    @Override
    default ItemStack getItemInOffHand() {
        org.spongepowered.api.item.inventory.ItemStack stack = this.getSpongeValue().getItemInHand(HandTypes.OFF_HAND).get();
        try {
            return Bonge.getInstance().convert(ItemStack.class, stack);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    default void setItemInOffHand(ItemStack item) {
        org.spongepowered.api.item.inventory.ItemStack is;
        try {
            is = Bonge.getInstance().convert(item, org.spongepowered.api.item.inventory.ItemStack.class);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        this.getSpongeValue().setItemInHand(HandTypes.OFF_HAND, is);
    }

    @Override
    @Deprecated
    default ItemStack getItemInHand() {
        return this.getItemInMainHand();
    }

    @Override
    @Deprecated
    default void setItemInHand(ItemStack stack) {
        this.setItemInMainHand(stack);
    }

    @Override
    default ItemStack getHelmet() {
        org.spongepowered.api.item.inventory.ItemStack stack = this.getSpongeValue().getBoots().get();
        try {
            return Bonge.getInstance().convert(ItemStack.class, stack);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    default void setHelmet(ItemStack helmet) {
        org.spongepowered.api.item.inventory.ItemStack is;
        try {
            is = Bonge.getInstance().convert(helmet, org.spongepowered.api.item.inventory.ItemStack.class);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        this.getSpongeValue().setHelmet(is);
    }

    @Override
    default ItemStack getChestplate() {
        org.spongepowered.api.item.inventory.ItemStack stack = this.getSpongeValue().getChestplate().get();
        try {
            return Bonge.getInstance().convert(ItemStack.class, stack);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    default void setChestplate(ItemStack chestplate) {
        org.spongepowered.api.item.inventory.ItemStack is;
        try {
            is = Bonge.getInstance().convert(chestplate, org.spongepowered.api.item.inventory.ItemStack.class);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        this.getSpongeValue().setChestplate(is);
    }

    @Override
    default ItemStack getLeggings() {
        org.spongepowered.api.item.inventory.ItemStack stack = this.getSpongeValue().getLeggings().get();
        try {
            return Bonge.getInstance().convert(ItemStack.class, stack);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    default void setLeggings(ItemStack leggings) {
        org.spongepowered.api.item.inventory.ItemStack is;
        try {
            is = Bonge.getInstance().convert(leggings, org.spongepowered.api.item.inventory.ItemStack.class);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        this.getSpongeValue().setLeggings(is);
    }

    @Override
    default ItemStack getBoots() {
        org.spongepowered.api.item.inventory.ItemStack stack = this.getSpongeValue().getBoots().get();
        try {
            return Bonge.getInstance().convert(ItemStack.class, stack);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    default void setBoots(ItemStack boots) {
        org.spongepowered.api.item.inventory.ItemStack is;
        try {
            is = Bonge.getInstance().convert(boots, org.spongepowered.api.item.inventory.ItemStack.class);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        this.getSpongeValue().setHelmet(is);
    }

    @Override
    default ItemStack[] getArmorContents() {
        return new ItemStack[0];
    }

    @Override
    default void setArmorContents(ItemStack[] items) {

    }

    @Override
    default void clear() {
        this.getSpongeValue().getInventory().clear();
    }

    @Override
    default float getItemInHandDropChance() {
        return 0;
    }

    @Override
    default void setItemInHandDropChance(float chance) {

    }

    @Override
    default float getItemInMainHandDropChance() {
        return 0;
    }

    @Override
    default void setItemInMainHandDropChance(float chance) {

    }

    @Override
    default float getItemInOffHandDropChance() {
        return 0;
    }

    @Override
    default void setItemInOffHandDropChance(float chance) {

    }

    @Override
    default float getHelmetDropChance() {
        return 0;
    }

    @Override
    default void setHelmetDropChance(float chance) {

    }

    @Override
    default float getChestplateDropChance() {
        return 0;
    }

    @Override
    default void setChestplateDropChance(float chance) {

    }

    @Override
    default float getLeggingsDropChance() {
        return 0;
    }

    @Override
    default void setLeggingsDropChance(float chance) {

    }

    @Override
    default float getBootsDropChance() {
        return 0;
    }

    @Override
    default void setBootsDropChance(float chance) {

    }

    @Override
    default Entity getHolder() {
        try {
            return Bonge.getInstance().convert(Entity.class, this.getSpongeValue());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
