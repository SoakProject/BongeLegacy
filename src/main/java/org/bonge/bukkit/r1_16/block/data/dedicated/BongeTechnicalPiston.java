package org.bonge.bukkit.r1_16.block.data.dedicated;

import org.bonge.bukkit.r1_16.block.data.BongeDirectional;
import org.bonge.bukkit.r1_16.block.data.IBongeBlockData;
import org.bukkit.block.data.type.TechnicalPiston;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.data.Keys;
import org.spongepowered.api.data.type.PistonType;
import org.spongepowered.api.data.type.PistonTypes;

public interface BongeTechnicalPiston extends IBongeBlockData, TechnicalPiston, BongeDirectional {

    @Override
    @NotNull
    default Type getType() {
        PistonType piston = this.getSpongeValue().get(Keys.PISTON_TYPE).get();
        if(piston.equals(PistonTypes.NORMAL.get())){
            return Type.NORMAL;
        }else if(piston.equals(PistonTypes.STICKY.get())){
            return Type.STICKY;
        }
        throw new IllegalStateException("Unknown Sponge PistonType." + piston.toString());
    }

    @Override
    default void setType(@NotNull Type type) {
        PistonType piston;
        switch (type){
            case NORMAL: piston = PistonTypes.NORMAL.get(); break;
            case STICKY: piston = PistonTypes.STICKY.get(); break;
            default: throw new IllegalStateException("Unknown Bukkit TechnicalPiston.Type." + type.name());
        }
        this.setSpongeValue(this.getSpongeValue().with(Keys.PISTON_TYPE, piston).get());
    }
}
