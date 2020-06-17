package org.bonge.bukkit.r1_13.command.rcon;

import org.bonge.convert.text.TextConverter;
import org.bonge.wrapper.BongeWrapper;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.RemoteConsoleCommandSender;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.plugin.Plugin;
import org.spongepowered.api.command.source.RconSource;

import java.util.Set;

public class RconCommandSource extends BongeWrapper<RconSource> implements RemoteConsoleCommandSender {

    public RconCommandSource(RconSource source){
        super(source);
    }

    @Override
    public void sendMessage(String message) {
        this.spongeValue.sendMessage(TextConverter.CONVERTER.from(message));
    }

    @Override
    public void sendMessage(String[] messages) {
        for(String message : messages){
            this.sendMessage(message);
        }
    }

    @Override
    public Server getServer() {
        return Bukkit.getServer();
    }

    @Override
    public String getName() {
        return this.spongeValue.getName();
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
