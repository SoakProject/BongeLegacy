package org.bonge.listeners;

import org.bonge.Bonge;
import org.bonge.bukkit.r1_13.entity.living.human.BongePlayer;
import org.bonge.util.ArrayUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerCommandSendEvent;
import org.bukkit.event.server.ServerCommandEvent;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.command.SendCommandEvent;
import org.spongepowered.api.event.command.TabCompleteEvent;
import org.spongepowered.api.event.filter.cause.First;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SourceListener {

    @Listener
    public void onTab(TabCompleteEvent event, @First CommandSource source){
        try{
            CommandSender sender = Bonge.getInstance().convert(CommandSender.class, source);
            org.bukkit.event.server.TabCompleteEvent bEvent = new org.bukkit.event.server.TabCompleteEvent(sender, event.getRawMessage(), new ArrayList<>(event.getTabCompletions()));
            Bukkit.getPluginManager().callEvent(bEvent);
            List<String> bComp = bEvent.getCompletions();
            if(!bComp.isEmpty()) {
                List<String> list = event.getTabCompletions();
                list.clear();
                list.addAll(bComp);
            }
            event.setCancelled(bEvent.isCancelled());
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    @Listener
    public void onCommand(SendCommandEvent event, @First CommandSource source){
        try {
            CommandSender sender = Bonge.getInstance().convert(CommandSender.class, source);
            ServerCommandEvent bEvent = new ServerCommandEvent(sender, event.getCommand() + " " + event.getArguments());
            Bukkit.getPluginManager().callEvent(bEvent);
            event.setCancelled(bEvent.isCancelled());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Listener
    public void onPlayerCommand(SendCommandEvent event, @First org.spongepowered.api.entity.living.player.Player player){
        BongePlayer player1 = BongePlayer.getPlayer(player);
        PlayerCommandPreprocessEvent pcpe = new PlayerCommandPreprocessEvent(player1, "/" + event.getCommand() + " " + event.getArguments());
        Bukkit.getPluginManager().callEvent(pcpe);
        event.setCancelled(pcpe.isCancelled());
        if(event.isCancelled()){
           return;
        }
        List<String> list = new ArrayList<>();
        list.add(event.getCommand());
        list.addAll(Arrays.asList(event.getArguments().split(" ")));
        PlayerCommandSendEvent pcse = new PlayerCommandSendEvent(player1, list);
        Bukkit.getPluginManager().callEvent(pcse);
        if(list.isEmpty()){
            event.setCancelled(true);
            return;
        }
        event.setCommand(list.get(0));
        list.remove(0);
        event.setArguments(ArrayUtils.toString(" ", t -> t, list));
    }
}
