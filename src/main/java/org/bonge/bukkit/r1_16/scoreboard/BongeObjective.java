package org.bonge.bukkit.r1_16.scoreboard;

import org.bonge.Bonge;
import org.bonge.wrapper.BongeWrapper;
import org.bukkit.OfflinePlayer;
import org.bukkit.scoreboard.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class BongeObjective extends BongeWrapper<org.spongepowered.api.scoreboard.objective.Objective> implements Objective {

    private BongeScoreboard scoreboard;

    public BongeObjective(org.spongepowered.api.scoreboard.objective.Objective value, BongeScoreboard board) {
        super(value);
        this.scoreboard = board;
    }

    @Override
    public @NotNull String getName() throws IllegalStateException {
        return this.spongeValue.name();
    }

    @Override
    public @NotNull String getDisplayName() throws IllegalStateException {
        return Bonge.getInstance().convert(this.spongeValue.displayName());
    }

    @Override
    public void setDisplayName(@NotNull String displayName) throws IllegalStateException, IllegalArgumentException {
        this.spongeValue.setDisplayName(Bonge.getInstance().convertText(displayName));
    }

    @Override
    public @NotNull String getCriteria() throws IllegalStateException {
        try {
            return Bonge.getInstance().convert(String.class, this.spongeValue.criterion());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public boolean isModifiable() throws IllegalStateException {
        return true;
    }

    @Override
    public Scoreboard getScoreboard() {
        return this.scoreboard;
    }

    @Override
    public void unregister() throws IllegalStateException {
        this.scoreboard.getSpongeValue().removeObjective(this.spongeValue);
    }

    @Override
    public void setDisplaySlot(DisplaySlot slot) throws IllegalStateException {
    }

    @Override
    public DisplaySlot getDisplaySlot() throws IllegalStateException {
        return null;
    }

    @Override
    public void setRenderType(@NotNull RenderType renderType) throws IllegalStateException {

    }

    @Override
    public @NotNull RenderType getRenderType() throws IllegalStateException {
        return null;
    }

    @Override
    public Score getScore(OfflinePlayer player) throws IllegalArgumentException, IllegalStateException {
        return this.getScore(player.getName());
    }

    @Override
    public Score getScore(String entry) throws IllegalArgumentException, IllegalStateException {
        org.spongepowered.api.scoreboard.Score score = this.spongeValue.scoreOrCreate(Bonge.getInstance().convertText(entry));
        return new BongeScore(score, this);

    }
}
