repositories {
    maven(url = "https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    implementation(project(":common"))
    implementation("com.google.devtools.ksp:symbol-processing-api:2.1.10-1.0.30")

    implementation("org.github.paperspigot:paperspigot-api:1.8.8-R0.1-SNAPSHOT")
}