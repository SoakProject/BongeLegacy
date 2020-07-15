package org.bonge.bukkit.r1_14.entity.living.animal;

import org.bonge.bukkit.r1_14.world.BongeLocation;
import org.bukkit.Location;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.EntityType;

public class BongeChicken extends BongeAbstractAnimal<org.spongepowered.api.entity.living.animal.Chicken> implements Chicken {

    public BongeChicken(org.spongepowered.api.entity.living.animal.Chicken entity) {
        super(entity);
    }

    @Override
    public double getEyeHeight() {
        return 1;
    }

    @Override
    public double getEyeHeight(boolean ignorePose) {
        return 1;
    }

    @Override
    public Location getEyeLocation() {
        return new BongeLocation(getLocation()).add(0, 1, 0);
    }

    @Override
    public EntityType getType() {
        return EntityType.CHICKEN;
    }
}
