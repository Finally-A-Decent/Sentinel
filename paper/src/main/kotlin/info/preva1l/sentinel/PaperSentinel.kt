package info.preva1l.sentinel

import info.preva1l.trashcan.extension.BasePlugin
import info.preva1l.trashcan.extension.annotations.PluginEnable

object PaperSentinel : BasePlugin(), Sentinel {
    @PluginEnable
    fun enable() {
        ApiSanityChecker.validApiInstance = true
    }
}