package info.preva1l.sentinel.events;

/**
 * Created on 18/02/2025
 *
 * @author Preva1l
 */
public interface Cancellable {
    void setCancelled(boolean cancelled);

    boolean isCancelled();
}
