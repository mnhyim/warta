@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)

    kotlin("kapt")
    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.dagger.hilt)
}

android {
    namespace = "com.mnhyim.data"
    compileSdk = 33

    android.buildFeatures.buildConfig = true

    defaultConfig {
        minSdk = 26

        buildConfigField("String", "API_KEY", providers.gradleProperty("API_KEY").get())
        buildConfigField("String", "API_LINK", providers.gradleProperty("API_LINK").get())
        buildConfigField("String", "API_VERSION", providers.gradleProperty("API_VERSION").get())

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

    implementation(project(":domain"))
    implementation(project(":common:utils"))

    implementation(libs.retrofit)
    implementation(libs.retrofit.moshi.converter)
    implementation(libs.okhttp3.logging)
    implementation(libs.moshi)
    implementation(libs.moshi.kotlin)
    ksp(libs.moshi.kotlin.codegen)


    implementation(libs.room.runtime)
    ksp(libs.room.compiler)
    implementation(libs.room.ktx)
    implementation(libs.room.paging)

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