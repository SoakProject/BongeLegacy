package org.bonge.bukkit.r1_16.entity.living.animal.horse;

import org.bonge.bukkit.r1_16.entity.BongeAbstractEntity;
import org.bonge.util.exception.NotImplementedException;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.inventory.HorseInventory;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

public class BongeHorse extends BongeAbstractEntity<org.spongepowered.api.entity.living.animal.horse.Horse> implements IHorse<org.spongepowered.api.entity.living.animal.horse.Horse>, Horse {

    public BongeHorse(org.spongepowered.api.entity.living.animal.horse.Horse entity) {
        super(entity);
    }

    @Override
    public void setVelocity(@NotNull Vector vector) {
        IHorse.super.setVelocity(vector);
    }

    @Override
    public @NotNull Vector getVelocity() {
        return IHorse.super.getVelocity();
    }

    @Override
    public @NotNull HorseInventory getInventory() {
        throw new NotImplementedException("Not looked at yet");
    }

    @NotNull
    @Override
    public Color getColor() {
        throw new NotImplementedException("Not looked at yet");
    }

    @Override
    public void setColor(@NotNull Horse.Color color) {
        throw new NotImplementedException("Not looked at yet");
    }

    @NotNull
    @Override
    public Style getStyle() {
        throw new NotImplementedException("Not looked at yet");
    }

    @Override
    public void setStyle(@NotNull Horse.Style style) {
        throw new NotImplementedException("Not looked at yet");
    }

    @Override
    @Deprecated
    public boolean isCarryingChest() {
        throw new NotImplementedException("deprecated code");
    }

    @Override
    @Deprecated
    public void setCarryingChest(boolean b) {
        throw new NotImplementedException("deprecated code");
    }

    @NotNull
    @Override
    public EntityType getType() {
        return EntityType.HORSE;
    }
}
