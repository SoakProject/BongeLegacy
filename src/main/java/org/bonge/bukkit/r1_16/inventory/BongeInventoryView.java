package org.bonge.bukkit.r1_16.inventory;

import org.bonge.Bonge;
import org.bonge.bukkit.r1_16.entity.living.human.BongePlayer;
import org.bonge.bukkit.r1_16.inventory.item.holder.ItemHolder;
import org.bonge.bukkit.r1_16.inventory.item.holder.ItemStackHolder;
import org.bonge.bukkit.r1_16.inventory.item.holder.ItemStackSnapshotHolder;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.data.Keys;
import org.spongepowered.api.entity.living.player.server.ServerPlayer;
import org.spongepowered.api.item.inventory.Container;

import java.util.Optional;

public class BongeInventoryView extends InventoryView {

    private final BongePlayer player;
    private final BongeInventory<? extends org.spongepowered.api.item.inventory.Inventory> top;
    private final Container topContainer;

    public BongeInventoryView(BongePlayer player, BongeInventory<? extends org.spongepowered.api.item.inventory.Inventory> top, Container container){
        if(!(player.getSpongeValue() instanceof ServerPlayer)){
            throw new IllegalStateException("InventoryView must have a ServerPlayer");
        }
        this.player = player;
        this.top = top;
        this.topContainer = container;
    }

    public ItemStack getCursor0(){
        Optional<org.spongepowered.api.item.inventory.ItemStack> opStack = this.topContainer.cursor();
        return opStack.map(ItemStack::new).orElse(null);
    }

    public void setCursor0(ItemStack stack){
        ItemHolder holder = stack.getItemMeta().getHolder();
        if(holder instanceof ItemStackSnapshotHolder){
            this.topContainer.setCursor(((ItemStackSnapshotHolder) holder).getSpongeValue().createStack());
        }else if(holder instanceof ItemStackHolder){
            this.topContainer.setCursor(((ItemStackHolder) holder).getSpongeValue());
        }
    }

    @Override
    public @NotNull Inventory getTopInventory() {
        return this.top;
    }

    @Override
    public @NotNull PlayerInventory getBottomInventory() {
        return this.player.getInventory();
    }

    @Override
    public @NotNull BongePlayer getPlayer() {
        return this.player;
    }

    @Override
    public @NotNull InventoryType getType() {
        return this.top.getType();
    }

    @Override
    public @NotNull String getTitle() {
        //TODO make sure this is correct
        return Bonge.getInstance().convert(this.top.getSpongeValue().get(Keys.DISPLAY_NAME).get());
    }
}
