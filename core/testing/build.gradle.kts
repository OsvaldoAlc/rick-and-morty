@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
}

android {
    namespace = "com.example.core.testing"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
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

    api(libs.core.ktx)
    api(libs.appcompat)
    api(libs.material)
    api(libs.junit)
    api(libs.androidx.test.ext.junit)
    api(libs.espresso.core)
    api(libs.mockk.android)
    api(libs.mockk.android)
    api(libs.mockk.agent)

    api(libs.mockk.android)
    api(libs.mockk.agent)
    api(libs.kotlinx.coroutines.test)
    api ("com.google.truth:truth:1.1.4")
}