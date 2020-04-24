package org.bonge.bukkit.r1_13.entity.living.other.bat;

import org.bonge.bukkit.r1_13.entity.living.BongeAbstractLivingEntity;
import org.bukkit.Location;
import org.bukkit.entity.Bat;
import org.bukkit.entity.LivingEntity;
import org.bukkit.loot.LootTable;
import org.spongepowered.api.data.key.Keys;

public class BongeBat extends BongeAbstractLivingEntity<org.spongepowered.api.entity.living.Bat> implements Bat {

    public BongeBat(org.spongepowered.api.entity.living.Bat entity) {
        super(entity);
    }

    @Override
    public boolean isAwake() {
        return !this.spongeValue.get(Keys.IS_SLEEPING).get();
    }

    @Override
    public void setAwake(boolean state) {
        this.spongeValue.offer(Keys.IS_SLEEPING, !state);
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
    public Location getEyeLocation() {
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
}
