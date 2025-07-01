package info.preva1l.sentinel.adapter

import net.kyori.adventure.key.Key

/**
 * Created on 1/07/2025
 *
 * @author Preva1l
 */
interface WorldAdapter<T> {
    fun toWorld(obj: T): Key

    fun fromWorld(world: Key): T
}