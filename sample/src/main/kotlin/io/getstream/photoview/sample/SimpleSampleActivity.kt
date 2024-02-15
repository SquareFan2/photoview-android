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

import android.graphics.Matrix
import android.graphics.RectF
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import io.getstream.photoview.OnMatrixChangedListener
import io.getstream.photoview.OnPhotoTapListener
import io.getstream.photoview.OnSingleFlingListener
import io.getstream.photoview.sample.databinding.ActivitySimpleSampleBinding
import java.util.Random

class SimpleSampleActivity : AppCompatActivity() {

  private val binding: ActivitySimpleSampleBinding by lazy {
    ActivitySimpleSampleBinding.inflate(layoutInflater)
  }

  private var mCurrentToast: Toast? = null
  private var currentDisplayMatrix: Matrix? = null

  public override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(binding.root)

    val photoView = binding.ivPhoto
    binding.toolbar.apply {
      title = "Simple Sample"
      setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)
      setNavigationOnClickListener { v: View? -> onBackPressed() }
      inflateMenu(R.menu.main_menu)
      setOnMenuItemClickListener { item: MenuItem ->
        when (item.itemId) {
          R.id.menu_zoom_toggle -> {
            photoView.isZoomable = !photoView.isZoomable
            item.setTitle(
              if (photoView.isZoomable) {
                R.string.menu_zoom_disable
              } else {
                R.string.menu_zoom_enable
              },
            )
            return@setOnMenuItemClickListener true
          }

          R.id.menu_scale_fit_center -> {
            photoView.scaleType = ImageView.ScaleType.CENTER
            return@setOnMenuItemClickListener true
          }

          R.id.menu_scale_fit_start -> {
            photoView.scaleType = ImageView.ScaleType.FIT_START
            return@setOnMenuItemClickListener true
          }

          R.id.menu_scale_fit_end -> {
            photoView.scaleType = ImageView.ScaleType.FIT_END
            return@setOnMenuItemClickListener true
          }

          R.id.menu_scale_fit_xy -> {
            photoView.scaleType = ImageView.ScaleType.FIT_XY
            return@setOnMenuItemClickListener true
          }

          R.id.menu_scale_scale_center -> {
            photoView.scaleType = ImageView.ScaleType.CENTER
            return@setOnMenuItemClickListener true
          }

          R.id.menu_scale_scale_center_crop -> {
            photoView.scaleType = ImageView.ScaleType.CENTER_CROP
            return@setOnMenuItemClickListener true
          }

          R.id.menu_scale_scale_center_inside -> {
            photoView.scaleType = ImageView.ScaleType.CENTER_INSIDE
            return@setOnMenuItemClickListener true
          }

          R.id.menu_scale_random_animate, R.id.menu_scale_random,
          -> {
            val r = Random()
            val minScale = photoView.minimumScale
            val maxScale = photoView.maximumScale
            val randomScale = minScale + r.nextFloat() * (maxScale - minScale)
            photoView.setScale(randomScale, item.itemId == R.id.menu_scale_random_animate)
            showToast(String.format(SCALE_TOAST_STRING, randomScale))
            return@setOnMenuItemClickListener true
          }

          R.id.menu_matrix_restore -> {
            if (currentDisplayMatrix == null) {
              showToast("You need to capture display matrix first")
            } else {
              photoView.setDisplayMatrix(
                currentDisplayMatrix,
              )
            }
            return@setOnMenuItemClickListener true
          }

          R.id.menu_matrix_capture -> {
            currentDisplayMatrix = Matrix()
            photoView.getDisplayMatrix(currentDisplayMatrix)
            return@setOnMenuItemClickListener true
          }

          else -> false
        }
      }
    }
    currentDisplayMatrix = binding.tvCurrentMatrix.matrix
    val bitmap = ContextCompat.getDrawable(this, R.drawable.wallpaper)
    photoView.setImageDrawable(bitmap)

    // Lets attach some listeners, not required though!
    photoView.setOnMatrixChangeListener(MatrixChangeListener())
    photoView.setOnPhotoTapListener(PhotoTapListener())
    photoView.setOnSingleFlingListener(SingleFlingListener())
  }

  private inner class PhotoTapListener : OnPhotoTapListener {
    override fun onPhotoTap(view: ImageView?, x: Float, y: Float) {
      val xPercentage = x * 100f
      val yPercentage = y * 100f
      showToast(
        String.format(
          PHOTO_TAP_TOAST_STRING,
          xPercentage,
          yPercentage,
          view?.id ?: 0,
        ),
      )
    }
  }

  private fun showToast(text: CharSequence) {
    if (mCurrentToast != null) {
      mCurrentToast!!.cancel()
    }
    mCurrentToast =
      Toast.makeText(this@SimpleSampleActivity, text, Toast.LENGTH_SHORT).apply { show() }
  }

  private inner class MatrixChangeListener : OnMatrixChangedListener {
    override fun onMatrixChanged(rect: RectF?) {
      val matrixText = binding.tvCurrentMatrix
      matrixText.text = rect.toString()
    }
  }

  private inner class SingleFlingListener : OnSingleFlingListener {
    override fun onFling(
      e1: MotionEvent?,
      e2: MotionEvent?,
      velocityX: Float,
      velocityY: Float,
    ): Boolean {
      Log.d("PhotoView", String.format(FLING_LOG_STRING, velocityX, velocityY))
      return true
    }
  }

  companion object {
    const val PHOTO_TAP_TOAST_STRING = "Photo Tap! X: %.2f %% Y:%.2f %% ID: %d"
    const val SCALE_TOAST_STRING = "Scaled to: %.2ff"
    const val FLING_LOG_STRING = "Fling velocityX: %.2f, velocityY: %.2f"
  }
}
