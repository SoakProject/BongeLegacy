package org.bonge.listeners;

import org.bonge.bukkit.r1_13.entity.living.human.BongePlayer;
import org.bonge.convert.text.TextConverter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.*;
import org.bukkit.event.server.ServerListPingEvent;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.entity.living.humanoid.player.KickPlayerEvent;
import org.spongepowered.api.event.network.ClientConnectionEvent;
import org.spongepowered.api.event.server.ClientPingServerEvent;

import java.net.InetAddress;
import java.util.Optional;
import java.util.UUID;

public class ConnectionListener {

    @org.spongepowered.api.event.Listener
    public void onPing(ClientPingServerEvent event){
        int players = 0;
        int max = 0;
        Optional<ClientPingServerEvent.Response.Players> opPlayers = event.getResponse().getPlayers();
        if(opPlayers.isPresent()){
            max = opPlayers.get().getMax();
            players = opPlayers.get().getOnline();
        }
        ServerListPingEvent bEvent = new ServerListPingEvent(event.getClient().getAddress().getAddress(), TextConverter.CONVERTER.to(event.getResponse().getDescription()), players, max);
        Bukkit.getPluginManager().callEvent(bEvent);
        event.getResponse().setDescription(TextConverter.CONVERTER.from(bEvent.getMotd()));
        if(opPlayers.isPresent()){
            opPlayers.get().setMax(bEvent.getMaxPlayers());
            opPlayers.get().setOnline(bEvent.getNumPlayers());
        }
    }

    @org.spongepowered.api.event.Listener
    public void onPlayerLogin(ClientConnectionEvent.Join event){
        Player player = BongePlayer.getPlayer(event.getTargetEntity());
        String hostName = event.getTargetEntity().getConnection().getVirtualHost().getHostName();
        InetAddress address = event.getTargetEntity().getConnection().getAddress().getAddress();
        PlayerLoginEvent event1 = new PlayerLoginEvent(player, hostName, address);
        Bukkit.getServer().getPluginManager().callEvent(event1);
        switch (event1.getResult()){
            case ALLOWED:
                break;
            case KICK_FULL:
            case KICK_BANNED:
            case KICK_WHITELIST:
            case KICK_OTHER:
                player.kickPlayer(event1.getKickMessage());
                return;
        }
        PlayerJoinEvent event2 = new PlayerJoinEvent(player, TextConverter.CONVERTER.to(event.getMessage()));
        Bukkit.getServer().getPluginManager().callEvent(event2);
        event.setMessage(TextConverter.CONVERTER.from(event2.getJoinMessage()));
    }

    @Listener
    public void onPlayerKick(KickPlayerEvent event){
        BongePlayer player = BongePlayer.getPlayer(event.getTargetEntity());
        PlayerKickEvent quitEvent = new PlayerKickEvent(player, "Unknown", TextConverter.CONVERTER.to(event.getMessage()));
        if(quitEvent.isCancelled()) {
            event.setMessageCancelled(true);
        }else {
            event.setMessage(TextConverter.CONVERTER.from(quitEvent.getLeaveMessage()));
        }
    }

    @Listener
    public void onPlayerLeave(ClientConnectionEvent.Disconnect event){
        BongePlayer player = BongePlayer.getPlayer(event.getTargetEntity());
        PlayerQuitEvent quitEvent = new PlayerQuitEvent(player, TextConverter.CONVERTER.to(event.getMessage()));
        if(quitEvent.getQuitMessage() == null) {
            event.setMessageCancelled(true);
        }else {
            event.setMessage(TextConverter.CONVERTER.from(quitEvent.getQuitMessage()));
        }
    }

    @org.spongepowered.api.event.Listener
    public void onPlayerConnectionPre(ClientConnectionEvent.Auth event){
        String playerName = event.getProfile().getName().get();
        InetAddress address = event.getConnection().getAddress().getAddress();
        UUID uuid = event.getProfile().getUniqueId();
        AsyncPlayerPreLoginEvent asyncBEvent = new AsyncPlayerPreLoginEvent(playerName, address, uuid);
        Bukkit.getServer().getPluginManager().callEvent(asyncBEvent);
        switch (asyncBEvent.getLoginResult()){
            case ALLOWED:
                return;
            case KICK_FULL:
            case KICK_BANNED:
            case KICK_WHITELIST:
            case KICK_OTHER:
                event.setCancelled(true);
                break;
        }
        String message = asyncBEvent.getKickMessage();
        event.setMessage(TextConverter.CONVERTER.from(message));
    }
}
