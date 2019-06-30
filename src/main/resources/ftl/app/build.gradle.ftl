apply plugin: 'com.android.application'
apply plugin: 'kotlin-android'
apply plugin: 'kotlin-android-extensions'

android {
    compileSdkVersion build_versions.target_sdk
    buildToolsVersion build_versions.build_tools

    defaultConfig {
        applicationId "${packageName}"
        minSdkVersion build_versions.min_sdk
        targetSdkVersion build_versions.target_sdk
        versionCode 1
        versionName "1.0"
    }

    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }
}

dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    implementation support.design
    implementation support.app_compat
    implementation deps.kotlin_stdlib8

    // 第三方库
    implementation 'com.jeremyliao:live-event-bus:1.4.4'
}