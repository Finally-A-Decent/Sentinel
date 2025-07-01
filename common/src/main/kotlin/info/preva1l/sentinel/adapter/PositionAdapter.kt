package info.preva1l.sentinel.adapter

import info.preva1l.sentinel.types.Position

/**
 * Created on 1/07/2025
 *
 * @author Preva1l
 */
interface PositionAdapter<T> {
    fun toPosition(obj: T): Position

    fun fromPosition(position: Position): T
}