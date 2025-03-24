plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.applicationmobileairvip"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.applicationmobileairvip"
        minSdk = 24
        targetSdk = 35
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
        sourceCompatibility = JavaVersion.VERSION_17 // Changé à Java 17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    // Dépendances de base
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)

    // Room Database (version catalog)
    implementation(libs.room.runtime)
    annotationProcessor(libs.room.compiler)

    // RecyclerView
    implementation(libs.recyclerview)

    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}