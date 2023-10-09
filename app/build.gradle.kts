plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")

    kotlin("kapt")
    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.dagger.hilt)
}

android {
    namespace = "com.mnhyim.warta"
    compileSdk = 34

    applicationVariants.all {
        addJavaSourceFoldersToModel(
            File(buildDir, "generated/ksp/$name/kotlin")
        )
    }

    defaultConfig {
        applicationId = "com.mnhyim.warta"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(project(":common:ui"))
    implementation(project(":data:network"))
    implementation(project(":domain"))
    implementation(project(":feature:news"))
    implementation(project(":feature:search"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.bundles.compose)

    implementation(platform(libs.compose.bom))

    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    debugImplementation(libs.androidx.compose.ui.tooling)

    implementation(libs.dagger.hilt.android)
    implementation(libs.dagger.hilt.android.gradle.plugin)
    kapt(libs.dagger.hilt.android.compiler)

    implementation(libs.compose.destinations.core)
    ksp(libs.compose.destinations.ksp)

    implementation(libs.material.icons.extended)
}

kapt {
    correctErrorTypes = true
}