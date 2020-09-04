package org.bonge.bukkit.r1_14.block.data.dedicated;

import org.bonge.bukkit.r1_14.block.data.IBongeBlockData;
import org.bonge.util.exception.NotImplementedException;
import org.bukkit.block.data.type.BubbleColumn;

public interface BongeBubbleColumn extends IBongeBlockData, BubbleColumn {

    @Override
    default boolean isDrag() {
        throw new NotImplementedException("BubbleColumn.isDrag() not got to yet");
    }

    @Override
    default void setDrag(boolean drag) {
        throw new NotImplementedException("BubbleColumn.setDrag(boolean) not got to yet");
    }
}
