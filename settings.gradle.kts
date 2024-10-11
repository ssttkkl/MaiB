rootProject.name = "MaiBestPicture"


pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        mavenLocal()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        mavenLocal()
    }
}

include(":composeApp")

includeBuild("external/gridlayout-compose") {
    dependencySubstitution {
        substitute(module("com.cheonjaeung.compose.grid:grid")).using(project(":grid"))
    }
}