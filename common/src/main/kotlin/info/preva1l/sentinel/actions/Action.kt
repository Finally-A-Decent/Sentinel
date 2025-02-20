package info.preva1l.sentinel.actions

import info.preva1l.sentinel.libraries.flavor.service.Configure
import info.preva1l.sentinel.libraries.flavor.service.Service

/**
 * Created on 18/02/2025
 *
 * @author Preva1l
 */
@Service
abstract class Action {
    @Configure
    abstract fun configure()
}