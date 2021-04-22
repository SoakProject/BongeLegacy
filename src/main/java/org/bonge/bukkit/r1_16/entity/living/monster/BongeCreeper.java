package org.bonge.bukkit.r1_16.entity.living.monster;

import org.bonge.bukkit.r1_16.entity.BongeAbstractEntity;
import org.bukkit.Location;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EntityType;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.data.Keys;
import org.spongepowered.api.util.Ticks;

public class BongeCreeper extends BongeAbstractEntity<org.spongepowered.api.entity.living.monster.Creeper> implements BongeAbstractMonster<org.spongepowered.api.entity.living.monster.Creeper>, Creeper {

    public BongeCreeper(org.spongepowered.api.entity.living.monster.Creeper entity) {
        super(entity);
    }

    @Override
    public boolean isPowered() {
        return this.spongeValue.get(Keys.IS_CHARGED).get();
    }

    @Override
    public void setPowered(boolean value) {
        this.spongeValue.offer(Keys.IS_CHARGED, value);
    }

    @Override
    public void setMaxFuseTicks(int ticks) {
        this.spongeValue.offer(Keys.FUSE_DURATION, Ticks.of(ticks));
    }

    @Override
    public int getMaxFuseTicks() {
        return (int) this.spongeValue.get(Keys.FUSE_DURATION).get().ticks();
    }

    @Override
    public void setFuseTicks(int i) {
        this.spongeValue.offer(Keys.FUSE_DURATION, Ticks.of(i));
    }

    @Override
    public int getFuseTicks() {
        return (int) this.spongeValue.get(Keys.FUSE_DURATION).get().ticks();
    }

    @Override
    public void setExplosionRadius(int radius) {
        this.spongeValue.offer(Keys.EXPLOSION_RADIUS, radius);
    }

    @Override
    public int getExplosionRadius() {
        return this.spongeValue.get(Keys.EXPLOSION_RADIUS).orElse(-1);
    }


    @Override
    public void explode() {
    }

    @Override
    public void ignite() {

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
        return EntityType.CREEPER;
    }
}
