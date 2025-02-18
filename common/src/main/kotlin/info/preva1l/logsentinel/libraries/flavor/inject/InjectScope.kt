package info.preva1l.logsentinel.libraries.flavor.inject

import kotlin.reflect.KClass

/**
 * @author GrowlyX
 * @since 1/2/2022
 */
enum class InjectScope(
    val instanceCreator: (KClass<*>) -> Any?
)
{
    SINGLETON({
        it.java.getDeclaredField("INSTANCE").get(null)
    }),
    NO_SCOPE({
        null
    })
}
