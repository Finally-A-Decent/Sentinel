repositories {

}

dependencies {
    compileOnly("org.aspectj:aspectjrt:1.9.22.1")
    compileOnly("org.aspectj:aspectjweaver:1.9.22.1")
}

tasks.register("publishApi") {
    dependsOn("publishMavenJavaPublicationToFinallyADecentRepository")
}