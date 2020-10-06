package org.bonge.bukkit.r1_15.inventory.item.meta;

import org.bonge.bukkit.r1_15.inventory.item.BongeItemFactory;
import org.bonge.bukkit.r1_15.inventory.item.holder.ItemHolder;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class UnknownItemMeta extends AbstractItemMeta {

    public UnknownItemMeta(ItemHolder stack) {
        super(stack);
    }

    @Override
    public ItemMetaBuilder<? extends AbstractItemMeta> createBuilder() {
        return BongeItemFactory.UNKNOWN;
    }

    @Override
    public void setHolder(ItemHolder holder) {
        this.stack = holder;
    }

    @Override
    public ItemHolder deserialize(Map<String, Object> map) {
        return null;
    }

    @Override
    public UnknownItemMeta clone() {
        return (UnknownItemMeta)super.clone();
    }

    @Override
    public @NotNull Map<String, Object> serialize() {
        return null;
    }
}
