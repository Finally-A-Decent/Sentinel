package info.preva1l.sentinel.events.impl;

import info.preva1l.sentinel.events.Event;
import info.preva1l.sentinel.user.User;

/**
 * Created on 18/02/2025
 *
 * @author Preva1l
 */
public abstract class UserEvent implements Event {
    private final User user;

    protected UserEvent(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
