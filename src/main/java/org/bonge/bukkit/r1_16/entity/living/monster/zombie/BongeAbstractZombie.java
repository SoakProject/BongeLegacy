package org.bonge.bukkit.r1_16.entity.living.monster.zombie;

import org.bonge.bukkit.r1_16.entity.living.monster.IMonster;
import org.bonge.util.exception.NotImplementedException;
import org.bukkit.entity.Ageable;
import org.spongepowered.api.data.Keys;

public interface BongeAbstractZombie<Z extends org.spongepowered.api.entity.living.Monster> extends IMonster<Z>, Ageable {

    @Override
    default int getAge() {
        return this.getSpongeValue().get(Keys.AGE).orElseThrow(() -> new IllegalStateException("Not ageable"));
    }

    @Override
    default void setAge(int age) {
        this.getSpongeValue().offer(Keys.AGE, age);
    }

    @Override
    default void setAgeLock(boolean lock) {
        throw new NotImplementedException("yet to look at");
    }

    @Override
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
        return this.getSpongeValue().get(Keys.IS_ADULT).orElseThrow(() -> new IllegalStateException("Not ageable"));
    }

    @Override
    default boolean canBreed() {
        return this.getSpongeValue().get(Keys.CAN_BREED).orElseThrow(() -> new IllegalStateException("Not ageable"));
    }

    @Override
    default void setBreed(boolean breed) {
        this.getSpongeValue().offer(Keys.CAN_BREED, breed);
    }
}
