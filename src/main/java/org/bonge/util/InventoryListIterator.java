package org.bonge.util;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ListIterator;

public class InventoryListIterator implements ListIterator<ItemStack> {

    private final Inventory inventory;
    private int slot;
    private Integer until;

    public InventoryListIterator(Inventory inventory){
        this.inventory = inventory;
    }

    public InventoryListIterator removeUntil(){
        this.until = null;
        return this;
    }

    public InventoryListIterator setUntil(int until){
        this.until = until;
        return this;
    }

    public int getUntil(){
        return (this.until == null) ? this.inventory.getSize() : this.until;
    }

    public Inventory getInventory(){
        return this.inventory;
    }

    @Override
    public boolean hasNext() {
        for(int A = this.slot + 1; A < this.getUntil(); A++){
            ItemStack stack = this.inventory.getItem(A);
            if(stack == null){
                continue;
            }
            return true;
        }
        return false;
    }

    @Override
    public ItemStack next() {
        for(int A = this.slot + 1; A < this.getUntil(); A++){
            ItemStack stack = this.inventory.getItem(A);
            if(stack == null){
                continue;
            }
            this.slot = A;
            return stack;
        }
        throw new IndexOutOfBoundsException();
    }

    @Override
    public boolean hasPrevious() {
        for(int A = this.slot - 1; A >= 0; A--){
            ItemStack stack = this.inventory.getItem(A);
            if(stack == null){
                continue;
            }
            return true;
        }
        return false;
    }

    @Override
    public ItemStack previous() {
        for(int A = this.slot - 1; A >= 0; A--){
            ItemStack stack = this.inventory.getItem(A);
            if(stack == null){
                continue;
            }
            this.slot = A;
            return stack;
        }
        throw new IndexOutOfBoundsException();
    }

    @Override
    public int nextIndex() {
        for(int A = this.slot + 1; A < this.getUntil(); A++){
            ItemStack stack = this.inventory.getItem(A);
            if(stack == null){
                continue;
            }
            return A;
        }
        throw new IndexOutOfBoundsException();
    }

    @Override
    public int previousIndex() {
        for(int A = this.slot - 1; A >= 0; A--){
            ItemStack stack = this.inventory.getItem(A);
            if(stack == null){
                continue;
            }
            return A;
        }
        throw new IndexOutOfBoundsException();
    }

    @Override
    public void remove() {
        this.inventory.clear(this.slot);
    }

    @Override
    public void set(ItemStack itemStack) {
        this.inventory.setItem(this.slot, itemStack);
    }

    @Override
    public void add(ItemStack itemStack) {
        this.inventory.addItem(itemStack);
    }
}
