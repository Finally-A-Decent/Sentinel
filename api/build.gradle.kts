plugins {
    sentinel.common
}


dependencies {
    api("net.kyori:adventure-api:4.13.0")
    api("net.kyori:adventure-nbt:4.13.0")
}

tasks.register("publishApi") {
    dependsOn("publishMavenJavaPublicationToFinallyADecentRepository")
}