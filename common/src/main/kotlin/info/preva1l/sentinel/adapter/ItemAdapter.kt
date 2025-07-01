package info.preva1l.sentinel.adapter

import info.preva1l.sentinel.types.Item

/**
 * Created on 1/07/2025
 *
 * @author Preva1l
 */
interface ItemAdapter<T> {
    fun toItem(obj: T): Item

    fun fromItem(item: Item): T
}