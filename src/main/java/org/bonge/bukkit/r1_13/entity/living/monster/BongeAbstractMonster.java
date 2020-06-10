package org.bonge.bukkit.r1_13.entity.living.monster;

import org.bonge.Bonge;
import org.bonge.bukkit.r1_13.entity.living.BongeAbstractLivingEntity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.loot.LootTable;
import org.spongepowered.api.entity.Entity;

import java.io.IOException;
import java.util.Optional;

public abstract class BongeAbstractMonster<T extends org.spongepowered.api.entity.living.monster.Monster> extends BongeAbstractLivingEntity<T> implements Monster {

    public BongeAbstractMonster(T entity) {
        super(entity);
    }

    @Override
    public void setTarget(LivingEntity target) {
        this.spongeValue.setTarget(((BongeAbstractLivingEntity<?>)target).getSpongeValue());
    }

    @Override
    public LivingEntity getTarget() {
        Optional<Entity> opEntity = this.getSpongeValue().getTarget();
        if(!opEntity.isPresent()){
            return null;
        }
        try {
            return (LivingEntity) Bonge.getInstance().convert(Entity.class, opEntity.get());
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
