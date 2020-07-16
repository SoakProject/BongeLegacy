package org.bonge.convert.inventory.item;

import org.bonge.Bonge;
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
        Optional<ItemType> opType = Bonge.getInstance().convertItem(value);
        if(opType.isPresent()){
            return opType.get();
        }
        throw new IOException("Material is not a ItemType");
    }

    @Override
    public Material to(ItemType value){
        return (Material)Bonge.getInstance().convert(value);
    }
}
