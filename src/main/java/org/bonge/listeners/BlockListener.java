package org.bonge.listeners;

import net.kyori.adventure.text.Component;
import org.array.utils.ArrayUtils;
import org.bonge.Bonge;
import org.bonge.bukkit.r1_16.block.BongeBlock;
import org.bonge.bukkit.r1_16.block.BongeBlockSnapshot;
import org.bonge.bukkit.r1_16.entity.living.human.BongePlayer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.block.*;
import org.spongepowered.api.block.BlockSnapshot;
import org.spongepowered.api.block.entity.Piston;
import org.spongepowered.api.data.Keys;
import org.spongepowered.api.data.Transaction;
import org.spongepowered.api.data.value.ListValue;
import org.spongepowered.api.event.block.ChangeBlockEvent;
import org.spongepowered.api.event.block.entity.ChangeSignEvent;
import org.spongepowered.api.event.filter.cause.Root;

import java.io.IOException;
import java.util.List;

public class BlockListener {

    //TODO - Filter just pistons
    @org.spongepowered.api.event.Listener
    public void onPistonChange(ChangeBlockEvent.All event) {
        Object source = event.source();
        if (!(source instanceof Piston)) {
            return;
        }
        Piston piston = (Piston) source;
        List<Block> blocks = ArrayUtils.convert(t -> {
            try {
                return Bonge.getInstance().convert(Location.class, t.finalReplacement().location().get()).getBlock();
            } catch (IOException e) {
                throw new IllegalArgumentException(e);
            }
        }, event.transactions());
        try {
            Block block = Bonge.getInstance().convert(Location.class, piston.location()).getBlock();
            BlockFace face = Bonge.getInstance().convert(BlockFace.class, piston.get(Keys.DIRECTION));
            BlockPistonEvent bEvent = null;
            if (piston.get(Keys.IS_EXTENDED).get()) {
                bEvent = new BlockPistonExtendEvent(block, blocks, face);
            } else {
                bEvent = new BlockPistonRetractEvent(block, blocks, face);
            }
            Bukkit.getPluginManager().callEvent(bEvent);
            event.setCancelled(bEvent.isCancelled());
        } catch (IOException e) {

        }
    }

    //TODO - Filter Redstone
    @org.spongepowered.api.event.Listener
    public void onRedstoneChange(ChangeBlockEvent.All event) {
        if (event.transactions().isEmpty()) {
            return;
        }
        Transaction<BlockSnapshot> tBlock = event.transactions().get(0);
        if (tBlock.original().get(Keys.POWER).isPresent() && tBlock.finalReplacement().get(Keys.POWER).isPresent()) {
            int originalPower = tBlock.original().get(Keys.POWER).get();
            int finalPower = tBlock.finalReplacement().get(Keys.POWER).get();
            if (originalPower == finalPower) {
                return;
            }
            try {
                BlockRedstoneEvent bEvent = new BlockRedstoneEvent(Bonge.getInstance().convert(Location.class, tBlock.finalReplacement().location().get()).getBlock(), originalPower, finalPower);
                Bukkit.getPluginManager().callEvent(bEvent);
                if (bEvent.getNewCurrent() != finalPower) {
                    tBlock.setCustom(BlockSnapshot.builder().from(tBlock.finalReplacement()).add(Keys.POWER, bEvent.getNewCurrent()).build());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //TODO - Filter break
    @org.spongepowered.api.event.Listener
    public void onBlockBreak(ChangeBlockEvent.All event, @Root org.spongepowered.api.entity.living.player.Player player) {
        if (event.transactions().size() == 1) {
            BlockSnapshot block = event.transactions().get(0).original();
            Player bPlayer = BongePlayer.getPlayer(player);
            BlockBreakEvent bukkitEvent = new BlockBreakEvent(new BongeBlockSnapshot(block), bPlayer);
            Bukkit.getServer().getPluginManager().callEvent(bukkitEvent);
            event.setCancelled(bukkitEvent.isCancelled());
        }
    }

    @org.spongepowered.api.event.Listener
    public void onSignChange(ChangeSignEvent event, @Root org.spongepowered.api.entity.living.player.Player player) {
        BongeBlock block = new BongeBlock(event.sign().location());
        BongePlayer bPlayer = BongePlayer.getPlayer(player);

        String[] lines = new String[4];
        for (int A = 0; A < lines.length; A++) {
            lines[A] = Bonge.getInstance().convert(event.originalText().get(A));
        }
        SignChangeEvent bukkitEvent = new SignChangeEvent(block, bPlayer, lines);
        Bukkit.getServer().getPluginManager().callEvent(bukkitEvent);
        ListValue.Mutable<Component> data = event.text();
        for (int A = 0; A < 4; A++) {
            data.set(A, Bonge.getInstance().convertText(bukkitEvent.getLine(A)));
        }
        event.setCancelled(bukkitEvent.isCancelled());
    }
}
