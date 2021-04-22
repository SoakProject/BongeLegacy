package org.bonge.bukkit.r1_16.material;

import org.bukkit.NamespacedKey;
import org.bukkit.block.data.BlockData;
import org.bukkit.material.MaterialData;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.block.BlockType;
import org.spongepowered.api.item.ItemType;

import java.util.Optional;
import java.util.function.Consumer;

public interface BongeMaterial {

    interface Block extends BongeMaterial{

        BlockType getSpongeBlockType();

    }

    interface Item extends BongeMaterial {

        ItemType getSpongeItemType();

    }

    Optional<Block> toBlock();
    Optional<Item> toItem();

    @NotNull NamespacedKey getKey();
    String name();
    int getMaxStackSize();
    short getMaxDurability();
    @Deprecated
    Class<? extends MaterialData> getData();
    @Deprecated
    MaterialData getNewData(final byte raw);
    boolean isBlock();
    boolean isEdible();
    boolean isRecord();
    boolean isSolid();
    @Deprecated
    boolean isTransparent();
    boolean isFlammable();
    boolean isBurnable();
    boolean isFuel();
    boolean isOccluding();
    boolean hasGravity();
    boolean isItem();
    boolean isInteractable();
    float getHardness();
    float getBlastResistance();

    BlockData createBlockData();

    BlockData createBlockData(Consumer<BlockData> consumer);

    BlockData createBlockData(String data) throws IllegalArgumentException;
}
