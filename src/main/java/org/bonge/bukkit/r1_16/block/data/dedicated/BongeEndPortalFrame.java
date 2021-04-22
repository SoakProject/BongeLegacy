package org.bonge.bukkit.r1_16.block.data.dedicated;

import org.bonge.bukkit.r1_16.block.data.BongeDirectional;
import org.bonge.bukkit.r1_16.block.data.IBongeBlockData;
import org.bonge.util.exception.NotImplementedException;
import org.bukkit.block.data.type.EndPortalFrame;

public interface BongeEndPortalFrame extends IBongeBlockData, BongeDirectional, EndPortalFrame {

    @Override
    default boolean hasEye() {
        throw new NotImplementedException("EndPortalFrame.hasEye() Not got to yet");
    }

    @Override
    default void setEye(boolean eye) {
        throw new NotImplementedException("EndPortalFrame.setEye(boolean) Not got to yet");
    }
}
