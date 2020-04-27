package org.bonge.listeners;

import org.bonge.bukkit.r1_13.block.BongeBlock;
import org.bonge.bukkit.r1_13.block.BongeBlockSnapshot;
import org.bonge.bukkit.r1_13.entity.living.human.BongePlayer;
import org.bonge.convert.text.TextConverter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.block.BlockSnapshot;
import org.spongepowered.api.data.manipulator.mutable.tileentity.SignData;
import org.spongepowered.api.event.block.ChangeBlockEvent;
import org.spongepowered.api.event.block.tileentity.ChangeSignEvent;
import org.spongepowered.api.event.filter.cause.Root;
import org.spongepowered.api.text.Text;

public class BlockListener {

    @org.spongepowered.api.event.Listener
    public void onBlockBreak(ChangeBlockEvent.Break event, @Root org.spongepowered.api.entity.living.player.Player player){
        if(event.getTransactions().size() == 1) {
            BlockSnapshot block = event.getTransactions().get(0).getOriginal();
            Player bPlayer = BongePlayer.getPlayer(player);
            BlockBreakEvent bukkitEvent = new BlockBreakEvent(new BongeBlockSnapshot(block), bPlayer);
            Bukkit.getServer().getPluginManager().callEvent(bukkitEvent);
            event.setCancelled(bukkitEvent.isCancelled());
        }
    }

    @org.spongepowered.api.event.Listener
    public void onSignChange(ChangeSignEvent event, @Root org.spongepowered.api.entity.living.player.Player player){
        BongeBlock block = new BongeBlock(event.getTargetTile().getLocation());
        BongePlayer bPlayer = BongePlayer.getPlayer(player);

        String[] lines = new String[4];
        for(int A = 0; A < lines.length; A++){
            lines[A] = TextConverter.CONVERTER.to(event.getOriginalText().asList().get(A));
        }
        SignChangeEvent bukkitEvent = new SignChangeEvent(block, bPlayer, lines);
        Bukkit.getServer().getPluginManager().callEvent(bukkitEvent);
        SignData data = event.getText();
        for(int A = 0; A < 4; A++){
            Sponge.getServer().getConsole().sendMessage(Text.join(Text.of(A + ") "), TextConverter.CONVERTER.from(bukkitEvent.getLine(A))));
            data.setElement(A, TextConverter.CONVERTER.from(bukkitEvent.getLine(A)));
        }
        event.setCancelled(bukkitEvent.isCancelled());
    }
}
