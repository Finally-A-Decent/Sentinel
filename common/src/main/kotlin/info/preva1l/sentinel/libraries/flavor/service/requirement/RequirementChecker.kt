package info.preva1l.sentinel.libraries.flavor.service.requirement

import info.preva1l.sentinel.utils.WriteOnce

/**
 * Created on 19/02/2025
 *
 * @author Preva1l
 */
abstract class RequirementChecker {
    companion object {
        var instance: RequirementChecker by WriteOnce()
    }

    abstract fun pluginExists(plugin: String): Boolean

    abstract fun serverVersionIsCompatible(version: String): Boolean
}