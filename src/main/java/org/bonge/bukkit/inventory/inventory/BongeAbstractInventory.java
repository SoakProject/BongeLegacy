package org.bonge.bukkit.inventory.inventory;

import org.bonge.convert.InventoryConvert;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.spongepowered.api.item.ItemType;
import org.spongepowered.api.item.inventory.Slot;
import org.spongepowered.api.item.inventory.property.SlotIndex;
import org.spongepowered.api.item.inventory.query.QueryOperationTypes;

import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

public interface BongeAbstractInventory<I extends org.spongepowered.api.item.inventory.Inventory> extends Inventory {
    
    I getSpongeInventoryValue();

    default Slot getSlot(int index){
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
        Optional<org.spongepowered.api.item.inventory.ItemStack> opItem = this.getSlot(index).peek();
        return opItem.map(InventoryConvert::getItemStack).orElse(null);
    }

    @Override
    default void setItem(int index, ItemStack item) {
        Optional<org.spongepowered.api.item.inventory.ItemStack> opStack = InventoryConvert.getItemStack(item);
        if(!opStack.isPresent()){
            return;
        }
        this.getSlot(index).set(opStack.get());
    }

    @Override
    default HashMap<Integer, ItemStack> addItem(ItemStack... items) throws IllegalArgumentException {
        for(ItemStack stack : items){
            Optional<org.spongepowered.api.item.inventory.ItemStack> opItem = InventoryConvert.getItemStack(stack);
            if(!opItem.isPresent()){
                continue;
            }
            this.getSpongeInventoryValue().offer(opItem.get());
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
        if(!material.getSpongeValue().isPresent()){
            return false;
        }
        ItemType type = (ItemType) material.getSpongeValue().get();
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
        return null;
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
