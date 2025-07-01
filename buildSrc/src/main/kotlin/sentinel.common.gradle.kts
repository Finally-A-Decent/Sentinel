import info.preva1l.sentinel.BuildConstants
import org.gradle.kotlin.dsl.support.uppercaseFirstChar
import info.preva1l.trashcan.finallyADecent

plugins {
    id("sentinel.publishing")
    kotlin("jvm")

    id("info.preva1l.trashcan")

    id("com.gradleup.shadow")
}

trashcan {
    kotlin = true
}

repositories {
    maven("https://maven-central.storage-download.googleapis.com/maven2")

    finallyADecent(dev = BuildConstants.DEV_MODE)
    finallyADecent()
}

kotlin {
    jvmToolchain(21)
}

tasks {
    withType<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar> {
        destinationDirectory.set(file("$rootDir/target"))
        archiveFileName.set("${rootProject.name}-${project.name.uppercaseFirstChar()}-$version.jar")
    }

    withType<JavaCompile> {
        options.compilerArgs.add("-parameters")
        options.isFork = true
        options.encoding = "UTF-8"
        options.release = 21
    }

    register<Jar>("sourcesJar") {
        archiveClassifier.set("sources")
        from(sourceSets.main.get().allSource)
    }

    withType<Javadoc> {
        (options as StandardJavadocDocletOptions).tags(
            "apiNote:a:API Note:",
            "implSpec:a:Implementation Requirements:",
            "implNote:a:Implementation Note:"
        )
    }

    register<Jar>("javadocJar") {
        dependsOn("javadoc")
        archiveClassifier.set("javadoc")
        from(named<Javadoc>("javadoc").get().destinationDir)
    }

    withType<Test> {
        useJUnitPlatform()
    }

    named("build") {
        dependsOn("shadowJar")
    }
}

publishing {
    publications {
        register<MavenPublication>("mavenJava") {
            from(components["java"])

            artifact(tasks.named("sourcesJar"))
            artifact(tasks.named("javadocJar"))
        }
    }
}