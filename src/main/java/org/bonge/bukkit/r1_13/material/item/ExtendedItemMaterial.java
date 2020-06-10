package org.bonge.bukkit.r1_13.material.item;

import org.bonge.bukkit.r1_13.material.BongeMaterial;
import org.bonge.bukkit.r1_13.material.block.ExtendedBlockMaterial;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.data.BlockData;
import org.bukkit.material.MaterialData;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.block.BlockState;
import org.spongepowered.api.block.BlockType;
import org.spongepowered.api.data.key.Key;
import org.spongepowered.api.data.property.item.BurningFuelProperty;
import org.spongepowered.api.data.property.item.FoodRestorationProperty;
import org.spongepowered.api.data.property.item.RecordProperty;
import org.spongepowered.api.data.property.item.UseLimitProperty;
import org.spongepowered.api.data.value.BaseValue;
import org.spongepowered.api.item.ItemType;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.item.inventory.ItemStackSnapshot;

import java.io.IOException;
import java.util.Optional;
import java.util.function.Consumer;

public class ExtendedItemMaterial extends Material implements BongeMaterial.Item {

    private ItemStackSnapshot snapshot;
    private String name;

    public ExtendedItemMaterial(ItemStackSnapshot snapshot, String name){
        this.snapshot = snapshot;
        this.name = name;
    }

    @Override
    public ItemType getSpongeItemType() {
        return this.snapshot.getType();
    }

    @Override
    public Optional<Block> toBlock() {
        Optional<BlockType> opType = this.snapshot.getType().getBlock();
        if(!opType.isPresent()){
            return Optional.empty();
        }
        BlockState state = opType.get().getDefaultState();
        for (Key<? extends BaseValue<? extends Object>> key : this.snapshot.getKeys()){
            state = this.apply(state, this.snapshot, key);
        }
        return Optional.of(new ExtendedBlockMaterial(state, this.name));
    }
    
    private <E extends Object, V extends BaseValue<E>> BlockState apply(BlockState state, ItemStackSnapshot snapshot, Key<? extends BaseValue<?>> key1){
        Key<V> key = (Key<V>)key1;
        Optional<E> opValue = snapshot.get(key);
        if(opValue.isPresent()){
            return state.with(key, opValue.get()).get();
        }
        throw new IllegalArgumentException("Could not get or apply " + key.getId() + " to block");
    }

    @Override
    public Optional<Item> toItem() {
        return Optional.of(this);
    }

    @Override
    public @NotNull NamespacedKey getKey() {
        return NamespacedKey.minecraft(this.snapshot.getType().getId().substring(10));
    }

    @Override
    public String name() {
        return this.name;
    }

    @Override
    public int getMaxStackSize() {
        return this.snapshot.getType().getMaxStackQuantity();
    }

    @Override
    public short getMaxDurability() {
        return (this.snapshot.getType().getDefaultProperty(UseLimitProperty.class).get().getValue()).shortValue();
    }

    @Override
    public Class<? extends MaterialData> getData() {
        Optional<Block> opBlock = this.toBlock();
        if(!opBlock.isPresent()){
            return null;
        }
        return opBlock.get().getData();
    }

    @Override
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
        return this.snapshot.getType().getDefaultProperty(FoodRestorationProperty.class).isPresent();
    }

    @Override
    public boolean isRecord() {
        return this.snapshot.getType().getDefaultProperty(RecordProperty.class).isPresent();
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
        return this.snapshot.getType().getDefaultProperty(BurningFuelProperty.class).isPresent();
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
