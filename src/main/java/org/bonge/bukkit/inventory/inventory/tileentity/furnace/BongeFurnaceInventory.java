package org.bonge.bukkit.inventory.inventory.tileentity.furnace;

import org.bonge.bukkit.inventory.inventory.BongeAbstractInventory;
import org.bonge.convert.InventoryConvert;
import org.bukkit.block.Furnace;
import org.bukkit.inventory.FurnaceInventory;
import org.bukkit.inventory.ItemStack;
import org.spongepowered.api.item.inventory.Slot;
import org.spongepowered.api.item.inventory.type.TileEntityInventory;

import java.util.Optional;

public class BongeFurnaceInventory implements BongeAbstractInventory<org.spongepowered.api.item.inventory.type.TileEntityInventory<org.spongepowered.api.block.tileentity.carrier.Furnace>>, FurnaceInventory {

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
        Optional<org.spongepowered.api.item.inventory.ItemStack> opItem = this.getSlot(RESULT_SLOT).peek();
        if(opItem.isPresent()){
            return InventoryConvert.getItemStack(opItem.get());
        }
        return null;
    }

    @Override
    public ItemStack getFuel() {
        Optional<org.spongepowered.api.item.inventory.ItemStack> opItem = this.getSlot(FUEL_SLOT).peek();
        if(opItem.isPresent()){
            return InventoryConvert.getItemStack(opItem.get());
        }
        return null;
    }

    @Override
    public ItemStack getSmelting() {
        Optional<org.spongepowered.api.item.inventory.ItemStack> opItem = this.getSlot(ITEM_SLOT).peek();
        if(opItem.isPresent()){
            return InventoryConvert.getItemStack(opItem.get());
        }
        return null;
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
        //TODO this.inventory.getTileEntity();
        return this.furnace;
    }
}
