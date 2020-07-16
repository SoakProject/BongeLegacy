package org.bonge.bukkit.r1_14.entity.other.arrow;

import org.bonge.Bonge;
import org.bonge.bukkit.r1_14.entity.BongeAbstractEntity;
import org.bukkit.block.Block;
import org.bukkit.entity.AbstractArrow;
import org.bukkit.entity.EntityType;
import org.bukkit.projectiles.ProjectileSource;
import org.spongepowered.api.data.Keys;

import java.io.IOException;

public class BongeAbstractArrowEntity<T extends org.spongepowered.api.entity.projectile.arrow.ArrowEntity> extends BongeAbstractEntity<T> implements AbstractArrow {

    public BongeAbstractArrowEntity(T entity) {
        super(entity);
    }

    @Override
    public int getKnockbackStrength() {
        return this.spongeValue.get(Keys.KNOCKBACK_STRENGTH).get().intValue();
    }

    @Override
    public void setKnockbackStrength(int knockbackStrength) {
        this.spongeValue.offer(Keys.KNOCKBACK_STRENGTH, (double)knockbackStrength);
    }

    @Override
    public double getDamage() {
        return this.spongeValue.getDouble(Keys.ATTACK_DAMAGE).getAsDouble();
    }

    @Override
    public void setDamage(double damage) {
        this.spongeValue.offer(Keys.ATTACK_DAMAGE, damage);
    }

    @Override
    public int getPierceLevel() {
        return 0;
    }

    @Override
    public void setPierceLevel(int pierceLevel) {

    }

    @Override
    public boolean isCritical() {
        return this.spongeValue.get(Keys.IS_CRITICAL_HIT).get();
    }

    @Override
    public void setCritical(boolean critical) {
        this.spongeValue.offer(Keys.IS_CRITICAL_HIT, critical);
    }

    @Override
    public boolean isInBlock() {
        return false;
    }

    @Override
    public Block getAttachedBlock() {
        return null;
    }

    @Override
    public PickupStatus getPickupStatus() {
        return null;
    }

    @Override
    public void setPickupStatus(PickupStatus status) {

    }

    @Override
    public ProjectileSource getShooter() {
        try {
            return Bonge.getInstance().convert(ProjectileSource.class, this.spongeValue.get(Keys.SHOOTER).get());
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void setShooter(ProjectileSource source) {
        try {
            this.spongeValue.offer(Keys.SHOOTER, Bonge.getInstance().convert(source, org.spongepowered.api.projectile.source.ProjectileSource.class));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public boolean doesBounce() {
        return false;
    }

    @Override
    public void setBounce(boolean doesBounce) {

    }

    @Override
    public EntityType getType() {
        return EntityType.ARROW;
    }
}
