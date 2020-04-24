package org.bonge.bukkit.r1_13.inventory.inventory.tileentity.workbench;

import org.bonge.bukkit.r1_13.inventory.inventory.BongeAbstractInventory;
import org.bonge.convert.InventoryConvert;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.spongepowered.api.item.inventory.Inventory;

import java.util.Optional;

public class BongeCustomWorkbenchInventory implements BongeAbstractInventory<Inventory>, CraftingInventory {

    private final org.spongepowered.api.item.inventory.Inventory inventory;
    private final InventoryHolder holder;

    public BongeCustomWorkbenchInventory(InventoryHolder holder, org.spongepowered.api.item.inventory.Inventory inventory){
        this.holder = holder;
        this.inventory = inventory;
    }


    @Override
    public org.spongepowered.api.item.inventory.Inventory getSpongeInventoryValue() {
        return this.inventory;
    }

    @Override
    public ItemStack getResult() {
        Inventory inv = this.getSlot(0);
        org.spongepowered.api.item.inventory.ItemStack item = inv.peek().get();
        return InventoryConvert.getItemStack(item);
    }

    @Override
    public void setResult(ItemStack newResult) {
        Optional<org.spongepowered.api.item.inventory.ItemStack> opStack = InventoryConvert.getItemStack(newResult);
        opStack.ifPresent(itemStack -> this.getSlot(0).set(itemStack));
    }

    @Override
    public ItemStack[] getMatrix() {
        ItemStack[] stacks = new ItemStack[9];
        for (int A = 0; A < 9; A++){
            stacks[A] = InventoryConvert.getItemStack(this.getSlot(A).peek().get());
        }
        return stacks;
    }

    @Override
    public void setMatrix(ItemStack[] contents) {
        for(int A = 0; A < contents.length; A++){
            Optional<org.spongepowered.api.item.inventory.ItemStack> opStack = InventoryConvert.getItemStack(contents[A]);
            if(opStack.isPresent()) {
                this.getSlot(A).set(opStack.get());
            }
        }
    }

    @Override
    public Recipe getRecipe() {
        return null;
    }

    @Override
    public InventoryHolder getHolder() {
        return this.holder;
    }
}
