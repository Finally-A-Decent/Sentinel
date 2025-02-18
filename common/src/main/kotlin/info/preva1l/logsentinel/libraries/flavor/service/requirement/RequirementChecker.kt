package info.preva1l.logsentinel.libraries.flavor.service.requirement

import com.sun.source.util.Plugin

/**
 * Created on 19/02/2025
 *
 * @author Preva1l
 */
abstract class RequirementChecker {
    companion object {
        private var instance: RequirementChecker? = null
    }

    abstract fun pluginExists(plugin: String): Boolean
}