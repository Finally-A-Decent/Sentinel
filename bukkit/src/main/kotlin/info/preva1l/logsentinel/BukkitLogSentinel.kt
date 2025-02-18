package info.preva1l.logsentinel

import info.preva1l.logsentinel.annotations.Plugin
import info.preva1l.logsentinel.libraries.flavor.Flavor
import info.preva1l.logsentinel.utils.WriteOnce
import org.bukkit.plugin.java.JavaPlugin

@Plugin(
    name = "LogSentinel",
    description = "Bullet proof logging & rollbacks for your server.",
    authors = ["Preva1l"],
    libraries = [
        "org.jetbrains.kotlin:kotlin-stdlib:2.1.10",
        "org.reflections:reflections:0.10.2",
        "org.aspectj:aspectjrt:1.9.22.1",
        "org.aspectj:aspectjweaver:1.9.22.1",
    ]
)
class BukkitLogSentinel : JavaPlugin(), LogSentinel {
    private var flavor: Flavor by WriteOnce()

    override fun flavor(flavor: Flavor) {
        this.flavor = flavor
    }

    override fun flavor(): Flavor = flavor
}