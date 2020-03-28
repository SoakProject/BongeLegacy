package org.bonge.bukkit.entity.living.animal;

import org.bukkit.Location;
import org.bukkit.entity.Cow;

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
}
