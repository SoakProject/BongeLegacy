package org.bonge.listeners;

import org.bonge.bukkit.r1_13.entity.living.human.BongePlayer;
import org.bonge.convert.text.TextConverter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.spongepowered.api.event.network.ClientConnectionEvent;

import java.net.InetAddress;
import java.util.UUID;

public class ConnectionListener {

    @org.spongepowered.api.event.Listener
    public void onPlayerLogin(ClientConnectionEvent.Join event){
        Player player = BongePlayer.getPlayer(event.getTargetEntity());
        String hostName = event.getTargetEntity().getConnection().getVirtualHost().getHostName();
        InetAddress address = event.getTargetEntity().getConnection().getAddress().getAddress();
        PlayerLoginEvent event1 = new PlayerLoginEvent(player, hostName, address);
        Bukkit.getServer().getPluginManager().callEvent(event1);
        switch (event1.getResult()){
            case ALLOWED:
                return;
            case KICK_FULL:
            case KICK_BANNED:
            case KICK_WHITELIST:
            case KICK_OTHER:
                player.kickPlayer(event1.getKickMessage());
                break;
        }
        PlayerJoinEvent event2 = new PlayerJoinEvent(player, TextConverter.CONVERTER.to(event.getMessage()));
        Bukkit.getServer().getPluginManager().callEvent(event2);
        event.setMessage(TextConverter.CONVERTER.from(event2.getJoinMessage()));
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
