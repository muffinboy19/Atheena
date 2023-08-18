plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.nossier"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.nossier"
        minSdk = 27
        targetSdk = 33
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.firebase:firebase-database-ktx:20.2.2")
    implementation("com.google.firebase:firebase-auth-ktx:22.1.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation ("androidx.room:room-runtime:2.5.2")
    implementation ("androidx.recyclerview:recyclerview:1.3.1")
    implementation (platform("com.google.firebase:firebase-bom:32.2.2"))
    implementation ("com.google.firebase:firebase-analytics-ktx")
    implementation ("com.google.firebase:firebase-core:21.1.1")
    implementation("com.squareup.picasso:picasso:2.71828")
    implementation ("androidx.room:room-runtime:x.y.z")
//    annotationProcessor ("androidx.room:room-compiler:x.y.z")
 //   implementation ("com.github.PhilJay:MPAndroidChart:v3.1.0")
    implementation ("com.google.firebase:firebase-firestore:24.7.0")
    implementation ("com.google.firebase:firebase-firestore-ktx:24.7.0")
    implementation ("com.google.firebase:firebase-storage:20.2.1")
    implementation ("com.google.firebase:firebase-auth")
    implementation("com.google.firebase:firebase-auth-ktx:22.1.1") // Optional, for authentication
    implementation ("com.google.firebase:firebase-firestore-ktx:24.7.0")
    implementation ("com.google.firebase:firebase-database-ktx")
    implementation ("androidx.core:core-ktx:1.10.1" )// Use the latest version
    implementation ("androidx.appcompat:appcompat:1.6.1") // Use the latest version
    implementation ("com.google.android.material:material:1.9.0") // Use the latest version
    implementation ("androidx.constraintlayout:constraintlayout:2.1.4") // Use the latest version
    implementation ("com.google.android.gms:play-services-auth:20.6.0") // Use the latest version
    implementation ("org.jetbrains.kotlin:kotlin-stdlib:1.9.0") // Use the latest version
    testImplementation ("junit:junit:4.13.2")
    androidTestImplementation ("androidx.test.ext:junit:1.1.5")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.5.1")

//    implementation ("com.jjoe64:graphview:4.2.2")




}