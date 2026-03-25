import java.net.URI
import java.nio.file.Files
import java.nio.file.StandardCopyOption

plugins {
    alias(libs.plugins.android.application)
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
    buildFeatures {
        resValues = true
    }
    androidResources {
        noCompress += listOf("bin", "pak")
    }
}

configurations.all {
    exclude(group = "org.jspecify", module = "jspecify")
}

// Download crwebview dependencies from GitHub Releases
val crwebviewVersion = "2.0.0"
val baseUrl = "https://github.com/wuruxu/crwebview/releases/download/$crwebviewVersion"
val aarFiles = listOf(
    "android_crwebview_webkit.WebView-v2.0.0.aar",
    "android_webview_resources-debug.aar",
    "content-debug.aar",
    "embedder_support_resources-debug.aar",
    "ui-debug.aar"
)

val libsDir = file("libs")
if (!libsDir.exists()) libsDir.mkdirs()

aarFiles.forEach { aar ->
    val aarFile = file("libs/$aar")
    if (!aarFile.exists()) {
        println("Downloading $aar from GitHub Releases...")
        try {
            URI.create("$baseUrl/$aar").toURL().openStream().use { input ->
                Files.copy(input, aarFile.toPath(), StandardCopyOption.REPLACE_EXISTING)
            }
        } catch (e: Exception) {
            println("Failed to download $aar: ${e.message}")
        }
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar", "*.aar"))))
}