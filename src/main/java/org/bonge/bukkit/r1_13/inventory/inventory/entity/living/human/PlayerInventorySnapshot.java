package org.bonge.bukkit.r1_13.inventory.inventory.entity.living.human;

import org.bonge.bukkit.r1_13.inventory.inventory.InventorySnapshot;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class PlayerInventorySnapshot extends InventorySnapshot<PlayerInventory> implements PlayerInventory {

    private int slot;

    public PlayerInventorySnapshot(PlayerInventory inventory, boolean apply) {
        super(inventory, apply);
        this.slot = inventory.getHeldItemSlot();
    }

    @Override
    public Player getHolder() {
        return (Player)super.getHolder();
    }

    @Override
    public ItemStack[] getArmorContents() {
        ItemStack[] armor = new ItemStack[4];
        for(int A = 36; A < 40; A++){
            armor[A - 36] = this.items.get(A);
        }
        return armor;
    }

    @Override
    public ItemStack[] getExtraContents() {
        return new ItemStack[0];
    }

    @Override
    public ItemStack getHelmet() {
        return this.items.get(39);
    }

    @Override
    public ItemStack getChestplate() {
        return this.items.get(38);
    }

    @Override
    public ItemStack getLeggings() {
        return this.items.get(37);
    }

    @Override
    public ItemStack getBoots() {
        return this.items.get(36);
    }

    @Override
    public void setArmorContents(ItemStack[] items) {
        if(this.apply){
            this.inv.setArmorContents(items);
        }
        for(int A = 0; A < items.length; A++){
            this.setItem(A + 36, items[A]);
        }
    }

    @Override
    public void setExtraContents(ItemStack[] items) {
        if(this.apply){
            this.inv.setExtraContents(items);
        }
    }

    @Override
    public void setHelmet(ItemStack helmet) {
        if(this.apply){
            this.inv.setHelmet(helmet);
        }
        this.setItem(39, helmet);
    }

    @Override
    public void setChestplate(ItemStack chestplate) {
        if(this.apply){
            this.inv.setChestplate(chestplate);
        }
        this.setItem(38, chestplate);
    }

    @Override
    public void setLeggings(ItemStack leggings) {
        if(this.apply){
            this.inv.setLeggings(leggings);
        }
        this.setItem(37, leggings);
    }

    @Override
    public void setBoots(ItemStack boots) {
        if(this.apply){
            this.inv.setBoots(boots);
        }
        this.setItem(36, boots);
    }

    @Override
    public ItemStack getItemInMainHand() {
        return this.getItem(this.getHeldItemSlot());
    }

    @Override
    public void setItemInMainHand(ItemStack item) {
        if(this.apply){
            this.inv.setItemInMainHand(item);
        }
        this.setItem(this.getHeldItemSlot(), item);
    }

    @Override
    public ItemStack getItemInOffHand() {
        return this.getItem(40);
    }

    @Override
    public void setItemInOffHand(ItemStack item) {
        if(this.apply){
            this.inv.setItemInOffHand(item);
        }
        this.setItem(40, item);
    }

    @Override
    public ItemStack getItemInHand() {
        return this.getItemInMainHand();
    }

    @Override
    public void setItemInHand(ItemStack stack) {
        this.setItemInMainHand(stack);
    }

    @Override
    public int getHeldItemSlot() {
        return this.slot;
    }

    @Override
    public void setHeldItemSlot(int slot) {
        if(this.apply){
            this.inv.setHeldItemSlot(slot);
        }
        this.slot = slot;
    }
}
