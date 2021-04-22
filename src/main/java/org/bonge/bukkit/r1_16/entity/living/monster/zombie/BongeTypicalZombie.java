package org.bonge.bukkit.r1_16.entity.living.monster.zombie;

import org.bonge.bukkit.r1_16.entity.BongeAbstractEntity;
import org.bonge.util.exception.NotImplementedException;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Zombie;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.data.Keys;

public class BongeTypicalZombie extends BongeAbstractEntity<org.spongepowered.api.entity.living.monster.zombie.Zombie> implements BongeAbstractZombie<org.spongepowered.api.entity.living.monster.zombie.Zombie>, Zombie {

    public BongeTypicalZombie(org.spongepowered.api.entity.living.monster.zombie.Zombie entity) {
        super(entity);
    }

    @Override
    public boolean isBaby() {
        return !this.spongeValue.get(Keys.IS_ADULT).get();
    }

    @Override
    public void setBaby(boolean flag) {
        this.spongeValue.offer(Keys.IS_ADULT, !flag);
    }

    @Deprecated
    @Override
    public boolean isVillager() {
        return false;
    }

    @Deprecated
    @Override
    public void setVillager(boolean flag) {
        throw new NotImplementedException("yet to look at");
    }

    @Deprecated
    @Override
    public void setVillagerProfession(Villager.Profession profession) {
        throw new NotImplementedException("yet to look at");

    }

    @Deprecated
    @Override
    public Villager.Profession getVillagerProfession() {
        throw new NotImplementedException("yet to look at");

    }

    @Override
    public boolean isConverting() {
        throw new NotImplementedException("yet to look at");

    }

    @Override
    public int getConversionTime() {
        throw new NotImplementedException("yet to look at");

    }

    @Override
    public void setConversionTime(int time) {
        throw new NotImplementedException("yet to look at");

    }

    @Override
    public double getEyeHeight() {
        throw new NotImplementedException("yet to look at");

    }

    @Override
    public double getEyeHeight(boolean ignorePose) {
        throw new NotImplementedException("yet to look at");

    }

    @Override
    public @NotNull Location getEyeLocation() {
        throw new NotImplementedException("yet to look at");

    }

    @Override
    public EntityType getType() {
        return EntityType.ZOMBIE;
    }
}
