package org.bonge.bukkit.r1_16.entity.vehicle.minecart;

import org.bonge.bukkit.r1_16.entity.BongeAbstractEntity;
import org.bonge.util.exception.NotImplementedException;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.minecart.StorageMinecart;
import org.bukkit.inventory.Inventory;
import org.bukkit.loot.LootTable;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.api.entity.vehicle.minecart.carrier.ChestMinecart;


public class BongeMinecartChest extends BongeAbstractEntity<ChestMinecart> implements IMinecart<ChestMinecart>, StorageMinecart {

    public BongeMinecartChest(ChestMinecart entity) {
        super(entity);
    }

    @NotNull
    @Override
    public EntityType getType() {
        return EntityType.MINECART_CHEST;
    }

    @NotNull
    @Override
    public Inventory getInventory() {
        throw new NotImplementedException("Not got to yet");
    }

    @Override
    public void setLootTable(@Nullable LootTable lootTable) {
        throw new NotImplementedException("Not got to yet");

    }

    @Nullable
    @Override
    public LootTable getLootTable() {
        throw new NotImplementedException("Not got to yet");
    }

    @Override
    public void setSeed(long l) {
        throw new NotImplementedException("Not got to yet");
    }

    @Override
    public long getSeed() {
        throw new NotImplementedException("Not got to yet");
    }

    @Override
    public void setVelocity(@NotNull Vector vector) {
        IMinecart.super.setVelocity(vector);
    }

    @Override
    public @NotNull Vector getVelocity() {
        return IMinecart.super.getVelocity();
    }
}
