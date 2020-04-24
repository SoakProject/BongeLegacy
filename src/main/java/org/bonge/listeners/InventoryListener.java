package org.bonge.listeners;

import org.bonge.bukkit.r1_13.block.state.BongeBlockState;
import org.bonge.bukkit.r1_13.entity.living.human.BongePlayer;
import org.bonge.bukkit.r1_13.inventory.inventory.BongeAbstractInventory;
import org.bonge.bukkit.r1_13.inventory.inventory.BongeInventoryView;
import org.bonge.convert.InventoryConvert;
import org.bukkit.Bukkit;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.InventoryHolder;
import org.spongepowered.api.block.BlockSnapshot;
import org.spongepowered.api.block.tileentity.TileEntity;
import org.spongepowered.api.block.tileentity.carrier.Chest;
import org.spongepowered.api.block.tileentity.carrier.TileEntityCarrier;
import org.spongepowered.api.data.Transaction;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.cause.EventContextKey;
import org.spongepowered.api.event.cause.EventContextKeys;
import org.spongepowered.api.event.filter.cause.First;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.event.item.inventory.InteractInventoryEvent;
import org.spongepowered.api.item.inventory.Inventory;
import org.spongepowered.api.item.inventory.ItemStackSnapshot;
import org.spongepowered.api.item.inventory.property.SlotIndex;
import org.spongepowered.api.item.inventory.transaction.SlotTransaction;

import java.util.Optional;

public class InventoryListener {

    @org.spongepowered.api.event.Listener
    public void onInventoryOpen(InteractInventoryEvent.Open event, @First Player player){
        Optional<BlockSnapshot> opBlock = event.getCause().getContext().get(EventContextKeys.BLOCK_HIT);
        BongeInventoryView biv = null;
        if(!opBlock.isPresent()){
            return;
        }
        if(!opBlock.get().getLocation().get().getTileEntity().isPresent()){
            return;
        }
        TileEntity tileEntity = opBlock.get().getLocation().get().getTileEntity().get();
        if(!(tileEntity instanceof TileEntityCarrier)){
            return;
        }
        TileEntityCarrier tec = (TileEntityCarrier) tileEntity;
        Optional<BongeBlockState<TileEntityCarrier>> opTile = BongeBlockState.of(tec);
        if(!opTile.isPresent()){
            return;
        }
        biv = new BongeInventoryView(BongePlayer.getPlayer(player), InventoryConvert.getInventory(opTile.get()));
        /*if(opBlock.isPresent() && opBlock.get().getLocation().get().getTileEntity().isPresent()){
            biv = new BongeInventoryView(BongePlayer.getPlayer(player), InventoryConvert.getInventory((InventoryHolder) BongeBlockState.of(opBlock.get().getLocation().get().getTileEntity().get()).orElse(null), event.getTargetInventory()));
        }else {
            biv = new BongeInventoryView(BongePlayer.getPlayer(player), InventoryConvert.getInventory(event.getTargetInventory()));
        }*/
        InventoryOpenEvent bEvent = new InventoryOpenEvent(biv);
        Bukkit.getPluginManager().callEvent(bEvent);
        event.setCancelled(bEvent.isCancelled());
    }

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
