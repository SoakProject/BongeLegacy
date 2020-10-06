package org.bonge.bukkit.r1_15.entity.living.monster.skeleton;

import org.bonge.bukkit.r1_15.entity.living.monster.BongeAbstractMonster;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Skeleton;
import org.jetbrains.annotations.NotNull;

public class BongeTypicalSkeleton extends BongeAbstractMonster<org.spongepowered.api.entity.living.monster.skeleton.SkeletonEntity> implements Skeleton {

    public BongeTypicalSkeleton(org.spongepowered.api.entity.living.monster.skeleton.SkeletonEntity entity) {
        super(entity);
    }

    @Deprecated
    @Override
    public SkeletonType getSkeletonType() {
        return null;
    }

    @Deprecated
    @Override
    public void setSkeletonType(SkeletonType type) {

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
    public EntityType getType() {
        return EntityType.SKELETON;
    }
}
