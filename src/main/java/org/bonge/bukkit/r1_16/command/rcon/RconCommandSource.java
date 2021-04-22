package org.bonge.bukkit.r1_16.command.rcon;

import org.bonge.Bonge;
import org.bonge.util.exception.NotImplementedException;
import org.bonge.wrapper.BongeWrapper;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.RemoteConsoleCommandSender;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.api.network.RconConnection;

import java.util.Set;
import java.util.UUID;

public class RconCommandSource extends BongeWrapper<RconConnection> implements RemoteConsoleCommandSender {

    public RconCommandSource(RconConnection source){
        super(source);
    }

    @Override
    public void sendMessage(String message) {
        this.spongeValue.sendMessage(Bonge.getInstance().convertText(message));
    }

    @Override
    public void sendMessage(String[] messages) {
        for(String message : messages){
            this.sendMessage(message);
        }
    }

    @Override
    public void sendMessage(@Nullable UUID sender, @NotNull String message) {
        throw new NotImplementedException("yet to look at");

    }

    @Override
    public void sendMessage(@Nullable UUID sender, @NotNull String[] messages) {
        throw new NotImplementedException("yet to look at");

    }

    @Override
    public Server getServer() {
        return Bukkit.getServer();
    }

    @Override
    public String getName() {
        return this.spongeValue.friendlyIdentifier().orElse("RCON");
    }

    @NotNull
    @Override
    public Spigot spigot() {
        throw new NotImplementedException("yet to look at");
    }

    @Override
    public boolean isPermissionSet(String name) {
        return false;
    }

    @Override
    public boolean isPermissionSet(Permission perm) {
        return false;
    }

    @Override
    public boolean hasPermission(String name) {
        return true;
    }

    @Override
    public boolean hasPermission(Permission perm) {
        return true;
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin, String name, boolean value) {
        return null;
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin) {
        return null;
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin, String name, boolean value, int ticks) {
        return null;
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin, int ticks) {
        return null;
    }

    @Override
    public void removeAttachment(PermissionAttachment attachment) {

    }

    @Override
    public void recalculatePermissions() {

    }

    @Override
    public Set<PermissionAttachmentInfo> getEffectivePermissions() {
        return null;
    }

    @Override
    public boolean isOp() {
        return true;
    }

    @Override
    public void setOp(boolean value) {

    }
}
