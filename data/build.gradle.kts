import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.util.Properties
import kotlin.apply

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
}

val props = Properties().apply {
    val f = rootProject.file("local.properties")
    if (f.exists()) load(f.inputStream())
}
val tmdbBearer: String = props.getProperty("TMDB_BEARER")
    ?: System.getenv("TMDB_BEARER")
    ?: ""

val tmdbBaseUrl: String = props.getProperty("TMDB_BASE_URL")
    ?: System.getenv("TMDB_BASE_URL")
    ?: "https://api.themoviedb.org/3/"

android {
    namespace = "com.sumeyra.data"
    compileSdk = 36

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        buildConfigField("String", "TMDB_BEARER", "\"$tmdbBearer\"")
        buildConfigField("String", "TMDB_BASE_URL", "\"$tmdbBaseUrl\"")

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

    buildFeatures { buildConfig = true }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlin {
        jvmToolchain(17)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
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
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter)
    ksp(libs.hilt.compiler)
    implementation(libs.hilt.android)
    implementation(libs.okhttp.logging)
    implementation(project(":domain"))
}