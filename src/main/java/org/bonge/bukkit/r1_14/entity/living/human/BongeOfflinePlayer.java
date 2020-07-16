package org.bonge.bukkit.r1_14.entity.living.human;

import org.bonge.command.Permissions;
import org.bonge.wrapper.BongeWrapper;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.data.Keys;
import org.spongepowered.api.entity.living.player.User;
import org.spongepowered.api.entity.living.player.server.ServerPlayer;
import org.spongepowered.api.service.permission.SubjectData;
import org.spongepowered.api.util.Tristate;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class BongeOfflinePlayer extends BongeWrapper<User> implements OfflinePlayer {

    public BongeOfflinePlayer(User value) {
        super(value);
    }

    @Override
    public boolean isOnline() {
        return this.getSpongeValue().isOnline();
    }

    @Override
    public String getName() {
        return this.getSpongeValue().getName();
    }

    @Override
    public @NotNull UUID getUniqueId() {
        return this.getSpongeValue().getUniqueId();
    }

    @Override
    public boolean isBanned() {
        return false;
    }

    @Override
    public boolean isWhitelisted() {
        return false;
    }

    @Override
    public void setWhitelisted(boolean value) {

    }

    @Override
    public Player getPlayer() {
        Optional<ServerPlayer> opPlayer = this.spongeValue.getPlayer();
        return opPlayer.map(BongePlayer::getPlayer).orElse(null);
    }

    @Override
    public long getFirstPlayed() {
        return 0;
    }

    @Override
    public long getLastPlayed() {
        return this.getSpongeValue().get(Keys.LAST_DATE_PLAYED).get().getNano();
    }

    @Override
    public boolean hasPlayedBefore() {
        return this.getLastPlayed() != 0;
    }

    @Override
    public Location getBedSpawnLocation() {
        return null;
    }

    @Override
    public @NotNull Map<String, Object> serialize() {
        return new HashMap<>();
    }

    @Override
    public boolean isOp() {
        return this.spongeValue.hasPermission(Permissions.BONGE_OP);
    }

    @Override
    public void setOp(boolean value) {
        this.spongeValue.getSubjectData().setPermission(SubjectData.GLOBAL_CONTEXT, Permissions.BONGE_OP, value ? Tristate.TRUE : Tristate.FALSE);
    }
}
