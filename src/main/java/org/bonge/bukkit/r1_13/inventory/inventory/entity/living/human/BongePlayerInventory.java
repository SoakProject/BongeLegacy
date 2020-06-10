package org.bonge.bukkit.r1_13.inventory.inventory.entity.living.human;

import org.bonge.Bonge;
import org.bonge.bukkit.r1_13.inventory.inventory.BongeAbstractInventory;
import org.bonge.util.InventoryListIterator;
import org.bonge.wrapper.BongeWrapper;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.spongepowered.api.data.type.HandTypes;
import org.spongepowered.api.entity.living.Human;
import org.spongepowered.api.entity.living.Humanoid;
import org.spongepowered.api.item.inventory.type.CarriedInventory;

import java.io.IOException;
import java.util.ListIterator;

public class BongePlayerInventory extends BongeWrapper<org.spongepowered.api.entity.living.Humanoid> implements BongeAbstractInventory<CarriedInventory<Humanoid>>, PlayerInventory {

    public BongePlayerInventory(org.spongepowered.api.entity.living.Humanoid player){
        super(player);
    }

    @Override
    public void setItem(int slot, ItemStack stack){
        BongeAbstractInventory.super.setItem(slot, stack);
    }

    @Override
    public InventoryType getType() {
        return InventoryType.PLAYER;
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
    public ItemStack getItemInMainHand() {
        org.spongepowered.api.item.inventory.ItemStack stack = this.getSpongeValue().getItemInHand(HandTypes.MAIN_HAND).get();
        try {
            return Bonge.getInstance().convert(ItemStack.class, stack);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void setItemInMainHand(ItemStack item) {
        org.spongepowered.api.item.inventory.ItemStack is;
        try {
            is = Bonge.getInstance().convert(item, org.spongepowered.api.item.inventory.ItemStack.class);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        this.getSpongeValue().setItemInHand(HandTypes.MAIN_HAND, is);
    }

    @Override
    public ItemStack getItemInOffHand() {
        org.spongepowered.api.item.inventory.ItemStack stack = this.getSpongeValue().getItemInHand(HandTypes.OFF_HAND).get();
        try {
            return Bonge.getInstance().convert(ItemStack.class, stack);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void setItemInOffHand(ItemStack item) {
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
    public ItemStack getItemInHand() {
        return this.getItemInMainHand();
    }

    @Override
    @Deprecated
    public void setItemInHand(ItemStack stack) {
        this.setItemInMainHand(stack);
    }

    @Override
    public ItemStack getHelmet() {
        org.spongepowered.api.item.inventory.ItemStack stack = this.getSpongeValue().getBoots().get();
        try {
            return Bonge.getInstance().convert(ItemStack.class, stack);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void setHelmet(ItemStack helmet) {
        org.spongepowered.api.item.inventory.ItemStack is;
        try {
            is = Bonge.getInstance().convert(helmet, org.spongepowered.api.item.inventory.ItemStack.class);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        this.getSpongeValue().setHelmet(is);
    }

    @Override
    public ItemStack getChestplate() {
        org.spongepowered.api.item.inventory.ItemStack stack = this.getSpongeValue().getChestplate().get();
        try {
            return Bonge.getInstance().convert(ItemStack.class, stack);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void setChestplate(ItemStack chestplate) {
        org.spongepowered.api.item.inventory.ItemStack is;
        try {
            is = Bonge.getInstance().convert(chestplate, org.spongepowered.api.item.inventory.ItemStack.class);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        this.getSpongeValue().setChestplate(is);
    }

    @Override
    public ItemStack getLeggings() {
        org.spongepowered.api.item.inventory.ItemStack stack = this.getSpongeValue().getLeggings().get();
        try {
            return Bonge.getInstance().convert(ItemStack.class, stack);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void setLeggings(ItemStack leggings) {
        org.spongepowered.api.item.inventory.ItemStack is;
        try {
            is = Bonge.getInstance().convert(leggings, org.spongepowered.api.item.inventory.ItemStack.class);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        this.getSpongeValue().setLeggings(is);
    }

    @Override
    public ItemStack getBoots() {
        org.spongepowered.api.item.inventory.ItemStack stack = this.getSpongeValue().getBoots().get();
        try {
            return Bonge.getInstance().convert(ItemStack.class, stack);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void setBoots(ItemStack boots) {
        org.spongepowered.api.item.inventory.ItemStack is;
        try {
            is = Bonge.getInstance().convert(boots, org.spongepowered.api.item.inventory.ItemStack.class);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        this.getSpongeValue().setHelmet(is);
    }

    @Override
    public void setArmorContents(ItemStack[] items) {

    }

    @Override
    public void setExtraContents(ItemStack[] items) {

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
        try {
            return (HumanEntity) Bonge.getInstance().convert(Entity.class, this.spongeValue);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ListIterator<ItemStack> iterator() {
        return new InventoryListIterator(this);
    }

    @Override
    public ListIterator<ItemStack> iterator(int index) {
        return new InventoryListIterator(this).setUntil(index);
    }

    @Override
    public Location getLocation() {
        return this.getHolder().getLocation();
    }

    @Override
    public CarriedInventory<Humanoid> getSpongeInventoryValue() {
        return (CarriedInventory<Humanoid>) this.spongeValue.getInventory();
    }
}
