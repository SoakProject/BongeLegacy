package org.bonge.convert.inventory;

import org.bonge.bukkit.r1_15.inventory.BongeInventory;
import org.bonge.bukkit.r1_15.inventory.chest.BongeChestInventory;
import org.bonge.bukkit.r1_15.inventory.chest.CustomChestInventory;
import org.bonge.bukkit.r1_15.inventory.entity.living.player.BongePlayerInventory;
import org.bonge.convert.Converter;
import org.bukkit.inventory.Inventory;
import org.spongepowered.api.block.entity.carrier.chest.Chest;
import org.spongepowered.api.item.inventory.Container;
import org.spongepowered.api.item.inventory.ContainerType;
import org.spongepowered.api.item.inventory.ContainerTypes;
import org.spongepowered.api.item.inventory.entity.PlayerInventory;
import org.spongepowered.api.item.inventory.type.BlockEntityInventory;
import org.spongepowered.api.item.inventory.type.ViewableInventory;

import java.io.IOException;

public class InventoryConvert implements Converter<Inventory, org.spongepowered.api.item.inventory.Inventory> {
    @Override
    public Class<org.spongepowered.api.item.inventory.Inventory> getSpongeClass() {
        return org.spongepowered.api.item.inventory.Inventory.class;
    }

    @Override
    public Class<Inventory> getBukkitClass() {
        return Inventory.class;
    }

    @Override
    public org.spongepowered.api.item.inventory.Inventory from(Inventory value) {
        return ((BongeInventory<? extends org.spongepowered.api.item.inventory.Inventory>)value).getSpongeValue();
    }

    @Override
    public Inventory to(org.spongepowered.api.item.inventory.Inventory value) throws IOException {
        if(value instanceof PlayerInventory){
            return new BongePlayerInventory((PlayerInventory) value);
        }
        if(!(value instanceof Container)){
            throw new IOException("Unknown inventory");
        }
        Container container = (Container)value;
        ContainerType type = container.getType();
        if(type.equals(ContainerTypes.GENERIC_9x1) || type.equals(ContainerTypes.GENERIC_9x2) || type.equals(ContainerTypes.GENERIC_9x3) || type.equals(ContainerTypes.GENERIC_9x4) || type.equals(ContainerTypes.GENERIC_9x5) || type.equals(ContainerTypes.GENERIC_9x6)){
            if(value instanceof BlockEntityInventory && ((BlockEntityInventory<Chest>)value).getBlockEntity().isPresent()){
                return new BongeChestInventory((BlockEntityInventory<Chest>) value);
            }else if (value instanceof ViewableInventory){
                return new CustomChestInventory((ViewableInventory) value);
            }
            throw new IOException("Wait what? That inventory was a chest like inventory but wasn't connected to a chest nor is it viewable????");
        }
        throw new IOException("Unknown inventory");
    }
}
