package org.bonge.bukkit.r1_16.entity.living.human;

import org.bonge.command.Permissions;
import org.bonge.util.exception.NotImplementedException;
import org.bonge.wrapper.BongeWrapper;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.Statistic;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.data.Keys;
import org.spongepowered.api.entity.living.player.User;
import org.spongepowered.api.entity.living.player.server.ServerPlayer;
import org.spongepowered.api.service.permission.SubjectData;
import org.spongepowered.api.util.Tristate;

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
        return this.getSpongeValue().name();
    }

    @Override
    public @NotNull UUID getUniqueId() {
        return this.getSpongeValue().uniqueId();
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
        Optional<ServerPlayer> opPlayer = this.spongeValue.player();
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
        throw new NotImplementedException("yet to look at");

    }

    @Override
    public void incrementStatistic(@NotNull Statistic statistic) throws IllegalArgumentException {
        throw new NotImplementedException("yet to look at");

    }

    @Override
    public void decrementStatistic(@NotNull Statistic statistic) throws IllegalArgumentException {
        throw new NotImplementedException("yet to look at");

    }

    @Override
    public void incrementStatistic(@NotNull Statistic statistic, int amount) throws IllegalArgumentException {
        throw new NotImplementedException("yet to look at");

    }

    @Override
    public void decrementStatistic(@NotNull Statistic statistic, int amount) throws IllegalArgumentException {
        throw new NotImplementedException("yet to look at");

    }

    @Override
    public void setStatistic(@NotNull Statistic statistic, int newValue) throws IllegalArgumentException {
        throw new NotImplementedException("yet to look at");

    }

    @Override
    public int getStatistic(@NotNull Statistic statistic) throws IllegalArgumentException {
        throw new NotImplementedException("yet to look at");
    }

    @Override
    public void incrementStatistic(@NotNull Statistic statistic, @NotNull Material material) throws IllegalArgumentException {
        throw new NotImplementedException("yet to look at");

    }

    @Override
    public void decrementStatistic(@NotNull Statistic statistic, @NotNull Material material) throws IllegalArgumentException {
        throw new NotImplementedException("yet to look at");

    }

    @Override
    public int getStatistic(@NotNull Statistic statistic, @NotNull Material material) throws IllegalArgumentException {
        throw new NotImplementedException("yet to look at");
    }

    @Override
    public void incrementStatistic(@NotNull Statistic statistic, @NotNull Material material, int amount) throws IllegalArgumentException {
        throw new NotImplementedException("yet to look at");

    }

    @Override
    public void decrementStatistic(@NotNull Statistic statistic, @NotNull Material material, int amount) throws IllegalArgumentException {
        throw new NotImplementedException("yet to look at");

    }

    @Override
    public void setStatistic(@NotNull Statistic statistic, @NotNull Material material, int newValue) throws IllegalArgumentException {
        throw new NotImplementedException("yet to look at");

    }

    @Override
    public void incrementStatistic(@NotNull Statistic statistic, @NotNull EntityType entityType) throws IllegalArgumentException {
        throw new NotImplementedException("yet to look at");

    }

    @Override
    public void decrementStatistic(@NotNull Statistic statistic, @NotNull EntityType entityType) throws IllegalArgumentException {
        throw new NotImplementedException("yet to look at");

    }

    @Override
    public int getStatistic(@NotNull Statistic statistic, @NotNull EntityType entityType) throws IllegalArgumentException {
        throw new NotImplementedException("yet to look at");
    }

    @Override
    public void incrementStatistic(@NotNull Statistic statistic, @NotNull EntityType entityType, int amount) throws IllegalArgumentException {
        throw new NotImplementedException("yet to look at");

    }

    @Override
    public void decrementStatistic(@NotNull Statistic statistic, @NotNull EntityType entityType, int amount) {
        throw new NotImplementedException("yet to look at");

    }

    @Override
    public void setStatistic(@NotNull Statistic statistic, @NotNull EntityType entityType, int newValue) {
        throw new NotImplementedException("yet to look at");

    }

    @Override
    public @NotNull Map<String, Object> serialize() {
        throw new NotImplementedException("yet to look at");

    }

    @Override
    public boolean isOp() {
        return this.spongeValue.hasPermission(Permissions.BONGE_OP);
    }

    @Override
    public void setOp(boolean value) {
        this.spongeValue.subjectData().setPermission(SubjectData.GLOBAL_CONTEXT, Permissions.BONGE_OP, value ? Tristate.TRUE : Tristate.FALSE);
    }
}
