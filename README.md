<h1 align="center">PhotoView</h1></br>

<p align="center">
🌇 PhotoView is an ImageView component for Android that enables zoom functionality through diverse touch gestures. This project has been forked from ChrisBanes' PhotoView.
</p>

> The aim of this library is to sync up to the recent androidx library, address bugs, enhance features, and distribute the package through Maven Central instead of Jitpack.

</br>
<p align="center">
  <a href="https://opensource.org/licenses/Apache-2.0"><img alt="License" src="https://img.shields.io/badge/License-Apache%202.0-blue.svg"/></a>
  <a href="https://android-arsenal.com/api?level=21"><img alt="API" src="https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat"/></a>
  <a href="https://github.com/GetStream/photoview-android/actions/workflows/android.yml"><img alt="Build Status" src="https://github.com/GetStream/photoview-android/actions/workflows/android.yml/badge.svg"/></a>
  <a href="https://getstream.io"><img src="https://img.shields.io/endpoint?url=https://gist.githubusercontent.com/HayesGordon/e7f3c4587859c17f3e593fd3ff5b13f4/raw/11d9d9385c9f34374ede25f6471dc743b977a914/badge.json" alt="Stream Feeds"></a>
</p><br>

<p align="center">
<img src="preview/preview0.gif" width="320"/>
<img src="preview/preview1.gif" width="320"/>
</p>

## Download
[![Maven Central](https://img.shields.io/maven-central/v/io.getstream/photoview.svg?label=Maven%20Central)](https://central.sonatype.com/artifact/io.getstream/photoview)

### Gradle

Add the dependency below to your **module**'s `build.gradle` file:

```gradle
dependencies {
    implementation("io.getstream:photoview:1.0.0")
}
```

## Usage

`PhotoView` can be utilized exactly like `ImageView`, as demonstrated in the example below:

```xml
<io.getstream.photoview.PhotoView
    android:id="@+id/phptoView"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:src="@drawable/wallpaper"/>
```

When incorporating third-party image loading libraries like Glide or Coil, images can be loaded as demonstrated in the following examples.

### Coil

```kotlin
val photoView = binding.photoView
photoView.load(IMAGE_URL) {
  crossfade(true)
}
```

### Glide

```kotlin
val photoView = binding.photoView
Glide.with(this)
  .load(IMAGE_URL)
  .into(photoView)
```

Exploring the [sample](https://github.com/GetStream/photoview-android/tree/main/sample) directory reveals a variety of practical examples, including integration with ViewPager, using PhotoView with shared transition elements, utilizing third-party image libraries (such as Coil, Glide, and Picasso), among others.

## Supported Features

- **Effortless Zooming**: Enables zooming capabilities through multi-touch gestures and double-tapping.
- **Smooth Scrolling**: Offers seamless scrolling experience with fluid fling motion.
- **Compatibility with Scrolling Parents**: Integrates flawlessly within scrolling containers, like ViewPager, ensuring smooth operation.
- **Matrix Change Notifications**: Alerts the application when the displayed Matrix changes, which is essential for UI adjustments based on the current zoom or scroll position.
- **User Tap Notifications**: Notifies the application when the user interacts with the Photo, facilitating responsive UI actions.

## Find this repository useful? :heart:
Support it by joining __[stargazers](https://github.com/skydoves/FlexibleBottomSheet/stargazers)__ for this repository. :star: <br>
Also, __[follow me](https://github.com/skydoves)__ on GitHub for my next creations! 🤩

# License
```xml
Copyright 2024 Stream.IO, Inc.
Copyright 2011, 2012 Chris Banes.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
