package org.bonge.bukkit.r1_15.scheduler;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;
import org.spongepowered.api.scheduler.ScheduledTask;

public class BongeTaskData implements BukkitTask {

    private final ScheduledTask task;
    private int id;
    private final Plugin plugin;
    private boolean synced;

    public BongeTaskData(ScheduledTask task, Plugin plugin, int id, boolean synced){
        this.task = task;
        this.plugin = plugin;
        this.id = id;
    }

    public ScheduledTask getTask(){
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

    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof BukkitTask)){
            return false;
        }
        return ((BukkitTask)obj).getTaskId() == this.getTaskId();
    }
}
