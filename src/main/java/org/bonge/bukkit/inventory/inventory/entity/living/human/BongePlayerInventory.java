package org.bonge.bukkit.inventory.inventory.entity.living.human;

import org.bonge.bukkit.entity.BongeAbstractEntity;
import org.bonge.bukkit.inventory.inventory.BongeAbstractInventory;
import org.bonge.convert.InventoryConvert;
import org.bonge.wrapper.BongeWrapper;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.spongepowered.api.data.type.HandTypes;
import org.spongepowered.api.entity.living.Human;
import org.spongepowered.api.entity.living.Humanoid;
import org.spongepowered.api.item.inventory.type.CarriedInventory;

import java.util.Optional;

public class BongePlayerInventory extends BongeWrapper<org.spongepowered.api.entity.living.Humanoid> implements BongeAbstractInventory<org.spongepowered.api.item.inventory.type.CarriedInventory<org.spongepowered.api.entity.living.Humanoid>>, PlayerInventory {

    public BongePlayerInventory(org.spongepowered.api.entity.living.Humanoid player){
        super(player);
    }

    @Override
    public void setItem(int slot, ItemStack stack){
        BongeAbstractInventory.super.setItem(slot, stack);
    }

    @Override
    public ItemStack[] getArmorContents() {
        return new ItemStack[0];
    }

    @Override
    public ItemStack[] getExtraContents() {
        return new ItemStack[0];
    }

    @Override
    public ItemStack getHelmet() {
        Optional<org.spongepowered.api.item.inventory.ItemStack> opItem = this.spongeValue.getHelmet();
        return opItem.map(InventoryConvert::getItemStack).orElse(null);
    }

    @Override
    public void setHelmet(ItemStack helmet) {
        Optional<org.spongepowered.api.item.inventory.ItemStack> opItem = InventoryConvert.getItemStack(helmet);
        if(!opItem.isPresent()){
            return;
        }
        this.spongeValue.setHelmet(opItem.get());
    }

    @Override
    public ItemStack getChestplate() {
        Optional<org.spongepowered.api.item.inventory.ItemStack> opItem = this.spongeValue.getChestplate();
        return opItem.map(InventoryConvert::getItemStack).orElse(null);
    }

    @Override
    public void setChestplate(ItemStack chestplate) {
        Optional<org.spongepowered.api.item.inventory.ItemStack> opItem = InventoryConvert.getItemStack(chestplate);
        if(!opItem.isPresent()){
            return;
        }
        this.spongeValue.setChestplate(opItem.get());
    }

    @Override
    public ItemStack getLeggings() {
        Optional<org.spongepowered.api.item.inventory.ItemStack> opItem = this.spongeValue.getLeggings();
        return opItem.map(InventoryConvert::getItemStack).orElse(null);
    }

    @Override
    public void setLeggings(ItemStack leggings) {
        Optional<org.spongepowered.api.item.inventory.ItemStack> opItem = InventoryConvert.getItemStack(leggings);
        if(!opItem.isPresent()){
            return;
        }
        this.spongeValue.setLeggings(opItem.get());
    }

    @Override
    public ItemStack getBoots() {
        Optional<org.spongepowered.api.item.inventory.ItemStack> opItem = this.spongeValue.getBoots();
        return opItem.map(InventoryConvert::getItemStack).orElse(null);
    }

    @Override
    public void setBoots(ItemStack boots) {
        Optional<org.spongepowered.api.item.inventory.ItemStack> opItem = InventoryConvert.getItemStack(boots);
        if(!opItem.isPresent()){
            return;
        }
        this.spongeValue.setBoots(opItem.get());
    }

    @Override
    public void setArmorContents(ItemStack[] items) {

    }

    @Override
    public void setExtraContents(ItemStack[] items) {

    }

    @Override
    public ItemStack getItemInMainHand() {
        Optional<org.spongepowered.api.item.inventory.ItemStack> opItem = this.spongeValue.getItemInHand(HandTypes.MAIN_HAND);
        return opItem.map(InventoryConvert::getItemStack).orElse(null);
    }

    @Override
    public void setItemInMainHand(ItemStack item) {
        Optional<org.spongepowered.api.item.inventory.ItemStack> opItem = InventoryConvert.getItemStack(item);
        if(!opItem.isPresent()){
            return;
        }
        this.spongeValue.setItemInHand(HandTypes.MAIN_HAND, opItem.get());
    }

    @Override
    public ItemStack getItemInOffHand() {
        Optional<org.spongepowered.api.item.inventory.ItemStack> opItem = this.spongeValue.getItemInHand(HandTypes.OFF_HAND);
        return opItem.map(InventoryConvert::getItemStack).orElse(null);
    }

    @Override
    public void setItemInOffHand(ItemStack item) {
        Optional<org.spongepowered.api.item.inventory.ItemStack> opItem = InventoryConvert.getItemStack(item);
        if(!opItem.isPresent()){
            return;
        }
        this.spongeValue.setItemInHand(HandTypes.OFF_HAND, opItem.get());
    }

    @Override
    @Deprecated
    public ItemStack getItemInHand() {
        return this.getItemInMainHand();
    }

    @Override
    @Deprecated
    public void setItemInHand(ItemStack stack) {
        this.setItemInMainHand(stack);
    }

    @Override
    public int getHeldItemSlot() {
        if(this.spongeValue instanceof Human){
            return 0;
        }
        return ((org.spongepowered.api.item.inventory.entity.PlayerInventory)((org.spongepowered.api.entity.living.player.Player)this.spongeValue).getInventory()).getHotbar().getSelectedSlotIndex();
    }

    @Override
    public void setHeldItemSlot(int slot) {
        if(this.spongeValue instanceof Human){
            return;
        }
        ((org.spongepowered.api.item.inventory.entity.PlayerInventory)((org.spongepowered.api.entity.living.player.Player)this.spongeValue).getInventory()).getHotbar().setSelectedSlotIndex(slot);
    }

    @Override
    public HumanEntity getHolder() {
        return (HumanEntity) BongeAbstractEntity.of(this.spongeValue);
    }

    @Override
    public CarriedInventory<Humanoid> getSpongeInventoryValue() {
        return (CarriedInventory<Humanoid>) this.spongeValue.getInventory();
    }
}
