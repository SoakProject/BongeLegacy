package org.bonge.bukkit.scheduler;

import org.bonge.Bonge;
import org.bonge.launch.BongeLaunch;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scheduler.BukkitWorker;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.scheduler.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class BongeScheduler implements BukkitScheduler {

    private class BongeWrappedRunnable implements Runnable {

        private Runnable run;
        private BongeTaskData data;

        public BongeWrappedRunnable(Runnable run){
            this.run = run;
        }

        public Optional<BongeTaskData> getData(){
            return Optional.ofNullable(this.data);
        }

        public void setData(BongeTaskData data){
            this.data = data;
        }

        @Override
        public void run() {
            try{
                this.run.run();
            }catch (Throwable e){
                if(this.data != null){
                    Bonge.createCrashFile(this.data.getOwner(), "scheduler", e);
                }
                throw e;
            }
        }
    }

    private List<BongeTaskData> tasks = new ArrayList<>();

    private Optional<BongeTaskData> getId(int id){
        Set<BongeTaskData> set = get(b -> b.getTaskId() == id);
        if (set.size() == 0){
            return Optional.empty();
        }
        return Optional.of(set.iterator().next());
    }

    private Set<BongeTaskData> get(Predicate<BongeTaskData> predicate){
        return this.tasks.stream().filter(predicate).collect(Collectors.toSet());

    }

    private int getRandomID(){
        int id = this.tasks.size();
        return id;
    }

    @Override
    public int scheduleSyncDelayedTask(@NotNull Plugin plugin, @NotNull Runnable task, long delay) {
        BongeWrappedRunnable runnable = new BongeWrappedRunnable(task);
        Task task1 = Task.builder().delayTicks(delay).execute(runnable).submit(BongeLaunch.getInstance());
        int id = getRandomID();
        BongeTaskData data = new BongeTaskData(task1, plugin, id, true);
        runnable.setData(data);
        this.tasks.add(data);
        return id;
    }

    @Override
    @Deprecated
    public int scheduleSyncDelayedTask(@NotNull Plugin plugin, @NotNull BukkitRunnable task, long delay) {
        return this.scheduleSyncDelayedTask(plugin, (Runnable)task, delay);
    }

    @Override
    public int scheduleSyncDelayedTask(@NotNull Plugin plugin, @NotNull Runnable task) {
        return this.scheduleSyncDelayedTask(plugin, task, 0);
    }

    @Override
    @Deprecated
    public int scheduleSyncDelayedTask(@NotNull Plugin plugin, @NotNull BukkitRunnable task) {
        return this.scheduleSyncDelayedTask(plugin, task, 0);
    }

    @Override
    public int scheduleSyncRepeatingTask(@NotNull Plugin plugin, @NotNull Runnable task, long delay, long period) {
        BongeWrappedRunnable runnable = new BongeWrappedRunnable(task);
        Task task1 = Task.builder().delayTicks(delay).intervalTicks(period).execute(runnable).submit(BongeLaunch.getInstance());
        int id = getRandomID();
        BongeTaskData data = new BongeTaskData(task1, plugin, id, true);
        runnable.setData(data);
        this.tasks.add(data);
        return id;
    }

    @Override
    @Deprecated
    public int scheduleSyncRepeatingTask(@NotNull Plugin plugin, @NotNull BukkitRunnable task, long delay, long period) {
        return this.scheduleSyncRepeatingTask(plugin, (Runnable) task, delay, period);
    }

    @Override
    public int scheduleAsyncDelayedTask(@NotNull Plugin plugin, @NotNull Runnable task, long delay) {
        BongeWrappedRunnable runnable = new BongeWrappedRunnable(task);
        Task task1 = Task.builder().async().delayTicks(delay).execute(runnable).submit(BongeLaunch.getInstance());
        int id = getRandomID();
        BongeTaskData data = new BongeTaskData(task1, plugin, id, false);
        runnable.setData(data);
        this.tasks.add(data);
        return id;
    }

    @Override
    public int scheduleAsyncDelayedTask(@NotNull Plugin plugin, @NotNull Runnable task) {
        return this.scheduleAsyncDelayedTask(plugin, task, 0);
    }

    @Override
    public int scheduleAsyncRepeatingTask(@NotNull Plugin plugin, @NotNull Runnable task, long delay, long period) {
        BongeWrappedRunnable runnable = new BongeWrappedRunnable(task);
        Task task1 = Task.builder().async().delayTicks(delay).intervalTicks(period).execute(runnable).submit(BongeLaunch.getInstance());
        int id = getRandomID();
        BongeTaskData data = new BongeTaskData(task1, plugin, id, false);
        runnable.setData(data);
        this.tasks.add(data);
        return id;
    }

    @NotNull
    @Override
    public <T> Future<T> callSyncMethod(@NotNull Plugin plugin, @NotNull Callable<T> task) {
        return null;
    }

    @Override
    public void cancelTask(int taskId) {
        getId(taskId).ifPresent(t -> {
            t.getTask().cancel();
            this.tasks.remove(t);
        });
    }

    @Override
    public void cancelTasks(@NotNull Plugin plugin) {
        get(b -> b.getOwner().equals(plugin)).stream().forEach(c -> {
            c.getTask().cancel();
            this.tasks.remove(c);
        });
    }

    @Override
    public boolean isCurrentlyRunning(int taskId) {
        Optional<BongeTaskData> opBongeTaskData = getId(taskId);
        return opBongeTaskData.isPresent();
    }

    @Override
    public boolean isQueued(int taskId) {
        return false;
    }

    @NotNull
    @Override
    public List<BukkitWorker> getActiveWorkers() {
        return new ArrayList<>();
    }

    @NotNull
    @Override
    public List<BukkitTask> getPendingTasks() {
        return new ArrayList<>();
    }

    @NotNull
    @Override
    public BukkitTask runTask(@NotNull Plugin plugin, @NotNull Runnable task) throws IllegalArgumentException {
        BongeWrappedRunnable runnable = new BongeWrappedRunnable(task);
        Task result = Sponge.getScheduler().createTaskBuilder().execute(runnable).submit(BongeLaunch.getInstance());
        BongeTaskData taskData = new BongeTaskData(result, plugin, this.getRandomID(), true);
        runnable.setData(taskData);
        this.tasks.add(taskData);
        return taskData;
    }

    @NotNull
    @Override
    public BukkitTask runTask(@NotNull Plugin plugin, @NotNull BukkitRunnable task) throws IllegalArgumentException {
        return null;
    }

    @NotNull
    @Override
    public BukkitTask runTaskAsynchronously(@NotNull Plugin plugin, @NotNull Runnable task) throws IllegalArgumentException {
        BongeWrappedRunnable runnable = new BongeWrappedRunnable(task);
        Task result = Sponge.getScheduler().createTaskBuilder().async().execute(runnable).submit(BongeLaunch.getInstance());
        BongeTaskData taskData = new BongeTaskData(result, plugin, this.getRandomID(), false);
        runnable.setData(taskData);
        this.tasks.add(taskData);
        return taskData;
    }

    @NotNull
    @Override
    public BukkitTask runTaskAsynchronously(@NotNull Plugin plugin, @NotNull BukkitRunnable task) throws IllegalArgumentException {
        return null;
    }

    @NotNull
    @Override
    public BukkitTask runTaskLater(@NotNull Plugin plugin, @NotNull Runnable task, long delay) throws IllegalArgumentException {
        BongeWrappedRunnable runnable = new BongeWrappedRunnable(task);
        Task result = Sponge.getScheduler().createTaskBuilder().execute(runnable).delayTicks(delay).submit(BongeLaunch.getInstance());
        BongeTaskData data = new BongeTaskData(result, plugin, this.getRandomID(), true);
        this.tasks.add(data);
        return data;
    }

    @NotNull
    @Override
    public BukkitTask runTaskLater(@NotNull Plugin plugin, @NotNull BukkitRunnable task, long delay) throws IllegalArgumentException {
        return null;
    }

    @NotNull
    @Override
    public BukkitTask runTaskLaterAsynchronously(@NotNull Plugin plugin, @NotNull Runnable task, long delay) throws IllegalArgumentException {
        return null;
    }

    @NotNull
    @Override
    public BukkitTask runTaskLaterAsynchronously(@NotNull Plugin plugin, @NotNull BukkitRunnable task, long delay) throws IllegalArgumentException {
        return null;
    }

    @NotNull
    @Override
    public BukkitTask runTaskTimer(@NotNull Plugin plugin, @NotNull Runnable task, long delay, long period) throws IllegalArgumentException {
        return null;
    }

    @NotNull
    @Override
    public BukkitTask runTaskTimer(@NotNull Plugin plugin, @NotNull BukkitRunnable task, long delay, long period) throws IllegalArgumentException {
        return null;
    }

    @NotNull
    @Override
    public BukkitTask runTaskTimerAsynchronously(@NotNull Plugin plugin, @NotNull Runnable task, long delay, long period) throws IllegalArgumentException {
        BongeWrappedRunnable runnable = new BongeWrappedRunnable(task);
        Task result = Sponge.getScheduler().createTaskBuilder().async().delayTicks(delay).intervalTicks(period).execute(runnable).submit(BongeLaunch.getInstance());
        BongeTaskData taskData = new BongeTaskData(result, plugin, this.getRandomID(), false);
        runnable.setData(taskData);
        this.tasks.add(taskData);
        return taskData;
    }

    @NotNull
    @Override
    public BukkitTask runTaskTimerAsynchronously(@NotNull Plugin plugin, @NotNull BukkitRunnable task, long delay, long period) throws IllegalArgumentException {
        return null;
    }
}
