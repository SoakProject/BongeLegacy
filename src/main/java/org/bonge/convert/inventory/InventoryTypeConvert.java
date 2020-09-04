package org.bonge.convert.inventory;

import org.bonge.convert.Converter;
import org.bonge.util.exception.NotImplementedException;
import org.bukkit.event.inventory.InventoryType;
import org.spongepowered.api.item.inventory.ContainerType;
import org.spongepowered.api.item.inventory.ContainerTypes;

import java.io.IOException;

public class InventoryTypeConvert implements Converter<InventoryType, ContainerType> {
    @Override
    public Class<ContainerType> getSpongeClass() {
        return ContainerType.class;
    }

    @Override
    public Class<InventoryType> getBukkitClass() {
        return InventoryType.class;
    }

    @Override
    public ContainerType from(InventoryType value) throws IOException {
        switch (value){
            case CHEST:
                return ContainerTypes.GENERIC_9x6.get();
            case DISPENSER:
            case DROPPER:
                return ContainerTypes.GENERIC_3x3.get();
            case FURNACE:
                return ContainerTypes.FURNACE.get();
            case WORKBENCH:
            case CRAFTING:
                return ContainerTypes.CRAFTING.get();
            case ENCHANTING:
                return ContainerTypes.ENCHANTMENT.get();
            case BREWING:
                return ContainerTypes.BREWING_STAND.get();
            case PLAYER:
                throw new NotImplementedException("Unknown PlayerInventory type for Sponge");
            case CREATIVE:
                throw new NotImplementedException("Unknown CreativeInventory type for Sponge");
            case MERCHANT:
                return ContainerTypes.MERCHANT.get();
            case ENDER_CHEST:
            case BARREL:
                return ContainerTypes.GENERIC_9x3.get();
            case ANVIL:
                return ContainerTypes.ANVIL.get();
            case BEACON:
                return ContainerTypes.BEACON.get();
            case HOPPER:
                return ContainerTypes.HOPPER.get();
            case SHULKER_BOX:
                return ContainerTypes.SHULKER_BOX.get();
            case BLAST_FURNACE:
                return ContainerTypes.BLAST_FURNACE.get();
            case LECTERN:
                return ContainerTypes.LECTERN.get();
            case SMOKER:
                return ContainerTypes.SMOKER.get();
            case LOOM:
                return ContainerTypes.LOOM.get();
            case CARTOGRAPHY:
                return ContainerTypes.CARTOGRAPHY_TABLE.get();
            case GRINDSTONE:
                return ContainerTypes.GRINDSTONE.get();
            case STONECUTTER:
                return ContainerTypes.STONECUTTER.get();
            default:
                throw new IOException("Unknown Bukkit InventoryType of " + value.name());
        }
    }

    @Override
    public InventoryType to(ContainerType value) throws IOException {
        return null;
    }
}
