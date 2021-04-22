package org.bonge.bukkit.r1_16.inventory.item.meta;

import org.bonge.Bonge;
import org.bonge.bukkit.r1_16.inventory.item.holder.ItemHolder;
import org.bukkit.Material;
import org.spongepowered.api.item.ItemType;

import java.util.Optional;
import java.util.function.Function;

public class ItemMetaBuilder<T extends AbstractItemMeta> {

    private final ItemType[] acceptable;
    private Function<ItemHolder, T> function;

    public ItemMetaBuilder(Function<ItemHolder, T> function, ItemType... types){
        this.acceptable = types;
    }

    public ItemType[] getAcceptableTypes(){
        return this.acceptable;
    }

    public boolean isAcceptable(ItemType type){
        for(ItemType type1 : this.acceptable){
            if(type1.equals(type)){
                return true;
            }
        }
        return false;
    }

    public boolean isAcceptable(Material material){
        Optional<ItemType> opItem = Bonge.getInstance().convertItem(material);
        return opItem.filter(this::isAcceptable).isPresent();
    }

    public T build(ItemHolder stack){
        if(!isAcceptable(Material.valueOf(stack.getType()))){
            throw new IllegalArgumentException("ItemMetaBuilder does not accept that type");
        }
        return this.function.apply(stack);
    }
}
