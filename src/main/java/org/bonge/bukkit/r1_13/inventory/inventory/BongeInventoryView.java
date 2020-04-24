package org.bonge.bukkit.r1_13.inventory.inventory;

import org.bonge.bukkit.r1_13.entity.living.human.BongePlayer;
import org.bonge.convert.InventoryConvert;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.spongepowered.api.event.item.inventory.AffectSlotEvent;
import org.spongepowered.api.item.inventory.Container;
import org.spongepowered.api.item.inventory.property.SlotIndex;
import org.spongepowered.api.item.inventory.transaction.SlotTransaction;

import java.util.Optional;

public class BongeInventoryView extends InventoryView {

    private BongePlayer player;
    private Inventory topInventory;
    private ItemStack cursor;
    private AffectSlotEvent eventReference;

    public BongeInventoryView(BongePlayer player, Inventory topInventory){
        this.player = player;
        this.topInventory = topInventory;
    }

    public void setEventReference(AffectSlotEvent eventReference){
        this.eventReference = eventReference;
    }

    @Override
    public Inventory getTopInventory() {
        return this.topInventory;
    }

    @Override
    public Inventory getBottomInventory() {
        return this.player.getInventory();
    }

    @Override
    public HumanEntity getPlayer() {
        return this.player;
    }

    //DARN IT BUKKIT - why have the item in cursor connected to the player?
    public ItemStack getCursorO(){
        return this.cursor;
    }

    public void setCursorO(ItemStack stack){
        this.cursor = stack;
    }

    @Override
    public ItemStack getItem(int slot){
        Inventory top = this.getTopInventory();
        if(top.getSize() >= slot) {
            if(this.eventReference != null){
                Optional<SlotTransaction> opTransaction = this.eventReference.getTransactions().stream().filter(t -> t.getSlot().getInventoryProperty(SlotIndex.class).get().getValue() == slot).findAny();
                if(opTransaction.isPresent()){
                    ItemStack item = InventoryConvert.getItemStack(opTransaction.get().getOriginal());
                    return item;
                }
            }
            ItemStack item = top.getItem(slot);
            return item;
        }
        ItemStack item = this.getBottomInventory().getItem(slot - top.getSize());
        return item;
    }

    @Override
    public InventoryType getType() {
        return null;
    }
}
