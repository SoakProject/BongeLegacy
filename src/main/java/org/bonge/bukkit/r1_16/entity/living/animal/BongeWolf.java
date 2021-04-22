package org.bonge.bukkit.r1_16.entity.living.animal;

import org.bonge.bukkit.r1_16.entity.BongeAbstractEntity;
import org.bonge.util.exception.NotImplementedException;
import org.bukkit.DyeColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Wolf;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.data.Keys;

public class BongeWolf extends BongeAbstractEntity<org.spongepowered.api.entity.living.animal.Wolf> implements ITameable<org.spongepowered.api.entity.living.animal.Wolf>, ISittable<org.spongepowered.api.entity.living.animal.Wolf>, Wolf {

    public BongeWolf(org.spongepowered.api.entity.living.animal.Wolf entity) {
        super(entity);
    }

    @Override
    public boolean isAngry() {
        return this.spongeValue.get(Keys.IS_ANGRY).orElse(false);
    }

    @Override
    public void setAngry(boolean b) {
        this.spongeValue.offer(Keys.IS_ANGRY, b);
    }

    @NotNull
    @Override
    public DyeColor getCollarColor() {
        throw new NotImplementedException("Not looked at yet");
    }

    @Override
    public void setCollarColor(@NotNull DyeColor dyeColor) {
        throw new NotImplementedException("Not looked at yet");

    }

    @NotNull
    @Override
    public EntityType getType() {
        return EntityType.WOLF;
    }
}
