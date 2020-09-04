package org.bonge.convert.block;

import org.bonge.convert.Converter;
import org.bonge.util.exception.NotImplementedException;
import org.bukkit.Axis;

public class AxisConverter implements Converter<Axis, org.spongepowered.api.util.Axis> {
    @Override
    public Class<org.spongepowered.api.util.Axis> getSpongeClass() {
        return org.spongepowered.api.util.Axis.class;
    }

    @Override
    public Class<Axis> getBukkitClass() {
        return Axis.class;
    }

    @Override
    public org.spongepowered.api.util.Axis from(Axis value) {
        switch (value){
            case X: return org.spongepowered.api.util.Axis.X;
            case Y: return org.spongepowered.api.util.Axis.Y;
            case Z: return org.spongepowered.api.util.Axis.Z;
        }
        throw new NotImplementedException("Bukkit has added a 4th axis of Axis." + value.name());
    }

    @Override
    public Axis to(org.spongepowered.api.util.Axis value) {
        switch (value){
            case X: return Axis.X;
            case Y: return Axis.Y;
            case Z: return Axis.Z;
        }
        throw new NotImplementedException("Sponge has added a 4th axis of Axis." + value.name());
    }
}
