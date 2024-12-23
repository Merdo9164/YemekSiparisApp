plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("androidx.navigation.safeargs.kotlin")

    id("dagger.hilt.android.plugin")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")

    id("com.google.gms.google-services")
}

android {
    namespace = "com.erdgn.yemeksiparisapp"
    compileSdk = 35

    buildFeatures{
        viewBinding = true
        //noinspection DataBindingWithoutKapt
        dataBinding = true
    }

    defaultConfig {
        applicationId = "com.erdgn.yemeksiparisapp"
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.androidx.navigation.fragment.ktx)
    implementation (libs.androidx.navigation.ui.ktx)

    implementation("androidx.lifecycle:lifecycle-viewmodel:2.5.1")
    implementation("androidx.activity:activity-ktx:1.6.1")

    implementation("com.google.dagger:hilt-android:2.48")
    kapt("com.google.dagger:hilt-android-compiler:2.48")

    implementation(platform("com.google.firebase:firebase-bom:33.7.0"))
    implementation ("com.google.firebase:firebase-firestore:25.1.0")

    implementation("com.github.bumptech.glide:glide:4.13.2")

    implementation("com.squareup.retrofit2:retrofit:2.6.0")
    implementation("com.google.code.gson:gson:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.5.0")
}
kapt {
    correctErrorTypes = true
}
