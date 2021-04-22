package org.bonge.bukkit.r1_16.entity.living.animal;

import org.bonge.bukkit.r1_16.entity.BongeAbstractEntity;
import org.bonge.util.exception.NotImplementedException;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Pig;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.data.Keys;

public class BongePig extends BongeAbstractEntity<org.spongepowered.api.entity.living.animal.Pig> implements IAnimal<org.spongepowered.api.entity.living.animal.Pig>, Pig {

    public BongePig(org.spongepowered.api.entity.living.animal.Pig entity) {
        super(entity);
    }

    @Override
    public @NotNull Vector getVelocity() {
        return super.getVelocity();
    }

    @Override
    public void setVelocity(@NotNull Vector vector) {
        super.setVelocity(vector);
    }

    @Override
    public boolean hasSaddle() {
        return this.spongeValue.get(Keys.IS_SADDLED).get();
    }

    @Override
    public void setSaddle(boolean saddled) {
        this.spongeValue.offer(Keys.IS_SADDLED, saddled);
    }

    @Override
    public int getBoostTicks() {
        throw new NotImplementedException("yet to look at");
    }

    @Override
    public void setBoostTicks(int ticks) {
        throw new NotImplementedException("yet to look at");

    }

    @Override
    public int getCurrentBoostTicks() {
        throw new NotImplementedException("yet to look at");
    }

    @Override
    public void setCurrentBoostTicks(int ticks) {
        throw new NotImplementedException("yet to look at");

    }

    @NotNull
    @Override
    public Material getSteerMaterial() {
        throw new NotImplementedException("yet to look at");
    }

    @Override
    public double getEyeHeight() {
        throw new NotImplementedException("yet to look at");

    }

    @Override
    public double getEyeHeight(boolean ignorePose) {
        throw new NotImplementedException("yet to look at");

    }

    @Override
    public @NotNull Location getEyeLocation() {
        throw new NotImplementedException("yet to look at");

    }

    @Override
    public @NotNull EntityType getType() {
        return EntityType.PIG;
    }
}
