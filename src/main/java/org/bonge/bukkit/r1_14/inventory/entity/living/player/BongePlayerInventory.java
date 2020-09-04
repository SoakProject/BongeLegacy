package org.bonge.bukkit.r1_14.inventory.entity.living.player;


import org.bonge.Bonge;
import org.bonge.bukkit.r1_14.entity.living.human.BongePlayer;
import org.bonge.bukkit.r1_14.inventory.BongeInventory;
import org.bonge.bukkit.r1_14.inventory.item.holder.ItemHolder;
import org.bonge.bukkit.r1_14.inventory.item.holder.ItemStackHolder;
import org.bonge.bukkit.r1_14.inventory.item.holder.ItemStackSnapshotHolder;
import org.bonge.util.exception.NotImplementedException;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.item.inventory.equipment.EquipmentType;
import org.spongepowered.api.item.inventory.equipment.EquipmentTypes;

import java.util.Optional;
import java.util.function.Supplier;

public class BongePlayerInventory extends BongeInventory<org.spongepowered.api.item.inventory.entity.PlayerInventory> implements CommonPlayerInventory {

    public BongePlayerInventory(org.spongepowered.api.item.inventory.entity.PlayerInventory value) {
        super(value);
    }

    @Override
    public @NotNull ItemStack[] getExtraContents() {
        return new ItemStack[0];
    }

    private @Nullable ItemStack getEquipment(Supplier<? extends EquipmentType> type){
        Optional<org.spongepowered.api.item.inventory.ItemStack> opItem = this.spongeValue.getEquipment().peek(type);
        if(!opItem.isPresent()){
            return null;
        }
        if(opItem.get().equalTo(org.spongepowered.api.item.inventory.ItemStack.empty())){
            return null;
        }
        return new ItemStack(opItem.get());
    }

    @Override
    public @Nullable ItemStack getHelmet() {
        return this.getEquipment(EquipmentTypes.HEAD);
    }

    @Override
    public @Nullable ItemStack getChestplate() {
        return this.getEquipment(EquipmentTypes.CHEST);
    }

    @Override
    public @Nullable ItemStack getLeggings() {
        return this.getEquipment(EquipmentTypes.LEGS);
    }

    @Override
    public @Nullable ItemStack getBoots() {
        return this.getEquipment(EquipmentTypes.FEET);
    }

    @Override
    public void setExtraContents(@Nullable ItemStack[] items) {
        throw new NotImplementedException("PlayerInventory.setExtraContents(ItemStack[]) Not got to yet");
    }

    private void setEquipment(Supplier<? extends EquipmentType> type, ItemStack stack){
        if(stack == null) {
            this.spongeValue.getEquipment().set(type, org.spongepowered.api.item.inventory.ItemStack.empty());
            return;
        }
        ItemHolder holder = stack.getItemMeta().getHolder();
        org.spongepowered.api.item.inventory.ItemStack stack1;
        if(holder instanceof ItemStackSnapshotHolder){
            stack1 = ((ItemStackSnapshotHolder)holder).getSpongeValue().createStack();
        }else{
            stack1 = ((ItemStackHolder)holder).getSpongeValue();
        }
        this.spongeValue.getEquipment().set(type, stack1);
    }

    @Override
    public void setHelmet(@Nullable ItemStack helmet) {
        this.setEquipment(EquipmentTypes.HEAD, helmet);
    }

    @Override
    public void setChestplate(@Nullable ItemStack chestplate) {
        this.setEquipment(EquipmentTypes.CHEST, chestplate);
    }

    @Override
    public void setLeggings(@Nullable ItemStack leggings) {
        this.setEquipment(EquipmentTypes.LEGS, leggings);
    }

    @Override
    public void setBoots(@Nullable ItemStack boots) {
        this.setEquipment(EquipmentTypes.FEET, boots);
    }

    @Override
    public @NotNull ItemStack getItemInMainHand() {
        return this.getEquipment(EquipmentTypes.MAIN_HAND);
    }

    @Override
    public void setItemInMainHand(@Nullable ItemStack item) {
        this.setEquipment(EquipmentTypes.MAIN_HAND, item);
    }

    @Override
    public @NotNull ItemStack getItemInOffHand() {
        return this.getEquipment(EquipmentTypes.OFF_HAND);
    }

    @Override
    public void setItemInOffHand(@Nullable ItemStack item) {
        this.setEquipment(EquipmentTypes.OFF_HAND, item);
    }

    @Override
    public int getHeldItemSlot() {
        return this.spongeValue.getHotbar().getSelectedSlotIndex();
    }

    @Override
    public void setHeldItemSlot(int slot) {
        this.spongeValue.getHotbar().setSelectedSlotIndex(slot);
    }

    @Override
    public @Nullable BongePlayer getHolder() {
        Optional<Player> opPlayer = this.spongeValue.getCarrier();
        return opPlayer.map(player -> Bonge.getInstance().convert(player)).orElse(null);
    }
}
