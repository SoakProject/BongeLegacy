package org.bonge.bukkit.r1_14.inventory.item.holder;

import org.bonge.bukkit.r1_14.material.BongeMaterial;
import org.spongepowered.api.data.Key;
import org.spongepowered.api.data.persistence.DataView;
import org.spongepowered.api.data.value.Value;

import java.util.Optional;
import java.util.function.Supplier;

public interface ItemHolder {

    BongeMaterial.Item getType();
    int getAmount();
    DataView toContainer();
    int getMaxStackSize();

    <V> Optional<V> get(Key<? extends Value<V>> key);
    <V> void offer(Key<? extends Value<V>> key, V value);

    ItemHolder copy();
    ItemHolder copy(int amount);

    default <V> Optional<V> get(Supplier<? extends Key<? extends Value<V>>> supplier){
        return this.get(supplier.get());
    }

    default <V> void offer(Supplier<? extends Key<? extends Value<V>>> key, V value){
        this.offer(key.get(), value);
    }
}
