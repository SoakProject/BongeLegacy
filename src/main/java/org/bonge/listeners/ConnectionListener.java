package org.bonge.listeners;

import org.bonge.Bonge;
import org.bonge.bukkit.r1_16.entity.living.human.BongePlayer;
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
        Optional<ClientPingServerEvent.Response.Players> opPlayers = event.response().players();
        if(opPlayers.isPresent()){
            max = opPlayers.get().max();
            players = opPlayers.get().online();
        }
        ServerListPingEvent bEvent = new ServerListPingEvent(event.client().address().getAddress(), Bonge.getInstance().convert(event.response().description()), players, max);
        Bukkit.getPluginManager().callEvent(bEvent);
        event.response().setDescription(Bonge.getInstance().convertText(bEvent.getMotd()));
        if(opPlayers.isPresent()){
            opPlayers.get().setMax(bEvent.getMaxPlayers());
            opPlayers.get().setOnline(bEvent.getNumPlayers());
        }
    }

    @org.spongepowered.api.event.Listener
    public void onPlayerLogin(ServerSideConnectionEvent.Join event){
        Player player = BongePlayer.getPlayer(event.player());
        String hostName = event.player().connection().virtualHost().getHostName();
        InetAddress address = event.player().connection().address().getAddress();
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
        PlayerJoinEvent event2 = new PlayerJoinEvent(player, Bonge.getInstance().convert(event.message()));
        Bukkit.getServer().getPluginManager().callEvent(event2);
        event.setMessage(Bonge.getInstance().convertText(event2.getJoinMessage()));
    }

    @Listener
    public void onPlayerKick(KickPlayerEvent event){
        BongePlayer player = BongePlayer.getPlayer(event.player());
        PlayerKickEvent quitEvent = new PlayerKickEvent(player, "Unknown", Bonge.getInstance().convert(event.message()));
        if(quitEvent.isCancelled()) {
            event.setMessage(null);
        }else {
            event.setMessage(Bonge.getInstance().convertText(quitEvent.getLeaveMessage()));
        }
    }

    @Listener
    public void onPlayerLeave(ServerSideConnectionEvent.Disconnect event){
        BongePlayer player = BongePlayer.getPlayer(event.player());
        PlayerQuitEvent quitEvent = new PlayerQuitEvent(player, Bonge.getInstance().convert(event.message()));
        if(quitEvent.getQuitMessage() == null) {
            event.setMessage(null);
        }else {
            event.setMessage(Bonge.getInstance().convertText(quitEvent.getQuitMessage()));
        }
    }

    @org.spongepowered.api.event.Listener
    public void onPlayerConnectionPre(ServerSideConnectionEvent.Auth event){
        String playerName = event.profile().name().get();
        InetAddress address = event.connection().address().getAddress();
        UUID uuid = event.profile().uniqueId();
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
