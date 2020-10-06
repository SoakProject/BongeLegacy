package org.bonge.bukkit.r1_15.inventory.entity.living.player;

import org.bonge.bukkit.r1_15.entity.living.human.BongePlayer;
import org.bukkit.Location;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;

public interface CommonPlayerInventory extends PlayerInventory {

    @Override
    BongePlayer getHolder();

    @Override
    default @NotNull ItemStack[] getArmorContents() {
        return new ItemStack[]{this.getHelmet(), this.getChestplate(), this.getLeggings(), this.getBoots()};
    }

    @Override
    default void setArmorContents(@Nullable ItemStack[] items) {
        this.setHelmet(items[0]);
        this.setChestplate(items[1]);
        this.setLeggings(items[2]);
        this.setBoots(items[3]);
    }

    @Override
    default  @NotNull List<HumanEntity> getViewers() {
        return Collections.singletonList(getHolder());
    }

    @Override
    default  @NotNull InventoryType getType() {
        return InventoryType.PLAYER;
    }

    @Override
    default Location getLocation(){
        BongePlayer player = this.getHolder();
        if(player == null){
            return null;
        }
        return player.getLocation();
    }

    @Override
    @Deprecated
    default  @NotNull ItemStack getItemInHand() {
        return this.getItemInMainHand();
    }

    @Override
    @Deprecated
    default void setItemInHand(@Nullable ItemStack stack) {
        this.setItemInMainHand(stack);
    }
}
