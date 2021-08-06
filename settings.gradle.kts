pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}
enableFeaturePreview("VERSION_CATALOGS")

rootProject.name = "kobweb"

include(":bin:kobweb")
include(":lib:core")
include(":lib:ui")
include(":lib:compose-ext")
include(":lib:plugins:markdown")
include(":examples:helloworld")
include(":examples:helloworld-runner")
