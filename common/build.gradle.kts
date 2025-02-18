repositories {

}

dependencies {
    ksp(project(":api"))
    api(project(":api"))

    compileOnly("org.reflections:reflections:0.10.2")
}