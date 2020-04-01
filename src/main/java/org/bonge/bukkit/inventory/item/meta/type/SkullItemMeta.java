package org.bonge.bukkit.inventory.item.meta.type;

import org.bonge.bukkit.inventory.item.meta.AbstractItemMeta;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.Skull;
import org.bukkit.inventory.meta.SkullMeta;

public class SkullItemMeta extends AbstractItemMeta implements SkullMeta {

    private OfflinePlayer player;

    public SkullItemMeta(){
        super(Material.PLAYER_HEAD);
    }

    @Override
    @Deprecated
    public String getOwner() {
        return null;
    }

    @Override
    public boolean hasOwner() {
        return this.player != null;
    }

    @Override
    @Deprecated
    public boolean setOwner(String owner) {
        return false;
    }

    @Override
    public OfflinePlayer getOwningPlayer() {
        return this.player;
    }

    @Override
    public boolean setOwningPlayer(OfflinePlayer owner) {
        this.player = owner;
        return true;
    }

    @Override
    public SkullItemMeta clone() {
        SkullItemMeta meta = new SkullItemMeta();
        meta.player = this.player;
        meta.displayName = this.displayName;
        meta.lore.addAll(this.lore);
        return meta;
    }
}
