package org.bonge.bukkit.r1_13.scheduler;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;
import org.spongepowered.api.scheduler.Task;

public class BongeTaskData implements BukkitTask {

    private Task task;
    private int id;
    private Plugin plugin;
    private boolean synced;

    public BongeTaskData(Task task, Plugin plugin, int id, boolean synced){
        this.task = task;
        this.plugin = plugin;
        this.id = id;
    }

    public Task getTask(){
        return this.task;
    }

    @Override
    public int getTaskId() {
        return this.id;
    }

    @Override
    public Plugin getOwner() {
        return this.plugin;
    }

    @Override
    public boolean isSync() {
        return this.synced;
    }

    @Override
    public boolean isCancelled() {
        return !Bukkit.getScheduler().isCurrentlyRunning(this.id);
    }

    @Override
    public void cancel() {
        Bukkit.getScheduler().cancelTask(this.id);
    }
}
