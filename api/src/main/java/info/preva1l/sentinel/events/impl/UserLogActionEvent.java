package info.preva1l.sentinel.events.impl;

import info.preva1l.sentinel.events.Cancellable;
import info.preva1l.sentinel.logs.Log;
import info.preva1l.sentinel.user.User;

/**
 * Created on 18/02/2025
 *
 * @author Preva1l
 */
public final class UserLogActionEvent extends UserEvent implements Cancellable {
    private boolean cancelled;
    private final Log log;

    public UserLogActionEvent(User user, Log log) {
        super(user);
        this.log = log;
    }

    public Log getLog() {
        return log;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }
}
