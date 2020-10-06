package org.bonge.convert.entity;

import org.bonge.Bonge;
import org.bonge.bukkit.r1_15.entity.BongeAbstractEntity;
import org.bonge.convert.Converter;
import org.bukkit.entity.Entity;
import org.bukkit.projectiles.ProjectileSource;

import java.io.IOException;

public class ProjectileSourceConverter implements Converter<ProjectileSource, org.spongepowered.api.projectile.source.ProjectileSource> {
    @Override
    public Class<org.spongepowered.api.projectile.source.ProjectileSource> getSpongeClass() {
        return org.spongepowered.api.projectile.source.ProjectileSource.class;
    }

    @Override
    public Class<ProjectileSource> getBukkitClass() {
        return ProjectileSource.class;
    }

    @Override
    public org.spongepowered.api.projectile.source.ProjectileSource from(ProjectileSource source) throws IOException{
        if(source instanceof Entity){
            return (org.spongepowered.api.projectile.source.ProjectileSource)((BongeAbstractEntity<?>)source).getSpongeValue();
        }
        throw new IOException("Unknown source of " + source.getClass().getSimpleName());
    }

    @Override
    public ProjectileSource to(org.spongepowered.api.projectile.source.ProjectileSource projectile) throws IOException{
        if(projectile instanceof org.spongepowered.api.entity.Entity){
            try {
                return (ProjectileSource) Bonge.getInstance().convert(Entity.class, projectile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        throw new IOException("Unknown source of " + projectile.getClass().getSimpleName());
    }
}
