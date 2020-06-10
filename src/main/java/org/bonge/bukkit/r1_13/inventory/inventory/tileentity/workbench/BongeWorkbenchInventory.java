package org.bonge.bukkit.r1_13.inventory.inventory.tileentity.workbench;

import org.bonge.Bonge;
import org.bonge.bukkit.r1_13.inventory.inventory.BongeAbstractInventory;
import org.bonge.bukkit.r1_13.world.BongeWorld;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.spongepowered.api.item.recipe.crafting.CraftingRecipe;

import java.io.IOException;
import java.util.ListIterator;
import java.util.Optional;

public class BongeWorkbenchInventory implements BongeAbstractInventory<org.spongepowered.api.item.inventory.crafting.CraftingInventory>, CraftingInventory {

    private final org.spongepowered.api.item.inventory.crafting.CraftingInventory inventory;
    private final InventoryHolder holder;

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
        org.spongepowered.api.item.inventory.ItemStack item = this.inventory.getResult().peek().get();
        try {
            return Bonge.getInstance().convert(ItemStack.class, item);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }


    @Override
    public void setResult(ItemStack newResult) {
        org.spongepowered.api.item.inventory.ItemStack itemStack;
        try {
            itemStack = Bonge.getInstance().convert(newResult, org.spongepowered.api.item.inventory.ItemStack.class);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        this.getSlot(0).set(itemStack);
    }

    @Override
    public ItemStack[] getMatrix() {
        ItemStack[] stacks = new ItemStack[9];
        for (int A = 0; A < 9; A++){
            try {
                stacks[A] = Bonge.getInstance().convert(ItemStack.class, this.getSlot(A).peek().get());
            } catch (IOException e) {
                continue;
            }
        }
        return stacks;
    }

    @Override
    public void setMatrix(ItemStack[] contents) {
        for(int A = 0; A < contents.length; A++){
            org.spongepowered.api.item.inventory.ItemStack itemStack;
            try {
                itemStack = Bonge.getInstance().convert(contents[A], org.spongepowered.api.item.inventory.ItemStack.class);
            } catch (IOException e) {
                continue;
            }
            this.getSlot(A).set(itemStack);
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
    public InventoryType getType() {
        return InventoryType.WORKBENCH;
    }

    @Override
    public InventoryHolder getHolder() {
        return this.holder;
    }

    @Override
    public ListIterator<ItemStack> iterator() {
        return null;
    }

    @Override
    public ListIterator<ItemStack> iterator(int index) {
        return null;
    }

    @Override
    public Location getLocation() {
        return null;
    }
}
