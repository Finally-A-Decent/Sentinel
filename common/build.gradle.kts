import info.preva1l.trashcan.trashcan

plugins {
    sentinel.common
}

dependencies {
    api(project(":api"))
    trashcan()

    api(libs.annotations)
    annotationProcessor(libs.annotations)

    testImplementation(libs.bundles.junit)
    testRuntimeOnly(libs.bundles.junit.runtime)
    testCompileOnly(libs.annotations)
}