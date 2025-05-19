pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
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

rootProject.name = "Courses"
include(":app")
include(":feature:onboarding")
include(":feature:login")
include(":feature:main")
include(":feature:home")
include(":feature:favourites")
include(":feature:profile")
include(":core:navigation")
include(":core:ui")
include(":core:designsystem")
include(":core:model")
include(":core:network")
include(":core:data")
include(":core:database")
include(":core:domain")
include(":core:common")
include(":core:datastore")
