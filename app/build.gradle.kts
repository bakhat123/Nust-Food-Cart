plugins {
    alias(libs.plugins.androidApplication)
    id("com.google.gms.google-services")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.layouts"
    compileSdk = 34
    viewBinding{
        enable = true;
    }

    defaultConfig {
        applicationId = "com.example.layouts"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures{
        viewBinding = true
        // Remove or set dataBinding to false
        dataBinding = true
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
    dependenciesInfo {
        includeInApk = true
        includeInBundle = true
    }
    buildToolsVersion = "34.0.0"
    kotlinOptions {
        jvmTarget = "17"
    }
}


dependencies {


    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.play.services.maps)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)

    implementation ("com.squareup.picasso:picasso:2.71828")
    implementation ("com.google.firebase:firebase-firestore:23.0.3")
    implementation ("com.google.firebase:firebase-storage:20.0.1")
    implementation ("com.google.android.material:material:1.4.0")
    implementation ("androidx.appcompat:appcompat:1.3.1")
    implementation ("androidx.constraintlayout:constraintlayout:2.1.1")
    implementation(platform("com.google.firebase:firebase-bom:33.0.0"))
    implementation("com.google.firebase:firebase-auth")
    implementation("com.google.android.gms:play-services-auth:21.1.1")
    implementation ("com.google.android.gms:play-services-auth:19.2.0")
    implementation("com.google.firebase:firebase-core:20.0.0")
    implementation ("com.github.denzcoskun:ImageSlideshow:0.1.2")
    implementation(libs.firebase.auth)
    implementation(libs.firebase.database)
    implementation("androidx.core:core-ktx:+")
    implementation(libs.firebase.storage)
    implementation(libs.firebase.firestore)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}