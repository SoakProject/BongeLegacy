package org.bonge.bukkit.r1_13.entity.living.monster.zombie;

import org.bonge.bukkit.r1_13.entity.living.monster.BongeAbstractMonster;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.entity.ZombieVillager;
import org.spongepowered.api.data.key.Keys;

public class BongeVillagerZombie extends BongeAbstractMonster<org.spongepowered.api.entity.living.monster.ZombieVillager> implements ZombieVillager {

    public BongeVillagerZombie(org.spongepowered.api.entity.living.monster.ZombieVillager entity) {
        super(entity);
    }

    @Override
    public boolean isBaby() {
        return !this.getSpongeValue().get(Keys.IS_ADULT).get();
    }

    @Override
    public void setBaby(boolean flag) {
        this.getSpongeValue().offer(Keys.IS_ADULT, !flag);
    }

    @Deprecated
    @Override
    public boolean isVillager() {
        return true;
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

    @Override
    public EntityType getType() {
        return EntityType.ZOMBIE;
    }
}
