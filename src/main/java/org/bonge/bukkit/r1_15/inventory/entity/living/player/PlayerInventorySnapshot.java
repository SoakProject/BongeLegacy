package org.bonge.bukkit.r1_15.inventory.entity.living.player;

import org.bonge.bukkit.r1_15.entity.living.human.BongePlayer;
import org.bonge.bukkit.r1_15.inventory.BongeInventorySnapshot;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.api.item.inventory.Inventory;
import org.spongepowered.api.item.inventory.entity.PlayerInventory;

public class PlayerInventorySnapshot extends BongeInventorySnapshot<Inventory> implements CommonPlayerInventory {

    public static final int HEAD_SLOT = 0;
    public static final int CHEST_SLOT = 0;
    public static final int LEG_SLOT = 0;
    public static final int BOOT_SLOT = 0;
    public static final int SIDE_SLOT = 0;

    private int hotBarSlot = 0;
    private final BongePlayer player;

    public PlayerInventorySnapshot(BongePlayer player, Inventory value) {
        super(value);
        this.player = player;
    }

    @Override
    public @NotNull ItemStack[] getExtraContents() {
        return new ItemStack[0];
    }

    @Override
    public @Nullable ItemStack getHelmet() {
        return this.getItem(HEAD_SLOT);
    }

    @Override
    public @Nullable ItemStack getChestplate() {
        return this.getItem(CHEST_SLOT);
    }

    @Override
    public @Nullable ItemStack getLeggings() {
        return this.getItem(LEG_SLOT);
    }

    @Override
    public @Nullable ItemStack getBoots() {
        return this.getItem(BOOT_SLOT);
    }

    @Override
    public void setExtraContents(@Nullable ItemStack[] items) {

    }

    @Override
    public void setHelmet(@Nullable ItemStack helmet) {
        this.setItem(HEAD_SLOT, helmet);
    }

    @Override
    public void setChestplate(@Nullable ItemStack chestplate) {
        this.setItem(CHEST_SLOT, chestplate);
    }

    @Override
    public void setLeggings(@Nullable ItemStack leggings) {
        this.setItem(LEG_SLOT, leggings);
    }

    @Override
    public void setBoots(@Nullable ItemStack boots) {
        this.setItem(BOOT_SLOT, boots);
    }

    @Override
    public @NotNull ItemStack getItemInMainHand() {
        return this.getItem(this.hotBarSlot);
    }

    @Override
    public void setItemInMainHand(@Nullable ItemStack item) {
        this.setItem(this.hotBarSlot, item);
    }

    @Override
    public @NotNull ItemStack getItemInOffHand() {
        return this.getItem(SIDE_SLOT);
    }

    @Override
    public void setItemInOffHand(@Nullable ItemStack item) {
        this.setItem(SIDE_SLOT, item);
    }

    @Override
    public int getHeldItemSlot() {
        return this.hotBarSlot;
    }

    @Override
    public void setHeldItemSlot(int slot) {
        this.hotBarSlot = slot;
    }

    @Override
    public @Nullable BongePlayer getHolder() {
        return this.player;
    }

    public static PlayerInventorySnapshot of(BongePlayer player){
        PlayerInventory inv = player.getSpongeValue().getInventory();
        Inventory inv2 = Inventory.builder().inventory(inv).completeStructure().build();
        return new PlayerInventorySnapshot(player, inv2);
    }
}
