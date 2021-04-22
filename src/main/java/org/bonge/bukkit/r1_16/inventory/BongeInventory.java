package org.bonge.bukkit.r1_16.inventory;

import org.bonge.bukkit.r1_16.inventory.item.holder.ItemStackHolder;
import org.bonge.bukkit.r1_16.inventory.item.holder.ItemStackSnapshotHolder;
import org.bonge.util.exception.NotImplementedException;
import org.bonge.wrapper.BongeWrapper;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.api.item.inventory.Slot;

import java.util.HashMap;
import java.util.ListIterator;
import java.util.Optional;
import java.util.function.Predicate;

public abstract class BongeInventory <I extends org.spongepowered.api.item.inventory.Inventory> extends BongeWrapper<I> implements Inventory {

    public BongeInventory(I value) {
        super(value);
    }

    protected org.spongepowered.api.item.inventory.ItemStack toSponge(ItemStack item){
        org.spongepowered.api.item.inventory.ItemStack stack;
        if(item == null){
            stack = org.spongepowered.api.item.inventory.ItemStack.empty();
        }else if(item.getItemMeta().getHolder() instanceof ItemStackHolder){
            stack = ((ItemStackHolder)item.getItemMeta().getHolder()).getSpongeValue();
        }else{
            stack = ((ItemStackSnapshotHolder)item.getItemMeta().getHolder()).getSpongeValue().createStack();
        }
        return stack;
    }

    @Override
    public int getSize() {
        return this.getSpongeValue().slots().size();
    }

    @Override
    public int getMaxStackSize() {
        throw new NotImplementedException("Inventory.getMaxStackSize() not looked at yet");
    }

    @Override
    public void setMaxStackSize(int size) {
        throw new NotImplementedException("Inventory.setMaxStackSize(int) not looked at yet");
    }

    @Override
    public @Nullable ItemStack getItem(int index) {
        Optional<org.spongepowered.api.item.inventory.ItemStack> opItem = this.spongeValue.peekAt(index);
        return opItem.map(ItemStack::new).orElse(null);
    }

    @Override
    public void setItem(int index, @Nullable ItemStack item) {
        this.spongeValue.set(index, toSponge(item));
    }

    @Override
    public @NotNull HashMap<Integer, ItemStack> addItem(@NotNull ItemStack... items) throws IllegalArgumentException {
        HashMap<Integer, ItemStack> map = new HashMap<>();
        int index = 0;
        for(int slotIndex = 0; slotIndex < this.spongeValue.slots().size(); slotIndex++){
            Optional<Slot> opSlot = this.spongeValue.slot(slotIndex);
            if(!opSlot.isPresent()){
                continue;
            }
            Slot slot = opSlot.get();
            if(slot.peek().equalTo(org.spongepowered.api.item.inventory.ItemStack.empty())){
                slot.offer(toSponge(items[index]));
                map.put(index, items[index]);
                index++;
            }
        }
        return map;
    }

    @Override
    public @NotNull HashMap<Integer, ItemStack> removeItem(@NotNull ItemStack... items) throws IllegalArgumentException {
        HashMap<Integer, ItemStack> map = new HashMap<>();
        for(ItemStack stack : items){
            int slot = first(stack);
            if(slot != -1){
                continue;
            }
            map.put(slot, stack);
        }
        return map;
    }

    @Override
    public @NotNull ItemStack[] getContents() {
        return new ItemStack[0];
    }

    @Override
    public void setContents(@NotNull ItemStack[] items) throws IllegalArgumentException {

    }

    @Override
    public @NotNull ItemStack[] getStorageContents() {
        return new ItemStack[0];
    }

    @Override
    public void setStorageContents(@NotNull ItemStack[] items) throws IllegalArgumentException {

    }

    @Override
    public boolean contains(@NotNull Material material) throws IllegalArgumentException {
        return first(material) != -1;
    }

    @Override
    public boolean contains(@Nullable ItemStack item) {
        if(item == null){
            return firstEmpty() != -1;
        }
        return first(item) != -1;
    }

    @Override
    public boolean contains(@NotNull Material material, int amount) throws IllegalArgumentException {
        return false;
    }

    @Override
    public boolean contains(@Nullable ItemStack item, int amount) {
        return false;
    }

    @Override
    public boolean containsAtLeast(@Nullable ItemStack item, int amount) {
        return false;
    }

    private HashMap<Integer, ? extends ItemStack> all(Predicate<ItemStack> predicate){
        HashMap<Integer, ItemStack> map = new HashMap<>();
        for(int slotIndex = 0; slotIndex < this.spongeValue.slots().size(); slotIndex++){
            Optional<Slot> opSlot = this.spongeValue.slot(slotIndex);
            if(!opSlot.isPresent()){
                continue;
            }
            Slot slot = opSlot.get();
            if(slot.peek().equalTo(org.spongepowered.api.item.inventory.ItemStack.empty())){
                continue;
            }
            ItemStack stack = new ItemStack(slot.peek());
            if (predicate.test(stack)){
                map.put(slotIndex, stack);
            }
        }
        return map;
    }

    @Override
    public @NotNull HashMap<Integer, ? extends ItemStack> all(@NotNull Material material) throws IllegalArgumentException {
        return all(m -> m.getType().equals(material));
    }

    @Override
    public @NotNull HashMap<Integer, ? extends ItemStack> all(@Nullable ItemStack item) {
        return all(m -> m.equals(item));
    }

    private int first(Predicate<ItemStack> predicate){
        for(int slotIndex = 0; slotIndex < this.spongeValue.slots().size(); slotIndex++){
            Optional<Slot> opSlot = this.spongeValue.slot(slotIndex);
            if(!opSlot.isPresent()){
                continue;
            }
            Slot slot = opSlot.get();
            if(slot.peek().equalTo(org.spongepowered.api.item.inventory.ItemStack.empty())){
                continue;
            }
            ItemStack stack = new ItemStack(slot.peek());
            if (predicate.test(stack)){
               return slotIndex;
            }
        }
        return -1;
    }

    @Override
    public int first(@NotNull Material material) throws IllegalArgumentException {
        return first(i -> i.getType().equals(material));
    }

    @Override
    public int first(@NotNull ItemStack item) {
        return first(i -> i.equals(item));
    }

    @Override
    public int firstEmpty() {
        for(int slotIndex = 0; slotIndex < this.spongeValue.slots().size(); slotIndex++){
            Optional<Slot> opSlot = this.spongeValue.slot(slotIndex);
            if(!opSlot.isPresent()){
                continue;
            }
            Slot slot = opSlot.get();
            if(slot.peek().equalTo(org.spongepowered.api.item.inventory.ItemStack.empty())){
                return slotIndex;
            }
        }
        return -1;
    }

    @Override
    public void remove(@NotNull Material material) throws IllegalArgumentException {
        all(material).keySet().forEach(i -> this.spongeValue.set(i, org.spongepowered.api.item.inventory.ItemStack.empty()));
    }

    @Override
    public void remove(@NotNull ItemStack item) {
        all(item).keySet().forEach(i -> this.spongeValue.set(i, org.spongepowered.api.item.inventory.ItemStack.empty()));
    }

    @Override
    public void clear(int index) {
        this.spongeValue.set(index, org.spongepowered.api.item.inventory.ItemStack.empty());
    }

    @Override
    public void clear() {
        for(Slot slot : this.spongeValue.slots()){
            slot.set(org.spongepowered.api.item.inventory.ItemStack.empty());
        }
    }

    @Override
    public @NotNull ListIterator<ItemStack> iterator() {
        throw new NotImplementedException("Inventory:iterator() Not got to yet");
    }

    @Override
    public @NotNull ListIterator<ItemStack> iterator(int index) {
        throw new NotImplementedException("Inventory:iterator(int) Not got to yet");
    }

    @Override
    public boolean isEmpty() {
        return this.spongeValue.freeCapacity() == this.spongeValue.capacity();
    }
}
