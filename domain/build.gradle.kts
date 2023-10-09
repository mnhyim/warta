plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")

    kotlin("kapt")
    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.dagger.hilt)
}

android {
    namespace = "com.mnhyim.domain"
    compileSdk = 33

    defaultConfig {
        minSdk = 26

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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(project(":common:utils"))

    implementation(libs.dagger.hilt.android)
    implementation(libs.dagger.hilt.android.gradle.plugin)
    kapt(libs.dagger.hilt.android.compiler)

    implementation(libs.bundles.paging3)

    implementation(libs.androidx.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.android.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}