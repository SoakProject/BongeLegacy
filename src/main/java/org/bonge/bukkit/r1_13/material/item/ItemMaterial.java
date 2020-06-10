package org.bonge.bukkit.r1_13.material.item;

import org.bonge.bukkit.r1_13.material.BongeMaterial;
import org.bonge.bukkit.r1_13.material.SpongeMaterialFix;
import org.bonge.bukkit.r1_13.material.block.BlockMaterial;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.data.BlockData;
import org.bukkit.material.MaterialData;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.block.BlockType;
import org.spongepowered.api.data.property.item.BurningFuelProperty;
import org.spongepowered.api.data.property.item.FoodRestorationProperty;
import org.spongepowered.api.data.property.item.RecordProperty;
import org.spongepowered.api.data.property.item.UseLimitProperty;
import org.spongepowered.api.item.ItemType;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

public class ItemMaterial extends Material implements BongeMaterial.Item {

    private final String name;
    private final ItemType spongeValue;

    public ItemMaterial(ItemType type, String name){
        this.spongeValue = type;
        this.name = name;
    }

    @Override
    public ItemType getSpongeItemType(){
        return this.spongeValue;
    }

    @Override
    public Optional<Block> toBlock(){
        Optional<BlockType> opBlock = this.spongeValue.getBlock();
        if(!opBlock.isPresent()){
            Optional<SpongeMaterialFix> opFix = SpongeMaterialFix.get(this);
            if(opFix.isPresent()){
                return Optional.of(opFix.get().getBlock());
            }
            return Optional.empty();
        }
        return Optional.of(new BlockMaterial(opBlock.get(), this.name));
    }

    public Optional<Item> toItem(){
        return Optional.of(this);
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
    @Deprecated
    public Class<? extends MaterialData> getData() {
        Optional<Block> opBlock = this.toBlock();
        if(!opBlock.isPresent()){
            return null;
        }
        return opBlock.get().getData();
    }

    @Override
    @Deprecated
    public MaterialData getNewData(byte raw) {
        Optional<Block> opBlock = this.toBlock();
        if(!opBlock.isPresent()){
            return null;
        }
        return opBlock.get().getNewData(raw);
    }

    @Override
    public boolean isBlock() {
        return this.toBlock().isPresent();
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
        Optional<Block> opBlock = this.toBlock();
        if(!opBlock.isPresent()){
            return false;
        }
        return opBlock.get().isSolid();
    }

    @Override
    public boolean isTransparent() {
        Optional<Block> opBlock = this.toBlock();
        if(!opBlock.isPresent()){
            return false;
        }
        return opBlock.get().isTransparent();
    }

    @Override
    public boolean isFlammable() {
        Optional<Block> opBlock = this.toBlock();
        if(!opBlock.isPresent()){
            return false;
        }
        return opBlock.get().isFlammable();
    }

    @Override
    public boolean isBurnable() {
        Optional<Block> opBlock = this.toBlock();
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
        Optional<Block> opBlock = this.toBlock();
        if(!opBlock.isPresent()){
            return false;
        }
        return opBlock.get().isOccluding();
    }

    @Override
    public boolean hasGravity() {
        Optional<Block> opBlock = this.toBlock();
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
        Optional<Block> opBlock = this.toBlock();
        if(!opBlock.isPresent()){
            return false;
        }
        return opBlock.get().isInteractable();
    }

    @Override
    public float getHardness() {
        Optional<Block> opBlock = this.toBlock();
        if(!opBlock.isPresent()){
            return 0;
        }
        return opBlock.get().getHardness();
    }

    @Override
    public float getBlastResistance() {
        Optional<Block> opBlock = this.toBlock();
        if(!opBlock.isPresent()){
            return 0;
        }
        return opBlock.get().getBlastResistance();
    }

    @Override
    public BlockData createBlockData() {
        Optional<Block> opMaterial = this.toBlock();
        if(opMaterial.isPresent()){
            return opMaterial.get().createBlockData();
        }
        return null;
    }

    @Override
    public BlockData createBlockData(Consumer<BlockData> consumer) {
        Optional<Block> opMaterial = this.toBlock();
        if(opMaterial.isPresent()){
            return opMaterial.get().createBlockData(consumer);
        }
        return null;
    }

    @Override
    public BlockData createBlockData(String data) throws IllegalArgumentException {
        Optional<Block> opMaterial = this.toBlock();
        if(opMaterial.isPresent()){
            return opMaterial.get().createBlockData(data);
        }
        return null;
    }
}
