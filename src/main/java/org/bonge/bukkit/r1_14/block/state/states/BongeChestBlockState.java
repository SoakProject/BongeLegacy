package org.bonge.bukkit.r1_14.block.state.states;

import org.bonge.bukkit.r1_14.block.state.BongeContainerBlockState;
import org.bonge.util.exception.NotImplementedException;
import org.bukkit.block.Chest;
import org.bukkit.inventory.Inventory;
import org.bukkit.loot.LootTable;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.api.block.entity.BlockEntity;
import org.spongepowered.api.data.Keys;
import org.spongepowered.api.world.BlockChangeFlags;

import java.io.IOException;
import java.util.Optional;

public class BongeChestBlockState extends BongeContainerBlockState<org.spongepowered.api.block.entity.carrier.chest.Chest> implements Chest {

    public static final String CUSTOM_NAME = "CUSTOM_NAME";
    public static final String LOOT_TABLE = "LOOT_TABLE";
    public static final String SPEED = "SEED";

    public BongeChestBlockState(org.spongepowered.api.block.entity.carrier.chest.Chest value) throws IOException {
        super(value);
    }

    @Override
    public @NotNull Inventory getBlockInventory(){
        return this.getInventory();
    }

    @Override
    public boolean update(boolean force, boolean applyPhysics) {
        Optional<? extends BlockEntity> opTile = this.position.getBlockEntity();
        if (!force && !opTile.isPresent()) {
            return false;
        }else if(force){
            this.position.setBlockType(BlockTypes.CHEST.get(), applyPhysics ? BlockChangeFlags.ALL : BlockChangeFlags.NONE);
            opTile = this.position.getBlockEntity();
        }
        if(!force && !(opTile.get() instanceof Chest)){
            return false;
        }else if(force){
            this.position.setBlockType(BlockTypes.CHEST.get(), applyPhysics ? BlockChangeFlags.ALL : BlockChangeFlags.NONE);
            opTile = this.position.getBlockEntity();
        }
        org.spongepowered.api.block.entity.carrier.chest.Chest chest = (org.spongepowered.api.block.entity.carrier.chest.Chest)opTile.get();
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
