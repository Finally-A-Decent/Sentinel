package info.preva1l.logsentinel.libraries.flavor.binder

import info.preva1l.logsentinel.libraries.flavor.FlavorBinder

/**
 * @author GrowlyX
 * @since 9/6/2022
 */
abstract class FlavorBinderContainer
{
    internal val binders = mutableListOf<FlavorBinder<*>>()

    abstract fun populate()

    fun bind(`object`: Any) = FlavorBinderMultiType(this, `object`)
}
