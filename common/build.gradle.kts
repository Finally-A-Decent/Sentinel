repositories {

}

dependencies {
    ksp(project(":api"))
    api(project(":api"))

    implementation("info.preva1l.showman:backend:1.0.0")
    compileOnly("de.exlll:configlib-yaml:4.5.0")

    compileOnly("org.reflections:reflections:0.10.2")
}