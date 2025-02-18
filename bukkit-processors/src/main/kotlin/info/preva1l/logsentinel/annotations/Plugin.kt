package info.preva1l.logsentinel.annotations

import org.bukkit.plugin.PluginLoadOrder

/**
 * Created on 19/02/2025
 *
 * @author Preva1l
 */
@MustBeDocumented
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.SOURCE)
annotation class Plugin(
    val name: String,
    val description: String = "",
    val load: PluginLoadOrder = PluginLoadOrder.STARTUP,
    val apiVersion: String = "",
    val authors: Array<String> = [],
    val website: String = "",
    val depends: Array<Dependency> = [],
    val libraries: Array<String> = [],
)
