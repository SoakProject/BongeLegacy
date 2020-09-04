package org.bonge.command;

import net.kyori.adventure.text.TextComponent;
import org.bonge.bukkit.r1_14.scheduler.BongeScheduler;
import org.bonge.bukkit.r1_14.scheduler.BongeTaskData;
import org.bukkit.Bukkit;
import org.spongepowered.api.command.Command;
import org.spongepowered.api.command.CommandExecutor;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.parameter.CommandContext;
import org.spongepowered.api.command.parameter.Parameter;

import java.util.Optional;

public class BongeControlCommand {

    private static final Parameter.Value<Integer> TASK = Parameter.integerNumber().setKey("Task").build();

    public static class TaskKill implements CommandExecutor {

        @Override
        public CommandResult execute(CommandContext context){
            int taskId = context.getOne(TASK).get();
            Optional<BongeTaskData> opTask = ((BongeScheduler)Bukkit.getScheduler()).getTasks().stream().filter(d -> d.getTaskId() == taskId).findAny();
            if(!opTask.isPresent()){
                context.sendMessage(TextComponent.of("Unknown task id to kill"));
                return CommandResult.empty();
            }
            opTask.get().cancel();
            return CommandResult.success();
        }
    }

    public static class Tasks implements CommandExecutor {

        @Override
        public CommandResult execute(CommandContext context) {
            ((BongeScheduler)Bukkit.getScheduler()).getTasks().forEach(t -> context.sendMessage(TextComponent.of("- Id: " + t.getTaskId() + " | Plugin: " + t.getOwner().getName() + " | async: " + !t.isSync() + " | Class: " + t.getTask().getName())));
            return CommandResult.empty();
        }
    }

    public static Command.Parameterized createCommand(){
        Command.Parameterized taskKill = Command.builder()
                .setExecutor(new TaskKill())
                .setPermission(Permissions.BONGE_CONTROL_TASK_KILL)
                .parameter(TASK)
                .build();
        Command.Parameterized tasks = Command.builder()
                .setExecutor(new Tasks())
                .setPermission(Permissions.BONGE_CONTROL_TASKS)
                .build();
        return Command.builder()
                .child(taskKill, "taskkill", "kill")
                .child(tasks, "tasks", "task")
                .build();
    }
}
