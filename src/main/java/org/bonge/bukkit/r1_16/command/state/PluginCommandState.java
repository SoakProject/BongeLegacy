package org.bonge.bukkit.r1_16.command.state;

import org.bukkit.command.Command;
import org.bukkit.command.PluginCommand;
import org.jetbrains.annotations.NotNull;

public class PluginCommandState implements CommandState {

    private final @NotNull PluginCommand command;

    public PluginCommandState(@NotNull PluginCommand command) {
        this.command = command;
    }

    @Override
    public Command getCmd() {
        return this.command;
    }

    @Override
    public String getLabel() {
        return command.getLabel();
    }

    @Override
    public String getPluginTag() {
        return this.command.getPlugin().getName().toLowerCase();
    }
}
