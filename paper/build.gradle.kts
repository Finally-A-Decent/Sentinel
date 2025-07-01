import info.preva1l.trashcan.paper
import info.preva1l.trashcan.trashcan

plugins {
    sentinel.common
}

trashcan {
    paper = true
}

dependencies {
    api(project(":common"))
    paper("1.20-R0.1-SNAPSHOT")
    trashcan()
}

paper {
    author = "Preva1l"
    main = rootProject.group.toString() + ".PaperLogSentinel"
    loader = rootProject.group.toString() + ".shaded.trashcan.extension.libloader.BaseLibraryLoader"
    bootstrapper = rootProject.group.toString() + ".Boot"
    foliaSupported = true
    apiVersion = "1.20"
}