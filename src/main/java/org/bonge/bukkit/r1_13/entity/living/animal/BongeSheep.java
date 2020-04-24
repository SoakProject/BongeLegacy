package org.bonge.bukkit.r1_13.entity.living.animal;

import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.entity.Sheep;
import org.spongepowered.api.data.key.Keys;

public class BongeSheep extends BongeAbstractAnimal<org.spongepowered.api.entity.living.animal.Sheep> implements Sheep {

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
        return 0;
    }

    @Override
    public double getEyeHeight(boolean ignorePose) {
        return 0;
    }

    @Override
    public Location getEyeLocation() {
        return null;
    }

    @Override
    public DyeColor getColor() {
        return null;
    }

    @Override
    public void setColor(DyeColor color) {

    }
}
