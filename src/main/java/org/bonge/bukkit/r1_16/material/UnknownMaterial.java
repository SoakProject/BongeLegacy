package org.bonge.bukkit.r1_16.material;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.data.BlockData;
import org.bukkit.material.MaterialData;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.function.Consumer;

public class UnknownMaterial implements BongeMaterial {

    @Override
    @Deprecated
    public Optional<Block> toBlock() {
        return Optional.empty();
    }

    @Override
    @Deprecated
    public Optional<Item> toItem() {
        return Optional.empty();
    }

    @Override
    public @NotNull NamespacedKey getKey() {
        return new NamespacedKey("bonge", "unknown");
    }

    @Override
    public String name() {
        return "Unknown";
    }

    @Override
    public int getMaxStackSize() {
        return 0;
    }

    @Override
    public short getMaxDurability() {
        return 0;
    }

    @Override
    public Class<? extends MaterialData> getData() {
        return MaterialData.class;
    }

    @Override
    @Deprecated
    public MaterialData getNewData(byte raw) {
        return new MaterialData(Material.UNKNOWN, raw);
    }

    @Override
    public boolean isBlock() {
        return false;
    }

    @Override
    public boolean isEdible() {
        return false;
    }

    @Override
    public boolean isRecord() {
        return false;
    }

    @Override
    public boolean isSolid() {
        return false;
    }

    @Override
    public boolean isTransparent() {
        return false;
    }

    @Override
    public boolean isFlammable() {
        return false;
    }

    @Override
    public boolean isBurnable() {
        return false;
    }

    @Override
    public boolean isFuel() {
        return false;
    }

    @Override
    public boolean isOccluding() {
        return false;
    }

    @Override
    public boolean hasGravity() {
        return false;
    }

    @Override
    public boolean isItem() {
        return false;
    }

    @Override
    public boolean isInteractable() {
        return false;
    }

    @Override
    public float getHardness() {
        return 0;
    }

    @Override
    public float getBlastResistance() {
        return 0;
    }

    @Override
    public BlockData createBlockData() {
        return null;
    }

    @Override
    public BlockData createBlockData(Consumer<BlockData> consumer) {
        return null;
    }

    @Override
    public BlockData createBlockData(String data) throws IllegalArgumentException {
        return null;
    }
}
