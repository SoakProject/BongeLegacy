package org.bonge.convert.block;

import org.bonge.Bonge;
import org.bonge.bukkit.r1_13.material.BlockMaterial;
import org.bonge.bukkit.r1_13.material.ItemMaterial;
import org.bonge.convert.Converter;
import org.bukkit.Material;
import org.spongepowered.api.block.BlockType;
import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.api.item.ItemType;

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
    public BlockType from(Material value) {
        if(value instanceof BlockMaterial){
            return ((BlockMaterial)value).getSpongeValue();
        }
        throw new IllegalArgumentException("Unknown material converter");
    }

    @Override
    public Material to(BlockType value) {
        Optional<Material> opType = Bonge.getInstance().getMaterials().stream().filter(t -> t instanceof BlockMaterial).filter(t -> ((BlockMaterial)t).getSpongeValue().equals(value)).findAny();
        if(opType.isPresent()){
            return opType.get();
        }
        throw new IllegalArgumentException("Unknown material converter");
    }
}
