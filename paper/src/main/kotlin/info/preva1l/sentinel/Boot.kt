package info.preva1l.sentinel

import io.papermc.paper.plugin.bootstrap.BootstrapContext
import io.papermc.paper.plugin.bootstrap.PluginBootstrap
import io.papermc.paper.plugin.bootstrap.PluginProviderContext

/**
 * Created on 1/07/2025
 *
 * @author Preva1l
 */
@Suppress("UnstableApiUsage")
class Boot : PluginBootstrap {
    override fun bootstrap(context: BootstrapContext) {}
    override fun createPlugin(context: PluginProviderContext) = PaperSentinel
}