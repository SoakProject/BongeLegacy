package org.bonge.bukkit.r1_15.block.data.dedicated;

import org.bonge.bukkit.r1_15.block.data.BongeDirectional;
import org.bonge.bukkit.r1_15.block.data.BongePowerable;
import org.bonge.bukkit.r1_15.block.data.IBongeBlockData;
import org.bonge.util.exception.NotImplementedException;
import org.bukkit.block.data.type.Switch;
import org.jetbrains.annotations.NotNull;

public interface BongeSwitch extends IBongeBlockData, Switch, BongeDirectional, BongePowerable {

    @Override
    @NotNull
    default Face getFace() {
        throw new NotImplementedException("Switch.getFace() Not got to yet");
    }

    @Override
    default void setFace(@NotNull Face face) {
        throw new NotImplementedException("Switch.setFace(Switch.Face) Not got to yet");
    }
}
