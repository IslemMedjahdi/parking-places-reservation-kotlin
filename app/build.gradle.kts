plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("com.google.devtools.ksp") version "1.9.21-1.0.15"
}



android {
    namespace = "com.example.parking_places_reservation"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.parking_places_reservation"
        minSdk = 24
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation (libs.androidx.navigation.compose)
    implementation (libs.ui)
    implementation (libs.androidx.material)
    implementation (libs.androidx.navigation.compose.v240alpha01)
    implementation(libs.coil.compose)
    //Retrofit
    implementation(libs.retrofit)
    implementation (libs.converter.gson)
    implementation (libs.gson)
    // Coil
    implementation(libs.coil.compose.v240)
    //Coroutine tests
    testImplementation (libs.kotlinx.coroutines.test)
    //MockWebserver
    testImplementation (libs.mockwebserver)
    implementation (libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)
    implementation (libs.androidx.room.ktx)
    testImplementation (libs.androidx.room.testing)

    implementation(libs.core.v102)

    // CORE
    implementation(libs.sheets.compose.dialogs.core)

    // CALENDAR
    implementation(libs.calendar)

    // CLOCK
    implementation(libs.clock)

    implementation (libs.maps.compose)

// Optionally, you can include the Compose utils library for Clustering,
// Street View metadata checks, etc.
    implementation (libs.android.maps.compose.utils)

// Optionally, you can include the widgets library for ScaleBar, etc.
    implementation (libs.android.maps.compose.widgets)

    implementation(libs.compose.qr.code)

    implementation(libs.onetapcompose)

}