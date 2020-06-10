package org.bonge.bukkit.r1_13.inventory.inventory.tileentity.chest;

import org.bonge.bukkit.r1_13.block.state.states.BongeChestBlockState;
import org.bonge.bukkit.r1_13.inventory.inventory.BongeAbstractInventory;
import org.bukkit.Location;
import org.bukkit.block.Chest;
import org.bukkit.block.DoubleChest;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.spongepowered.api.item.inventory.type.TileEntityInventory;

import java.util.ListIterator;

public class BongeChestInventory implements BongeAbstractInventory<TileEntityInventory<org.spongepowered.api.block.tileentity.carrier.Chest>> {

    private InventoryHolder holder;
    private org.spongepowered.api.item.inventory.type.TileEntityInventory<org.spongepowered.api.block.tileentity.carrier.Chest> tile;


    public BongeChestInventory(InventoryHolder chest, org.spongepowered.api.item.inventory.type.TileEntityInventory<org.spongepowered.api.block.tileentity.carrier.Chest> tile){
        this.holder = chest;
        this.tile = tile;
    }

    @Override
    public TileEntityInventory<org.spongepowered.api.block.tileentity.carrier.Chest> getSpongeInventoryValue() {
        return this.tile;
    }

    @Override
    public InventoryType getType() {
        return InventoryType.CHEST;
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
        if(this.getHolder() instanceof Chest){
            return ((Chest)this.getHolder()).getLocation();
        }else if(this.getHolder() instanceof DoubleChest){
            return ((DoubleChest)this.getHolder()).getLocation();
        }
        return null;
    }
}
