package org.bonge.convert.inventory.item;

import org.bonge.Bonge;
import org.bonge.bukkit.r1_13.material.ItemMaterial;
import org.bonge.convert.Converter;
import org.bukkit.Material;
import org.spongepowered.api.item.ItemType;

import java.util.Optional;

public class ItemTypeConverter implements Converter<Material, ItemType> {
    @Override
    public Class<ItemType> getSpongeClass() {
        return ItemType.class;
    }

    @Override
    public Class<Material> getBukkitClass() {
        return Material.class;
    }

    @Override
    public ItemType from(Material value) {
        Optional<Material> opType = Bonge.getInstance().getMaterials().stream().filter(t -> t instanceof ItemMaterial).filter(t -> ((ItemMaterial)t).getSpongeValue().equals(value)).findAny();
        if(opType.isPresent()){
            return ((ItemMaterial)opType.get()).getSpongeValue();
        }
        throw new IllegalArgumentException("Unknown material converter");
    }

    @Override
    public Material to(ItemType value) {
        Optional<Material> opType = Bonge.getInstance().getMaterials().stream().filter(t -> t instanceof ItemMaterial).filter(t -> ((ItemMaterial)t).getSpongeValue().equals(value)).findAny();
        if(opType.isPresent()){
            return opType.get();
        }
        throw new IllegalArgumentException("Unknown material converter");
    }
}
