package org.bonge.bukkit.r1_13.material.block;

import org.bonge.bukkit.r1_13.material.BongeMaterial;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.data.BlockData;
import org.bukkit.material.MaterialData;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.block.BlockState;
import org.spongepowered.api.block.BlockType;
import org.spongepowered.api.data.property.block.*;

import java.util.Optional;
import java.util.function.Consumer;

public class ExtendedBlockMaterial extends Material implements BongeMaterial.Block {

    private BlockState state;
    private String name;

    public ExtendedBlockMaterial(BlockState state, String name){
        this.state = state;
        this.name = name;
    }

    @Override
    public Optional<Block> toBlock() {
        return Optional.of(this);
    }

    @Override
    public Optional<Item> toItem() {
        return Optional.empty();
    }

    @Override
    public @NotNull NamespacedKey getKey() {
        return NamespacedKey.minecraft(this.state.getType().getId().substring(10));
    }

    @Override
    public String name() {
        return this.name;
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
        return this.getNewData((byte)0).getClass();
    }

    @Override
    public MaterialData getNewData(byte raw) {
        return MaterialData.getData(this.state);
    }

    @Override
    public boolean isBlock() {
        return true;
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
        Optional<MatterProperty> opProp = this.state.getType().getProperty(MatterProperty.class);
        if(!opProp.isPresent()){
            return false;
        }
        return opProp.get().getValue().equals(MatterProperty.Matter.SOLID);
    }

    @Override
    public boolean isTransparent() {
        return false;
    }

    @Override
    public boolean isFlammable() {
        return this.state.getType().getProperty(FlammableProperty.class).get().getValue();
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
        return this.state.getType().getProperty(GravityAffectedProperty.class).get().getValue();
    }


    @Override
    public boolean isItem() {
        return this.toItem().isPresent();
    }


    @Override
    public boolean isInteractable() {
        return false;
    }

    @Override
    public float getHardness() {
        Optional<HardnessProperty> opProp = this.state.getType().getProperty(HardnessProperty.class);
        if(opProp.isPresent()){
            return opProp.get().getValue().floatValue();
        }
        return 0;
    }

    @Override
    public float getBlastResistance() {
        Optional<BlastResistanceProperty> opProp = this.state.getType().getProperty(BlastResistanceProperty.class);
        if(opProp.isPresent()){
            return opProp.get().getValue().floatValue();
        }
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

    @Override
    public BlockType getSpongeBlockType() {
        return this.state.getType();
    }
}
