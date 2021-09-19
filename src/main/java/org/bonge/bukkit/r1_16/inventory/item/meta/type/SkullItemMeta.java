package org.bonge.bukkit.r1_16.inventory.item.meta.type;

import org.bonge.bukkit.r1_16.inventory.item.BongeItemFactory;
import org.bonge.bukkit.r1_16.inventory.item.holder.ItemHolder;
import org.bonge.bukkit.r1_16.inventory.item.meta.AbstractItemMeta;
import org.bonge.bukkit.r1_16.inventory.item.meta.ItemMetaBuilder;
import org.bonge.util.exception.NotImplementedException;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.meta.SkullMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class SkullItemMeta extends AbstractItemMeta implements SkullMeta {

    public SkullItemMeta(ItemHolder holder){
        super(holder);
    }

    @Override
    public ItemMetaBuilder<? extends AbstractItemMeta> createBuilder() {
        return BongeItemFactory.SKULL;
    }

    @Override
    public ItemHolder deserialize(Map<String, Object> map) {
        return null;
    }

    @Override
    public void setHolder(ItemHolder holder) {
        if (!this.createBuilder().isAcceptable(Material.valueOf(holder.getType()))){
            throw new IllegalArgumentException("Invalid material");
        }
        this.stack = holder;
    }

    @Override
    public @Nullable String getOwner() {
        OfflinePlayer player = this.getOwningPlayer();
        if(player == null){
            return null;
        }
        return player.getName();
    }

    @Override
    public boolean hasOwner() {
        return this.getOwningPlayer() != null;
    }

    @Override
    @Deprecated
    public boolean setOwner(@Nullable String owner) {
        throw new NotImplementedException("SkullItemMeta.setOwner(String) not looked at yet");
    }

    @Override
    public @Nullable OfflinePlayer getOwningPlayer() {
        throw new NotImplementedException("SkullItemMeta.getOwningPlayer() not looked at yet");
    }

    @Override
    public boolean setOwningPlayer(@Nullable OfflinePlayer owner) {
        throw new NotImplementedException("SkullItemMeta.setOwningPlayer(OfflinePlayer) not looked at yet");
    }

    @Override
    public @NotNull Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();
        return map;
    }

    @Override
    public @NotNull SkullItemMeta clone(){
        return (SkullItemMeta) super.clone();
    }
}
