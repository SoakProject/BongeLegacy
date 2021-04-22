package org.bonge.bukkit.r1_16.inventory.item.holder;

import org.bonge.Bonge;
import org.bonge.bukkit.r1_16.material.BongeMaterial;
import org.bonge.wrapper.BongeWrapper;
import org.spongepowered.api.data.Key;
import org.spongepowered.api.data.persistence.DataView;
import org.spongepowered.api.data.value.Value;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.item.inventory.ItemStackSnapshot;

import java.util.Optional;

public class ItemStackSnapshotHolder extends BongeWrapper<ItemStackSnapshot> implements ItemHolder {

    public ItemStackSnapshotHolder(ItemStackSnapshot value) {
        super(value);
    }

    @Override
    public BongeMaterial.Item getType() {
        return Bonge.getInstance().convert(this.spongeValue.type());
    }

    @Override
    public int getAmount() {
        return this.spongeValue.quantity();
    }

    @Override
    public DataView toContainer() {
        return this.getSpongeValue().toContainer();
    }

    @Override
    public int getMaxStackSize() {
        return this.spongeValue.createStack().maxStackQuantity();
    }

    @Override
    public <V> Optional<V> get(Key<? extends Value<V>> key) {
        return this.spongeValue.get(key);
    }

    @Override
    public <V> void offer(Key<? extends Value<V>> key, V value) {
        this.spongeValue.with(key, value).ifPresent(s -> this.spongeValue = s);
    }

    @Override
    public ItemStackSnapshotHolder copy() {
        return new ItemStackSnapshotHolder(this.spongeValue.copy());
    }

    @Override
    public ItemHolder copy(int amount) {
        return new ItemStackSnapshotHolder(ItemStack.builder().fromSnapshot(this.spongeValue).quantity(amount).build().createSnapshot());
    }
}
