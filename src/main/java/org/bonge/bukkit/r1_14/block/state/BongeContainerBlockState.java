package org.bonge.bukkit.r1_14.block.state;

import org.bonge.convert.InventoryConvert;
import org.bonge.util.exception.NotImplementedException;
import org.bukkit.block.Container;
import org.bukkit.inventory.Inventory;
import org.bukkit.persistence.PersistentDataContainer;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.data.Keys;

public abstract class BongeContainerBlockState<T extends org.spongepowered.api.block.entity.carrier.CarrierBlockEntity> extends BongeBlockState<T> implements Container {

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
    public @NotNull PersistentDataContainer getPersistentDataContainer() {
        throw new NotImplementedException("TileState.getPersistentDataContainer() has not been looked at");
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
