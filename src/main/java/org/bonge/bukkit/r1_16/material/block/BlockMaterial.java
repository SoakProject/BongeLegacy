package org.bonge.bukkit.r1_16.material.block;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainComponentSerializer;
import org.bonge.bukkit.r1_16.block.data.BongeAbstractBlockData;
import org.bonge.bukkit.r1_16.material.BongeMaterial;
import org.bonge.bukkit.r1_16.material.SpongeMaterialFix;
import org.bonge.bukkit.r1_16.material.item.ItemMaterial;
import org.bonge.util.exception.NotImplementedException;
import org.bukkit.NamespacedKey;
import org.bukkit.block.data.BlockData;
import org.bukkit.material.MaterialData;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.ResourceKey;
import org.spongepowered.api.block.BlockType;
import org.spongepowered.api.data.Keys;
import org.spongepowered.api.data.type.MatterType;
import org.spongepowered.api.data.type.MatterTypes;
import org.spongepowered.api.item.ItemType;
import org.spongepowered.api.registry.RegistryTypes;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

public class BlockMaterial implements BongeMaterial.Block {

    private final String name;
    private final BlockType spongeValue;

    public BlockMaterial(BlockType type, String name) {
        this.spongeValue = type;
        this.name = name;
    }

    public BlockType getSpongeBlockType() {
        return this.spongeValue;
    }

    @Override
    public Optional<Item> toItem() {
        Optional<ItemType> opItem = this.spongeValue.item();
        if (!opItem.isPresent()) {
            Optional<SpongeMaterialFix> opFix = SpongeMaterialFix.get(this);
            return opFix.map(SpongeMaterialFix::getItem);
        }
        return Optional.of(new ItemMaterial(opItem.get(), this.name));
    }

    @Override
    public Optional<Block> toBlock() {
        return Optional.of(this);
    }

    @Override
    public @NotNull NamespacedKey getKey() {
        Optional<ResourceKey> opKey = this.spongeValue.findKey(RegistryTypes.BLOCK_TYPE);
        if (!opKey.isPresent()) {
            Component component = this.spongeValue.asComponent();
            String plain = PlainComponentSerializer.plain().serialize(component);
            throw new IllegalStateException("BlockType of " + plain + " does not have a ID");
        }
        return Objects.requireNonNull(NamespacedKey.fromString(opKey.get().asString()));
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
        try {
            return BongeAbstractBlockData.findDynamicClass(this.spongeValue.defaultState());
        } catch (InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
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
        return getNewData((byte) 0).getClass();
    }

    @Override
    public MaterialData getNewData(byte raw) {
        return MaterialData.getData(this.spongeValue.defaultState());
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

        Optional<MatterType> opMatter = this.spongeValue.get(Keys.MATTER_TYPE);
        return opMatter.map(matterType -> matterType == MatterTypes.SOLID.get()).orElse(true);
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
        /*Optional<Double> opProp = this.spongeValue.get(Keys.HARDNESS);
        return opProp.map(Double::floatValue).orElse(0F);*/
        throw new NotImplementedException("Material.getHardness() not found alternative");
    }

    @Override
    public float getBlastResistance() {
        Optional<Double> opProp = this.spongeValue.get(Keys.BLAST_RESISTANCE);
        return opProp.map(Double::floatValue).orElse(0F);
    }

    @Override
    public int hashCode() {
        return (toString()).hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof BlockMaterial)) {
            return false;
        }
        return this.hashCode() == obj.hashCode();
    }

    @Override
    public String toString() {
        return "Block:" + this.name;
    }
}
