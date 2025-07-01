package info.preva1l.sentinel.adapter

import net.kyori.adventure.key.Key
import net.kyori.adventure.nbt.CompoundBinaryTag
import net.kyori.adventure.nbt.TagStringIO
import org.bukkit.NamespacedKey
import java.nio.charset.StandardCharsets

/**
 * Created on 1/07/2025
 *
 * @author Preva1l
 */
fun Key.bukkit() = NamespacedKey(namespace(), value())


private val nbtIo = TagStringIO.get()

fun CompoundBinaryTag.asString() = nbtIo.asString(this)
fun CompoundBinaryTag.asByteArray() = nbtIo.asString(this).toByteArray(StandardCharsets.UTF_8)
fun String.asNBT() = nbtIo.asCompound(this)
fun ByteArray.asNBT() = nbtIo.asCompound(String(this, StandardCharsets.UTF_8))