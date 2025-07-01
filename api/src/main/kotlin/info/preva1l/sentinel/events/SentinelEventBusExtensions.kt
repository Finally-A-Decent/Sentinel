package info.preva1l.sentinel.events

/**
 * A nice inline function for kotlin users to subscribe to events.
 *
 * Created on 18/02/2025
 *
 * @author Preva1l
 */
inline fun <reified T : Event> SentinelEventBus.subscribe(
    order: EventOrder = EventOrder.NORMAL
): SubscriptionBuilder<T> {
    return subscribe(T::class.java, order)
}
