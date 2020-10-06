package org.bonge.bukkit.r1_15.inventory;

import org.spongepowered.api.item.inventory.Inventory;

public abstract class BongeInventorySnapshot<I extends Inventory> extends BongeInventory<I>{

    public BongeInventorySnapshot(I value) {
        super(value);
    }
}
