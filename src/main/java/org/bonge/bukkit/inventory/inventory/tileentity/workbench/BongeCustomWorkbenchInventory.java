package org.bonge.bukkit.inventory.inventory.tileentity.workbench;

import org.bonge.bukkit.inventory.inventory.BongeAbstractInventory;
import org.bonge.bukkit.world.BongeWorld;
import org.bonge.convert.InventoryConvert;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.spongepowered.api.item.inventory.Inventory;
import org.spongepowered.api.item.inventory.crafting.CraftingGridInventory;
import org.spongepowered.api.item.inventory.property.SlotIndex;
import org.spongepowered.api.item.recipe.crafting.CraftingRecipe;

import java.util.Optional;

public class BongeCustomWorkbenchInventory implements BongeAbstractInventory<org.spongepowered.api.item.inventory.Inventory>, CraftingInventory {

    private org.spongepowered.api.item.inventory.Inventory inventory;
    private InventoryHolder holder;

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
        Optional<org.spongepowered.api.item.inventory.ItemStack> opItem = inv.peek();
        if(!opItem.isPresent()){
            return null;
        }
        return InventoryConvert.getItemStack(opItem.get());
    }

    @Override
    public void setResult(ItemStack newResult) {
        Optional<org.spongepowered.api.item.inventory.ItemStack> opStack = InventoryConvert.getItemStack(newResult);
        if(opStack.isPresent()){
            this.getSlot(0).set(opStack.get());
            return;
        }

    }

    @Override
    public ItemStack[] getMatrix() {
        ItemStack[] stacks = new ItemStack[9];
        for (int A = 0; A < 9; A++){
            Optional<org.spongepowered.api.item.inventory.ItemStack> opStack = this.getSlot(A).peek();
            if(opStack.isPresent()){
                stacks[A] = InventoryConvert.getItemStack(opStack.get());
            }
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
