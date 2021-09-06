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
        classpath("com.android.tools.build:gradle:4.2.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.0")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.35.1")
    }
}

allprojects {
    repositories {
        maven { url = uri("http://maven.aliyun.com/nexus/content/groups/public/") }
        maven { url = uri("http://maven.aliyun.com/nexus/content/repositories/jcenter") }
        maven { url = uri("http://maven.aliyun.com/nexus/content/repositories/google") }
        google()
        mavenCentral()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}