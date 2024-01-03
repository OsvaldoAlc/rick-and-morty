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

rootProject.name = "Rick"
include(":app")
include(":core:network")
include(":core:common")
include(":core:data")
include(":core:model")
include(":core:domain")
include(":feature:home")
include(":core:testing")
