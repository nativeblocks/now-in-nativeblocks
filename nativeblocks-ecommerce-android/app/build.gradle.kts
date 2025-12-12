import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
    alias(libs.plugins.nativeblocks.gradle.plugin)
}

android {
    namespace = "io.nativeblocks.nativeblocks.ecommerce"
    compileSdk = 36

    defaultConfig {
        applicationId = "io.nativeblocks.nativeblocks.ecommerce"
        minSdk = 26
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

val _basePackageName = "io.nativeblocks.nativeblocks.ecommerce"
val _moduleName = "ecommerce"

val nativeblocksPropsFile = File(project.rootDir, "local.properties")
if (nativeblocksPropsFile.exists()) {
    val nativeblocksProps = Properties()
    nativeblocksProps.load(nativeblocksPropsFile.inputStream())

    nativeblocks {
        basePackageName = _basePackageName
        moduleName = _moduleName
        endpoint = nativeblocksProps.getProperty("endpoint", "")
        authToken = nativeblocksProps.getProperty("authToken", "")
        organizationId = nativeblocksProps.getProperty("organizationId", "")
    }
} else {
    println("[Nativeblocks] Skipping nativeblocks config: local.properties not found")
}

ksp {
    arg("basePackageName", _basePackageName)
    arg("moduleName", _moduleName)
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.coil.compose)
    implementation(libs.androidx.compose.material.icons.extended)

    implementation(libs.nativeblocks.android)
    implementation(libs.nativeblocks.foundation.android)
    implementation(libs.nativeblocks.wandkit.android)
    implementation(libs.nativeblocks.compiler.android)
    ksp(libs.nativeblocks.compiler.android)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}