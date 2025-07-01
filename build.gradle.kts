group = "info.preva1l.sentinel"
version = "1.0.0"

tasks.register("clean") {
    doLast {
        project.delete("$rootDir/target")
    }
}

logger.lifecycle("Building Sentinel $version")