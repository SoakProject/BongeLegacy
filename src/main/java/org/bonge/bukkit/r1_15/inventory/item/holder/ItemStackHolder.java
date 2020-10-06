package org.bonge.bukkit.r1_15.inventory.item.holder;

import org.bonge.Bonge;
import org.bonge.bukkit.r1_15.material.BongeMaterial;
import org.bonge.wrapper.BongeWrapper;
import org.spongepowered.api.data.Key;
import org.spongepowered.api.data.persistence.DataView;
import org.spongepowered.api.data.value.Value;
import org.spongepowered.api.item.inventory.ItemStack;

import java.util.Optional;

public class ItemStackHolder extends BongeWrapper<ItemStack> implements ItemHolder{

    public ItemStackHolder(ItemStack value) {
        super(value);
    }

    @Override
    public BongeMaterial.Item getType() {
        return Bonge.getInstance().convert(this.getSpongeValue().getType());
    }

    @Override
    public int getAmount() {
        return this.spongeValue.getQuantity();
    }

    @Override
    public DataView toContainer() {
        return this.getSpongeValue().toContainer();
    }

    @Override
    public int getMaxStackSize() {
        return this.spongeValue.getMaxStackQuantity();
    }

    @Override
    public <V> Optional<V> get(Key<? extends Value<V>> key) {
        return this.spongeValue.get(key);
    }

    @Override
    public <V> void offer(Key<? extends Value<V>> key, V value) {
        this.spongeValue.offer(key, value);
    }

    @Override
    public ItemStackHolder copy() {
        return new ItemStackHolder(this.spongeValue.copy());
    }

    @Override
    public ItemHolder copy(int amount) {
        return new ItemStackHolder(ItemStack.builder().from(this.spongeValue).quantity(amount).build());
    }
}
