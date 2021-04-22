package org.bonge.bukkit.r1_16.entity.living.animal;

import org.bonge.bukkit.r1_16.entity.BongeAbstractEntity;
import org.bonge.util.exception.NotImplementedException;
import org.bukkit.entity.AnimalTamer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fox;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BongeFox extends BongeAbstractEntity<org.spongepowered.api.entity.living.animal.Fox> implements IAnimal<org.spongepowered.api.entity.living.animal.Fox> /*, ISittable<org.spongepowered.api.entity.living.animal.Fox>*/, Fox {

    public BongeFox(org.spongepowered.api.entity.living.animal.Fox entity) {
        super(entity);
    }

    @NotNull
    @Override
    public Type getFoxType() {
        throw new NotImplementedException("Not got to yet");
    }

    @Override
    public void setFoxType(@NotNull Fox.Type type) {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    public boolean isCrouching() {
        throw new NotImplementedException("Not got to yet");
    }

    @Override
    public void setCrouching(boolean b) {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    public void setSleeping(boolean b) {
        throw new NotImplementedException("Not got to yet");

    }

    @Nullable
    @Override
    public AnimalTamer getFirstTrustedPlayer() {
        throw new NotImplementedException("Not got to yet");
    }

    @Override
    public void setFirstTrustedPlayer(@Nullable AnimalTamer animalTamer) {
        throw new NotImplementedException("Not got to yet");

    }

    @Nullable
    @Override
    public AnimalTamer getSecondTrustedPlayer() {
        throw new NotImplementedException("Not got to yet");
    }

    @Override
    public void setSecondTrustedPlayer(@Nullable AnimalTamer animalTamer) {
        throw new NotImplementedException("Not got to yet");

    }

    @NotNull
    @Override
    public EntityType getType() {
        return EntityType.FOX;
    }

    @Override
    public boolean isSitting() {
        throw new NotImplementedException("Not got to yet");
    }

    @Override
    public void setSitting(boolean b) {
        throw new NotImplementedException("Not got to yet");
    }
}
