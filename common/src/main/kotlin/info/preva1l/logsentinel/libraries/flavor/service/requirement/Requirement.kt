package info.preva1l.logsentinel.libraries.flavor.service.requirement

import java.lang.annotation.Inherited

/**
 * Created on 19/02/2025
 *
 * @author Preva1l
 */
@Inherited
@MustBeDocumented
@Repeatable
annotation class Requirement(
    val check: Require,
    val data: String,
)
