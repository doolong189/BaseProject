plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    alias(libs.plugins.google.gms.google.services)
}

android {
    namespace = "dev.hoanglong180903.baseproject"
    compileSdk = 36

    defaultConfig {
        applicationId = "dev.hoanglong180903.baseproject"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }

    kapt {
        correctErrorTypes = true
    }

    buildFeatures {
        viewBinding = true
        dataBinding = true
        buildConfig = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.firebase.firestore)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // livedata && view model
    val lifecycle_version = "2.2.0"
    implementation("androidx.lifecycle:lifecycle-extensions:${lifecycle_version}")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:${lifecycle_version}")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.0")
    implementation("androidx.lifecycle:lifecycle-runtime:${lifecycle_version}")

    // retrofit
    val retrofit_version = "2.9.0"
    implementation("com.squareup.retrofit2:retrofit:${retrofit_version}")
    implementation("com.squareup.retrofit2:converter-gson:${retrofit_version}")
    implementation("com.squareup.retrofit2:adapter-rxjava3:${retrofit_version}")
    val moshi_version = "1.9.3"
    val converter_moshi_version = "2.9.0"
    implementation("com.squareup.moshi:moshi:$moshi_version")
    kapt("com.squareup.moshi:moshi-kotlin-codegen:$moshi_version")
    implementation("com.squareup.retrofit2:converter-moshi:$converter_moshi_version")

    // logging && config client
    val okhttp_version = "4.7.2"
    implementation("com.squareup.okhttp3:okhttp:${okhttp_version}")
    implementation("com.squareup.okhttp3:logging-interceptor:${okhttp_version}")


    //navigation bar
    val navigation_version = "2.5.3"
    implementation("androidx.navigation:navigation-fragment:${navigation_version}")
    implementation("androidx.navigation:navigation-ui:${navigation_version}")
    implementation("androidx.navigation:navigation-fragment-ktx:${navigation_version}")
    implementation("androidx.navigation:navigation-ui-ktx:${navigation_version}")

    //coroutines
    val coroutines_version = "1.3.9"
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines_version")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines_version")

    // Dagger - Hilt
    implementation("com.google.dagger:hilt-android:2.51.1")
    kapt("com.google.dagger:hilt-android-compiler:2.51.1")


    // Image Loading Library
    val glideVersion = "4.13.2"
    implementation("com.github.bumptech.glide:glide:$glideVersion")
    annotationProcessor("com.github.bumptech.glide:compiler:$glideVersion")
}