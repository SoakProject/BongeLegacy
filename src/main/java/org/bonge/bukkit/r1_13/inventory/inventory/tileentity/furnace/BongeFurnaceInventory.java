package org.bonge.bukkit.r1_13.inventory.inventory.tileentity.furnace;

import org.bonge.bukkit.r1_13.inventory.inventory.BongeAbstractInventory;
import org.bonge.convert.InventoryConvert;
import org.bukkit.block.Furnace;
import org.bukkit.inventory.FurnaceInventory;
import org.bukkit.inventory.ItemStack;
import org.spongepowered.api.item.inventory.type.TileEntityInventory;

import java.util.Optional;

public class BongeFurnaceInventory implements BongeAbstractInventory<TileEntityInventory<org.spongepowered.api.block.tileentity.carrier.Furnace>>, FurnaceInventory {

    public static final int RESULT_SLOT = 2;
    public static final int ITEM_SLOT = 1;
    public static final int FUEL_SLOT = 0;

    private org.spongepowered.api.item.inventory.type.TileEntityInventory<org.spongepowered.api.block.tileentity.carrier.Furnace> inventory;
    private Furnace furnace;

    public BongeFurnaceInventory(Furnace fun, org.spongepowered.api.item.inventory.type.TileEntityInventory<org.spongepowered.api.block.tileentity.carrier.Furnace> inventory){
        this.inventory = inventory;
        this.furnace = fun;
    }

    @Override
    public TileEntityInventory<org.spongepowered.api.block.tileentity.carrier.Furnace> getSpongeInventoryValue() {
        return this.inventory;
    }

    @Override
    public ItemStack getResult() {
        org.spongepowered.api.item.inventory.ItemStack item = this.getSlot(RESULT_SLOT).peek().get();
        return InventoryConvert.getItemStack(item);
    }

    @Override
    public ItemStack getFuel() {
        org.spongepowered.api.item.inventory.ItemStack item = this.getSlot(FUEL_SLOT).peek().get();
        return InventoryConvert.getItemStack(item);
    }

    @Override
    public ItemStack getSmelting() {
        org.spongepowered.api.item.inventory.ItemStack item = this.getSlot(ITEM_SLOT).peek().get();
        return InventoryConvert.getItemStack(item);
    }

    @Override
    public void setFuel(ItemStack stack) {
        Optional<org.spongepowered.api.item.inventory.ItemStack> opItem = InventoryConvert.getItemStack(stack);
        if(opItem.isPresent()){
            this.getSlot(FUEL_SLOT).set(opItem.get());
        }
    }

    @Override
    public void setResult(ItemStack stack) {
        Optional<org.spongepowered.api.item.inventory.ItemStack> opItem = InventoryConvert.getItemStack(stack);
        if(opItem.isPresent()){
            this.getSlot(RESULT_SLOT).set(opItem.get());
        }
    }

    @Override
    public void setSmelting(ItemStack stack) {
        Optional<org.spongepowered.api.item.inventory.ItemStack> opItem = InventoryConvert.getItemStack(stack);
        if(opItem.isPresent()){
            this.getSlot(FUEL_SLOT).set(opItem.get());
        }
    }

    @Override
    public Furnace getHolder() {
        return this.furnace;
    }
}
