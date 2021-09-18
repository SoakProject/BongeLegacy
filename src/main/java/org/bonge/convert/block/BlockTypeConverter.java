package org.bonge.convert.block;

import org.bonge.bukkit.r1_16.material.BongeMaterial;
import org.bonge.convert.Converter;
import org.bukkit.Material;
import org.spongepowered.api.block.BlockType;

import java.io.IOException;
import java.util.Optional;

public class BlockTypeConverter implements Converter<Material, BlockType> {
    @Override
    public Class<BlockType> getSpongeClass() {
        return BlockType.class;
    }

    @Override
    public Class<Material> getBukkitClass() {
        return Material.class;
    }

    @Override
    public BlockType from(Material value) throws IOException {
        Optional<BongeMaterial.Block> opBlock = value.getWrapper().toBlock();
        if (!opBlock.isPresent()) {
            throw new IOException("Cannot convert material " + value.name() + " to block. No Block variant found");
        }
        return opBlock.get().getSpongeBlockType();
    }

    @Override
    public Material to(BlockType value) throws IOException {
        return Material.valueOf(value);
    }
}
