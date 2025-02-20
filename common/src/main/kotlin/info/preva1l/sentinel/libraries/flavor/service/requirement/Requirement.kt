package info.preva1l.sentinel.libraries.flavor.service.requirement

/**
 * Created on 19/02/2025
 *
 * @author Preva1l
 */
enum class Requirement(
    val test: (String) -> Boolean,
) {
    /**
     * Checks if the server version is greater than or equal to the provided version.
     *
     * The version must be formatted as: `1.20.4`
     */
    SERVER_VERSION({ RequirementChecker.instance.serverVersionIsCompatible(it) }),
    /**
     * Checks if logging this action is enabled in the config.
     */
    ENABLED({ true }),
    /**
     * Checks if the provided plugin is installed and enabled.
     */
    PLUGIN({ RequirementChecker.instance.pluginExists(it) })
}