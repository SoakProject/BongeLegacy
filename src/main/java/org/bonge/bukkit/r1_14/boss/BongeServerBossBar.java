package org.bonge.bukkit.r1_14.boss;

import org.array.utils.ArrayUtils;
import org.bonge.Bonge;
import org.bonge.bukkit.r1_14.entity.living.human.BongePlayer;
import org.bonge.util.exception.NotImplementedException;
import org.bonge.wrapper.BongeWrapper;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.Sponge;

import java.io.IOException;
import java.util.List;

public class BongeServerBossBar extends BongeWrapper<net.kyori.adventure.bossbar.BossBar> implements BossBar {

    public BongeServerBossBar(net.kyori.adventure.bossbar.BossBar value) {
        super(value);
    }

    @Override
    public String getTitle() {
        return Bonge.getInstance().convert(this.spongeValue.name());
    }

    @Override
    public void setTitle(String title) {
        this.spongeValue.name(Bonge.getInstance().convertText(title));
    }

    @Override
    public @NotNull BarColor getColor() {
        try {
            return Bonge.getInstance().convert(BarColor.class, this.spongeValue.color());
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void setColor(@NotNull BarColor color) {
        try {
            this.spongeValue.color(Bonge.getInstance().convert(color, net.kyori.adventure.bossbar.BossBar.Color.class));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public @NotNull BarStyle getStyle() {
        try {
            return Bonge.getInstance().convert(BarStyle.class, this.spongeValue.overlay());
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void setStyle(@NotNull BarStyle style) {
        try {
            this.spongeValue.overlay(Bonge.getInstance().convert(style, net.kyori.adventure.bossbar.BossBar.Overlay.class));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void removeFlag(@NotNull BarFlag flag) {

    }

    @Override
    public void addFlag(@NotNull BarFlag flag) {

    }

    @Override
    public boolean hasFlag(@NotNull BarFlag flag) {
        return false;
    }

    @Override
    public void setProgress(double progress) {
        this.spongeValue.percent((float)progress);
    }

    @Override
    public double getProgress() {
        return this.spongeValue.percent();
    }

    @Override
    public void addPlayer(@NotNull Player player) {
        ((BongePlayer)player).getSpongeValue().showBossBar(this.spongeValue);
    }

    @Override
    public void removePlayer(@NotNull Player player) {
        ((BongePlayer)player).getSpongeValue().hideBossBar(this.spongeValue);
    }

    @Override
    public void removeAll() {
        Sponge.getServer().getOnlinePlayers().forEach(p -> p.hideBossBar(this.spongeValue));

    }

    @Override
    public @NotNull List<Player> getPlayers() {
        throw new NotImplementedException("ServerBossBar.getPlayers() Sponge has no alternative");
        //return ArrayUtils.convert(BongePlayer::new, this.spongeValue.getPlayers());
    }

    @Override
    public void setVisible(boolean visible) {
        throw new NotImplementedException("ServerBossBar.setVisible(boolean) Sponge has no alternative");
    }

    @Override
    public boolean isVisible() {
        throw new NotImplementedException("ServerBossBar.isVisble() Sponge has no alternative");
    }

    @Override
    public void show() {
    }

    @Override
    public void hide() {

    }
}
