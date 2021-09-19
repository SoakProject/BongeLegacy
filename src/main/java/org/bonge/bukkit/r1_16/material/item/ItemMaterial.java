package org.bonge.bukkit.r1_16.material.item;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bonge.bukkit.r1_16.material.BongeMaterial;
import org.bonge.bukkit.r1_16.material.SpongeMaterialFix;
import org.bonge.bukkit.r1_16.material.block.BlockMaterial;
import org.bukkit.NamespacedKey;
import org.bukkit.block.data.BlockData;
import org.bukkit.material.MaterialData;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.ResourceKey;
import org.spongepowered.api.block.BlockType;
import org.spongepowered.api.data.Keys;
import org.spongepowered.api.item.ItemType;
import org.spongepowered.api.registry.RegistryTypes;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

public class ItemMaterial implements BongeMaterial.Item {

    private final String name;
    private final ItemType spongeValue;

    public ItemMaterial(ItemType type, String name) {
        this.spongeValue = type;
        this.name = name;
    }

    @Override
    public ItemType getSpongeItemType() {
        return this.spongeValue;
    }

    @Override
    public Optional<Block> toBlock() {
        Optional<BlockType> opBlock = this.spongeValue.block();
        if (!opBlock.isPresent()) {
            Optional<SpongeMaterialFix> opFix = SpongeMaterialFix.get(this);
            return opFix.map(SpongeMaterialFix::getBlock);
        }
        return Optional.of(new BlockMaterial(opBlock.get(), this.name));
    }

    public Optional<Item> toItem() {
        return Optional.of(this);
    }

    @Override
    public @NotNull NamespacedKey getKey() {
        Optional<ResourceKey> opKey = this.spongeValue.findKey(RegistryTypes.ITEM_TYPE);
        if (!opKey.isPresent()) {
            Component component = this.spongeValue.asComponent();
            String plain = PlainTextComponentSerializer.plainText().serialize(component);
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
        return this.spongeValue.maxStackQuantity();
    }

    @Override
    public short getMaxDurability() {
        return this.spongeValue.get(Keys.ITEM_DURABILITY).orElse(-1).shortValue();
    }

    @Override
    @Deprecated
    public Class<? extends MaterialData> getData() {
        Optional<Block> opBlock = this.toBlock();
        return opBlock.<Class<? extends MaterialData>>map(BongeMaterial::getData).orElse(null);
    }

    @Override
    @Deprecated
    public MaterialData getNewData(byte raw) {
        Optional<Block> opBlock = this.toBlock();
        return opBlock.map(block -> block.getNewData(raw)).orElse(null);
    }

    @Override
    public boolean isBlock() {
        return this.toBlock().isPresent();
    }

    @Override
    public boolean isEdible() {
        return this.spongeValue.supports(Keys.REPLENISHED_FOOD);
    }

    @Override
    public boolean isRecord() {
        return this.spongeValue.supports(Keys.MUSIC_DISC);
    }

    @Override
    public boolean isSolid() {
        Optional<Block> opBlock = this.toBlock();
        return opBlock.map(BongeMaterial::isSolid).orElse(false);
    }

    @Override
    @Deprecated
    public boolean isTransparent() {
        Optional<Block> opBlock = this.toBlock();
        return opBlock.map(BongeMaterial::isTransparent).orElse(false);
    }

    @Override
    public boolean isFlammable() {
        Optional<Block> opBlock = this.toBlock();
        return opBlock.map(BongeMaterial::isFlammable).orElse(false);
    }

    @Override
    public boolean isBurnable() {
        Optional<Block> opBlock = this.toBlock();
        return opBlock.map(BongeMaterial::isBurnable).orElse(false);
    }

    @Override
    public boolean isFuel() {
        return this.spongeValue.supports(Keys.FUEL);
    }

    @Override
    public boolean isOccluding() {
        Optional<Block> opBlock = this.toBlock();
        return opBlock.map(BongeMaterial::isOccluding).orElse(false);
    }

    @Override
    public boolean hasGravity() {
        Optional<Block> opBlock = this.toBlock();
        return opBlock.map(BongeMaterial::hasGravity).orElse(false);
    }

    @Override
    public boolean isItem() {
        return true;
    }

    @Override
    public boolean isInteractable() {
        Optional<Block> opBlock = this.toBlock();
        return opBlock.map(BongeMaterial::isInteractable).orElse(false);
    }

    @Override
    public float getHardness() {
        Optional<Block> opBlock = this.toBlock();
        return opBlock.map(BongeMaterial::getHardness).orElse(0F);
    }

    @Override
    public float getBlastResistance() {
        Optional<Block> opBlock = this.toBlock();
        return opBlock.map(BongeMaterial::getBlastResistance).orElse(0F);
    }

    @Override
    public BlockData createBlockData() {
        Optional<Block> opMaterial = this.toBlock();
        return opMaterial.map(BongeMaterial::createBlockData).orElse(null);
    }

    @Override
    public BlockData createBlockData(Consumer<BlockData> consumer) {
        Optional<Block> opMaterial = this.toBlock();
        return opMaterial.map(block -> block.createBlockData(consumer)).orElse(null);
    }

    @Override
    public BlockData createBlockData(String data) throws IllegalArgumentException {
        Optional<Block> opMaterial = this.toBlock();
        return opMaterial.map(block -> block.createBlockData(data)).orElse(null);
    }

    @Override
    public int hashCode() {
        return (toString()).hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ItemMaterial)) {
            return false;
        }
        return this.hashCode() == obj.hashCode();
    }

    @Override
    public String toString() {
        return "Item:" + this.name;
    }
}
