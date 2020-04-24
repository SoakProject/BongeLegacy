package org.bonge.bukkit.r1_13.entity.living.monster.spider;

import org.bonge.bukkit.r1_13.entity.living.monster.BongeAbstractMonster;
import org.bukkit.Location;
import org.bukkit.entity.CaveSpider;

public class BongeCaveSpider extends BongeAbstractMonster<org.spongepowered.api.entity.living.monster.CaveSpider> implements CaveSpider {

    public BongeCaveSpider(org.spongepowered.api.entity.living.monster.CaveSpider entity) {
        super(entity);
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
