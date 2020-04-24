package org.bonge.bukkit.r1_13.block.state;

import org.bonge.convert.InventoryConvert;
import org.bukkit.block.Container;
import org.bukkit.inventory.Inventory;
import org.spongepowered.api.data.key.Keys;

public abstract class BongeContainerBlockState<T extends org.spongepowered.api.block.tileentity.carrier.TileEntityCarrier> extends BongeBlockState<T> implements Container {

    public static final String INVENTORY = "INVENTORY";
    public static final String LOCK = "LOCK";
    public static final String INVENTORY_SNAPSHOT = "INVENTORY_SNAPSHOT";

    public BongeContainerBlockState(T value) {
        super(value);
        this.changes.put(INVENTORY_SNAPSHOT, InventoryConvert.getInventory(this, value.getInventory()));
        this.changes.put(INVENTORY, InventoryConvert.getInventory(this, value.getInventory()));
        this.changes.put(LOCK, value.get(Keys.LOCK_TOKEN).orElse(null));
    }

    @Override
    public Inventory getInventory() {
        return (Inventory)this.changes.get(INVENTORY);
    }

    @Override
    public Inventory getSnapshotInventory() {
        return (Inventory)this.changes.get(INVENTORY_SNAPSHOT);
    }

    @Override
    public boolean isLocked() {
        return getLock() != null;
    }

    @Override
    public String getLock() {
        return (String)this.changes.get(LOCK);
    }

    @Override
    public void setLock(String key) {
        this.changes.replace(LOCK, key);
    }
}
