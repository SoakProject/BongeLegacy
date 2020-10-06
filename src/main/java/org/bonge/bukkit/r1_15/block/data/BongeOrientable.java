package org.bonge.bukkit.r1_15.block.data;

import org.bonge.Bonge;
import org.bukkit.Axis;
import org.bukkit.block.data.Orientable;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.data.Keys;

import java.util.HashSet;
import java.util.Set;

public interface BongeOrientable extends IBongeBlockData, Orientable {

    @Override
    default @NotNull Axis getAxis(){
        return Bonge.getInstance().convert(this.getSpongeValue().get(Keys.AXIS).get());
    }

    @Override
    default void setAxis(@NotNull Axis axis){
        this.setSpongeValue(this.getSpongeValue().with(Keys.AXIS, Bonge.getInstance().convert(axis)).get());
    }

    @Override
    default @NotNull Set<Axis> getAxes(){
        Set<Axis> axis = new HashSet<>(2);
        for(Axis ax : Axis.values()){
            if (this.getSpongeValue().with(Keys.AXIS, Bonge.getInstance().convert(ax)).isPresent()){
                axis.add(ax);
            }
        }
        return axis;
    }
}
