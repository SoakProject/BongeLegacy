package org.bonge.bukkit.r1_13.inventory.inventory;

import org.bonge.wrapper.BongeWrapper;
import org.bukkit.Location;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.ListIterator;

public class BongeCustomInventory extends BongeWrapper<org.spongepowered.api.item.inventory.Inventory> implements BongeAbstractInventory<org.spongepowered.api.item.inventory.Inventory>, BongeAbstractInventory.Swappable {

    private InventoryHolder holder;

    public BongeCustomInventory(org.spongepowered.api.item.inventory.Inventory value) {
        super(value);
    }

    @Override
    public org.spongepowered.api.item.inventory.Inventory getSpongeInventoryValue() {
        return this.getSpongeValue();
    }

    @Override
    public InventoryType getType() {
        return InventoryType.CHEST;
    }

    @Override
    public InventoryHolder getHolder() {
        return this.holder;
    }

    @Override
    public ListIterator<ItemStack> iterator() {
        return null;
    }

    @Override
    public ListIterator<ItemStack> iterator(int index) {
        return null;
    }

    @Override
    public Location getLocation() {
        return null;
    }

    @Override
    public void setHolder(InventoryHolder holder) {
        this.holder = holder;
    }
}
