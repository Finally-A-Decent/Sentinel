package info.preva1l.sentinel.events;

import org.jetbrains.annotations.ApiStatus;

/**
 * Created on 18/02/2025
 *
 * @author Preva1l
 */
public enum EventOrder {
    /**
     * Listener will have a priority of 50.
     */
    FIRST(50),
    /**
     * Listener will have a priority of 40.
     */
    EARLY(40),
    /**
     * Listener will have a priority of 30.
     */
    NORMAL(30),
    /**
     * Listener will have a priority of 20.
     */
    LATE(20),
    /**
     * Listener will have a priority of 10.
     */
    LAST(10),
    /**
     * Listener will have a priority of 0.
     * <p>
     *     <b>INTERNAL USE ONLY</b>
     *     This is for Sentinel to handle the event. (i.e., putting data into a database)
     * </p>
     *
     * @hidden
     */
    @ApiStatus.Internal
    INTERNAL(0)
    ;

    /**
     * The priority that this order will give the listener.
     */
    private final int priority;

    EventOrder(int priority) {
        this.priority = priority;
    }

    /**
     * The priority that this order will give the listener.
     *
     * @return the priority
     */
    int getPriority() {
        return priority;
    }
}