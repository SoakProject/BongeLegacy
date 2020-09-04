package org.bonge.bukkit.r1_14.inventory.chest;

import org.array.utils.ArrayUtils;
import org.bonge.Bonge;
import org.bonge.bukkit.r1_14.entity.living.human.BongePlayer;
import org.bonge.bukkit.r1_14.inventory.BongeInventory;
import org.bukkit.Location;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.api.entity.living.player.server.ServerPlayer;
import org.spongepowered.api.item.inventory.type.ViewableInventory;

import java.util.List;
import java.util.Set;

public class CustomChestInventory<I extends ViewableInventory> extends BongeInventory<I> {

    public CustomChestInventory(I value) {
        super(value);
    }

    @Override
    public @NotNull List<HumanEntity> getViewers() {
        return ArrayUtils.convert(s -> Bonge.getInstance().convert(s), this.spongeValue.getViewers());
    }

    @Override
    public @NotNull InventoryType getType() {
        return InventoryType.CHEST;
    }

    @Override
    public @Nullable BongePlayer getHolder() {
        Set<ServerPlayer> players = this.spongeValue.getViewers();
        if(players.isEmpty()){
            return null;
        }
        ServerPlayer player = players.iterator().next();
        return Bonge.getInstance().convert(player);
    }

    @Override
    public @Nullable Location getLocation() {
        BongePlayer player = this.getHolder();
        if(player == null){
            return null;
        }
        return player.getLocation();
    }

    public static CustomChestInventory<ViewableInventory> of(ViewableInventory inv){
        return new CustomChestInventory<>(inv);
    }
}
