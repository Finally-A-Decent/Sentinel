package info.preva1l.logsentinel.annotations.processors

import com.google.devtools.ksp.KspExperimental
import com.google.devtools.ksp.getAnnotationsByType
import com.google.devtools.ksp.processing.*
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSDeclaration
import info.preva1l.logsentinel.annotations.Dependency
import info.preva1l.logsentinel.annotations.Plugin
import org.bukkit.plugin.PluginLoadOrder
import org.yaml.snakeyaml.Yaml
import java.io.IOException
import java.io.OutputStream
import java.util.*

/**
 * Created on 19/02/2025
 *
 * @author Preva1l
 */
class PluginProcessor(
    private val environment: SymbolProcessorEnvironment,
) : SymbolProcessor {
    @OptIn(KspExperimental::class)
    override fun process(resolver: Resolver): List<KSAnnotated> {
        val annotatedElements: Sequence<KSAnnotated> = resolver.getSymbolsWithAnnotation(Plugin::class.qualifiedName!!)
        if (annotatedElements.none()) {
            return emptyList()
        }

        if (annotatedElements.count() > 1) {
            environment.logger.error("More than one @Plugin element found.")
            return emptyList()
        }

        val skib = annotatedElements.first()
        val element: KSDeclaration = if (skib is KSDeclaration) skib else {
            environment.logger.error("More than one @Plugin element found.")
            return emptyList()
        }

        if (element !is KSClassDeclaration) {
            environment.logger.error("@Plugin element is not instance of KSClassDeclaration")
            return emptyList()
        }

        val type = element
        val data: MutableMap<String, Any> = LinkedHashMap()
        val annotation: Plugin = type.getAnnotationsByType(Plugin::class).first()

        data["name"] = annotation.name

        data["version"] = environment.options["version"] as String

        data["main"] = type.qualifiedName!!.asString()

        val description = annotation.description
        if (description.isNotEmpty()) {
            data["description"] = description
        }

        val order = annotation.load
        if (order != PluginLoadOrder.POSTWORLD) {
            data["load"] = order.name
        }

        val apiVersion = annotation.apiVersion
        if (apiVersion.isNotEmpty()) {
            data["api-version"] = apiVersion
        }

        val authors = annotation.authors
        if (authors.size == 1) {
            data["author"] = authors[0]
        } else if (authors.size > 1) {
            data["authors"] = ArrayList(listOf(authors))
        }

        val website = annotation.website
        if (website.isNotEmpty()) {
            data["website"] = website
        }

        val depends: Array<Dependency> = annotation.depends
        val hard: MutableList<String> = ArrayList()
        val soft: MutableList<String> = ArrayList()

        for (depend in depends) {
            if (depend.soft) {
                soft.add(depend.value)
            } else {
                hard.add(depend.value)
            }
        }

        if (hard.isNotEmpty()) {
            data["depend"] = hard
        }

        if (soft.isNotEmpty()) {
            data["softdepend"] = soft
        }

        val libraries: Array<String> = annotation.libraries
        if (libraries.isNotEmpty()) {
            data["libraries"] = ArrayList(listOf(libraries))
        }

        try {
            val yaml = Yaml()
            val resource: OutputStream =
                environment.codeGenerator.createNewFile(
                    Dependencies.ALL_FILES,
                    "",
                    "plugin", "yml"
                )

            resource.bufferedWriter().use { bw ->
                yaml.dump(data, bw)
                bw.flush()
            }
            return listOf()
        } catch (e: IOException) {
            throw RuntimeException("Cannot serialize plugin descriptor: " + e.message, e)
        }
    }
}