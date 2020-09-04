package org.bonge.bukkit.r1_14.block.data.dedicated;

import org.bonge.bukkit.r1_14.block.data.*;
import org.bukkit.block.data.type.Door;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.data.Keys;
import org.spongepowered.api.data.type.DoorHinge;
import org.spongepowered.api.data.type.DoorHinges;

public interface BongeDoor extends IBongeBlockData, Door, BongeBisected, BongeDirectional, BongeOpenable, BongePowerable {

    @Override
    @NotNull
    default Hinge getHinge() {
        DoorHinge hinge = this.getSpongeValue().get(Keys.DOOR_HINGE).get();
        if(hinge.equals(DoorHinges.RIGHT.get())){
            return Hinge.RIGHT;
        }else if(hinge.equals(DoorHinges.LEFT.get())){
            return Hinge.LEFT;
        }
        throw new IllegalStateException("Unknown Sponge hinge location '" + hinge.getKey().getValue() + "'");
    }

    @Override
    default void setHinge(@NotNull Hinge hinge) {
        DoorHinge hinge1;
        switch (hinge){
            case LEFT: hinge1 = DoorHinges.LEFT.get(); break;
            case RIGHT: hinge1 = DoorHinges.RIGHT.get(); break;
            default: throw new IllegalStateException("Unknown Bukkit hinge location '" + hinge.name() + "'");
        }
        this.setSpongeValue(this.getSpongeValue().with(Keys.DOOR_HINGE, hinge1).get());
    }
}
