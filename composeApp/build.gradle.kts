import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)

    // Mark 1
    alias(libs.plugins.room)
    alias(libs.plugins.ksp)

    alias(libs.plugins.kotlin.serialization)

}

kotlin {

    compilerOptions.freeCompilerArgs.add("-Xexpect-actual-classes") // avoid compilation warning on expect/actual classes

    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    
    jvm("desktop")
    
//    @OptIn(ExperimentalWasmDsl::class)
//    wasmJs {
//        moduleName = "composeApp"
//        browser {
//            val rootDirPath = project.rootDir.path
//            val projectDirPath = project.projectDir.path
//            commonWebpackConfig {
//                outputFileName = "composeApp.js"
//                devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
//                    static = (static ?: mutableListOf()).apply {
//                        // Serve sources to debug inside browser
//                        add(rootDirPath)
//                        add(projectDirPath)
//                    }
//                }
//            }
//        }
//        binaries.executable()
//    }
    
    sourceSets {
        val desktopMain by getting

        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)

            implementation(libs.koin.android)
            implementation(libs.koin.compose)
            implementation(libs.ktor.client.android)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtime.compose)
            implementation(libs.navigation.compose)
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.lifecycle.viewmodel.compose)
            implementation(libs.savedstate)
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)


            implementation(libs.material3)
            
            // Mark 2
            implementation(libs.androidx.room.runtime)
            implementation(libs.sqlite.bundled)
            api(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.koin.compose.viewmodel)

            implementation(compose.materialIconsExtended)

            implementation(libs.kotlinx.datetime)

            // Preferences
            implementation(libs.datastore.preferences.core)


        }
        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutines.swing)
            implementation(libs.ktor.client.java)
        }

        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }

        commonTest.dependencies {
            implementation(kotlin("test"))
            implementation(libs.kotlinx.coroutines.test)
        }
    }
}

android {
    namespace = "com.jrb.divishare"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.jrb.divishare"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    debugImplementation(compose.uiTooling)

    // Mark 3
    // After running find ksp generated directories on: composeApp/build/generated/...
    add("kspAndroid", libs.androidx.room.compiler) // Android
    add("kspIosSimulatorArm64", libs.androidx.room.compiler) // Apple Silicon iOS Simulators
    add("kspIosX64", libs.androidx.room.compiler) // Intel-based iOS Simulators
    add("kspIosArm64", libs.androidx.room.compiler) // Real iOS Devices
    add("kspDesktop", libs.androidx.room.compiler) // jvm


}

compose.desktop {
    application {
        mainClass = "com.jrb.divishare.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "com.jrb.divishare"
            packageVersion = "1.0.0"
        }
    }
}

// Mark 4
room {
    schemaDirectory("$projectDir/schemas")
}


