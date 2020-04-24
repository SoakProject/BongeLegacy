package org.bonge.convert;

import org.bonge.bukkit.r1_13.block.state.BongeBlockState;
import org.bonge.bukkit.r1_13.inventory.inventory.BongeCustomInventory;
import org.bonge.bukkit.r1_13.inventory.inventory.tileentity.chest.BongeChestInventory;
import org.bonge.util.ArrayUtils;
import org.bonge.util.exception.NotImplementedException;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.spongepowered.api.block.tileentity.TileEntity;
import org.spongepowered.api.block.tileentity.TileEntityTypes;
import org.spongepowered.api.block.tileentity.carrier.Chest;
import org.spongepowered.api.block.tileentity.carrier.TileEntityCarrier;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.item.ItemType;
import org.spongepowered.api.item.inventory.Container;
import org.spongepowered.api.item.inventory.InventoryArchetype;
import org.spongepowered.api.item.inventory.InventoryArchetypes;
import org.spongepowered.api.item.inventory.Slot;
import org.spongepowered.api.item.inventory.slot.*;
import org.spongepowered.api.item.inventory.type.TileEntityInventory;

import java.util.Optional;

public class InventoryConvert {

    public static Inventory getInventory(org.spongepowered.api.item.inventory.Inventory inventory){
        return getInventory(null, inventory);
    }

    public static Inventory getInventory(InventoryHolder holder, org.spongepowered.api.item.inventory.Inventory inventory){
        InventoryArchetype ia = inventory.getArchetype();
        if(inventory instanceof TileEntityInventory){
            TileEntityInventory<? extends TileEntity> tei = ((TileEntityInventory) inventory);
            if(tei.getTileEntity().isPresent()){
                TileEntityCarrier tileEntity = (TileEntityCarrier) tei.getTileEntity().get();
                return getInventory(holder, tileEntity);
            }
        }
        return new BongeCustomInventory(inventory);
    }

    public static Inventory getInventory(BongeBlockState<? extends org.spongepowered.api.block.tileentity.carrier.TileEntityCarrier> tileEntity){
        return getInventory((InventoryHolder) tileEntity, tileEntity.getSpongeValue().getInventory());
    }

    public static Inventory getInventory(InventoryHolder holder, org.spongepowered.api.block.tileentity.carrier.TileEntityCarrier te){
        if(te.getType().equals(TileEntityTypes.CHEST)){
            TileEntityInventory<org.spongepowered.api.block.tileentity.carrier.Chest> tei = (TileEntityInventory<org.spongepowered.api.block.tileentity.carrier.Chest>)(Object)te.getInventory();
            return new BongeChestInventory(holder, tei);
        }
        throw new NotImplementedException("Unknown Inventory convert for " + te.getType().getId());
    }

    public static InventoryType.SlotType getSlotType(Slot slot){
        if(slot instanceof EquipmentSlot){
            return InventoryType.SlotType.ARMOR;
        }else if(slot instanceof FuelSlot){
            return InventoryType.SlotType.FUEL;
        }else if(slot instanceof InputSlot){
            return InventoryType.SlotType.CRAFTING;
        }else if(slot instanceof OutputSlot){
            return InventoryType.SlotType.RESULT;
        }
        return InventoryType.SlotType.CONTAINER;
    }

    public static org.spongepowered.api.item.inventory.InventoryArchetype getInventoryType(InventoryType type){
        switch (type){
            case CHEST:
            case ENDER_CHEST:
            case SHULKER_BOX:
                return InventoryArchetypes.CHEST;
            case DISPENSER:
            case DROPPER:
                return InventoryArchetypes.DISPENSER;
            case FURNACE: return InventoryArchetypes.FURNACE;
            case WORKBENCH: return InventoryArchetypes.WORKBENCH;
            case CRAFTING: return InventoryArchetypes.CRAFTING;
            case ENCHANTING: return InventoryArchetypes.ENCHANTING_TABLE;
            case BREWING: return InventoryArchetypes.BREWING_STAND;
            case PLAYER: return InventoryArchetypes.PLAYER;
            case CREATIVE: return InventoryArchetypes.UNKNOWN;
            case ANVIL: return InventoryArchetypes.ANVIL;
            case BEACON: return InventoryArchetypes.BEACON;
            case HOPPER: return InventoryArchetypes.HOPPER;
            default:
                throw new IllegalStateException("Unknown inventory type of " + type.name());
        }
    }

    public static Optional<org.spongepowered.api.item.inventory.ItemStack> getItemStack(ItemStack stack){
        Optional<ItemType> opItemType = stack.getType().getSpongeItemValue();
        if(!opItemType.isPresent()){
            return Optional.empty();
        }
        org.spongepowered.api.item.inventory.ItemStack.Builder item = org.spongepowered.api.item.inventory.ItemStack.builder()
                .itemType(opItemType.get())
                .quantity(stack.getAmount());
        ItemMeta meta = stack.getItemMeta();
        item.add(Keys.UNBREAKABLE, meta.isUnbreakable());
        if (meta.hasDisplayName()){
            item.add(Keys.DISPLAY_NAME, InterfaceConvert.fromString(meta.getDisplayName()));
        }
        if(meta.hasLore()){
            item.add(Keys.ITEM_LORE, ArrayUtils.convert(s -> InterfaceConvert.fromString(s), meta.getLore()));
        }
        return Optional.of(item.build());
    }

    public static ItemStack getItemStack(org.spongepowered.api.item.inventory.ItemStack stack){
        ItemStack stack2 = new ItemStack(Material.getMaterial(stack.getType()), stack.getQuantity());
        ItemMeta meta = stack2.getItemMeta();
        meta.setDisplayName(InterfaceConvert.toString(stack.get(Keys.DISPLAY_NAME).orElse(null)));
        stack.get(Keys.UNBREAKABLE).ifPresent(meta::setUnbreakable);
        stack.get(Keys.DISPLAY_NAME).ifPresent(t -> meta.setDisplayName(InterfaceConvert.toString(t)));
        stack.get(Keys.ITEM_LORE).ifPresent(t -> meta.setLore(ArrayUtils.convert(s -> InterfaceConvert.toString(s), t)));
        stack2.setItemMeta(meta);
        return stack2;
    }

    public static ItemStack getItemStack(org.spongepowered.api.item.inventory.ItemStackSnapshot stack){
        return getItemStack(stack.createStack());
    }
}
