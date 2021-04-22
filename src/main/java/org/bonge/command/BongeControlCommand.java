package org.bonge.command;

import net.kyori.adventure.identity.Identity;
import net.kyori.adventure.text.Component;
import org.bonge.bukkit.r1_16.scheduler.BongeScheduler;
import org.bonge.bukkit.r1_16.scheduler.BongeTaskData;
import org.bukkit.Bukkit;
import org.spongepowered.api.command.Command;
import org.spongepowered.api.command.CommandExecutor;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.parameter.CommandContext;
import org.spongepowered.api.command.parameter.Parameter;

import java.util.Optional;

public class BongeControlCommand {

    private static final Parameter.Value<Integer> TASK = Parameter.integerNumber().key("Task").build();

    public static class TaskKill implements CommandExecutor {

        @Override
        public CommandResult execute(CommandContext context){
            int taskId = context.one(TASK).get();
            Optional<BongeTaskData> opTask = ((BongeScheduler)Bukkit.getScheduler()).getTasks().stream().filter(d -> d.getTaskId() == taskId).findAny();
            if(!opTask.isPresent()){
                context.sendMessage(Identity.nil(), Component.text("Unknown task id to kill"));
                return CommandResult.empty();
            }
            opTask.get().cancel();
            return CommandResult.success();
        }
    }

    public static class Tasks implements CommandExecutor {

        @Override
        public CommandResult execute(CommandContext context) {
            ((BongeScheduler)Bukkit.getScheduler()).getTasks().forEach(t -> context.sendMessage(Identity.nil(), Component.text("- Id: " + t.getTaskId() + " | Plugin: " + t.getOwner().getName() + " | async: " + !t.isSync() + " | Class: " + t.getTask().name())));
            return CommandResult.empty();
        }
    }

    public static Command.Parameterized createCommand(){
        Command.Parameterized taskKill = Command.builder()
                .executor(new TaskKill())
                .permission(Permissions.BONGE_CONTROL_TASK_KILL)
                .addParameter(TASK)
                .build();
        Command.Parameterized tasks = Command.builder()
                .executor(new Tasks())
                .permission(Permissions.BONGE_CONTROL_TASKS)
                .build();
        return Command.builder()
                .addChild(taskKill, "taskkill", "kill")
                .addChild(tasks, "tasks", "task")
                .build();
    }
}
