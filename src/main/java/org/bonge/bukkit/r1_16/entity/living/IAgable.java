package org.bonge.bukkit.r1_16.entity.living;

import org.bonge.Bonge;
import org.bonge.bukkit.r1_16.entity.BongeAbstractEntity;
import org.bonge.util.exception.NotImplementedException;
import org.bukkit.entity.Ageable;
import org.bukkit.entity.LivingEntity;
import org.bukkit.loot.LootTable;
import org.spongepowered.api.data.Keys;
import org.spongepowered.api.entity.Entity;

import java.io.IOException;
import java.util.Optional;

public interface IAgable<S extends org.spongepowered.api.entity.living.Ageable> extends ILivingEntity<S>, Ageable {

    @Override
    default int getAge() {
        return this.getSpongeValue().get(Keys.AGE).get();
    }

    @Override
    default void setAge(int age) {
        this.getSpongeValue().offer(Keys.AGE, age);
    }

    @Override
    @Deprecated
    default void setAgeLock(boolean lock) {
        throw new NotImplementedException("yet to look at");
    }

    @Override
    @Deprecated
    default boolean getAgeLock() {
        throw new NotImplementedException("yet to look at");
    }

    @Override
    default void setBaby() {
        this.getSpongeValue().offer(Keys.IS_ADULT, false);
    }

    @Override
    default void setAdult() {
        this.getSpongeValue().offer(Keys.IS_ADULT, true);
    }

    @Override
    default boolean isAdult() {
        return this.getSpongeValue().get(Keys.IS_ADULT).get();
    }

    @Override
    @Deprecated
    default boolean canBreed() {
        return this.getSpongeValue().get(Keys.CAN_BREED).get();
    }

    @Override
    @Deprecated
    default void setBreed(boolean breed) {
        this.getSpongeValue().offer(Keys.CAN_BREED, breed);
    }


    @Override
    default void setTarget(LivingEntity target) {
        this.getSpongeValue().offer(Keys.TARGET_ENTITY, (((BongeAbstractEntity<?>) target).getSpongeValue()));
    }

    @Override
    default LivingEntity getTarget() {
        Optional<Entity> opEntity = this.getSpongeValue().get(Keys.TARGET_ENTITY);
        if (!opEntity.isPresent()) {
            return null;
        }
        org.bukkit.entity.Entity entity = null;
        try {
            entity = Bonge.getInstance().convert(org.bukkit.entity.Entity.class, opEntity.get());
            if (entity instanceof LivingEntity) {
                return (LivingEntity) entity;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    default void setAware(boolean aware) {
        throw new NotImplementedException("yet to look at");
    }

    @Override
    default boolean isAware() {
        throw new NotImplementedException("yet to look at");
    }

    @Override
    default void setLootTable(LootTable table) {
        throw new NotImplementedException("yet to look at");
    }

    @Override
    default LootTable getLootTable() {
        throw new NotImplementedException("yet to look at");
    }

    @Override
    default void setSeed(long seed) {
        throw new NotImplementedException("yet to look at");
    }

    @Override
    default long getSeed() {
        throw new NotImplementedException("yet to look at");
    }
}
