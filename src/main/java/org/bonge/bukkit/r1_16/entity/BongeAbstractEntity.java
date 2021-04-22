package org.bonge.bukkit.r1_16.entity;

import org.bonge.wrapper.BongeWrapper;

public abstract class BongeAbstractEntity<T extends org.spongepowered.api.entity.Entity> extends BongeWrapper<T> implements IEntity<T> {

    public BongeAbstractEntity(T entity) {
        super(entity);
    }

}
