package org.bonge.bukkit.r1_13.command;

import org.bukkit.command.Command;
import org.bukkit.command.PluginCommand;

public class CommandState {

    private Command cmd;
    private String label;
    private String pluginTag;

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
