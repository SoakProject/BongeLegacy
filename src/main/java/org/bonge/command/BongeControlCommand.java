package org.bonge.command;

import org.bonge.bukkit.r1_13.scheduler.BongeScheduler;
import org.bonge.bukkit.r1_13.scheduler.BongeTaskData;
import org.bonge.bukkit.r1_13.server.plugin.BongePluginManager;
import org.bonge.util.ArrayUtils;
import org.bukkit.Bukkit;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.text.Text;

import java.util.Optional;

public class BongeControlCommand {

    private static final Text TASK = Text.of("task");

    public static class TaskKill implements CommandExecutor {

        @Override
        public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
            int taskId = args.<Integer>getOne(TASK).get();
            Optional<BongeTaskData> opTask = ((BongeScheduler)Bukkit.getScheduler()).getTasks().stream().filter(d -> d.getTaskId() == taskId).findAny();
            if(!opTask.isPresent()){
                src.sendMessage(Text.of("Unknown task id to kill"));
                return CommandResult.empty();
            }
            opTask.get().cancel();
            return CommandResult.success();
        }
    }

    public static class Tasks implements CommandExecutor {

        @Override
        public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
            ((BongeScheduler)Bukkit.getScheduler()).getTasks().stream().forEach(t -> src.sendMessage(Text.of("- Id: " + t.getTaskId() + " | Plugin: " + t.getOwner().getName() + " | async: " + !t.isSync() + " | Class: " + t.getTask().getName())));
            return CommandResult.empty();
        }
    }

    public static CommandSpec createCommand(){
        CommandSpec taskKill = CommandSpec.builder().executor(new TaskKill()).permission(Permissions.BONGE_CONTROL_TASK_KILL).arguments(GenericArguments.withSuggestions(GenericArguments.integer(TASK), c -> ArrayUtils.convert(t -> t.getTaskId() + "", ((BongeScheduler)Bukkit.getScheduler()).getTasks()))).build();
        CommandSpec tasks = CommandSpec.builder().executor(new Tasks()).permission(Permissions.BONGE_CONTROL_TASKS).build();
        return CommandSpec.builder()
                .child(taskKill, "taskkill", "kill")
                .child(tasks, "tasks", "task")
                .build();
    }
}
