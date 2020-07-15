package org.bonge.bukkit.r1_14.material.block;

import org.bonge.bukkit.r1_14.block.data.BongeAbstractBlockData;
import org.bonge.bukkit.r1_14.material.BongeMaterial;
import org.bonge.bukkit.r1_14.material.item.ItemMaterial;
import org.bonge.bukkit.r1_14.material.SpongeMaterialFix;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.data.BlockData;
import org.bukkit.material.MaterialData;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.block.BlockType;
import org.spongepowered.api.data.Keys;
import org.spongepowered.api.data.type.MatterState;
import org.spongepowered.api.data.type.MatterStates;
import org.spongepowered.api.item.ItemType;

import java.util.Optional;
import java.util.function.Consumer;

public class BlockMaterial extends Material implements BongeMaterial.Block {

    private final String name;
    private final BlockType spongeValue;

    public BlockMaterial(BlockType type, String name){
        this.spongeValue = type;
        this.name = name;
    }

    public BlockType getSpongeBlockType(){
        return this.spongeValue;
    }

    @Override
    public Optional<Item> toItem(){
        Optional<ItemType> opItem = this.spongeValue.getItem();
        if(!opItem.isPresent()){
            Optional<SpongeMaterialFix> opFix = SpongeMaterialFix.get(this);
            return opFix.map(SpongeMaterialFix::getItem);
        }
        return Optional.of(new ItemMaterial(opItem.get(), this.name));
    }

    @Override
    public Optional<Block> toBlock(){
        return Optional.of(this);
    }

    @Override
    public @NotNull NamespacedKey getKey() {
        return NamespacedKey.minecraft(this.spongeValue.getKey().getValue().substring(10));

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
        return BongeAbstractBlockData.of(this.spongeValue.getDefaultState());
    }

    @Override
    public BlockData createBlockData(Consumer<BlockData> consumer) {
        BlockData data = this.createBlockData();
        consumer.accept(data);
        return data;
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

        Optional<MatterState> opProp = this.spongeValue.get(Keys.MATTER_STATE);
        return opProp.map(matterState -> matterState.equals(MatterStates.SOLID.get())).orElse(false);
    }

    @Override
    public boolean isTransparent() {
        return false;
    }

    @Override
    public boolean isFlammable() {
        return this.spongeValue.get(Keys.IS_FLAMMABLE).orElse(false);
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
        return this.spongeValue.get(Keys.IS_GRAVITY_AFFECTED).orElse(false);
    }

    @Override
    public boolean isItem() {
        return this.toItem().isPresent();
    }

    @Override
    public boolean isInteractable() {
        //TODO
        return false;
    }

    @Override
    public float getHardness() {
        Optional<Double> opProp = this.spongeValue.get(Keys.HARDNESS);
        return opProp.map(Double::floatValue).orElse(0F);
    }

    @Override
    public float getBlastResistance() {
        Optional<Double> opProp = this.spongeValue.get(Keys.BLAST_RESISTANCE);
        return opProp.map(Double::floatValue).orElse(0F);
    }
}
