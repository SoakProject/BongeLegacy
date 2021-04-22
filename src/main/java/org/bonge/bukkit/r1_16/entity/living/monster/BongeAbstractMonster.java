package org.bonge.bukkit.r1_16.entity.living.monster;

import org.bonge.Bonge;
import org.bonge.bukkit.r1_16.entity.living.ILivingEntity;
import org.bonge.util.exception.NotImplementedException;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.loot.LootTable;
import org.spongepowered.api.data.Keys;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.entity.living.Living;

import java.io.IOException;
import java.util.Optional;

public interface BongeAbstractMonster<T extends org.spongepowered.api.entity.living.Monster> extends ILivingEntity<T>, Monster {

    @Override
    default void setAware(boolean aware) {
        throw new NotImplementedException("Not got to yet");
    }

    @Override
    default boolean isAware() {
        throw new NotImplementedException("Not got to yet");
    }

    @Override
    default void setTarget(LivingEntity target) {
        try {
            this.getSpongeValue().offer(Keys.TARGET_ENTITY, Bonge.getInstance().convert(target, Living.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    default LivingEntity getTarget() {
        Optional<Entity> opEntity = this.getSpongeValue().get(Keys.TARGET_ENTITY);
        if (!opEntity.isPresent()) {
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
    default void setLootTable(LootTable table) {

    }

    @Override
    default LootTable getLootTable() {
        return null;
    }

    @Override
    default void setSeed(long seed) {
    }

    @Override
    default long getSeed() {
        return 0;
    }
}
