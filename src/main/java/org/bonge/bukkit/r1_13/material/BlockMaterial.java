package org.bonge.bukkit.r1_13.material;

import org.bonge.wrapper.BongeWrapper;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.data.BlockData;
import org.bukkit.material.MaterialData;
import org.spongepowered.api.block.BlockType;
import org.spongepowered.api.data.property.block.*;
import org.spongepowered.api.data.property.item.BurningFuelProperty;
import org.spongepowered.api.item.ItemType;

import java.util.Optional;
import java.util.function.Consumer;

public class BlockMaterial extends BongeWrapper<BlockType> implements Material {

    private String name;

    public BlockMaterial(BlockType type, String name){
        super(type);
        this.name = name;
    }

    @Override
    public NamespacedKey getKey() {
        return NamespacedKey.minecraft(this.spongeValue.getId().substring(10));

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
    public Class<? extends MaterialData> getData() {
        return getNewData((byte)0).getClass();
    }

    @Override
    public MaterialData getNewData(byte raw) {
        return MaterialData.getData(this.spongeValue.getDefaultState());
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
        Optional<MatterProperty> opProp = this.spongeValue.getProperty(MatterProperty.class);
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
        return this.spongeValue.getProperty(FlammableProperty.class).get().getValue();

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
        //TODO
        return false;
    }

    @Override
    public boolean hasGravity() {
        return this.spongeValue.getProperty(GravityAffectedProperty.class).get().getValue();
    }

    @Override
    public boolean isItem() {
        return false;
    }

    @Override
    public boolean isInteractable() {
        //TODO
        return false;
    }

    @Override
    public float getHardness() {
        Optional<HardnessProperty> opProp = this.spongeValue.getProperty(HardnessProperty.class);
        if(opProp.isPresent()){
            return opProp.get().getValue().floatValue();
        }
        return 0;
    }

    @Override
    public float getBlastResistance() {
        Optional<BlastResistanceProperty> opProp = this.spongeValue.getProperty(BlastResistanceProperty.class);
        if(opProp.isPresent()){
            return opProp.get().getValue().floatValue();
        }
        return 0;
    }
}
