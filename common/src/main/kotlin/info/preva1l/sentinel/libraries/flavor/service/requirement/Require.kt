package info.preva1l.sentinel.libraries.flavor.service.requirement

import java.lang.annotation.Inherited

/**
 * Created on 19/02/2025
 *
 * @author Preva1l
 */
@Inherited
@MustBeDocumented
@Repeatable
@Target(AnnotationTarget.CLASS)
annotation class Require(
    val check: Requirement,
    val data: String,
)
