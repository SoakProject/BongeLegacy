package org.bonge.convert.inventory.item;

import org.bonge.Bonge;
import org.bonge.bukkit.r1_13.material.BongeMaterial;
import org.bonge.convert.Converter;
import org.bukkit.Material;
import org.spongepowered.api.item.ItemType;

import java.io.IOException;
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
    public ItemType from(Material value) throws IOException {
        Optional<Material> opType = Bonge.getInstance().getMaterials().stream()
                .filter(t -> (t instanceof BongeMaterial))
                .filter(Material::isItem)
                .filter(t -> ((BongeMaterial)t).toItem().get().equals(value))
                .findAny();
        if(opType.isPresent()){
            return ((BongeMaterial)opType.get()).toItem().get().getSpongeItemType();
        }
        throw new IOException("Unknown material converter for " + value.name());
    }

    @Override
    public Material to(ItemType value) throws IOException{
        Optional<Material> opType = Bonge.getInstance().getMaterials().stream()
                .filter(t -> (t instanceof BongeMaterial))
                .filter(Material::isItem)
                .filter(t -> ((BongeMaterial) t).toItem().get().getSpongeItemType().equals(value))
                .findAny();
        if(opType.isPresent()){
            return opType.get();
        }
        throw new IOException("Unknown material converter for " + value.getId());
    }
}
