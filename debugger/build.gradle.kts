plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    id ("maven-publish")
}
group = "com.zz.flipper"
version = "1.0.0"

android {
    namespace = "com.zz.debugger"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
        targetSdk = 34

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
    implementation(libs.flipper)
    implementation(libs.flipper.network.plugin)
    implementation(libs.soloader)
    implementation(libs.okhttp)
}

//afterEvaluate {
//    publishing {
//        publications {
//            create<MavenPublication>("maven") {
//                from(components["java"])
//
//                groupId = "com.zz"
//                artifactId = "debugger"
//                version = "1.0.0"
//
//                pom {
//                    name.set("flipper_debugger")
//                    description.set("A sample Gradle Kotlin DSL library project")
//                    url.set("https://example.com")
//
//                    licenses {
//                        license {
//                            name.set("The Apache License, Version 2.0")
//                            url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
//                        }
//                    }
//
//                    developers {
//                        developer {
//                            id.set("jelychow")
//                            name.set("zhou zheng")
//                            email.set("jelychow@gmail.com")
//                        }
//                    }
//
//                    scm {
//                        connection.set("scm:git:git://example.com/my-library.git")
//                        developerConnection.set("scm:git:ssh://example.com/my-library.git")
//                        url.set("https://example.com/my-library")
//                    }
//                }
//            }
//        }
//        repositories {
//            maven {
//                name = "GitHubPackages"
//                url = uri("https://maven.pkg.github.com/OWNER/REPOSITORY")
//                credentials {
//                    username = project.findProperty("gpr.user") as String? ?: System.getenv("USERNAME")
//                    password = project.findProperty("gpr.key") as String? ?: System.getenv("TOKEN")
//                }
//            }
//        }
//    }
//}