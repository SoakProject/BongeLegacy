package org.bonge.bukkit.r1_16.inventory.item;

import org.bonge.bukkit.r1_16.inventory.item.holder.ItemHolder;
import org.bonge.bukkit.r1_16.inventory.item.meta.AbstractItemMeta;
import org.bonge.bukkit.r1_16.inventory.item.meta.ItemMetaBuilder;
import org.bonge.bukkit.r1_16.inventory.item.meta.UnknownItemMeta;
import org.bonge.bukkit.r1_16.inventory.item.meta.type.SkullItemMeta;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFactory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.item.ItemType;
import org.spongepowered.api.item.ItemTypes;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Stream;

public class BongeItemFactory implements ItemFactory {

    private static final Set<ItemMetaBuilder<? extends AbstractItemMeta>> METAS = new HashSet<>();

    //public static final ItemMetaBuilder<UnknownItemMeta> UNKNOWN = register(UnknownItemMeta::new, Sponge.server().registries().registry(RegistryTypes.ITEM_TYPE).stream());
    public static final ItemMetaBuilder<SkullItemMeta> SKULL = register(SkullItemMeta::new, ItemTypes.SKELETON_SKULL.get());

    public AbstractItemMeta createItemMeta(ItemHolder stack) {
        Optional<ItemMetaBuilder<? extends AbstractItemMeta>> opMeta = METAS.stream().filter(m -> m.isAcceptable(Material.valueOf(stack.getType()))).findAny();
        if (opMeta.isPresent()) {
            return opMeta.get().build(stack);
        }
        return new UnknownItemMeta(stack);
    }

    @Override
    public ItemMeta getItemMeta(@NotNull Material material) {
        return new ItemStack(material).getItemMeta();
    }

    @Override
    public boolean isApplicable(ItemMeta meta, ItemStack stack) throws IllegalArgumentException {
        return isApplicable(meta, stack.getType());
    }

    @Override
    public boolean isApplicable(ItemMeta meta, Material material) throws IllegalArgumentException {
        return ((AbstractItemMeta) meta).createBuilder().isAcceptable(material);
    }

    @Override
    public boolean equals(@NotNull ItemMeta meta1, @NotNull ItemMeta meta2) throws IllegalArgumentException {
        return meta1.equals(meta2);
    }

    @Override
    public ItemMeta asMetaFor(@NotNull ItemMeta meta, ItemStack stack) throws IllegalArgumentException {
        return asMetaFor(meta, stack.getType());
    }

    @Override
    public ItemMeta asMetaFor(@NotNull ItemMeta meta, @NotNull Material material) throws IllegalArgumentException {
        return meta;
    }

    @Override
    public Color getDefaultLeatherColor() {
        return null;
    }

    @Override
    @Deprecated
    public Material updateMaterial(@NotNull ItemMeta meta, @NotNull Material material) throws IllegalArgumentException {
        return material;
    }

    public static <Meta extends AbstractItemMeta> ItemMetaBuilder<Meta> getMeta(ItemType type) {
        return METAS
                .parallelStream()
                .filter(m -> Arrays.asList(m.getAcceptableTypes()).contains(type))
                .findFirst().map(m -> (ItemMetaBuilder<Meta>) m)
                .orElseGet(() -> (ItemMetaBuilder<Meta>) new ItemMetaBuilder<>(UnknownItemMeta::new, type));
    }

    private static <T extends AbstractItemMeta> ItemMetaBuilder<T> register(Function<ItemHolder, T> func, Stream<ItemType> typeStream) {
        return register(func, typeStream.toArray(ItemType[]::new));
    }

    private static <T extends AbstractItemMeta> ItemMetaBuilder<T> register(Function<ItemHolder, T> func, ItemType... types) {
        ItemMetaBuilder<T> builder = new ItemMetaBuilder<>(func, types);
        METAS.add(builder);
        return builder;
    }
}
