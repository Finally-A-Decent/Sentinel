package info.preva1l.sentinel.events.impl;

import info.preva1l.sentinel.action.Action;
import info.preva1l.sentinel.events.Cancellable;
import info.preva1l.sentinel.user.User;

/**
 * Created on 18/02/2025
 *
 * @author Preva1l
 */
public final class ActionLoggedEvent extends UserEvent implements Cancellable {
    private boolean cancelled;
    private final Action action;

    public ActionLoggedEvent(User user, Action action) {
        super(user);
        this.action = action;
    }

    public Action getAction() {
        return action;
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
