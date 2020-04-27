package org.bonge.listeners;

import org.bonge.Bonge;
import org.bonge.bukkit.r1_13.block.BongeBlockSnapshot;
import org.bonge.bukkit.r1_13.entity.living.human.BongePlayer;
import org.bukkit.Bukkit;
import org.bukkit.block.BlockFace;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.spongepowered.api.data.type.HandTypes;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.block.InteractBlockEvent;
import org.spongepowered.api.event.filter.cause.Root;

import java.io.IOException;

public class PlayerListener {

    @Listener
    public void onBlockClick(InteractBlockEvent event, @Root org.spongepowered.api.entity.living.player.Player player){
        BongePlayer bPlayer = BongePlayer.getPlayer(player);
        Action action = Action.RIGHT_CLICK_BLOCK;
        org.spongepowered.api.item.inventory.ItemStack stack = player.getItemInHand(HandTypes.OFF_HAND).get();
        if(event instanceof InteractBlockEvent.Primary){
            action = Action.LEFT_CLICK_BLOCK;
            stack = player.getItemInHand(HandTypes.MAIN_HAND).get();
        }
        BongeBlockSnapshot bbs = new BongeBlockSnapshot(event.getTargetBlock());
        try {
            BlockFace face = Bonge.getInstance().convert(BlockFace.class, event.getTargetSide());
            ItemStack stack2 = Bonge.getInstance().convert(ItemStack.class, stack);
            PlayerInteractEvent bEvent = new PlayerInteractEvent(bPlayer, action, stack2, bbs, face);
            Bukkit.getServer().getPluginManager().callEvent(bEvent);
            if(bEvent.isCancelled()){
                event.setCancelled(true);
            }
        } catch (IOException ignored) {
        }
    }
}
