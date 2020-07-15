package org.bonge.bukkit.r1_14.scoreboard;

import org.bonge.convert.text.TextConverter;
import org.bonge.wrapper.BongeWrapper;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

public class BongeScore extends BongeWrapper<org.spongepowered.api.scoreboard.Score> implements Score {

    private BongeObjective objective;

    public BongeScore(org.spongepowered.api.scoreboard.Score value, BongeObjective objective) {
        super(value);
        this.objective = objective;
    }

    @Override
    public OfflinePlayer getPlayer() {
        return Bukkit.getOfflinePlayer(this.getEntry());
    }

    @Override
    public String getEntry() {
        return TextConverter.CONVERTER.to(this.spongeValue.getName());
    }

    @Override
    public Objective getObjective() {
        return this.objective;
    }

    @Override
    public int getScore() throws IllegalStateException {
        return this.spongeValue.getScore();
    }

    @Override
    public void setScore(int score) throws IllegalStateException {
        this.spongeValue.setScore(score);
    }

    @Override
    public boolean isScoreSet() throws IllegalStateException {
        return true;
    }

    @Override
    public Scoreboard getScoreboard() {
        return this.objective.getScoreboard();
    }
}
