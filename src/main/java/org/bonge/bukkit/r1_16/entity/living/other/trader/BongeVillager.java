package org.bonge.bukkit.r1_16.entity.living.other.trader;

import org.bonge.bukkit.r1_16.entity.BongeAbstractEntity;
import org.bonge.bukkit.r1_16.entity.living.IAgable;
import org.bonge.util.exception.NotImplementedException;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.MerchantRecipe;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BongeVillager extends BongeAbstractEntity<org.spongepowered.api.entity.living.trader.Villager> implements IAgable<org.spongepowered.api.entity.living.trader.Villager>, Villager {

    public BongeVillager(org.spongepowered.api.entity.living.trader.Villager entity) {
        super(entity);
    }

    @Override
    public void setAgeLock(boolean lock) {
        IAgable.super.setAgeLock(lock);
    }

    @Override
    public void setBreed(boolean breed) {
        IAgable.super.setBreed(breed);
    }

    @Override
    public boolean canBreed() {
        return IAgable.super.canBreed();
    }

    @Override
    public boolean getAgeLock() {
        return IAgable.super.getAgeLock();
    }

    @NotNull
    @Override
    public Profession getProfession() {
        throw new NotImplementedException("Not looked at");
    }

    @Override
    public void setProfession(@NotNull Villager.Profession profession) {
        throw new NotImplementedException("Not looked at");
    }

    @NotNull
    @Override
    public Type getVillagerType() {
        throw new NotImplementedException("Not looked at");
    }

    @Override
    public void setVillagerType(@NotNull Villager.Type type) {
        throw new NotImplementedException("Not looked at");
    }

    @Override
    public int getVillagerLevel() {
        throw new NotImplementedException("Not looked at");
    }

    @Override
    public void setVillagerLevel(int i) {
        throw new NotImplementedException("Not looked at");
    }

    @Override
    public int getVillagerExperience() {
        throw new NotImplementedException("Not looked at");
    }

    @Override
    public void setVillagerExperience(int i) {
        throw new NotImplementedException("Not looked at");
    }

    @Override
    public boolean sleep(@NotNull Location location) {
        throw new NotImplementedException("Not looked at");

    }

    @Override
    public void wakeup() {
        throw new NotImplementedException("Not looked at");

    }

    @NotNull
    @Override
    public Inventory getInventory() {
        throw new NotImplementedException("Not looked at");
    }

    @NotNull
    @Override
    public EntityType getType() {
        return EntityType.VILLAGER;
    }

    @NotNull
    @Override
    public List<MerchantRecipe> getRecipes() {
        throw new NotImplementedException("Not looked at");
    }

    @Override
    public void setRecipes(@NotNull List<MerchantRecipe> list) {
        throw new NotImplementedException("Not looked at");
    }

    @NotNull
    @Override
    public MerchantRecipe getRecipe(int i) throws IndexOutOfBoundsException {
        throw new NotImplementedException("Not looked at");
    }

    @Override
    public void setRecipe(int i, @NotNull MerchantRecipe merchantRecipe) throws IndexOutOfBoundsException {
        throw new NotImplementedException("Not looked at");
    }

    @Override
    public int getRecipeCount() {
        throw new NotImplementedException("Not looked at");
    }

    @Override
    public boolean isTrading() {
        throw new NotImplementedException("Not looked at");
    }

    @Nullable
    @Override
    public HumanEntity getTrader() {
        throw new NotImplementedException("Not looked at");
    }
}
