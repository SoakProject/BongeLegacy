package org.bonge.bukkit.r1_15.entity.living.animal;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Pig;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.data.Keys;

public class BongePig extends BongeAbstractAnimal<org.spongepowered.api.entity.living.animal.Pig> implements Pig {

    public BongePig(org.spongepowered.api.entity.living.animal.Pig entity) {
        super(entity);
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
    public double getEyeHeight() {
        return 0;
    }

    @Override
    public double getEyeHeight(boolean ignorePose) {
        return 0;
    }

    @Override
    public @NotNull Location getEyeLocation() {
        return null;
    }

    @Override
    public @NotNull EntityType getType() {
        return EntityType.PIG;
    }
}
