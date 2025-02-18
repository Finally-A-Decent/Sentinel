repositories {
    maven(url = "https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    api(project(":common"))

    ksp(project(":bukkit-processors"))
    implementation(project(":bukkit-processors")) {
        exclude(group = "org.github.paperspigot")
        exclude(group = "org.jetbrains.kotlin")
    }

    compileOnly("org.github.paperspigot:paperspigot-api:1.8.8-R0.1-SNAPSHOT")
}