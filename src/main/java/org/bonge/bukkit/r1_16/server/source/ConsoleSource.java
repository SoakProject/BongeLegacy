package org.bonge.bukkit.r1_16.server.source;

import org.bonge.Bonge;
import org.bonge.util.exception.NotImplementedException;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.conversations.Conversation;
import org.bukkit.conversations.ConversationAbandonedEvent;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.SystemSubject;

import java.util.Set;
import java.util.UUID;

public class ConsoleSource implements ConsoleCommandSender {

    private final SystemSubject subject;

    public ConsoleSource(){
        this(Sponge.systemSubject());
    }

    public ConsoleSource(SystemSubject subject){
        this.subject = subject;
    }

    @Override
    public void sendMessage(@NotNull String message) {
        this.subject.sendMessage(Bonge.getInstance().convertText(message));
    }

    @Override
    public void sendMessage(@NotNull String[] messages) {
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

    }

    @Override
    public @NotNull Server getServer() {
        return Bukkit.getServer();
    }

    @Override
    public @NotNull String getName() {
        return "Console";
    }

    @NotNull
    @Override
    @Deprecated
    public Spigot spigot() {
        throw new NotImplementedException("yet to look at");
    }

    @Override
    public boolean isConversing() {
        throw new NotImplementedException("yet to look at");

    }

    @Override
    public void acceptConversationInput(@NotNull String input) {
        throw new NotImplementedException("yet to look at");

    }

    @Override
    public boolean beginConversation(@NotNull Conversation conversation) {
        throw new NotImplementedException("yet to look at");

    }

    @Override
    public void abandonConversation(@NotNull Conversation conversation) {
        throw new NotImplementedException("yet to look at");

    }

    @Override
    public void abandonConversation(@NotNull Conversation conversation, @NotNull ConversationAbandonedEvent details) {
        throw new NotImplementedException("yet to look at");

    }

    @Override
    public void sendRawMessage(@NotNull String message) {
        this.subject.sendMessage(Bonge.getInstance().convertText(message));
    }

    @Override
    public void sendRawMessage(@Nullable UUID sender, @NotNull String message) {
        throw new NotImplementedException("yet to look at");

    }

    @Override
    public boolean isPermissionSet(@NotNull String name) {
        throw new NotImplementedException("yet to look at");

    }

    @Override
    public boolean isPermissionSet(@NotNull Permission perm) {
        throw new NotImplementedException("yet to look at");

    }

    @Override
    public boolean hasPermission(@NotNull String name) {
        return true;
    }

    @Override
    public boolean hasPermission(@NotNull Permission perm) {
        return true;
    }

    @Override
    public @NotNull PermissionAttachment addAttachment(@NotNull Plugin plugin, @NotNull String name, boolean value) {
        return null;
    }

    @Override
    public @NotNull PermissionAttachment addAttachment(@NotNull Plugin plugin) {
        return null;
    }

    @Override
    public @Nullable PermissionAttachment addAttachment(@NotNull Plugin plugin, @NotNull String name, boolean value, int ticks) {
        return null;
    }

    @Override
    public @Nullable PermissionAttachment addAttachment(@NotNull Plugin plugin, int ticks) {
        return null;
    }

    @Override
    public void removeAttachment(@NotNull PermissionAttachment attachment) {

    }

    @Override
    public void recalculatePermissions() {

    }

    @Override
    public @NotNull Set<PermissionAttachmentInfo> getEffectivePermissions() {
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
