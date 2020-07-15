package org.bonge.bukkit.r1_14.entity.living.monster.spider;

import org.bonge.bukkit.r1_14.entity.living.monster.BongeAbstractMonster;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Spider;

public class BongeTypicalSpider extends BongeAbstractMonster<org.spongepowered.api.entity.living.monster.spider.Spider> implements Spider {

    public BongeTypicalSpider(org.spongepowered.api.entity.living.monster.spider.Spider entity) {
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

    @Override
    public EntityType getType() {
        return EntityType.SPIDER;
    }
}
