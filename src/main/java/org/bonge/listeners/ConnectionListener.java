package org.bonge.listeners;

import org.bonge.convert.InterfaceConvert;
import org.bukkit.Bukkit;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.spongepowered.api.event.network.ClientConnectionEvent;

import java.net.InetAddress;
import java.util.UUID;

public class ConnectionListener {

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
                break;
        }
        String message = asyncBEvent.getKickMessage();
        event.setMessage(InterfaceConvert.fromString(message));
    }
}
