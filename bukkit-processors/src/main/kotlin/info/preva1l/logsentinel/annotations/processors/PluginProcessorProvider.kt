package info.preva1l.logsentinel.annotations.processors

import com.google.auto.service.AutoService
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.processing.SymbolProcessorProvider

/**
 * Created on 19/02/2025
 *
 * @author Preva1l
 */
@AutoService(SymbolProcessorProvider::class)
class PluginProcessorProvider : SymbolProcessorProvider {
    override fun create(environment: SymbolProcessorEnvironment): SymbolProcessor = PluginProcessor(environment)
}