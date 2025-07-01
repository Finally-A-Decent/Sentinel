package info.preva1l.sentinel.user;

import net.kyori.adventure.text.Component;

import java.util.UUID;

/**
 * Created on 18/02/2025
 *
 * @author Preva1l
 */
public interface User {
    String name();

    UUID uniqueId();

    void sendMessage(Component message);
}
