package info.preva1l.logsentinel

import info.preva1l.logsentinel.libraries.flavor.Flavor
import info.preva1l.logsentinel.libraries.flavor.FlavorOptions
import java.util.logging.Logger

interface LogSentinel {
    fun load() {

    }

    fun start() {

    }

    fun stop() {

    }

    fun flavor(): Flavor
}