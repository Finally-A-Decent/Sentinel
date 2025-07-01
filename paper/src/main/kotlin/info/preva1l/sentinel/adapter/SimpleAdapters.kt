package info.preva1l.sentinel.adapter

import info.preva1l.sentinel.types.Item
import info.preva1l.sentinel.types.Position
import net.kyori.adventure.key.Key
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.World
import org.bukkit.inventory.ItemStack

/**
 * Created on 1/07/2025
 *
 * @author Preva1l
 */
object SimpleAdapters : PositionAdapter<Location>, WorldAdapter<World>, ItemAdapter<ItemStack> {
    override fun toPosition(obj: Location) =
        Position(
            toWorld(obj.world),
            obj.x,
            obj.y,
            obj.z,
        )

    override fun fromPosition(position: Position) =
        Location(
            fromWorld(position.world),
            position.x,
            position.y,
            position.z,
        )

    override fun toWorld(obj: World) = obj.key
    override fun fromWorld(world: Key) = Bukkit.getWorld(world.bukkit())!!

    override fun toItem(obj: ItemStack) =
        Item(obj.serializeAsBytes().asNBT())

    override fun fromItem(item: Item) =
        ItemStack.deserializeBytes(item.rawNbtData.asByteArray())
}