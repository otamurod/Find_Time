import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composePlugin)
}

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
        }
    }
    sourceSets {
        val jvmMain by getting {
            kotlin.srcDirs("src/jvmMain/kotlin")
            dependencies {
                // Bringing the compose libraries.(Pre-defined variables. Do not use from libs version catalog.)
                implementation(compose.desktop.currentOs)
                api(compose.runtime)
                api(compose.foundation)
                api(compose.material)
                api(compose.ui)
                api(compose.materialIconsExtended)

                implementation(libs.napier)
                // Coroutines
                implementation(libs.coroutines.core)

                implementation(projects.shared)
                // implementation(project(":shared-ui"))
            }
        }
    }
}

compose.desktop {
    application() {
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "FindTime"
            macOS {
                bundleID = "uz.otamurod.kmp.findtime"
            }
        }
    }
}