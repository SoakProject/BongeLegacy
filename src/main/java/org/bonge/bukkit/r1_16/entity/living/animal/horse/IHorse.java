package org.bonge.bukkit.r1_16.entity.living.animal.horse;

import org.bonge.bukkit.r1_16.entity.living.animal.IAnimal;
import org.bonge.util.exception.NotImplementedException;
import org.bukkit.entity.AbstractHorse;
import org.bukkit.entity.AnimalTamer;
import org.bukkit.entity.Horse;
import org.bukkit.inventory.AbstractHorseInventory;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.api.entity.living.animal.horse.HorseLike;

public interface IHorse<H extends HorseLike> extends IAnimal<H>, AbstractHorse {

    @Override
    default void setVelocity(@NotNull Vector vector) {
        IAnimal.super.setVelocity(vector);
    }

    @Override
    default @NotNull Vector getVelocity() {
        return IAnimal.super.getVelocity();
    }

    @NotNull
    @Override
    @Deprecated
    default Horse.Variant getVariant() {
        throw new NotImplementedException("BongeAbstractHorse.getVariant() - Deprecated code");
    }

    @Override
    @Deprecated
    default void setVariant(Horse.Variant variant) {
        throw new NotImplementedException("BongeAbstractHorse.setVariant(Horse.Variant) - Deprecated code");
    }

    @Override
    default int getDomestication() {
        throw new NotImplementedException("BongeAbstractHorse.getDomestication() - Not looked at yet");
    }

    @Override
    default void setDomestication(int i) {
        throw new NotImplementedException("Not looked at yet");
    }

    @Override
    default int getMaxDomestication() {
        throw new NotImplementedException("Not looked at yet");

    }

    @Override
    default void setMaxDomestication(int i) {
        throw new NotImplementedException("Not looked at yet");

    }

    @Override
    default double getJumpStrength() {
        throw new NotImplementedException("Not looked at yet");
    }

    @Override
    default void setJumpStrength(double v) {
        throw new NotImplementedException("Not looked at yet");

    }

    @NotNull
    @Override
    default AbstractHorseInventory getInventory() {
        throw new NotImplementedException("Not looked at yet");
    }

    @Override
    default boolean isTamed() {
        //OVERRIDE IF TAMABLE
        return false;
    }

    @Override
    default void setTamed(boolean b) {
        //OVERRIDE IF TAMABLE
    }

    @Nullable
    @Override
    default AnimalTamer getOwner() {
        //OVERRIDE IF TAMABLE
        return null;
    }

    @Override
    default void setOwner(@Nullable AnimalTamer animalTamer) {
        //OVERRIDE IF TAMABLE
    }
}
