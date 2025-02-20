package info.preva1l.sentinel;

import info.preva1l.sentinel.events.EventOrder;
import info.preva1l.sentinel.events.SentinelEventBus;
import info.preva1l.sentinel.events.Subscription;
import info.preva1l.sentinel.events.impl.UserLogActionEvent;

/**
 * Created on 18/02/2025
 *
 * @author Preva1l
 */
public class ExampleJavaUsage {
    ExampleJavaUsage() {
        // its not as clean in java (just use kotlin pluh)
        SentinelEventBus.instance()
                .subscribe(UserLogActionEvent.class, EventOrder.LAST)
                .filter(it -> it.getUser().name().equals("Skullians")) // filtering!!
                .handler(event -> {
                        System.out.println("Hello there Skullians! (${it.user.uniqueId()})");
                });


        Subscription<UserLogActionEvent> subscription = SentinelEventBus.instance() // it returns an object??
                .subscribe(UserLogActionEvent.class) // Event order is optional!
                .filter(it -> it.getUser().name().equals("Skullians"))
                .handler(event -> {
                    System.out.println("Hello there Skullians! (${it.user.uniqueId()})");
                });

        subscription.unsubscribe(); // OHHH that's why it returns an object!!!
    }
}
