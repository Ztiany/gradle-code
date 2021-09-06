// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        maven { url = uri("http://maven.aliyun.com/nexus/content/groups/public/") }
        maven { url = uri("http://maven.aliyun.com/nexus/content/repositories/jcenter") }
        maven { url = uri("http://maven.aliyun.com/nexus/content/repositories/google") }
        maven { url = uri("http://maven.aliyun.com/nexus/content/repositories/gradle-plugin") }
        google()
        mavenCentral()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:3.5.3")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.50")
    }
}

allprojects {
    repositories {
        maven { url = uri("https://maven.aliyun.com/repository/central") }
        maven { url = uri("https://maven.aliyun.com/repository/public") }
        maven { url = uri("https://maven.aliyun.com/repository/google") }
        maven { url = uri("https://dl.google.com/dl/android/maven2/") }
        maven { url = uri("https://dl.bintray.com/umsdk/release") }
        maven { url = uri("https://jitpack.io") }
        maven { url = uri("https://kotlin.bintray.com/kotlinx") }
        google()
        mavenCentral()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}