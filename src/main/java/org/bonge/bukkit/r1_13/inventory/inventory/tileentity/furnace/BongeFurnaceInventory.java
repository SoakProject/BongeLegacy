package org.bonge.bukkit.r1_13.inventory.inventory.tileentity.furnace;

import org.bonge.Bonge;
import org.bonge.bukkit.r1_13.inventory.inventory.BongeAbstractInventory;
import org.bukkit.Location;
import org.bukkit.block.Furnace;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.FurnaceInventory;
import org.bukkit.inventory.ItemStack;
import org.spongepowered.api.item.inventory.type.TileEntityInventory;

import java.io.IOException;
import java.util.ListIterator;

public class BongeFurnaceInventory implements BongeAbstractInventory<TileEntityInventory<org.spongepowered.api.block.tileentity.carrier.Furnace>>, FurnaceInventory {

    public static final int RESULT_SLOT = 2;
    public static final int ITEM_SLOT = 1;
    public static final int FUEL_SLOT = 0;

    private final org.spongepowered.api.item.inventory.type.TileEntityInventory<org.spongepowered.api.block.tileentity.carrier.Furnace> inventory;
    private final Furnace furnace;

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
        try {
            return Bonge.getInstance().convert(ItemStack.class, item);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public ItemStack getFuel() {
        org.spongepowered.api.item.inventory.ItemStack item = this.getSlot(FUEL_SLOT).peek().get();
        try {
            return Bonge.getInstance().convert(ItemStack.class, item);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public ItemStack getSmelting() {
        org.spongepowered.api.item.inventory.ItemStack item = this.getSlot(ITEM_SLOT).peek().get();
        try {
            return Bonge.getInstance().convert(ItemStack.class, item);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void setFuel(ItemStack stack) {
        org.spongepowered.api.item.inventory.ItemStack is;
        try {
            is = Bonge.getInstance().convert(stack, org.spongepowered.api.item.inventory.ItemStack.class);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        this.getSlot(FUEL_SLOT).set(is);
    }

    @Override
    public void setResult(ItemStack stack) {
        org.spongepowered.api.item.inventory.ItemStack is;
        try {
            is = Bonge.getInstance().convert(stack, org.spongepowered.api.item.inventory.ItemStack.class);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        this.getSlot(RESULT_SLOT).set(is);
    }

    @Override
    public void setSmelting(ItemStack stack) {
        org.spongepowered.api.item.inventory.ItemStack is;
        try {
            is = Bonge.getInstance().convert(stack, org.spongepowered.api.item.inventory.ItemStack.class);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        this.getSlot(ITEM_SLOT).set(is);
    }

    @Override
    public InventoryType getType() {
        return InventoryType.FURNACE;
    }

    @Override
    public Furnace getHolder() {
        return this.furnace;
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
        return this.getHolder().getLocation();
    }
}
