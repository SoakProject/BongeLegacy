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

public class BongeWorkbenchInventory implements BongeAbstractInventory<org.spongepowered.api.item.inventory.crafting.CraftingInventory>, CraftingInventory {

    private org.spongepowered.api.item.inventory.crafting.CraftingInventory inventory;
    private InventoryHolder holder;

    public BongeWorkbenchInventory(InventoryHolder holder, org.spongepowered.api.item.inventory.crafting.CraftingInventory inventory){
        this.holder = holder;
        this.inventory = inventory;
    }


    @Override
    public org.spongepowered.api.item.inventory.crafting.CraftingInventory getSpongeInventoryValue() {
        return this.inventory;
    }

    @Override
    public ItemStack getResult() {
        Optional<org.spongepowered.api.item.inventory.ItemStack> opItem = this.inventory.getResult().peek();
        if(!opItem.isPresent()){
            return null;
        }
        return InventoryConvert.getItemStack(opItem.get());
    }

    @Override
    public ItemStack[] getMatrix() {
        CraftingGridInventory grid = this.inventory.getCraftingGrid();
        ItemStack[] stacks = new ItemStack[9];
        for (Inventory inventory : grid.slots()){
            int index = inventory.getInventoryProperty(SlotIndex.class).get().getValue();
            Optional<org.spongepowered.api.item.inventory.ItemStack> opStack = inventory.peek();
            if(opStack.isPresent()){
                stacks[index] = InventoryConvert.getItemStack(opStack.get());
            }
        }
        return stacks;
    }

    @Override
    public void setResult(ItemStack newResult) {
        Optional<org.spongepowered.api.item.inventory.ItemStack> opStack = InventoryConvert.getItemStack(newResult);
        if(opStack.isPresent()){
            this.inventory.getResult().set(opStack.get());
            return;
        }

    }

    @Override
    public void setMatrix(ItemStack[] contents) {
        CraftingGridInventory grid = this.inventory.getCraftingGrid();
        for(int A = 0; A < contents.length; A++){
            Optional<org.spongepowered.api.item.inventory.ItemStack> opStack = InventoryConvert.getItemStack(contents[A]);
            if(opStack.isPresent()) {
                grid.set(SlotIndex.of(A), opStack.get());
            }
        }
    }

    @Override
    public Recipe getRecipe() {
        if(this.getHolder() == null){
            return null;
        }
        World world = null;
        if(this.getHolder() instanceof Entity){
            world = ((Entity)this.getHolder()).getWorld();
        }
        if(world == null) {
            return null;
        }
        Optional<CraftingRecipe> opRecipe = this.inventory.getRecipe(((BongeWorld)world).getSpongeValue());
        if (!opRecipe.isPresent()) {
            return null;
        }
        //TODO convert recipe
        return null;
    }

    @Override
    public InventoryHolder getHolder() {
        return this.holder;
    }
}
