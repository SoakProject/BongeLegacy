package org.bonge.bukkit.r1_16.entity.living.monster.skeleton;

import org.bonge.bukkit.r1_16.entity.BongeAbstractEntity;
import org.bonge.bukkit.r1_16.entity.living.monster.IMonster;
import org.bonge.util.exception.NotImplementedException;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Skeleton;
import org.jetbrains.annotations.NotNull;

public class BongeTypicalSkeleton extends BongeAbstractEntity<org.spongepowered.api.entity.living.monster.skeleton.Skeleton> implements IMonster<org.spongepowered.api.entity.living.monster.skeleton.Skeleton>, Skeleton {

    public BongeTypicalSkeleton(org.spongepowered.api.entity.living.monster.skeleton.Skeleton entity) {
        super(entity);
    }

    @Deprecated
    @Override
    public SkeletonType getSkeletonType() {
        throw new NotImplementedException("Not got to yet");
    }

    @Deprecated
    @Override
    public void setSkeletonType(SkeletonType type) {
        throw new NotImplementedException("Not got to yet");
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
        return EntityType.SKELETON;
    }
}
