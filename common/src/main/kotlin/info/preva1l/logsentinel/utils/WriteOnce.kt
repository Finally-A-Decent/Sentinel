package info.preva1l.logsentinel.utils

import kotlin.reflect.KProperty

/**
 * Created on 19/02/2025
 *
 * @author Preva1l
 */
class WriteOnce<T> {
    private var _value: T? = null

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return _value ?: throw IllegalStateException("${property.name} has not been initialized")
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, newValue: T) {
        if (_value == null) {
            _value = newValue
        }
    }
}
