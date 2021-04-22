package org.bonge.bukkit.r1_16.entity.living.animal;

import org.bonge.bukkit.r1_16.entity.BongeAbstractEntity;
import org.bonge.util.exception.NotImplementedException;
import org.bukkit.Location;
import org.bukkit.entity.Cow;
import org.bukkit.entity.EntityType;
import org.jetbrains.annotations.NotNull;

public class BongeCow extends BongeAbstractEntity<org.spongepowered.api.entity.living.animal.cow.Cow> implements IAnimal<org.spongepowered.api.entity.living.animal.cow.Cow>, Cow {

    public BongeCow(org.spongepowered.api.entity.living.animal.cow.Cow entity) {
        super(entity);
    }

    @Override
    public double getEyeHeight() {
        throw new NotImplementedException("Not got to yet");
    }

    @Override
    public double getEyeHeight(boolean ignorePose) {
        throw new NotImplementedException("Not got to yet");
    }

    @Override
    public @NotNull Location getEyeLocation() {
        throw new NotImplementedException("Not got to yet");
    }

    @Override
    public EntityType getType() {
        return EntityType.COW;
    }
}
