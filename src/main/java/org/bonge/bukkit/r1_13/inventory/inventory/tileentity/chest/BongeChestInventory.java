package org.bonge.bukkit.r1_13.inventory.inventory.tileentity.chest;

import org.bonge.bukkit.r1_13.block.state.states.BongeChestBlockState;
import org.bonge.bukkit.r1_13.inventory.inventory.BongeAbstractInventory;
import org.bukkit.block.Chest;
import org.bukkit.inventory.InventoryHolder;
import org.spongepowered.api.item.inventory.type.TileEntityInventory;

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
    public InventoryHolder getHolder() {
        return this.holder;
    }
}
