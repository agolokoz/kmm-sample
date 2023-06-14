plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = "dev.glk.multiplatformsample.android"
    compileSdk = 33
    defaultConfig {
        applicationId = "dev.glk.multiplatformsample.android"
        minSdk = 23
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }
    kotlinOptions.jvmTarget = "1.8"

    signingConfigs {
        getByName("debug") {
            storeFile = file("./config/debug.keystore")
            storePassword = "android"
            keyAlias = "androiddebugkey"
            keyPassword = "android"
        }
        create("release") {
            storeFile = file("./config/release.keystore")
            storePassword = "android"
            keyAlias = "androidreleasekey"
            keyPassword = "android"
        }
    }

    buildTypes {
        getByName("debug") {
            signingConfig = signingConfigs.getByName("debug")
        }
        getByName("release") {
            signingConfig = signingConfigs.getByName("release")
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation(project(":shared"))
}