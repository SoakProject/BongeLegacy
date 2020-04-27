package org.bonge.bukkit.r1_13.scoreboard;

import org.bonge.Bonge;
import org.bonge.convert.text.TextConverter;
import org.bonge.util.ArrayUtils;
import org.bonge.wrapper.BongeWrapper;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.scoreboard.*;
import org.spongepowered.api.scoreboard.critieria.Criterion;

import java.io.IOException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class BongeScoreboard extends BongeWrapper<org.spongepowered.api.scoreboard.Scoreboard> implements Scoreboard {

    public BongeScoreboard(org.spongepowered.api.scoreboard.Scoreboard value) {
        super(value);
    }

    @Override
    public Objective registerNewObjective(String name, String criteria) throws IllegalArgumentException {
        return registerNewObjective(name, criteria, name);
    }

    @Override
    public Objective registerNewObjective(String name, String criteria, String displayName) throws IllegalArgumentException {
        Criterion criterion;
        try {
            criterion = Bonge.getInstance().convert(criteria, Criterion.class);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        org.spongepowered.api.scoreboard.objective.Objective objective = org.spongepowered.api.scoreboard.objective.Objective
                .builder()
                .displayName(TextConverter.CONVERTER.from(displayName))
                .name(name)
                .criterion(criterion)
                .build();
        this.spongeValue.addObjective(objective);
        return new BongeObjective(objective, this);
    }

    @Override
    public Objective getObjective(String name) throws IllegalArgumentException {
        Optional<org.spongepowered.api.scoreboard.objective.Objective> opObj = this.spongeValue.getObjective(name);
        return opObj.map(objective -> new BongeObjective(objective, this)).orElse(null);
    }

    @Override
    public Set<Objective> getObjectivesByCriteria(String criteria) throws IllegalArgumentException {
        Set<org.spongepowered.api.scoreboard.objective.Objective> objectives;
        try {
            objectives = this.spongeValue.getObjectivesByCriteria(Bonge.getInstance().convert(criteria, Criterion.class));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        return ArrayUtils.convert(new HashSet<>(), o -> new BongeObjective(o, BongeScoreboard.this), objectives);
    }

    @Override
    public Set<Objective> getObjectives() {
        return ArrayUtils.convert(new HashSet<>(), o -> new BongeObjective(o, this), this.spongeValue.getObjectives());
    }

    @Override
    public Objective getObjective(DisplaySlot slot) throws IllegalArgumentException {
        org.spongepowered.api.scoreboard.displayslot.DisplaySlot slot1;
        try {
            slot1 = Bonge.getInstance().convert(slot, org.spongepowered.api.scoreboard.displayslot.DisplaySlot.class);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        Optional<org.spongepowered.api.scoreboard.objective.Objective> opSlot = this.spongeValue.getObjective(slot1);
        return opSlot.map(objective -> new BongeObjective(objective, this)).orElse(null);
    }

    @Override
    public Set<Score> getScores(OfflinePlayer player) throws IllegalArgumentException {
        return this.getScores(player.getName());
    }

    @Override
    public Set<Score> getScores(String entry) throws IllegalArgumentException {
        return ArrayUtils.build(new HashSet<>(), (e, t) -> t
                .getScores()
                .values()
                .stream()
                .filter(s -> TextConverter.CONVERTER.to(s.getName()).equals(entry))
                .forEach(v -> e
                        .add(new BongeScore(v, new BongeObjective(t, BongeScoreboard.this)))),
                this.spongeValue.getObjectives());
    }

    @Override
    public void resetScores(OfflinePlayer player) throws IllegalArgumentException {

    }

    @Override
    public void resetScores(String entry) throws IllegalArgumentException {

    }

    @Override
    public Team getPlayerTeam(OfflinePlayer player) throws IllegalArgumentException {
        return null;
    }

    @Override
    public Team getEntryTeam(String entry) throws IllegalArgumentException {
        return null;
    }

    @Override
    public Team getTeam(String teamName) throws IllegalArgumentException {
        return null;
    }

    @Override
    public Set<Team> getTeams() {
        return null;
    }

    @Override
    public Team registerNewTeam(String name) throws IllegalArgumentException {
        return null;
    }

    @Override
    public Set<OfflinePlayer> getPlayers() {
        return Bukkit.getOnlinePlayers().stream().filter(p -> p.getScoreboard().equals(this)).collect(Collectors.toSet());
    }

    @Override
    public Set<String> getEntries() {
        return ArrayUtils.build(new HashSet<>(), (l, e) -> e.getScores().values().forEach(s -> l.add(TextConverter.CONVERTER.to(s.getName()))), this.spongeValue.getObjectives());
    }

    @Override
    public void clearSlot(DisplaySlot slot) throws IllegalArgumentException {
        try {
            this.spongeValue.clearSlot(Bonge.getInstance().convert(slot, org.spongepowered.api.scoreboard.displayslot.DisplaySlot.class));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
