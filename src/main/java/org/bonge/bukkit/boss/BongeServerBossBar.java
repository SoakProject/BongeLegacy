package org.bonge.bukkit.boss;

import org.bonge.bukkit.entity.living.human.BongePlayer;
import org.bonge.convert.EnumConvert;
import org.bonge.convert.InterfaceConvert;
import org.bonge.util.WrappedArrayList;
import org.bonge.wrapper.BongeWrapper;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BongeServerBossBar extends BongeWrapper<org.spongepowered.api.boss.ServerBossBar> implements BossBar {

    private Set<Player> list = new HashSet<>();

    public BongeServerBossBar(org.spongepowered.api.boss.ServerBossBar value) {
        super(value);
    }

    @Override
    public String getTitle() {
        return InterfaceConvert.toString(this.spongeValue.getName());
    }

    @Override
    public void setTitle(String title) {
        this.spongeValue.setName(InterfaceConvert.fromString(title));
    }

    @Override
    public BarColor getColor() {
        return EnumConvert.getColour(this.spongeValue.getColor());
    }

    @Override
    public void setColor(BarColor color) {
        this.spongeValue.setColor(EnumConvert.getColour(color));
    }

    @Override
    public BarStyle getStyle() {
        return EnumConvert.getStyle(this.spongeValue.getOverlay());
    }

    @Override
    public void setStyle(BarStyle style) {
        this.spongeValue.setOverlay(EnumConvert.getStyle(style));
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
        this.spongeValue.addPlayer(((BongePlayer)player).getSpongeValue());
    }

    @Override
    public void removePlayer(Player player) {
        this.spongeValue.removePlayer(((BongePlayer)player).getSpongeValue());

    }

    @Override
    public void removeAll() {
        this.spongeValue.removePlayers(this.spongeValue.getPlayers());
    }

    @Override
    public List<Player> getPlayers() {
        return WrappedArrayList.ofImmutable(p -> new BongePlayer(p), this.spongeValue.getPlayers());
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
