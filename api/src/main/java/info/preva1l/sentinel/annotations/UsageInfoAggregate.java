package info.preva1l.sentinel.annotations;

import java.lang.annotation.*;

/**
 * Created on 18/02/2025
 *
 * @author Preva1l
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface UsageInfoAggregate {
    UsageInfo[] value();
}
