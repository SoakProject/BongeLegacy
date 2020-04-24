package org.bonge.bukkit.r1_13.entity.living.monster.spider;

import org.bonge.bukkit.r1_13.entity.living.monster.BongeAbstractMonster;
import org.bukkit.Location;
import org.bukkit.entity.Spider;

public class BongeTypicalSpider extends BongeAbstractMonster<org.spongepowered.api.entity.living.monster.Spider> implements Spider {

    public BongeTypicalSpider(org.spongepowered.api.entity.living.monster.Spider entity) {
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
