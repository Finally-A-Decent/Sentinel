package info.preva1l.sentinel

import info.preva1l.sentinel.annotations.Plugin
import info.preva1l.sentinel.libraries.flavor.Flavor
import info.preva1l.sentinel.libraries.flavor.FlavorOptions
import info.preva1l.sentinel.libraries.flavor.service.requirement.BukkitRequirementChecker
import info.preva1l.sentinel.utils.WriteOnce
import org.bukkit.plugin.java.JavaPlugin
import java.util.logging.Logger

@Plugin(
    name = "LogSentinel",
    description = "Bullet proof logging & rollbacks for your server.",
    authors = ["Preva1l"],
    libraries = [
        "org.jetbrains.kotlin:kotlin-stdlib:2.1.10",
        "org.reflections:reflections:0.10.2",
        "org.aspectj:aspectjrt:1.9.22.1",
        "org.aspectj:aspectjweaver:1.9.22.1",
        "de.exlll:configlib-yaml:4.5.0",
    ]
)
class BukkitLogSentinel : JavaPlugin(), LogSentinel {
    private var flavor: Flavor by WriteOnce()

    override fun onLoad() {
        flavor = Flavor.create<LogSentinel>(
            BukkitRequirementChecker(),
            FlavorOptions(Logger.getLogger("LogSentinel")),
        )
        load()
    }

    override fun onEnable() {
        start()
    }

    override fun onDisable() {
        stop()
    }

    override fun flavor(): Flavor = flavor
}