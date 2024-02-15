/*
 * Copyright 2024 Stream.IO, Inc.
 * Copyright 2011, 2012 Chris Banes.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.getstream.photoview.sample

import android.R.attr
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowInsetsController
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import io.getstream.photoview.sample.databinding.ActivityImmersiveBinding

/**
 * Shows immersive image viewer
 */
class ImmersiveActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val binding = ActivityImmersiveBinding.inflate(layoutInflater)
    setContentView(binding.root)

    val photoView = binding.photoView
    Picasso.with(this)
      .load(
        "https://images.unsplash.com/photo-1577643816920-65b43ba99fba?ixlib=rb-1.2.1&" +
          "auto=format&fit=crop&w=3300&q=80",
      )
      .into(photoView)
    photoView.setOnPhotoTapListener { view: ImageView?, x: Float, y: Float -> }
    fullScreen()
  }

  private fun fullScreen() {
    // BEGIN_INCLUDE (get_current_ui_flags)
    // The UI options currently enabled are represented by a bitfield.
    // getSystemUiVisibility() gives us that bitfield.
    val uiOptions = window.decorView.systemUiVisibility
    var newUiOptions = uiOptions
    // END_INCLUDE (get_current_ui_flags)
    // BEGIN_INCLUDE (toggle_ui_flags)
    val isImmersiveModeEnabled = isImmersiveModeEnabled
    if (isImmersiveModeEnabled) {
      Log.i("TEST", "Turning immersive mode mode off. ")
    } else {
      Log.i("TEST", "Turning immersive mode mode on.")
    }

    // Navigation bar hiding:  Backwards compatible to ICS.
    newUiOptions = newUiOptions xor View.SYSTEM_UI_FLAG_HIDE_NAVIGATION

    // Status bar hiding: Backwards compatible to Jellybean
    newUiOptions = newUiOptions xor View.SYSTEM_UI_FLAG_FULLSCREEN

    // Immersive mode: Backward compatible to KitKat.
    // Note that this flag doesn't do anything by itself, it only augments the behavior
    // of HIDE_NAVIGATION and FLAG_FULLSCREEN.  For the purposes of this sample
    // all three flags are being toggled together.
    // Note that there are two immersive mode UI flags, one of which is referred to as "sticky".
    // Sticky immersive mode differs in that it makes the navigation and status bars
    // semi-transparent, and the UI flag does not get cleared when the user interacts with
    // the screen.
    newUiOptions = newUiOptions xor WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
    window.decorView.systemUiVisibility = newUiOptions
    // END_INCLUDE (set_ui_flags)
  }

  private val isImmersiveModeEnabled: Boolean
    get() = attr.uiOptions or
      WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE == attr.uiOptions
}
