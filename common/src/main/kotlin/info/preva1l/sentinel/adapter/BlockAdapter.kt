package info.preva1l.sentinel.adapter

import info.preva1l.sentinel.types.Block

/**
 * Created on 1/07/2025
 *
 * @author Preva1l
 */
interface BlockAdapter<T, S, N> {
    fun toBlock(obj: T): Block

    fun extractData(block: Block): S

    fun extractState(block: Block): N
}