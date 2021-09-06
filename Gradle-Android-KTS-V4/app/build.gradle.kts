plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

//another way of adding plugins
//apply(plugin = "com.google.firebase.crashlytics")

android {
    compileSdkVersion(30)
    buildToolsVersion("30.0.3")

    signingConfigs{
        create("release"){
            enableV1Signing = true
            enableV2Signing = true
            keyAlias("123")
            keyPassword("123")
            storeFile(File(""))
            storePassword("123")
        }
    }

    defaultConfig {
        applicationId = "com.example.application"
        minSdkVersion(16)
        targetSdkVersion(27)
        versionCode = 1
        versionName = "1.0"
        multiDexEnabled = true
        vectorDrawables.useSupportLibrary = true
        resConfigs(listOf("en","cn"))
    }

    testOptions{
        unitTests.isReturnDefaultValues = true
    }

    dexOptions{
        javaMaxHeapSize = "4g"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            isShrinkResources = false
            manifestPlaceholders(mapOf("screenOrientation" to "unspecified"))
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            ndk {
                abiFilters.add("armeabi-v7a")
            }
        }
    }

    lintOptions {
        isAbortOnError = false
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    //如果不想生成某个布局的绑定类，可以在根视图添加 tools:viewBindingIgnore="true" 属性。
    buildFeatures {
        viewBinding = true
    }

    sourceSets {
        getByName("main"){
            manifest{

            }
            java {
                srcDir("src/app/java")
            }
            res {
                srcDir("src/app/res")
            }
        }
    }

    applicationVariants.all {
        
    }

}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.5.0")
    implementation(AppDependencies.androidKtx)
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("com.google.android.material:material:1.2.1")
    implementation("androidx.constraintlayout:constraintlayout:2.0.1")
    implementation("androidx.navigation:navigation-fragment-ktx:2.3.0")
    implementation("androidx.navigation:navigation-ui-ktx:2.3.0")
    testImplementation("junit:junit:4.+")
    androidTestImplementation("androidx.test.ext:junit:1.1.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.3.0")
    api("com.google.dagger:hilt-android:2.35.1")
    kapt("com.google.dagger:hilt-android-compiler:2.35.1")
}