package org.bonge.listeners;

import org.bonge.Bonge;
import org.bonge.bukkit.r1_14.entity.living.human.BongePlayer;
import org.bonge.bukkit.r1_14.inventory.BongeInventory;
import org.bonge.bukkit.r1_14.inventory.BongeInventoryView;
import org.bukkit.Bukkit;
import org.bukkit.block.BlockState;
import org.bukkit.block.Container;
import org.bukkit.entity.Item;
import org.bukkit.event.entity.EntityDropItemEvent;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;
import org.spongepowered.api.block.BlockSnapshot;
import org.spongepowered.api.block.entity.BlockEntity;
import org.spongepowered.api.block.entity.carrier.CarrierBlockEntity;
import org.spongepowered.api.data.Transaction;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.EventContextKeys;
import org.spongepowered.api.event.filter.cause.First;
import org.spongepowered.api.event.item.inventory.DropItemEvent;
import org.spongepowered.api.event.item.inventory.container.ClickContainerEvent;
import org.spongepowered.api.event.item.inventory.container.InteractContainerEvent;
import org.spongepowered.api.item.inventory.Inventory;
import org.spongepowered.api.item.inventory.ItemStackSnapshot;
import org.spongepowered.api.item.inventory.transaction.SlotTransaction;

import java.io.IOException;
import java.util.Optional;

public class InventoryListener {

    @org.spongepowered.api.event.Listener
    public void onDropItem(DropItemEvent.Destruct event){
        if(!(event.getSource() instanceof Entity)){
            return;
        }
        try {
            org.bukkit.entity.Entity entity = Bonge.getInstance().convert(org.bukkit.entity.Entity.class, event.getSource());
            for (Entity entity1 : event.filterEntities(e -> e instanceof org.spongepowered.api.entity.Item)) {
                Item item = (Item) Bonge.getInstance().convert(org.bukkit.entity.Entity.class, entity1);
                if (entity instanceof Player) {
                    PlayerDropItemEvent dropEvent = new PlayerDropItemEvent((org.bukkit.entity.Player) entity, item);
                    Bukkit.getPluginManager().callEvent(dropEvent);
                    event.setCancelled(dropEvent.isCancelled());
                } else {
                    EntityDropItemEvent dropEvent = new EntityDropItemEvent(entity, item);
                    Bukkit.getPluginManager().callEvent(dropEvent);
                    event.setCancelled(dropEvent.isCancelled());
                }
            }
        }catch (IOException e){

        }
    }

    @org.spongepowered.api.event.Listener
    public void onInventoryOpen(InteractContainerEvent.Open event, @First Player player){
        Optional<BlockSnapshot> opBlock = event.getCause().getContext().get(EventContextKeys.BLOCK_HIT);
        if(!opBlock.isPresent()){
            return;
        }
        if(!opBlock.get().getLocation().get().hasBlockEntity()){
            return;
        }
        BlockEntity blockEntity = opBlock.get().getLocation().get().getBlockEntity().get();
        if(!(blockEntity instanceof CarrierBlockEntity)){
            return;
        }
        CarrierBlockEntity cbe = (CarrierBlockEntity) blockEntity;
        BlockState bs;
        try {
            bs = Bonge.getInstance().convert(BlockState.class, cbe);
        } catch (IOException e) {
            return;
        }
        Container container = (Container)bs;
        BongeInventory<? extends Inventory> inv = (BongeInventory<? extends Inventory>) container.getInventory();
        BongePlayer bPlayer = BongePlayer.getPlayer(player);
        BongeInventoryView biv = new BongeInventoryView(bPlayer, inv, event.getContainer());
        bPlayer.setInventoryView(biv);
        InventoryOpenEvent bEvent = new InventoryOpenEvent(biv);
        Bukkit.getPluginManager().callEvent(bEvent);
        event.setCancelled(bEvent.isCancelled());
    }

    @org.spongepowered.api.event.Listener
    public void onPlayerClick(ClickContainerEvent.Primary event, @First Player sPlayer){
        BongePlayer player = BongePlayer.getPlayer(sPlayer);
        BongeInventoryView view = player.getOpenInventory();
        BongeInventory<org.spongepowered.api.item.inventory.Container> bInv;
        try {
            bInv = Bonge.getInstance().convert(event.getInventory());
        } catch (IOException e) {
            //NOT SURE HOW IT FAILS BUT JUST IN CASE
            throw new IllegalStateException(e);
        }
        if(view == null){
            //WELP ... SOMETHING WENT WRONG
            view = new BongeInventoryView(player, bInv, event.getContainer());
            player.setInventoryView(view);
        }
        if(!event.getCursorTransaction().getFinal().equals(ItemStackSnapshot.empty())){
            try {
                ItemStack stack = Bonge.getInstance().convert(ItemStack.class, event.getCursorTransaction().getFinal());
                view.setCursor0(stack);
            } catch (IOException e) {

            }
        }
        //view.setEventReference(event);
        for (int A = 0; A < event.getTransactions().size(); A++){
            SlotTransaction transaction = event.getTransactions().get(A);
            InventoryAction action = null;
            Transaction<ItemStackSnapshot> cursorTrans = event.getCursorTransaction();
            if(cursorTrans.getOriginal().equals(ItemStackSnapshot.empty()) && !cursorTrans.getFinal().equals(ItemStackSnapshot.empty())){
                action = InventoryAction.PICKUP_ALL;
            }else if(!cursorTrans.getOriginal().equals(ItemStackSnapshot.empty()) && cursorTrans.getFinal().equals(ItemStackSnapshot.empty())) {
                action = InventoryAction.PLACE_ALL;
            }else if(cursorTrans.getOriginal().equals(ItemStackSnapshot.empty()) && cursorTrans.getFinal().equals(ItemStackSnapshot.empty())) {
                action = InventoryAction.UNKNOWN;
            }else{
                System.err.println("Could not work out InventoryAction:");
                System.err.println("Original: " + cursorTrans.getOriginal());
                System.err.println("Final: " + cursorTrans.getFinal());
                action = InventoryAction.UNKNOWN;
            }
            //TODO - Find how to get SlotIndex from Slot
            /*InventoryClickEvent bEvent = new InventoryClickEvent(view, InventoryConvert.getSlotType(transaction.getSlot()), transaction.getSlot().getProperty(SlotIndex.class).get().getValue(), ClickType.RIGHT, action);
            Bukkit.getPluginManager().callEvent(bEvent);
            if(bEvent.isCancelled()){
                if(action.equals(InventoryAction.PICKUP_ALL)){
                    transaction.setCustom(transaction.getOriginal());
                    cursorTrans.setCustom(cursorTrans.getOriginal());
                }
               continue;
            }*/



            /*if(action.equals(InventoryAction.PLACE_ALL)) {
                try {
                    org.spongepowered.api.item.inventory.ItemStack item = Bonge.getInstance().convert(bEvent.getCurrentItem(), org.spongepowered.api.item.inventory.ItemStack.class);
                    transaction.setCustom(item);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }*/
        }
        //view.setEventReference(null);
    }
}
