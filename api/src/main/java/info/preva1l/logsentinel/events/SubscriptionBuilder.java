package info.preva1l.logsentinel.events;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Created on 18/02/2025
 *
 * @author Preva1l
 */
public final class SubscriptionBuilder<T extends Event> {
    private final Class<T> event;
    private final EventOrder order;
    private final List<Predicate<T>> filters = new ArrayList<>();

    SubscriptionBuilder(Class<T> event, EventOrder order) {
        this.event = event;
        this.order = order;
    }

    public SubscriptionBuilder<T> filter(Predicate<T> filter) {
        filters.add(filter);
        return this;
    }

    public Subscription<T> handler(Consumer<T> handler) {
        return LogSentinelEventBus.instance().complete(event, order, filters, handler);
    }
}
