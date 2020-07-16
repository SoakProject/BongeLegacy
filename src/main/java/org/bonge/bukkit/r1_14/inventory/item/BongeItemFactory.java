package org.bonge.bukkit.r1_14.inventory.item;

import org.bonge.bukkit.r1_14.inventory.item.holder.ItemHolder;
import org.bonge.bukkit.r1_14.inventory.item.meta.AbstractItemMeta;
import org.bonge.bukkit.r1_14.inventory.item.meta.ItemMetaBuilder;
import org.bonge.bukkit.r1_14.inventory.item.meta.UnknownItemMeta;
import org.bonge.bukkit.r1_14.inventory.item.meta.type.SkullItemMeta;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFactory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.item.ItemType;
import org.spongepowered.api.item.ItemTypes;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

public class BongeItemFactory implements ItemFactory {

    private static final Set<ItemMetaBuilder<? extends AbstractItemMeta>> METAS = new HashSet<>();

    public static final ItemMetaBuilder<UnknownItemMeta> UNKNOWN = register(i -> new UnknownItemMeta(i), Sponge.getRegistry().getCatalogRegistry().getAllOf(ItemType.class).toArray(new ItemType[0]));
    public static final ItemMetaBuilder<SkullItemMeta> SKULL = register(i -> new SkullItemMeta(i), ItemTypes.SKELETON_SKULL.get());

    public AbstractItemMeta createItemMeta(ItemHolder stack){
        Optional<ItemMetaBuilder<? extends AbstractItemMeta>> opMeta = METAS.stream().filter(m -> m.isAcceptable((Material)stack.getType())).findAny();
        if(opMeta.isPresent()){
            return opMeta.get().build(stack);
        }
        return new UnknownItemMeta(stack);
    }

    @Override
    public ItemMeta getItemMeta(Material material) {
        return new ItemStack(material).getItemMeta();
    }

    @Override
    public boolean isApplicable(ItemMeta meta, ItemStack stack) throws IllegalArgumentException {
        return isApplicable(meta, stack.getType());
    }

    @Override
    public boolean isApplicable(ItemMeta meta, Material material) throws IllegalArgumentException {
        return ((AbstractItemMeta)meta).createBuilder().isAcceptable(material);
    }

    @Override
    public boolean equals(ItemMeta meta1, ItemMeta meta2) throws IllegalArgumentException {
        return meta1.equals(meta2);
    }

    @Override
    public ItemMeta asMetaFor(ItemMeta meta, ItemStack stack) throws IllegalArgumentException {
        return asMetaFor(meta, stack.getType());
    }

    @Override
    public ItemMeta asMetaFor(ItemMeta meta, Material material) throws IllegalArgumentException {
        return meta;
    }

    @Override
    public Color getDefaultLeatherColor() {
        return null;
    }

    @Override
    @Deprecated
    public Material updateMaterial(ItemMeta meta, Material material) throws IllegalArgumentException {
        return material;
    }

    private static <T extends AbstractItemMeta> ItemMetaBuilder<T> register(Function<ItemHolder, T> func, ItemType... types){
        ItemMetaBuilder<T> builder = new ItemMetaBuilder<>(func, types);
        METAS.add(builder);
        return builder;
    }
}
