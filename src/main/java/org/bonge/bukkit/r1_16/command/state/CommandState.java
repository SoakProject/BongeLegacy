package org.bonge.bukkit.r1_16.command.state;

import org.bukkit.command.Command;

public interface CommandState {

    Command getCmd();
    String getLabel();
    String getPluginTag();
}
