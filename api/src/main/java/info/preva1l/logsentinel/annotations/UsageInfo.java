package info.preva1l.logsentinel.annotations;

import java.lang.annotation.*;

/**
 * To warn the person interacting with the api about an implication of the method.
 *
 * <p> Created on 18/02/2025 </p>
 *
 * @author Preva1l
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(UsageInfoAggregate.class)
@Target({
        ElementType.METHOD,
        ElementType.FIELD
})
public @interface UsageInfo {
    /**
     * The message the user will get sent.
     *
     * @return the warning message.
     */
    String value();

    LogLevel logLevel() default LogLevel.WARNING;

    enum LogLevel {
        INFO,
        WARNING,
        FATAL
    }
}
