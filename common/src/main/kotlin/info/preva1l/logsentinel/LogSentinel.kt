package info.preva1l.logsentinel

import info.preva1l.logsentinel.libraries.flavor.Flavor
import info.preva1l.logsentinel.libraries.flavor.FlavorOptions
import java.util.logging.Logger

interface LogSentinel {
    fun load() {
        flavor(
            Flavor.create<LogSentinel>(
                FlavorOptions(Logger.getLogger("LogSentinel")),
            )
        )
    }

    fun start() {

    }

    fun stop() {

    }

    fun flavor(flavor: Flavor)
    fun flavor(): Flavor
}