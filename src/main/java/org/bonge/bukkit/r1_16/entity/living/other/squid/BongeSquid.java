package org.bonge.bukkit.r1_16.entity.living.other.squid;

import org.bonge.bukkit.r1_16.entity.BongeAbstractEntity;
import org.bonge.bukkit.r1_16.entity.living.ILivingEntity;
import org.bonge.util.exception.NotImplementedException;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Squid;
import org.bukkit.loot.LootTable;
import org.jetbrains.annotations.NotNull;

public class BongeSquid extends BongeAbstractEntity<org.spongepowered.api.entity.living.aquatic.Squid> implements ILivingEntity<org.spongepowered.api.entity.living.aquatic.Squid>, Squid {

    public BongeSquid(org.spongepowered.api.entity.living.aquatic.Squid entity) {
        super(entity);
    }

    @Override
    public void setTarget(LivingEntity target) {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    public LivingEntity getTarget() {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    public void setAware(boolean aware) {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    public boolean isAware() {
        throw new NotImplementedException("Not got to yet");
    }

    @Override
    public double getEyeHeight() {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    public double getEyeHeight(boolean ignorePose) {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    public @NotNull Location getEyeLocation() {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    public void setLootTable(LootTable table) {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    public LootTable getLootTable() {        throw new NotImplementedException("Not got to yet");

    }

    @Override
    public void setSeed(long seed) {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    public long getSeed() {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    public EntityType getType() {
        return EntityType.SQUID;
    }
}
