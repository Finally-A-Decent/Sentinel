package info.preva1l.logsentinel.libraries.flavor.service.requirement

/**
 * Created on 19/02/2025
 *
 * @author Preva1l
 */
enum class Require(
    val test: (String) -> Boolean,
) {
    SERVER_VERSION({ true }),
    ENABLED({
        true
    }),
    PLUGIN({

    })
}