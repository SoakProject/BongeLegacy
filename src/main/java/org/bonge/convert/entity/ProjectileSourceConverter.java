package org.bonge.convert.entity;

import org.bonge.bukkit.r1_13.entity.BongeAbstractEntity;
import org.bonge.convert.Converter;
import org.bukkit.entity.Entity;
import org.bukkit.projectiles.ProjectileSource;

public class ProjectileSourceConverter implements Converter<ProjectileSource, org.spongepowered.api.entity.projectile.source.ProjectileSource> {
    @Override
    public Class<org.spongepowered.api.entity.projectile.source.ProjectileSource> getSpongeClass() {
        return org.spongepowered.api.entity.projectile.source.ProjectileSource.class;
    }

    @Override
    public Class<ProjectileSource> getBukkitClass() {
        return ProjectileSource.class;
    }

    @Override
    public org.spongepowered.api.entity.projectile.source.ProjectileSource from(ProjectileSource source) {
        if(source instanceof Entity){
            return (org.spongepowered.api.entity.projectile.source.ProjectileSource)((BongeAbstractEntity<?>)source).getSpongeValue();
        }
        throw new IllegalArgumentException("Unknown source of " + source.getClass().getSimpleName());
    }

    @Override
    public ProjectileSource to(org.spongepowered.api.entity.projectile.source.ProjectileSource projectile) {
        if(projectile instanceof org.spongepowered.api.entity.Entity){
            return (ProjectileSource) BongeAbstractEntity.of((org.spongepowered.api.entity.Entity)projectile);
        }
        throw new IllegalArgumentException("Unknown source of " + projectile.getClass().getSimpleName());
    }
}
