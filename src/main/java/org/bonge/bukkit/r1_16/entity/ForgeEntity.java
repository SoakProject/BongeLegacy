package org.bonge.bukkit.r1_16.entity;

import org.bukkit.entity.EntityType;
import org.jetbrains.annotations.NotNull;

public class ForgeEntity<E extends org.spongepowered.api.entity.Entity> extends BongeAbstractEntity<E>{

    public ForgeEntity(E entity) {
        super(entity);
    }

    @NotNull
    @Override
    public EntityType getType() {
        return EntityType.UNKNOWN;
    }

}
