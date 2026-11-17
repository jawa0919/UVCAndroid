[![Maven Central](https://img.shields.io/maven-central/v/com.herohan/UVCAndroid.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22com.herohan%22%20AND%20a:%22UVCAndroid%22)

UVCAndroid
=========

Library and sample to access UVC camera on non-rooted Android device

[中文文档： UVCAndroid，安卓UVC相机通用开发库](https://blog.csdn.net/hanshiying007/article/details/124118486)

How do I use it?
---

### Setup

##### Dependencies
```groovy
repositories {
  mavenCentral()
}

dependencies {
    implementation 'com.herohan:UVCAndroid:1.0.13'
}
```
##### JitPack (fork builds)

This fork can also be consumed from [JitPack](https://jitpack.io/#jawa0919/UVCAndroid)
without waiting for a Maven Central release:

```groovy
repositories {
    mavenCentral()
    maven { url 'https://jitpack.io' }
}

dependencies {
    implementation 'com.github.jawa0919.UVCAndroid:libuvccamera:v1.0.14'
}
```

`v1.0.14` is the tag (or commit hash) to build; replace it with the tag you want.
On GitHub, push a tag / create a release, then request the build once at
https://jitpack.io/#jawa0919/UVCAndroid. The first request triggers the build.

R8 / ProGuard
-------------

If you are using R8 the shrinking and obfuscation rules are included automatically.

ProGuard users must manually add the below options.
```groovy
-keep class com.herohan.uvcapp.** { *; }
-keep class com.serenegiant.usb.** { *; }
-keepclassmembers class * implements com.serenegiant.usb.IButtonCallback {*;}
-keepclassmembers class * implements com.serenegiant.usb.IFrameCallback {*;}
-keepclassmembers class * implements com.serenegiant.usb.IStatusCallback {*;}
-keepclassmembers class * implements com.serenegiant.usb.IStillImageCallback {*;}
```

Requirements
--------------
Android 5.0+