plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
}

android {
    namespace = "uz.otamurod.kmp.findtime.android"
    compileSdk = 34
    defaultConfig {
        applicationId = "uz.otamurod.kmp.findtime.android"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(projects.shared)
    implementation(projects.sharedUi)
    implementation(libs.compose.ui)
    implementation(libs.compose.uiUtil)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.compose.foundation)
    implementation(libs.compose.foundationLayout)
    implementation(libs.compose.material3)
    implementation(libs.compose.material)
    implementation(libs.material)
    implementation(libs.napier)
    implementation(libs.compose.runtime)
    implementation(libs.compose.runtimeLiveData)
    implementation(libs.compose.compiler)
    implementation(libs.compose.material.icons)
    implementation(libs.androidx.activity.compose)
    debugImplementation(libs.compose.ui.tooling)
}