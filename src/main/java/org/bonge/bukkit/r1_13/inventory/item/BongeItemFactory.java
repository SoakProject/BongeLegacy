package org.bonge.bukkit.r1_13.inventory.item;

import org.bonge.bukkit.r1_13.inventory.item.meta.AbstractItemMeta;
import org.bonge.bukkit.r1_13.inventory.item.meta.UnknownItemMeta;
import org.bonge.bukkit.r1_13.inventory.item.meta.type.SkullItemMeta;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFactory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class BongeItemFactory implements ItemFactory {

    private static final Set<AbstractItemMeta> METAS = new HashSet<>();


    public static final SkullItemMeta SKULL = register(new SkullItemMeta());

    @Override
    public ItemMeta getItemMeta(Material material) {
        Optional<AbstractItemMeta> opMeta = METAS.stream().filter(m -> m.isAcceptableMaterial(material)).findAny();
        if(opMeta.isPresent()){
            return opMeta.get().clone();
        }
        return new UnknownItemMeta();
    }

    @Override
    public boolean isApplicable(ItemMeta meta, ItemStack stack) throws IllegalArgumentException {
        return isApplicable(meta, stack.getType());
    }

    @Override
    public boolean isApplicable(ItemMeta meta, Material material) throws IllegalArgumentException {
        return ((AbstractItemMeta)meta).isAcceptableMaterial(material);
    }

    @Override
    public boolean equals(ItemMeta meta1, ItemMeta meta2) throws IllegalArgumentException {
        return false;
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

    private static <T extends AbstractItemMeta> T register(T meta){
        METAS.add(meta);
        return meta;
    }
}
