package org.bonge.bukkit.r1_13.entity.living.animal;

import org.bukkit.Location;
import org.bukkit.entity.Cow;
import org.bukkit.entity.EntityType;

public class BongeCow extends BongeAbstractAnimal<org.spongepowered.api.entity.living.animal.Cow> implements Cow {

    public BongeCow(org.spongepowered.api.entity.living.animal.Cow entity) {
        super(entity);
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
    public EntityType getType() {
        return EntityType.COW;
    }
}
