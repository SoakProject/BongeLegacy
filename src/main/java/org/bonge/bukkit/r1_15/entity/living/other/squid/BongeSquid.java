package org.bonge.bukkit.r1_15.entity.living.other.squid;

import org.bonge.bukkit.r1_15.entity.living.BongeAbstractLivingEntity;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Squid;
import org.bukkit.loot.LootTable;
import org.jetbrains.annotations.NotNull;

public class BongeSquid extends BongeAbstractLivingEntity<org.spongepowered.api.entity.living.aquatic.Squid> implements Squid {

    public BongeSquid(org.spongepowered.api.entity.living.aquatic.Squid entity) {
        super(entity);
    }

    @Override
    public void setTarget(LivingEntity target) {

    }

    @Override
    public LivingEntity getTarget() {
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
    public @NotNull Location getEyeLocation() {
        return null;
    }

    @Override
    public void setLootTable(LootTable table) {

    }

    @Override
    public LootTable getLootTable() {
        return null;
    }

    @Override
    public void setSeed(long seed) {

    }

    @Override
    public long getSeed() {
        return 0;
    }

    @Override
    public EntityType getType() {
        return EntityType.SQUID;
    }
}
