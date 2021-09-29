package org.bonge.bukkit.r1_16.command.state;

import org.bukkit.command.Command;

public class RawCommandState implements CommandState {

    private final Command cmd;
    private final String label;
    private final String pluginTag;

    public RawCommandState(String label, String pluginTag, Command cmd) {
        this.cmd = cmd;
        this.label = label;
        this.pluginTag = pluginTag;
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
