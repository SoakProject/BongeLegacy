package org.bonge.listeners;

import org.bonge.bukkit.entity.living.human.BongePlayer;
import org.bonge.bukkit.inventory.inventory.BongeAbstractInventory;
import org.bonge.bukkit.inventory.inventory.BongeInventoryView;
import org.bonge.convert.InventoryConvert;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.spongepowered.api.data.Transaction;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.filter.cause.First;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.inventory.ItemStackSnapshot;
import org.spongepowered.api.item.inventory.property.SlotIndex;
import org.spongepowered.api.item.inventory.transaction.SlotTransaction;

import java.util.ArrayList;
import java.util.List;

public class InventoryListener {

    @org.spongepowered.api.event.Listener
    public void onPlayerClick(ClickInventoryEvent.Primary event, @First Player sPlayer){
        BongePlayer player = BongePlayer.getPlayer(sPlayer);
        BongeInventoryView view = player.getOpenInventory();
        if(view == null){
            view = new BongeInventoryView(player, InventoryConvert.getInventory(event.getTargetInventory()));
        }else if(!((BongeAbstractInventory)view.getTopInventory()).getSpongeInventoryValue().equals(event.getTargetInventory())){
            view = new BongeInventoryView(player, InventoryConvert.getInventory(event.getTargetInventory()));
        }
        if(!event.getCursorTransaction().getFinal().equals(ItemStackSnapshot.NONE)){
            view.setCursorO(InventoryConvert.getItemStack(event.getCursorTransaction().getFinal()));
        }
        view.setEventReference(event);
        for (int A = 0; A < event.getTransactions().size(); A++){
            SlotTransaction transaction = event.getTransactions().get(A);
            InventoryAction action = null;
            Transaction<ItemStackSnapshot> cursorTrans = event.getCursorTransaction();
            if(cursorTrans.getOriginal().equals(ItemStackSnapshot.NONE) && !cursorTrans.getFinal().equals(ItemStackSnapshot.NONE)){
                action = InventoryAction.PICKUP_ALL;
            }else if(!cursorTrans.getOriginal().equals(ItemStackSnapshot.NONE) && cursorTrans.getFinal().equals(ItemStackSnapshot.NONE)) {
                action = InventoryAction.PLACE_ALL;
            }else if(cursorTrans.getOriginal().equals(ItemStackSnapshot.NONE) && cursorTrans.getFinal().equals(ItemStackSnapshot.NONE)) {
                action = InventoryAction.UNKNOWN;
            }else{
                System.err.println("Could not work out InventoryAction:");
                System.err.println("Original: " + cursorTrans.getOriginal());
                System.err.println("Final: " + cursorTrans.getFinal());
                action = InventoryAction.UNKNOWN;
            }
            InventoryClickEvent bEvent = new InventoryClickEvent(view, InventoryConvert.getSlotType(transaction.getSlot()), transaction.getSlot().getInventoryProperty(SlotIndex.class).get().getValue(), ClickType.RIGHT, action);
            Bukkit.getPluginManager().callEvent(bEvent);
            if(bEvent.isCancelled()){
                if(action.equals(InventoryAction.PICKUP_ALL)){
                    transaction.setCustom(transaction.getOriginal());
                    cursorTrans.setCustom(cursorTrans.getOriginal());
                }
               continue;
            }
            transaction.setCustom(InventoryConvert.getItemStack(bEvent.getCurrentItem()).get());
        }
        view.setEventReference(null);
    }
}
