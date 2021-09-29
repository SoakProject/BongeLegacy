package org.bonge.bukkit.r1_16.inventory.chest;

import org.bonge.Bonge;
import org.bonge.bukkit.r1_16.entity.living.human.BongePlayer;
import org.bonge.bukkit.r1_16.inventory.BongeInventory;
import org.bukkit.Location;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.api.entity.living.player.server.ServerPlayer;
import org.spongepowered.api.item.inventory.type.ViewableInventory;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CustomChestInventory<I extends ViewableInventory> extends BongeInventory<I> {

    public CustomChestInventory(I value) {
        super(value);
    }

    @Override
    public @NotNull List<HumanEntity> getViewers() {
        return this
                .spongeValue
                .viewers()
                .stream()
                .map(s -> Bonge.getInstance().convert(s))
                .collect(Collectors.toList());
    }

    @Override
    public @NotNull InventoryType getType() {
        return InventoryType.CHEST;
    }

    @Override
    public @Nullable BongePlayer getHolder() {
        Set<ServerPlayer> players = this.spongeValue.viewers();
        if (players.isEmpty()) {
            return null;
        }
        ServerPlayer player = players.iterator().next();
        return Bonge.getInstance().convert(player);
    }

    @Override
    public @Nullable Location getLocation() {
        BongePlayer player = this.getHolder();
        if (player == null) {
            return null;
        }
        return player.getLocation();
    }

    public static CustomChestInventory<ViewableInventory> of(ViewableInventory inv) {
        return new CustomChestInventory<>(inv);
    }
}
