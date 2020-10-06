package org.bonge.bukkit.r1_15.block.data.dedicated;

import org.bonge.bukkit.r1_15.block.data.BongeDirectional;
import org.bonge.util.exception.NotImplementedException;
import org.bukkit.block.data.type.CommandBlock;

public interface BongeCommandBlock extends CommandBlock, BongeDirectional {

    @Override
    default boolean isConditional() {
        throw new NotImplementedException("CommandBlock.isConditional() Not got to yet");
    }

    @Override
    default void setConditional(boolean conditional) {
        throw new NotImplementedException("CommandBlock.setConditional(boolean) Not got to yet");
    }
}
