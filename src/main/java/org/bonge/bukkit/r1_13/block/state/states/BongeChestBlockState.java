package org.bonge.bukkit.r1_13.block.state.states;

import org.bonge.bukkit.r1_13.block.state.BongeContainerBlockState;
import org.bonge.convert.InventoryConvert;
import org.bonge.util.exception.NotImplementedException;
import org.bukkit.block.Chest;
import org.bukkit.inventory.Inventory;
import org.bukkit.loot.LootTable;
import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.api.block.tileentity.TileEntity;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.world.BlockChangeFlags;

import java.util.Optional;

public class BongeChestBlockState extends BongeContainerBlockState<org.spongepowered.api.block.tileentity.carrier.Chest> implements Chest {

    public static final String CUSTOM_NAME = "CUSTOM_NAME";
    public static final String LOOT_TABLE = "LOOT_TABLE";
    public static final String SPEED = "SEED";

    public BongeChestBlockState(org.spongepowered.api.block.tileentity.carrier.Chest value) {
        super(value);
    }

    @Override
    public Inventory getBlockInventory(){
        return this.getInventory();
    }

    @Override
    public boolean update(boolean force, boolean applyPhysics) {
        Optional<TileEntity> opTile = this.position.getTileEntity();
        if (!force && !this.position.getTileEntity().isPresent()) {
            return false;
        }else if(force){
            this.position.setBlockType(BlockTypes.CHEST, applyPhysics ? BlockChangeFlags.ALL : BlockChangeFlags.NONE);
            opTile = this.position.getTileEntity();
        }
        if(!force && !(opTile.get() instanceof Chest)){
            return false;
        }else if(force){
            this.position.setBlockType(BlockTypes.CHEST, applyPhysics ? BlockChangeFlags.ALL : BlockChangeFlags.NONE);
            opTile = this.position.getTileEntity();
        }
        org.spongepowered.api.block.tileentity.carrier.Chest chest = (org.spongepowered.api.block.tileentity.carrier.Chest)opTile.get();
        opTile.get().offer(Keys.LOCK_TOKEN, (String)this.changes.get(BongeChestBlockState.LOCK));
        return true;
    }


    @Override
    public String getCustomName() {
        return (String)this.changes.get(CUSTOM_NAME);
    }

    @Override
    public void setCustomName(String name) {
        this.changes.replace(CUSTOM_NAME, name);
    }

    @Override
    public void setLootTable(LootTable table) {
        throw new NotImplementedException("LootTable is not supported by Sponge as far as im aware");
    }

    @Override
    public LootTable getLootTable() {
        throw new NotImplementedException("LootTable is not supported by Sponge as far as im aware");
    }

    @Override
    public void setSeed(long seed) {
        throw new NotImplementedException("Chest seed is not supported by Sponge as far as im aware");

    }

    @Override
    public long getSeed() {
        throw new NotImplementedException("Chest seed is not supported by Sponge as far as im aware");

    }
}
