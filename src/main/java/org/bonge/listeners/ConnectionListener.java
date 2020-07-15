package org.bonge.listeners;

import org.bonge.Bonge;
import org.bonge.bukkit.r1_14.entity.living.human.BongePlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.*;
import org.bukkit.event.server.ServerListPingEvent;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.entity.living.player.KickPlayerEvent;
import org.spongepowered.api.event.network.ServerSideConnectionEvent;
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
        ServerListPingEvent bEvent = new ServerListPingEvent(event.getClient().getAddress().getAddress(), Bonge.getInstance().convert(event.getResponse().getDescription()), players, max);
        Bukkit.getPluginManager().callEvent(bEvent);
        event.getResponse().setDescription(Bonge.getInstance().convertText(bEvent.getMotd()));
        if(opPlayers.isPresent()){
            opPlayers.get().setMax(bEvent.getMaxPlayers());
            opPlayers.get().setOnline(bEvent.getNumPlayers());
        }
    }

    @org.spongepowered.api.event.Listener
    public void onPlayerLogin(ServerSideConnectionEvent.Join event){
        Player player = BongePlayer.getPlayer(event.getPlayer());
        String hostName = event.getPlayer().getConnection().getVirtualHost().getHostName();
        InetAddress address = event.getPlayer().getConnection().getAddress().getAddress();
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
        PlayerJoinEvent event2 = new PlayerJoinEvent(player, Bonge.getInstance().convert(event.getMessage()));
        Bukkit.getServer().getPluginManager().callEvent(event2);
        event.setMessage(Bonge.getInstance().convertText(event2.getJoinMessage()));
    }

    @Listener
    public void onPlayerKick(KickPlayerEvent event){
        BongePlayer player = BongePlayer.getPlayer(event.getPlayer());
        PlayerKickEvent quitEvent = new PlayerKickEvent(player, "Unknown", Bonge.getInstance().convert(event.getMessage()));
        if(quitEvent.isCancelled()) {
            event.setMessageCancelled(true);
        }else {
            event.setMessage(Bonge.getInstance().convertText(quitEvent.getLeaveMessage()));
        }
    }

    @Listener
    public void onPlayerLeave(ServerSideConnectionEvent.Disconnect event){
        BongePlayer player = BongePlayer.getPlayer(event.getPlayer());
        PlayerQuitEvent quitEvent = new PlayerQuitEvent(player, Bonge.getInstance().convert(event.getMessage()));
        if(quitEvent.getQuitMessage() == null) {
            event.setMessageCancelled(true);
        }else {
            event.setMessage(Bonge.getInstance().convertText(quitEvent.getQuitMessage()));
        }
    }

    @org.spongepowered.api.event.Listener
    public void onPlayerConnectionPre(ServerSideConnectionEvent.Auth event){
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
        event.setMessage(Bonge.getInstance().convertText(message));
    }
}
