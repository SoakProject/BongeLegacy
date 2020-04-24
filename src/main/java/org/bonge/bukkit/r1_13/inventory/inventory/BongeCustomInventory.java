package org.bonge.bukkit.r1_13.inventory.inventory;

import org.bonge.wrapper.BongeWrapper;
import org.bukkit.inventory.InventoryHolder;

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
    public InventoryHolder getHolder() {
        return this.holder;
    }

    @Override
    public void setHolder(InventoryHolder holder) {
        this.holder = holder;
    }
}
