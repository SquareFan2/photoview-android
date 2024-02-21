import io.getstream.photoview.Configuration

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
  id(libs.plugins.android.application.get().pluginId)
  id(libs.plugins.kotlin.android.get().pluginId)
  id(libs.plugins.baseline.profile.get().pluginId)
}

android {
  compileSdk = Configuration.compileSdk
  namespace = "io.getstream.photoview.sample"

  defaultConfig {
    applicationId = "io.getstream.photoview.sample"
    minSdk = Configuration.minSdk
    targetSdk = Configuration.targetSdk
    versionCode = Configuration.versionCode
    versionName = Configuration.versionName
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }

  buildFeatures {
    viewBinding = true
    buildConfig = true
  }

  lint {
    abortOnError = false
  }

  buildTypes {
    create("benchmark") {
      initWith(buildTypes.getByName("release"))
      signingConfig = signingConfigs.getByName("debug")
      matchingFallbacks += listOf("release")
      isDebuggable = false
    }
  }
}

tasks.withType(JavaCompile::class.java).configureEach {
  this.targetCompatibility = libs.versions.jvmTarget.get()
  this.sourceCompatibility = libs.versions.jvmTarget.get()
}

dependencies {
  implementation(libs.androidx.material)

  implementation(libs.coil)
  implementation(libs.glide)

  implementation(project(":photoview"))
  implementation(project(":photoview-dialog"))

  baselineProfile(project(":benchmark"))
}
