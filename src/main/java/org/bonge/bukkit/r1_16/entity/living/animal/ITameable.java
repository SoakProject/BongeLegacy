package org.bonge.bukkit.r1_16.entity.living.animal;

import org.bukkit.Bukkit;
import org.bukkit.entity.AnimalTamer;
import org.bukkit.entity.Tameable;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.api.data.Keys;
import org.spongepowered.api.entity.living.animal.TameableAnimal;

import java.util.Optional;
import java.util.UUID;

public interface ITameable<S extends TameableAnimal> extends IAnimal<S>, Tameable {

    @Override
    default boolean isTamed() {
        return this.getSpongeValue().get(Keys.IS_TAMED).orElse(false);
    }

    @Override
    default void setTamed(boolean b) {
        this.getSpongeValue().offer(Keys.IS_TAMED, b);
    }

    @Nullable
    @Override
    default AnimalTamer getOwner() {
        Optional<UUID> opTamer = this.getSpongeValue().get(Keys.TAMER);
        if (!opTamer.isPresent()) {
            return null;
        }
        Optional<AnimalTamer> opTamerEntity = Bukkit.getWorlds().stream().flatMap(world -> world.getEntities().stream()).filter(e -> e.getUniqueId().equals(opTamer.get())).map(e -> (AnimalTamer) e).findFirst();
        return opTamerEntity.orElseThrow(() -> new IllegalStateException("Could not find tamer within the server"));
    }

    @Override
    default void setOwner(@Nullable AnimalTamer animalTamer) {
        this.getSpongeValue().offer(Keys.TAMER, animalTamer.getUniqueId());
    }
}
