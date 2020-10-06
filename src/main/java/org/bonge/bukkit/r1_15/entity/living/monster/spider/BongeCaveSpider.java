package org.bonge.bukkit.r1_15.entity.living.monster.spider;

import org.bonge.bukkit.r1_15.entity.living.monster.BongeAbstractMonster;
import org.bukkit.Location;
import org.bukkit.entity.CaveSpider;
import org.bukkit.entity.EntityType;
import org.jetbrains.annotations.NotNull;

public class BongeCaveSpider extends BongeAbstractMonster<org.spongepowered.api.entity.living.monster.spider.CaveSpider> implements CaveSpider {

    public BongeCaveSpider(org.spongepowered.api.entity.living.monster.spider.CaveSpider entity) {
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
    public @NotNull Location getEyeLocation() {
        return null;
    }

    @Override
    public EntityType getType() {
        return EntityType.CAVE_SPIDER;
    }
}
