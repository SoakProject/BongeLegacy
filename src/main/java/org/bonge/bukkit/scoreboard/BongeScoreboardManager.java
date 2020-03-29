package org.bonge.bukkit.scoreboard;

import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.spongepowered.api.Sponge;

import java.util.Optional;

public class BongeScoreboardManager implements ScoreboardManager {
    @Override
    public Scoreboard getMainScoreboard() {
        Optional<org.spongepowered.api.scoreboard.Scoreboard> opScoreboard = Sponge.getServer().getServerScoreboard();
        return opScoreboard.map(BongeScoreboard::new).orElse(null);
    }

    @Override
    public Scoreboard getNewScoreboard() {
        return new BongeScoreboard(org.spongepowered.api.scoreboard.Scoreboard.builder().build());
    }
}
