package info.preva1l.sentinel.types;

import net.kyori.adventure.key.Key;
import net.kyori.adventure.nbt.CompoundBinaryTag;

/**
 * Created on 1/07/2025
 *
 * @author Preva1l
 */
public record Block(
        Key material,
        Position position,
        String rawData
) {
}
