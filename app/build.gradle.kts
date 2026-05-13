plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.google.services)

    kotlin("kapt")
}

android {

    namespace = "com.example.raitha_bharosa_hub"

    compileSdk = 36

    defaultConfig {

        applicationId = "com.example.raitha_bharosa_hub"

        minSdk = 24
        targetSdk = 36

        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner =
            "androidx.test.runner.AndroidJUnitRunner"

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {

        release {

            isMinifyEnabled = false

            proguardFiles(
                getDefaultProguardFile(
                    "proguard-android-optimize.txt"
                ),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {

        sourceCompatibility =
            JavaVersion.VERSION_11

        targetCompatibility =
            JavaVersion.VERSION_11
    }

    kotlinOptions {

        jvmTarget = "11"
    }

    buildFeatures {

        compose = true
    }

    composeOptions {

        kotlinCompilerExtensionVersion =
            "1.5.14"
    }

    packaging {

        resources {

            excludes +=
                "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    // ---------------- CORE ----------------

    implementation(libs.androidx.core.ktx)

    implementation(
        "androidx.lifecycle:lifecycle-runtime-ktx:2.8.0"
    )

    implementation(
        "androidx.activity:activity-compose:1.9.0"
    )

    // ---------------- COMPOSE ----------------

    implementation(
        platform(libs.androidx.compose.bom)
    )

    implementation(
        "androidx.compose.ui:ui"
    )

    implementation(
        "androidx.compose.material3:material3"
    )

    implementation(
        "androidx.compose.ui:ui-tooling-preview"
    )

    implementation(
        "androidx.compose.foundation:foundation"
    )

    implementation(
        "androidx.compose.runtime:runtime"
    )

    debugImplementation(
        "androidx.compose.ui:ui-tooling"
    )

    debugImplementation(
        "androidx.compose.ui:ui-test-manifest"
    )

    androidTestImplementation(
        "androidx.compose.ui:ui-test-junit4"
    )

    // ---------------- MATERIAL ICONS ----------------

    implementation(
        "androidx.compose.material:material-icons-extended"
    )

    // ---------------- NAVIGATION ----------------

    implementation(
        "androidx.navigation:navigation-compose:2.7.7"
    )

    // ---------------- ANIMATION ----------------

    implementation(
        "androidx.compose.animation:animation:1.6.7"
    )

    // ---------------- FIREBASE ----------------

    implementation(
        platform(
            "com.google.firebase:firebase-bom:32.7.0"
        )
    )

    implementation(
        "com.google.firebase:firebase-auth"
    )

    implementation(
        "com.google.firebase:firebase-firestore"
    )

    // ---------------- LOCATION ----------------

    implementation(
        "com.google.android.gms:play-services-location:21.0.1"
    )

    // ---------------- RETROFIT ----------------

    implementation(
        "com.squareup.retrofit2:retrofit:2.9.0"
    )

    implementation(
        "com.squareup.retrofit2:converter-gson:2.9.0"
    )

    // ---------------- ROOM DATABASE ----------------

    implementation(
        "androidx.room:room-runtime:2.6.1"
    )

    implementation(
        "androidx.room:room-ktx:2.6.1"
    )

    kapt(
        "androidx.room:room-compiler:2.6.1"
    )

    // ---------------- COROUTINES ----------------

    implementation(
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.1"
    )

    // ---------------- TEST ----------------

    testImplementation(
        "junit:junit:4.13.2"
    )

    androidTestImplementation(
        "androidx.test.ext:junit:1.1.5"
    )

    androidTestImplementation(
        "androidx.test.espresso:espresso-core:3.5.1"
    )
}

kapt {

    correctErrorTypes = true
}