import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    `maven-publish`
    kotlin("jvm") version "2.1.10"
    id("com.google.devtools.ksp") version "2.1.10-1.0.30"
    id("org.ajoberstar.grgit") version "5.3.0"
    id("com.gradleup.shadow") version "8.3.0"
}

var currentBranch: String = grgit.branch.current().name
if (currentBranch != "master") {
    println("Starting in development mode")
}

allprojects {
    group = "info.preva1l"
    version = "1.0.0"

    repositories {
        mavenCentral()
        maven(url = "https://jitpack.io")
        if (currentBranch != "master") configureFinallyADecentRepository(dev = true)
        configureFinallyADecentRepository()
    }
}

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "com.google.devtools.ksp")
    apply(plugin = "com.gradleup.shadow")
    apply(plugin = "maven-publish")

    dependencies {
        compileOnly(kotlin("stdlib", "2.1.10"))

        ksp("dev.zacsweers.autoservice:auto-service-ksp:1.2.0")
        implementation("com.google.auto.service:auto-service-annotations:1.1.1")
    }

    kotlin {
        jvmToolchain(jdkVersion = 21)
    }

    sourceSets {
        main {
            java {
                setSrcDirs(listOf("src/main/java", "src/main/kotlin"))
            }
        }
    }

    ksp {
        arg("version", rootProject.version.toString())
    }

    tasks.withType<ShadowJar> {
        archiveClassifier.set("")
        exclude(
            "**/*.kotlin_metadata",
            "**/*.kotlin_builtins",
            "META-INF/"
        )

        archiveFileName.set("${rootProject.name}-${project.name}.jar")
    }

    tasks.withType<JavaCompile> {
        options.compilerArgs.add("-parameters")
        options.fork()
        options.encoding = "UTF-8"
    }

    tasks.withType<KotlinCompile> {
        compilerOptions {
            javaParameters = true
            jvmTarget.set(JvmTarget.JVM_21)
        }
    }

    publishing {
        repositories.configureFinallyADecentRepository(
            dev = currentBranch != "master"
        )

        publications {
            register(
                name = "mavenJava",
                type = MavenPublication::class,
                configurationAction = shadow::component
            )
        }
    }

    tasks.getByName("build")
        .dependsOn(
            "shadowJar"
        )
}

fun RepositoryHandler.configureFinallyADecentRepository(dev: Boolean = false)
{
    val user: String? = properties["fad_username"]?.toString()
    val pass: String? = properties["fad_password"]?.toString()

    if (user != null && pass != null) {
        maven("https://repo.preva1l.info/${if (dev) "development" else "releases"}/") {
            name = "FinallyADecent"
            credentials {
                username = user
                password = pass
            }
        }
        return
    }

    maven("https://repo.preva1l.info/${if (dev) "development" else "releases"}/") {
        name = "FinallyADecent"
    }
}