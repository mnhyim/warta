pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "warta"
include(":app")
include(":common:ui")
include(":feature:news")
include(":domain")
include(":common:utils")
include(":feature:search")
include(":data")
include(":feature:favorite")
