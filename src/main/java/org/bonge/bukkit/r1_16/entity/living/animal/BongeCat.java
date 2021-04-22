package org.bonge.bukkit.r1_16.entity.living.animal;

import org.bonge.bukkit.r1_16.entity.BongeAbstractEntity;
import org.bonge.util.exception.NotImplementedException;
import org.bukkit.DyeColor;
import org.bukkit.entity.Cat;
import org.bukkit.entity.EntityType;
import org.jetbrains.annotations.NotNull;

public class BongeCat extends BongeAbstractEntity<org.spongepowered.api.entity.living.animal.Cat> implements ITameable<org.spongepowered.api.entity.living.animal.Cat>, ISittable<org.spongepowered.api.entity.living.animal.Cat>, Cat {

    public BongeCat(org.spongepowered.api.entity.living.animal.Cat entity) {
        super(entity);
    }

    @NotNull
    @Override
    public Type getCatType() {
        throw new NotImplementedException("Not got to yet");
    }

    @Override
    public void setCatType(@NotNull Cat.Type type) {
        throw new NotImplementedException("Not got to yet");

    }

    @NotNull
    @Override
    public DyeColor getCollarColor() {
        throw new NotImplementedException("Not got to yet");
    }

    @Override
    public void setCollarColor(@NotNull DyeColor dyeColor) {
        throw new NotImplementedException("Not got to yet");
    }

    @NotNull
    @Override
    public EntityType getType() {
        return EntityType.CAT;
    }
}
