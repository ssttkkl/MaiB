import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig
import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlinPluginSerialization)
    alias(libs.plugins.aboutLibraries)
    alias(libs.plugins.buildkonfig)
}

if (enableAndroid) {
    apply(plugin = libs.plugins.androidApplication.get().pluginId)
}

kotlin {
    if (enableWasm) {
        @OptIn(ExperimentalWasmDsl::class)
        wasmJs {
            moduleName = "composeApp"
            browser {
                commonWebpackConfig {
                    outputFileName = "composeApp.js"
                }
            }
            binaries.executable()
        }
        println("${project.name} target: wasmJs")
    }

    if (enableAndroid) {
        androidTarget()
        println("${project.name} target: android")
    }

    if (enableDesktop) {
        jvm("desktop")
        println("${project.name} target: desktop")
    }

    if (enableIos) {
        listOf(
            iosX64(),
            iosArm64(),
            iosSimulatorArm64()
        ).forEach { iosTarget ->
            iosTarget.binaries.framework {
                baseName = "ComposeApp"
                isStatic = true
            }
            println("${project.name} target: ${iosTarget}")
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtime.compose)
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.logging)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)
        }

        if (enableAndroid) {
            androidMain.dependencies {
                implementation(compose.preview)
                implementation(libs.androidx.activity.compose)
                implementation(libs.ktor.client.okhttp)
            }
        }

        if (enableIos) {
            iosMain.dependencies {
                implementation(libs.ktor.client.darwin)
            }
        }

        if (enableDesktop) {
            val desktopMain by getting
            desktopMain.dependencies {
                implementation(compose.desktop.currentOs)
                implementation(libs.kotlinx.coroutines.swing)
                implementation(libs.ktor.client.cio)
            }
        }

        if (enableWasm) {
            val wasmJsMain by getting
            wasmJsMain.dependencies {
                implementation(libs.ktor.client.js)
            }
        }
    }

    @Suppress("OPT_IN_USAGE")
    compilerOptions {
        optIn.add("org.jetbrains.compose.resources.ExperimentalResourceApi")
        freeCompilerArgs.add("-Xexpect-actual-classes")
    }
}

tasks.withType<KotlinJvmCompile>().configureEach {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_11)
    }
}

buildkonfig {
    packageName = "io.ssttkkl.maib"

    defaultConfigs {
//        buildConfigField(STRING, "VERSION_NAME", versionName)
//        buildConfigField(STRING, "VERSION_CODE", versionCode.toString())
//        buildConfigField(STRING, "OPENSOURCE_REPO", properties["opensource.repo"].toString())
//        buildConfigField(STRING, "OPENSOURCE_LICENSE", properties["opensource.license"].toString())
    }
}

aboutLibraries {
    // 移除 "generated" 时间戳
    excludeFields = arrayOf("generated")
}

if (enableAndroid) {
    (extensions.getByName("android") as BaseAppModuleExtension).apply {
        namespace = "io.ssttkkl.maib"
        compileSdk = libs.versions.android.compileSdk.get().toInt()

        defaultConfig {
            applicationId = "io.ssttkkl.maib"
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
        dependenciesInfo {
            // Disables dependency metadata when building APKs.
            includeInApk = false
            // Disables dependency metadata when building Android App Bundles.
            includeInBundle = false
        }
    }

    dependencies {
        add("debugImplementation", compose.uiTooling)
    }
}

if (enableDesktop) {
    compose.desktop {
        application {
            mainClass = "io.ssttkkl.maib.MainKt"

            nativeDistributions {
                targetFormats(TargetFormat.Dmg, TargetFormat.Exe, TargetFormat.AppImage)
                packageName = "io.ssttkkl.maib"
                packageVersion = "1.0.0"
            }
        }
    }
}

if (enableWasm) {
    compose.web {}
}
