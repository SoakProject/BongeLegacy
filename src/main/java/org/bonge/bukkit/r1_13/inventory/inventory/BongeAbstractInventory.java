package org.bonge.bukkit.r1_13.inventory.inventory;

import org.bonge.Bonge;
import org.bonge.convert.InventoryConvert;
import org.bonge.convert.text.TextConverter;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.spongepowered.api.item.ItemType;
import org.spongepowered.api.item.inventory.property.InventoryTitle;
import org.spongepowered.api.item.inventory.property.SlotIndex;
import org.spongepowered.api.item.inventory.query.QueryOperationTypes;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

public interface BongeAbstractInventory<I extends org.spongepowered.api.item.inventory.Inventory> extends Inventory {

    interface Swappable {

        void setHolder(InventoryHolder holder);

    }

    I getSpongeInventoryValue();

    default org.spongepowered.api.item.inventory.Inventory getSlot(int index){
        return this.getSpongeInventoryValue().query(QueryOperationTypes.INVENTORY_PROPERTY.of(SlotIndex.of(index)));
    }

    @Override
    default int getSize() {
        return this.getSpongeInventoryValue().size();
    }

    @Override
    default int getMaxStackSize() {
        return this.getSpongeInventoryValue().getMaxStackSize();
    }

    @Override
    default void setMaxStackSize(int size) {
        this.getSpongeInventoryValue().setMaxStackSize(size);
    }

    @Override
    default String getName() {
        return this.getSpongeInventoryValue().getName().get();
    }

    @Override
    default ItemStack getItem(int index) {
        org.spongepowered.api.item.inventory.ItemStack item = this.getSlot(index).peek().get();
        try {
            return Bonge.getInstance().convert(ItemStack.class, item);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    default void setItem(int index, ItemStack item) {
        org.spongepowered.api.item.inventory.ItemStack stack;
        try {
            stack = Bonge.getInstance().convert(item, org.spongepowered.api.item.inventory.ItemStack.class);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        this.getSlot(index).set(stack);
    }

    @Override
    default HashMap<Integer, ItemStack> addItem(ItemStack... items) throws IllegalArgumentException {
        for(ItemStack stack : items){
            org.spongepowered.api.item.inventory.ItemStack stack1;
            try {
                stack1 = Bonge.getInstance().convert(stack, org.spongepowered.api.item.inventory.ItemStack.class);
            } catch (IOException e) {
                continue;
            }
            this.getSpongeInventoryValue().offer(stack1);
        }
        return null;
    }

    @Override
    default HashMap<Integer, ItemStack> removeItem(ItemStack... items) throws IllegalArgumentException {
        return null;
    }

    @Override
    default ItemStack[] getContents() {
        return new ItemStack[0];
    }

    @Override
    default void setContents(ItemStack[] items) throws IllegalArgumentException {

    }

    @Override
    default ItemStack[] getStorageContents() {
        return new ItemStack[0];
    }

    @Override
    default void setStorageContents(ItemStack[] items) throws IllegalArgumentException {

    }

    @Override
    default boolean contains(Material material) throws IllegalArgumentException {
        ItemType type;
        try {
            type = Bonge.getInstance().convert(material, ItemType.class);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        return this.getSpongeInventoryValue().contains(type);
    }

    @Override
    default boolean contains(ItemStack item) {
        return false;
    }

    @Override
    default boolean contains(Material material, int amount) throws IllegalArgumentException {
        return false;
    }

    @Override
    default boolean contains(ItemStack item, int amount) {
        return false;
    }

    @Override
    default boolean containsAtLeast(ItemStack item, int amount) {
        return false;
    }

    @Override
    default HashMap<Integer, ? extends ItemStack> all(Material material) throws IllegalArgumentException {
        return null;
    }

    @Override
    default HashMap<Integer, ? extends ItemStack> all(ItemStack item) {
        return null;
    }

    @Override
    default int first(Material material) throws IllegalArgumentException {
        return 0;
    }

    @Override
    default int first(ItemStack item) {
        return 0;
    }

    @Override
    default int firstEmpty() {
        return 0;
    }

    @Override
    default void remove(Material material) throws IllegalArgumentException {

    }

    @Override
    default void remove(ItemStack item) {

    }

    @Override
    default void clear(int index) {

    }

    @Override
    default void clear() {
        this.getSpongeInventoryValue().clear();
    }

    @Override
    default List<HumanEntity> getViewers() {
        return null;
    }

    @Override
    default String getTitle() {
        Optional<InventoryTitle> opTitle = this.getSpongeInventoryValue().getInventoryProperty(InventoryTitle.class);
        if(!opTitle.isPresent()){
            return null;
        }
        return TextConverter.CONVERTER.to(opTitle.get().getValue());
    }

    @Override
    default InventoryType getType() {
        return null;
    }

    @Override
    default ListIterator<ItemStack> iterator() {
        return null;
    }

    @Override
    default ListIterator<ItemStack> iterator(int index) {
        return null;
    }

    @Override
    default Location getLocation() {
        return null;
    }
}
