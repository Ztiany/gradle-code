import com.android.build.gradle.api.BaseVariantOutput
import com.android.build.gradle.internal.api.BaseVariantOutputImpl

repositories {
    flatDir {
        dirs("libs")
    }
}

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    //id("dagger.hilt.android.plugin")
}

kapt{
    arguments {
        arg("AROUTER_MODULE_NAME", project.name)
    }
}

apply {
    from("test.gradle.kts")
}

android {
    compileSdkVersion(30)
    buildToolsVersion("30.0.3")

    defaultConfig {
        applicationId = "com.example.application"
        minSdkVersion(21)
        targetSdkVersion(27)
        versionCode = 1
        versionName = "1.0"
        multiDexEnabled = true
        buildConfigField("boolean","openLog","true")
        resConfigs("zh", "en")//只需要中文和英文资源
        javaCompileOptions {
            annotationProcessorOptions {
                includeCompileClasspath = false
            }
        }
        ndk {
            abiFilters("armeabi-v7a","x86")
        }
    }

    testOptions {
        unitTests.isReturnDefaultValues = true
    }

    dexOptions {
        javaMaxHeapSize = "4g"
    }

    buildTypes {
        //测试
        getByName("debug") {
            isMinifyEnabled = false
            isShrinkResources = false
            addManifestPlaceholders(mapOf("screenOrientation" to "unspecified"))
        }

        //正式发布
        getByName("release") {
            isZipAlignEnabled = true
            isMinifyEnabled = false
            isShrinkResources = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            ndk {
                abiFilters("armeabi-v7a")
            }
        }
    }

    applicationVariants.all {
        outputs.all(object : Action<BaseVariantOutput> {
            override fun execute(t: BaseVariantOutput) {
                val output = t as BaseVariantOutputImpl
                output.outputFileName = "${project.name}-${versionName}.apk"
            }
        })
    }

    packagingOptions {
        pickFirst("lib/x86/libc++_shared.so")
        pickFirst("lib/x86_64/libc++_shared.so")
        pickFirst("lib/armeabi-v7a/libc++_shared.so")
        pickFirst("lib/arm64-v8a/libc++_shared.so")
    }

}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.3.50")
    implementation("androidx.appcompat:appcompat:1.0.2")
    implementation("androidx.core:core-ktx:1.0.2")
    implementation("androidx.constraintlayout:constraintlayout:1.1.3")
    implementation("com.google.android.material:material:1.0.0")
    testImplementation("junit:junit:4.12")
    androidTestImplementation("androidx.test.ext:junit:1.1.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.1.1")
}
