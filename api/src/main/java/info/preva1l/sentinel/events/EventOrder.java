package info.preva1l.sentinel.events;

import info.preva1l.sentinel.annotations.UsageInfo;

/**
 * Created on 18/02/2025
 *
 * @author Preva1l
 */
public enum EventOrder {
    FIRST(50),
    EARLY(40),
    NORMAL(30),
    LATE(20),
    LAST(10),
    @UsageInfo("Avoid using EventOrder.EDIT as it can cause conflicts!")
    EDIT(0)
    ;

    private final int priority;

    EventOrder(int priority) {
        this.priority = priority;
    }

    int getPriority() {
        return priority;
    }
}