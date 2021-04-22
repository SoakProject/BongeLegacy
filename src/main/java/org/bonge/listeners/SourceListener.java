package org.bonge.listeners;

import org.array.utils.ArrayUtils;
import org.bonge.Bonge;
import org.bonge.bukkit.r1_16.entity.living.human.BongePlayer;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.event.player.PlayerCommandSendEvent;
import org.bukkit.event.server.ServerCommandEvent;
import org.spongepowered.api.event.EventContextKeys;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.command.ExecuteCommandEvent;
import org.spongepowered.api.service.permission.Subject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class SourceListener {

    /*@Listener
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

    }*/

    @Listener
    public void onCommand(ExecuteCommandEvent.Pre event){
        Optional<Subject> opSubject = event.context().get(EventContextKeys.SUBJECT);
        if(!opSubject.isPresent()){
            //SHOULDNT FAIL BUT JUST INCASE
            return;
        }
        try {
            CommandSender sender = Bonge.getInstance().convert(CommandSender.class, opSubject.get());
            if(sender instanceof BongePlayer) {
                BongePlayer player = (BongePlayer) sender;
                List<String> list = new ArrayList<>();
                list.add(event.command());
                list.addAll(Arrays.asList(event.arguments().split(" ")));
                PlayerCommandSendEvent pcse = new PlayerCommandSendEvent(player, list);
                Bukkit.getPluginManager().callEvent(pcse);
                if(list.isEmpty()){
                    event.setCancelled(true);
                    return;
                }
                event.setCommand(list.get(0));
                list.remove(0);
                event.setArguments(ArrayUtils.toString(" ", t -> t, list));
            }else{
                ServerCommandEvent bEvent = new ServerCommandEvent(sender, event.command() + " " + event.arguments());
                Bukkit.getPluginManager().callEvent(bEvent);
                event.setCancelled(bEvent.isCancelled());
                List<String> list = new ArrayList<>(Arrays.asList(bEvent.getCommand().split(" ")));
                event.setCommand(list.get(0));
                list.remove(0);
                event.setArguments(ArrayUtils.toString(" ", t -> t, list));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
