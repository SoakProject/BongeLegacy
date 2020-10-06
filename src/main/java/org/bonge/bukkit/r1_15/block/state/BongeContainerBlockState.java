package org.bonge.bukkit.r1_15.block.state;

import org.bonge.Bonge;
import org.bonge.util.exception.NotImplementedException;
import org.bukkit.block.Container;
import org.bukkit.inventory.Inventory;
import org.bukkit.persistence.PersistentDataContainer;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.data.Keys;

import java.io.IOException;

public abstract class BongeContainerBlockState<T extends org.spongepowered.api.block.entity.carrier.CarrierBlockEntity> extends BongeBlockState<T> implements Container {

    public static final String INVENTORY = "INVENTORY";
    public static final String LOCK = "LOCK";
    public static final String INVENTORY_SNAPSHOT = "INVENTORY_SNAPSHOT";

    public BongeContainerBlockState(T value) throws IOException {
        super(value);
        this.changes.put(INVENTORY_SNAPSHOT, Bonge.getInstance().convert(value.getInventory()));
        this.changes.put(INVENTORY, Bonge.getInstance().convert(value.getInventory()));
        this.changes.put(LOCK, value.get(Keys.LOCK_TOKEN).orElse(null));
    }

    @Override
    public @NotNull PersistentDataContainer getPersistentDataContainer() {
        throw new NotImplementedException("TileState.getPersistentDataContainer() has not been looked at");
    }

    @Override
    public @NotNull Inventory getInventory() {
        return (Inventory)this.changes.get(INVENTORY);
    }

    @Override
    public @NotNull Inventory getSnapshotInventory() {
        return (Inventory)this.changes.get(INVENTORY_SNAPSHOT);
    }

    @Override
    public boolean isLocked() {
        return getLock() != null;
    }

    @Override
    public @NotNull String getLock() {
        return (String)this.changes.get(LOCK);
    }

    @Override
    public void setLock(String key) {
        this.changes.replace(LOCK, key);
    }
}
