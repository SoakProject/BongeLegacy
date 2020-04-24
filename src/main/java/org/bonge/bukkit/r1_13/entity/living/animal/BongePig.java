package org.bonge.bukkit.r1_13.entity.living.animal;

import org.bukkit.Location;
import org.bukkit.entity.Pig;
import org.spongepowered.api.data.key.Keys;

public class BongePig extends BongeAbstractAnimal<org.spongepowered.api.entity.living.animal.Pig> implements Pig {

    public BongePig(org.spongepowered.api.entity.living.animal.Pig entity) {
        super(entity);
    }

    @Override
    public boolean hasSaddle() {
        return this.spongeValue.get(Keys.PIG_SADDLE).get();
    }

    @Override
    public void setSaddle(boolean saddled) {
        this.spongeValue.offer(Keys.PIG_SADDLE, saddled);
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
