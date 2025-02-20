package info.preva1l.sentinel

import info.preva1l.sentinel.libraries.flavor.Flavor

interface LogSentinel {
    fun load() {

    }

    fun start() {

    }

    fun stop() {

    }

    fun flavor(): Flavor
}