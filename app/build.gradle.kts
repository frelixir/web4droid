plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.web.android"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.web.android"
        minSdk = 26
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        
        ndk {
            abiFilters.add("arm64-v8a") 
        }
    }

    signingConfigs {
        create("release") {
            enableV1Signing = true
            enableV2Signing = true
            enableV3Signing = true
            enableV4Signing = true
        }
        getByName("debug") {
            enableV1Signing = true
            enableV2Signing = true
            enableV3Signing = true
            enableV4Signing = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
        }
        debug {
            signingConfig = signingConfigs.getByName("debug")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    androidResources {
        noCompress += listOf("bin", "pak")
    }
}

configurations.all {
    exclude(group = "org.jspecify", module = "jspecify")
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    
    // Download crwebview dependencies from GitHub Releases
    val crwebviewVersion = "2.0.0"
    val baseUrl = "https://github.com/wuruxu/crwebview/releases/download/$crwebviewVersion"
    
    implementation(files("$baseUrl/android_crwebview_webkit.WebView-v2.0.0.aar"))
    implementation(files("$baseUrl/android_webview_resources-debug.aar"))
    implementation(files("$baseUrl/content-debug.aar"))
    implementation(files("$baseUrl/embedder_support_resources-debug.aar"))
    implementation(files("$baseUrl/ui-debug.aar"))
    
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
}