package org.bonge.bukkit.r1_14.boss;

import org.bonge.Bonge;
import org.bonge.bukkit.r1_14.entity.living.human.BongePlayer;
import org.bonge.convert.text.TextConverter;
import org.bonge.util.ArrayUtils;
import org.bonge.wrapper.BongeWrapper;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.spongepowered.api.boss.BossBarColor;
import org.spongepowered.api.boss.BossBarOverlay;
import org.spongepowered.api.entity.living.player.server.ServerPlayer;

import java.io.IOException;
import java.util.List;

public class BongeServerBossBar extends BongeWrapper<org.spongepowered.api.boss.ServerBossBar> implements BossBar {

    public BongeServerBossBar(org.spongepowered.api.boss.ServerBossBar value) {
        super(value);
    }

    @Override
    public String getTitle() {
        return TextConverter.CONVERTER.to(this.spongeValue.getName());
    }

    @Override
    public void setTitle(String title) {
        this.spongeValue.setName(TextConverter.CONVERTER.from(title));
    }

    @Override
    public BarColor getColor() {
        try {
            return Bonge.getInstance().convert(BarColor.class, this.spongeValue.getColor());
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void setColor(BarColor color) {
        try {
            this.spongeValue.setColor(Bonge.getInstance().convert(color, BossBarColor.class));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public BarStyle getStyle() {
        try {
            return Bonge.getInstance().convert(BarStyle.class, this.spongeValue.getOverlay());
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void setStyle(BarStyle style) {
        try {
            this.spongeValue.setOverlay(Bonge.getInstance().convert(style, BossBarOverlay.class));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void removeFlag(BarFlag flag) {

    }

    @Override
    public void addFlag(BarFlag flag) {

    }

    @Override
    public boolean hasFlag(BarFlag flag) {
        return false;
    }

    @Override
    public void setProgress(double progress) {
        this.spongeValue.setPercent((float)progress);
    }

    @Override
    public double getProgress() {
        return this.spongeValue.getPercent();
    }

    @Override
    public void addPlayer(Player player) {
        this.spongeValue.addPlayer((ServerPlayer) Bonge.getInstance().convert(player));
    }

    @Override
    public void removePlayer(Player player) {
        this.spongeValue.removePlayer((ServerPlayer) Bonge.getInstance().convert(player));

    }

    @Override
    public void removeAll() {
        this.spongeValue.removePlayers(this.spongeValue.getPlayers());
    }

    @Override
    public List<Player> getPlayers() {
        return ArrayUtils.convert(BongePlayer::new, this.spongeValue.getPlayers());
    }

    @Override
    public void setVisible(boolean visible) {
        this.spongeValue.setVisible(visible);
    }

    @Override
    public boolean isVisible() {
        return this.spongeValue.isVisible();
    }

    @Override
    public void show() {
    }

    @Override
    public void hide() {

    }
}
