package org.bonge.bukkit.r1_13.entity.other.arrow;

import org.bonge.bukkit.r1_13.entity.BongeAbstractEntity;
import org.bonge.convert.InterfaceConvert;
import org.bukkit.block.Block;
import org.bukkit.entity.Arrow;
import org.bukkit.projectiles.ProjectileSource;
import org.spongepowered.api.data.key.Keys;

public class BongeAbstractArrowEntity<T extends org.spongepowered.api.entity.projectile.arrow.Arrow> extends BongeAbstractEntity<T> implements Arrow {

    public BongeAbstractArrowEntity(T entity) {
        super(entity);
    }

    @Override
    public int getKnockbackStrength() {
        return this.spongeValue.get(Keys.KNOCKBACK_STRENGTH).get();
    }

    @Override
    public void setKnockbackStrength(int knockbackStrength) {
        this.spongeValue.offer(Keys.KNOCKBACK_STRENGTH, knockbackStrength);
    }

    @Override
    public boolean isCritical() {
        return false;
    }

    @Override
    public void setCritical(boolean critical) {

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
        return InterfaceConvert.getProjectile(this.spongeValue.getShooter());
    }

    @Override
    public void setShooter(ProjectileSource source) {
        this.spongeValue.setShooter(InterfaceConvert.getProjectile(source));
    }

    @Override
    public boolean doesBounce() {
        return false;
    }

    @Override
    public void setBounce(boolean doesBounce) {

    }
}
