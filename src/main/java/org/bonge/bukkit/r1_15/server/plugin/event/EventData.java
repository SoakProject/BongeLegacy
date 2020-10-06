package org.bonge.bukkit.r1_15.server.plugin.event;

import org.bukkit.event.*;
import org.bukkit.plugin.EventExecutor;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EventData<T extends Event> {

    public static class SimpleEventExecutor implements EventExecutor {

        private final EventPriority pri;

        public SimpleEventExecutor(EventPriority pri){
            this.pri = pri;
        }

        @Override
        public void execute(@NotNull Listener listener, @NotNull Event event) throws EventException {
            Method[] methods = listener.getClass().getMethods();
            List<Method> methodsList = Stream.of(methods)
                    .filter(m -> Stream.of(m.getAnnotations()).anyMatch(a -> a.annotationType().isAssignableFrom(EventHandler.class)))
                    .filter(m -> m.getParameterCount() == 1)
                    .filter(m -> m.getParameters()[0].getType().getName().equals(event.getClass().getName()))
                    .filter(m -> m.getAnnotation(EventHandler.class).priority().equals(this.pri))
                    .collect(Collectors.toList());
            boolean isCancelled = false;
            for (Method method : methodsList){
                EventHandler eventHandler = method.getAnnotation(EventHandler.class);
                if(isCancelled && !eventHandler.ignoreCancelled()){
                    continue;
                }
                try {
                    method.invoke(listener, event);
                    if(event instanceof Cancellable){
                        isCancelled = ((Cancellable)event).isCancelled();
                    }
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new EventException(e);
                }
            }
        }
    }

    protected Class<T> event;
    protected EventPriority priority;
    protected Listener listener;
    protected Plugin holder;
    protected EventExecutor executor;

    public EventData(Class<T> event, EventPriority priority, Listener listener, Plugin holder){
        this(event, priority, listener, new SimpleEventExecutor(priority), holder);
    }

    public EventData(Class<T> event, EventPriority priority, Listener listener, EventExecutor executor, Plugin holder) {
        this.event = event;
        this.priority = priority;
        this.listener = listener;
        this.holder = holder;
        this.executor = executor;
    }

    public boolean isValid(Event event){
        return event.getClass().getName().equals(this.getEvent().getName());
    }

    public Class<T> getEvent() {
        return event;
    }

    public EventPriority getPriority() {
        return priority;
    }

    public Listener getListener() {
        return listener;
    }

    public Plugin getHolder() {
        return holder;
    }

    public EventExecutor getExecutor() {
        return executor;
    }

    public void run(T event) throws EventException {
        this.executor.execute(this.listener, event);
    }
}
