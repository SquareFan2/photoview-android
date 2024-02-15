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

import android.os.Bundle
import android.os.Handler
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import io.getstream.photoview.PhotoView
import io.getstream.photoview.sample.databinding.ActivityRotationSampleBinding

class RotationSampleActivity : AppCompatActivity() {
  private var photo: PhotoView? = null
  private val handler = Handler()
  private var rotating = false

  public override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val binding = ActivityRotationSampleBinding.inflate(layoutInflater)
    setContentView(binding.root)

    binding.toolbar.inflateMenu(R.menu.rotation)
    binding.toolbar.setOnMenuItemClickListener { item: MenuItem ->
      when (item.itemId) {
        R.id.action_rotate_10_right -> {
          photo!!.setRotationBy(10f)
          return@setOnMenuItemClickListener true
        }

        R.id.action_rotate_10_left -> {
          photo!!.setRotationBy(-10f)
          return@setOnMenuItemClickListener true
        }

        R.id.action_toggle_automatic_rotation -> {
          toggleRotation()
          return@setOnMenuItemClickListener true
        }

        R.id.action_reset_to_0 -> {
          photo!!.setRotationTo(0f)
          return@setOnMenuItemClickListener true
        }

        R.id.action_reset_to_90 -> {
          photo!!.setRotationTo(90f)
          return@setOnMenuItemClickListener true
        }

        R.id.action_reset_to_180 -> {
          photo!!.setRotationTo(180f)
          return@setOnMenuItemClickListener true
        }

        R.id.action_reset_to_270 -> {
          photo!!.setRotationTo(270f)
          return@setOnMenuItemClickListener true
        }

        else -> false
      }
    }
    photo = binding.ivPhoto.apply {
      setImageResource(R.drawable.wallpaper)
    }
  }

  override fun onPause() {
    super.onPause()
    handler.removeCallbacksAndMessages(null)
  }

  private fun toggleRotation() {
    if (rotating) {
      handler.removeCallbacksAndMessages(null)
    } else {
      rotateLoop()
    }
    rotating = !rotating
  }

  private fun rotateLoop() {
    handler.postDelayed(
      {
        photo!!.setRotationBy(1f)
        rotateLoop()
      },
      15,
    )
  }
}
