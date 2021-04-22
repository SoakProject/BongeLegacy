package org.bonge.bukkit.r1_16.scoreboard;

import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.Sponge;

import java.util.Optional;

public class BongeScoreboardManager implements ScoreboardManager {
    @Override
    public @NotNull Scoreboard getMainScoreboard() {
        Optional<? extends org.spongepowered.api.scoreboard.Scoreboard> opScoreboard = Sponge.server().serverScoreboard();
        return opScoreboard.map(BongeScoreboard::new).orElse(null);
    }

    @Override
    public @NotNull Scoreboard getNewScoreboard() {
        return new BongeScoreboard(org.spongepowered.api.scoreboard.Scoreboard.builder().build());
    }
}
