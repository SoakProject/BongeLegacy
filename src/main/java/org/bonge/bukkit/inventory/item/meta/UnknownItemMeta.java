package org.bonge.bukkit.inventory.item.meta;

import org.bukkit.Material;

public class UnknownItemMeta extends AbstractItemMeta {

    public boolean isAcceptableMaterial(Material material){
        return true;
    }

    @Override
    public UnknownItemMeta clone() {
        UnknownItemMeta meta = new UnknownItemMeta();
        meta.displayName = this.displayName;
        meta.lore.addAll(this.lore);
        return meta;
    }
}
