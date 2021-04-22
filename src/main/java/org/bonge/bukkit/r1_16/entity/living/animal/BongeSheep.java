package org.bonge.bukkit.r1_16.entity.living.animal;

import org.bonge.bukkit.r1_16.entity.BongeAbstractEntity;
import org.bonge.util.exception.NotImplementedException;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Sheep;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.data.Keys;

public class BongeSheep extends BongeAbstractEntity<org.spongepowered.api.entity.living.animal.Sheep> implements IAnimal<org.spongepowered.api.entity.living.animal.Sheep>, Sheep {

    public BongeSheep(org.spongepowered.api.entity.living.animal.Sheep entity) {
        super(entity);
    }

    @Override
    public boolean isSheared() {
        return this.spongeValue.get(Keys.IS_SHEARED).get();
    }

    @Override
    public void setSheared(boolean flag) {
        this.spongeValue.offer(Keys.IS_SHEARED, flag);
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
    public DyeColor getColor() {
        throw new NotImplementedException("yet to look at");

    }

    @Override
    public void setColor(DyeColor color) {
        throw new NotImplementedException("yet to look at");

    }

    @Override
    public EntityType getType() {
        return EntityType.SHEEP;
    }
}
