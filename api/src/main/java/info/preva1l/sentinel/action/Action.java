package info.preva1l.sentinel.action;

import net.kyori.adventure.key.Key;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * Created on 1/07/2025
 *
 * @author Preva1l
 */
public interface Action {
    @NotNull Key type();

    @NotNull UUID performer();
}
