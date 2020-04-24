package org.bonge.bukkit.r1_13.inventory.inventory.entity;

import org.bonge.bukkit.r1_13.entity.BongeAbstractEntity;
import org.bonge.convert.InventoryConvert;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.spongepowered.api.data.type.HandTypes;

import java.util.Optional;

public interface BongeEntityInventory<T extends org.spongepowered.api.entity.ArmorEquipable & org.spongepowered.api.entity.Entity> extends EntityEquipment {

    T getSpongeValue();

    @Override
    default ItemStack getItemInMainHand() {
        org.spongepowered.api.item.inventory.ItemStack item = this.getSpongeValue().getItemInHand(HandTypes.MAIN_HAND).get();
        return InventoryConvert.getItemStack(item);
    }

    @Override
    default void setItemInMainHand(ItemStack item) {
        Optional<org.spongepowered.api.item.inventory.ItemStack> opItem = InventoryConvert.getItemStack(item);
        if(!opItem.isPresent()){
            return;
        }
        this.getSpongeValue().setItemInHand(HandTypes.MAIN_HAND, opItem.get());
    }

    @Override
    default ItemStack getItemInOffHand() {
        org.spongepowered.api.item.inventory.ItemStack item = this.getSpongeValue().getItemInHand(HandTypes.OFF_HAND).get();
        return InventoryConvert.getItemStack(item);
    }

    @Override
    default void setItemInOffHand(ItemStack item) {
        Optional<org.spongepowered.api.item.inventory.ItemStack> opItem = InventoryConvert.getItemStack(item);
        if(!opItem.isPresent()){
            return;
        }
        this.getSpongeValue().setItemInHand(HandTypes.OFF_HAND, opItem.get());
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
        org.spongepowered.api.item.inventory.ItemStack item = this.getSpongeValue().getHelmet().get();
        return InventoryConvert.getItemStack(item);
    }

    @Override
    default void setHelmet(ItemStack helmet) {
        Optional<org.spongepowered.api.item.inventory.ItemStack> opItem = InventoryConvert.getItemStack(helmet);
        if(!opItem.isPresent()){
            return;
        }
        this.getSpongeValue().setHelmet(opItem.get());
    }

    @Override
    default ItemStack getChestplate() {
        org.spongepowered.api.item.inventory.ItemStack item = this.getSpongeValue().getChestplate().get();
        return InventoryConvert.getItemStack(item);
    }

    @Override
    default void setChestplate(ItemStack chestplate) {
        Optional<org.spongepowered.api.item.inventory.ItemStack> opItem = InventoryConvert.getItemStack(chestplate);
        if(!opItem.isPresent()){
            return;
        }
        this.getSpongeValue().setChestplate(opItem.get());
    }

    @Override
    default ItemStack getLeggings() {
        org.spongepowered.api.item.inventory.ItemStack item = this.getSpongeValue().getLeggings().get();
        return InventoryConvert.getItemStack(item);
    }

    @Override
    default void setLeggings(ItemStack leggings) {
        Optional<org.spongepowered.api.item.inventory.ItemStack> opItem = InventoryConvert.getItemStack(leggings);
        if(!opItem.isPresent()){
            return;
        }
        this.getSpongeValue().setLeggings(opItem.get());
    }

    @Override
    default ItemStack getBoots() {
        org.spongepowered.api.item.inventory.ItemStack item = this.getSpongeValue().getBoots().get();
        return InventoryConvert.getItemStack(item);
    }

    @Override
    default void setBoots(ItemStack boots) {
        Optional<org.spongepowered.api.item.inventory.ItemStack> opItem = InventoryConvert.getItemStack(boots);
        if(!opItem.isPresent()){
            return;
        }
        this.getSpongeValue().setBoots(opItem.get());
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
        return BongeAbstractEntity.of(this.getSpongeValue());
    }
}
