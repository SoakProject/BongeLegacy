package org.bonge.bukkit.r1_16.entity.vehicle;

import org.bonge.bukkit.r1_16.entity.IEntity;
import org.bukkit.entity.Vehicle;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

public interface IVehicle<V extends org.spongepowered.api.entity.vehicle.Vehicle> extends IEntity<V>, Vehicle {
    @Override
    default void setVelocity(@NotNull Vector vector) {
        IEntity.super.setVelocity(vector);
    }

    @Override
    default @NotNull Vector getVelocity() {
        return IEntity.super.getVelocity();
    }
}
