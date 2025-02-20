package info.preva1l.sentinel.events;

import info.preva1l.sentinel.events.annotations.Subscribe;
import info.preva1l.sentinel.sanity.ApiSanityChecker;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * The LogSentinel Event Bus is a simple, yet feature rich event system for the LogSentinel API.
 *
 * <p> Created on 18/02/2025 </p>
 *
 * @author Preva1l
 */
public final class SentinelEventBus {
    private static final SentinelEventBus INSTANCE = new SentinelEventBus();
    private final Map<Class<? extends Event>, SortedSet<Subscription<?>>> subscribers = new HashMap<>();

    /**
     * Open a subscription to an event class with a normal priority.
     * You add the handler in the next step!
     */
    public <T extends Event> SubscriptionBuilder<T> subscribe(Class<T> event) {
        return subscribe(event, EventOrder.FIRST);
    }

    /**
     * Open a subscription to an event class with a specific priority.
     * You add the handler in the next step!
     */
    public <T extends Event> SubscriptionBuilder<T> subscribe(Class<T> event, EventOrder order) {
        return new SubscriptionBuilder<>(event, order);
    }

    /**
     * An alternate way to subscribe to events.
     *
     * <p>
     *     To use this alternate subscription method it's very similar to the Velocity & Bukkit API.
     * <pre>{@code
     *  class MyPlugin {
     *      init {
     *          LogSentinelEventBus.instance().subscribe(MyEventListener())
     *      }
     *  }
     *
     *  class MyEventListener {
     *      @Subscribe
     *      fun on(event: UserLogActionEvent) {
     *          println(event.user.toString())
     *      }
     *  }
     * }</pre>
     * </p>
     */
    public void subscribe(Object... subscribers) {
        for (Object subscriber : subscribers) {
            for (Method method : subscriber.getClass().getDeclaredMethods()) {
                var annotation = method.getAnnotation(Subscribe.class);
                if (annotation == null || method.getParameterCount() != 1) continue;

                method.setAccessible(true);
                var paramType = method.getParameterTypes()[0];

                if (!Event.class.isAssignableFrom(paramType)) continue;

                @SuppressWarnings("unchecked")
                var eventClass = (Class<? extends Event>) paramType;

                complete(eventClass, annotation.value(), List.of(), event -> {
                    try {
                        method.invoke(subscriber, event);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
        }
    }

    @SuppressWarnings("unchecked")
    public <T extends Event> boolean post(T event) {
        var set = subscribers.get(event.getClass());
        if (set == null) return false;

        for (Subscription<?> wrapper : set) {
            var handler = (Consumer<T>) wrapper.handler;
            for (Predicate<?> filter : wrapper.filters) {
                if (!((Predicate<T>) filter).test(event)) return false;
            }
            handler.accept(event);
        }

        return event instanceof Cancellable cancellable && cancellable.isCancelled();
    }

    <T extends Event> boolean isActive(Subscription<T> subscription) {
        var set = subscribers.get(subscription.eventClass);
        return set != null && set.contains(subscription);
    }

    <T extends Event> Subscription<T> complete(Class<T> event, EventOrder order, List<Predicate<T>> filters, Consumer<T> handler) {
        var queue = subscribers.computeIfAbsent(event, k ->
                new TreeSet<>(Comparator.comparingInt((Subscription<?> s) -> s.priority).reversed())
        );
        var sub = new Subscription<>(event, order.getPriority(), filters, handler);
        queue.add(sub);
        return sub;
    }

    <T extends Event> void unsubscribe(Subscription<T> subscription) {
        subscribers.computeIfPresent(subscription.eventClass, (k, set) -> {
            set.remove(subscription);
            return set;
        });
    }

    /**
     * Get the instance of the LogSentinel Event Bus
     */
    public static SentinelEventBus instance() {
        if (!ApiSanityChecker.validApiInstance)
            throw new IllegalStateException(
                    """
                    The LogSentinelAPI is not available!
                    You most likely shaded/implemented the api instead of compiling against it!
                    """.stripIndent()
            );
        return INSTANCE;
    }
}
