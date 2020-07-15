package org.bonge.bukkit.r1_14.entity.living.monster;

import org.bonge.Bonge;
import org.bonge.bukkit.r1_14.entity.living.BongeAbstractLivingEntity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.loot.LootTable;
import org.spongepowered.api.data.Keys;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.entity.living.Living;

import java.io.IOException;
import java.util.Optional;

public abstract class BongeAbstractMonster<T extends org.spongepowered.api.entity.living.Monster> extends BongeAbstractLivingEntity<T> implements Monster {

    public BongeAbstractMonster(T entity) {
        super(entity);
    }

    @Override
    public void setTarget(LivingEntity target) {
        try {
            this.spongeValue.offer(Keys.TARGET_ENTITY, Bonge.getInstance().convert(target, Living.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public LivingEntity getTarget() {
        Optional<Entity> opEntity = this.getSpongeValue().get(Keys.TARGET_ENTITY);
        if(!opEntity.isPresent()){
            return null;
        }
        try {
            return Bonge.getInstance().convert(LivingEntity.class, opEntity.get());
        } catch (IOException e) {
            e.printStackTrace();
        }
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
