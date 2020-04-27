package org.bonge.bukkit.r1_13.material;

import org.bonge.Bonge;
import org.bonge.wrapper.BongeWrapper;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.material.MaterialData;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.block.BlockType;
import org.spongepowered.api.data.property.item.BurningFuelProperty;
import org.spongepowered.api.data.property.item.FoodRestorationProperty;
import org.spongepowered.api.data.property.item.RecordProperty;
import org.spongepowered.api.data.property.item.UseLimitProperty;
import org.spongepowered.api.item.ItemType;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

public class ItemMaterial extends BongeWrapper<ItemType> implements Material {

    private final String name;

    public ItemMaterial(ItemType type, String name){
        super(type);
        this.name = name;
    }

    public Optional<BlockMaterial> toBlock(){
        Optional<BlockType> opBlock = this.getSpongeValue().getBlock();
        if(!opBlock.isPresent()){
            return Optional.empty();
        }
        try {
            return Optional.of(Bonge.getInstance().convert(BlockMaterial.class, opBlock.get()));
        } catch (IOException e) {
            return Optional.empty();
        }
    }

    @Override
    @NotNull
    public NamespacedKey getKey() {
        return NamespacedKey.minecraft(this.spongeValue.getId().substring(10));

    }

    @Override
    public String name() {
        return this.name;
    }

    @Override
    public int getMaxStackSize() {
        return this.spongeValue.getMaxStackQuantity();
    }

    @Override
    public short getMaxDurability() {
        return Objects.requireNonNull(this.spongeValue.getDefaultProperty(UseLimitProperty.class).get().getValue()).shortValue();
    }

    @Override
    public Class<? extends MaterialData> getData() {
        Optional<BlockMaterial> opBlock = this.toBlock();
        if(!opBlock.isPresent()){
            return null;
        }
        return opBlock.get().getData();
    }

    @Override
    public MaterialData getNewData(byte raw) {
        Optional<BlockMaterial> opBlock = this.toBlock();
        if(!opBlock.isPresent()){
            return null;
        }
        return opBlock.get().getNewData(raw);
    }

    @Override
    public boolean isBlock() {
        return false;
    }

    @Override
    public boolean isEdible() {
        return this.spongeValue.getDefaultProperty(FoodRestorationProperty.class).isPresent();

    }

    @Override
    public boolean isRecord() {
        return this.spongeValue.getDefaultProperty(RecordProperty.class).isPresent();
    }

    @Override
    public boolean isSolid() {
        Optional<BlockMaterial> opBlock = this.toBlock();
        if(!opBlock.isPresent()){
            return false;
        }
        return opBlock.get().isSolid();
    }

    @Override
    public boolean isTransparent() {
        Optional<BlockMaterial> opBlock = this.toBlock();
        if(!opBlock.isPresent()){
            return false;
        }
        return opBlock.get().isTransparent();
    }

    @Override
    public boolean isFlammable() {
        Optional<BlockMaterial> opBlock = this.toBlock();
        if(!opBlock.isPresent()){
            return false;
        }
        return opBlock.get().isFlammable();
    }

    @Override
    public boolean isBurnable() {
        Optional<BlockMaterial> opBlock = this.toBlock();
        if(!opBlock.isPresent()){
            return false;
        }
        return opBlock.get().isBurnable();
    }

    @Override
    public boolean isFuel() {
        return this.spongeValue.getDefaultProperty(BurningFuelProperty.class).isPresent();
    }

    @Override
    public boolean isOccluding() {
        Optional<BlockMaterial> opBlock = this.toBlock();
        if(!opBlock.isPresent()){
            return false;
        }
        return opBlock.get().isOccluding();
    }

    @Override
    public boolean hasGravity() {
        Optional<BlockMaterial> opBlock = this.toBlock();
        if(!opBlock.isPresent()){
            return false;
        }
        return opBlock.get().hasGravity();
    }

    @Override
    public boolean isItem() {
        return true;
    }

    @Override
    public boolean isInteractable() {
        Optional<BlockMaterial> opBlock = this.toBlock();
        if(!opBlock.isPresent()){
            return false;
        }
        return opBlock.get().isInteractable();
    }

    @Override
    public float getHardness() {
        Optional<BlockMaterial> opBlock = this.toBlock();
        if(!opBlock.isPresent()){
            return 0;
        }
        return opBlock.get().getHardness();
    }

    @Override
    public float getBlastResistance() {
        Optional<BlockMaterial> opBlock = this.toBlock();
        if(!opBlock.isPresent()){
            return 0;
        }
        return opBlock.get().getBlastResistance();
    }
}
