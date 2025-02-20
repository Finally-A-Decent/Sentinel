package info.preva1l.sentinel.events;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Created on 18/02/2025
 *
 * @author Preva1l
 */
public final class Subscription<T extends Event> {
    private final UUID identifier = UUID.randomUUID();

    public final Class<T> eventClass;
    public final int priority;
    final List<Predicate<T>> filters;
    final Consumer<T> handler;

    Subscription(Class<T> eventClass, int priority, List<Predicate<T>> filters, Consumer<T> handler) {
        this.eventClass = eventClass;
        this.priority = priority;
        this.filters = filters;
        this.handler = handler;
    }

    public void unsubscribe() {
        SentinelEventBus.instance().unsubscribe(this);
    }

    public boolean isActive() {
        return SentinelEventBus.instance().isActive(this);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Subscription<?> that = (Subscription<?>) o;
        return Objects.equals(identifier, that.identifier);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(identifier);
    }
}