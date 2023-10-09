plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")

    kotlin("kapt")
    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.dagger.hilt)
}

android {
    namespace = "com.mnhyim.search"
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
}

dependencies {
    implementation(project(":common:ui"))
    implementation(project(":common:utils"))
    implementation(project(":domain"))

    implementation(libs.compose.destinations.core)
    ksp(libs.compose.destinations.ksp)

    implementation(libs.dagger.hilt.android)
    implementation(libs.dagger.hilt.android.gradle.plugin)
    implementation(libs.dagger.hilt.navigation.compose)
    kapt(libs.dagger.hilt.android.compiler)

    implementation(libs.bundles.paging3)
    implementation(libs.material.icons.extended)

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
}
ksp {
    arg("compose-destinations.mode", "navgraphs")
    arg("compose-destinations.moduleName", "search")
    arg("compose-destinations.useComposableVisibility", "true")
}