package org.bonge.bukkit.r1_15.command;

import org.bukkit.command.Command;
import org.bukkit.command.PluginCommand;

public class CommandState {

    private final Command cmd;
    private final String label;
    private final String pluginTag;

    public CommandState(String label, String pluginTag, Command cmd) {
        this.cmd = cmd;
        this.label = label;
        this.pluginTag = pluginTag;
    }

    public CommandState(PluginCommand command){
        this(command.getLabel(), command.getPlugin().getName().toLowerCase(), command);
    }

    public Command getCmd() {
        return cmd;
    }

    public String getLabel() {
        return label;
    }

    public String getPluginTag() {
        return pluginTag;
    }
}
