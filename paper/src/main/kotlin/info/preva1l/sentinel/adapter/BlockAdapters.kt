package info.preva1l.sentinel.adapter

import info.preva1l.sentinel.types.Block
import org.bukkit.block.BlockState
import org.bukkit.block.data.BlockData

/**
 * Created on 1/07/2025
 *
 * @author Preva1l
 */
object BlockAdapters : BlockAdapter<org.bukkit.block.Block, BlockData, BlockState> {
    override fun toBlock(obj: org.bukkit.block.Block) =
        Block(
            obj.type.key,
            SimpleAdapters.toPosition(obj.location),
            obj.blockData.asString
        )

    override fun extractData(block: Block): BlockData {
        TODO("Not yet implemented")
    }

    override fun extractState(block: Block): BlockState {
        TODO("Not yet implemented")
    }
}