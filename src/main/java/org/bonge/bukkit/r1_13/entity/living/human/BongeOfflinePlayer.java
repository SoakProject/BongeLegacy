package org.bonge.bukkit.r1_13.entity.living.human;

import org.bonge.wrapper.BongeWrapper;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.entity.living.player.User;

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
    public UUID getUniqueId() {
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
        Optional<org.spongepowered.api.entity.living.player.Player> opPlayer = this.spongeValue.getPlayer();
        if(!opPlayer.isPresent()){
            return null;
        }
        return BongePlayer.getPlayer(opPlayer.get());
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
    public Map<String, Object> serialize() {
        return new HashMap<>();
    }

    @Override
    @Deprecated
    public boolean isOp() {
        return false;
    }

    @Override
    @Deprecated
    public void setOp(boolean value) {

    }
}
