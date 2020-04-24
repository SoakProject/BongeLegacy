package org.bonge.bukkit.r1_13.entity.living.monster.zombie;

import org.bonge.bukkit.r1_13.entity.living.monster.BongeAbstractMonster;
import org.bukkit.Location;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Zombie;
import org.spongepowered.api.data.key.Keys;

public class BongeTypicalZombie extends BongeAbstractMonster<org.spongepowered.api.entity.living.monster.Zombie> implements Zombie {

    public BongeTypicalZombie(org.spongepowered.api.entity.living.monster.Zombie entity) {
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

    }

    @Deprecated
    @Override
    public void setVillagerProfession(Villager.Profession profession) {

    }

    @Deprecated
    @Override
    public Villager.Profession getVillagerProfession() {
        return null;
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
    public Location getEyeLocation() {
        return null;
    }
}
