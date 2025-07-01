package info.preva1l.sentinel.types;

import net.kyori.adventure.key.Key;

/**
 * Created on 1/07/2025
 *
 * @author Preva1l
 */
public record Position(
        Key world,
        double x,
        double y,
        double z
) {}
