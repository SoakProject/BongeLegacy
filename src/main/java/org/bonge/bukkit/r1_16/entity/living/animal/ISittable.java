package org.bonge.bukkit.r1_16.entity.living.animal;

import org.bonge.bukkit.r1_16.entity.IEntity;
import org.bukkit.entity.Sittable;
import org.spongepowered.api.data.Keys;

public interface ISittable<S extends org.spongepowered.api.entity.living.animal.Sittable> extends IEntity<S>, Sittable {

    @Override
    default boolean isSitting() {
        return this.getSpongeValue().get(Keys.IS_SITTING).orElse(false);
    }

    @Override
    default void setSitting(boolean b) {
        this.getSpongeValue().offer(Keys.IS_SITTING, b);
    }
}
