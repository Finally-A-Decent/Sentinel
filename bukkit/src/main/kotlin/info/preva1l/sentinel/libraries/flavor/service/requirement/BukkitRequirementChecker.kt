package info.preva1l.sentinel.libraries.flavor.service.requirement

import org.bukkit.Bukkit
import kotlin.math.max

class BukkitRequirementChecker : RequirementChecker() {
    override fun pluginExists(plugin: String): Boolean =
        Bukkit.getPluginManager().getPlugin(plugin) != null
                && Bukkit.getPluginManager().isPluginEnabled(plugin)

    override fun serverVersionIsCompatible(version: String): Boolean =
        compareVersions(version, Bukkit.getBukkitVersion().split("-")[0]) >= 0

    private fun compareVersions(v1: String, v2: String): Int {
        val parts1 = v1.split(".").dropLastWhile { it.isEmpty() }.toTypedArray()
        val parts2 = v2.split(".").dropLastWhile { it.isEmpty() }.toTypedArray()

        val length = max(parts1.size.toDouble(), parts2.size.toDouble()).toInt()
        for (i in 0 until length) {
            val num1 = if (i < parts1.size) parts1[i].toInt() else 0
            val num2 = if (i < parts2.size) parts2[i].toInt() else 0
            if (num1 != num2) {
                return num1 - num2
            }
        }
        return 0
    }
}