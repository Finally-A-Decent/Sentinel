package info.preva1l.logsentinel;

import info.preva1l.logsentinel.events.EventOrder;
import info.preva1l.logsentinel.events.LogSentinelEventBus;
import info.preva1l.logsentinel.events.Subscription;
import info.preva1l.logsentinel.events.impl.UserLogActionEvent;

/**
 * Created on 18/02/2025
 *
 * @author Preva1l
 */
public class ExampleJavaUsage {
    ExampleJavaUsage() {
        // its not as clean in java (just use kotlin pluh)
        LogSentinelEventBus.instance()
                .subscribe(UserLogActionEvent.class, EventOrder.LAST)
                .filter(it -> it.getUser().name().equals("Skullians")) // filtering!!
                .handler(event -> {
                        System.out.println("Hello there Skullians! (${it.user.uniqueId()})");
                });


        Subscription<UserLogActionEvent> subscription = LogSentinelEventBus.instance() // it returns an object??
                .subscribe(UserLogActionEvent.class) // Event order is optional!
                .filter(it -> it.getUser().name().equals("Skullians"))
                .handler(event -> {
                    System.out.println("Hello there Skullians! (${it.user.uniqueId()})");
                });

        subscription.unsubscribe(); // OHHH that's why it returns an object!!!
    }
}
