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
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import io.getstream.photoview.dialog.PhotoViewDialog
import io.getstream.photoview.sample.databinding.ActivityPhotoviewDialogBinding

class PhotoViewDialogActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val binding = ActivityPhotoviewDialogBinding.inflate(layoutInflater)
    setContentView(binding.root)

    val urls = listOf(
      "https://images.unsplash.com/photo-1577643816920-65b43ba99fba?ixlib=rb-1.2.1",
      "https://images.unsplash.com/photo-1577643816920-65b43ba99fba?ixlib=rb-1.2.1",
      "https://images.unsplash.com/photo-1577643816920-65b43ba99fba?ixlib=rb-1.2.1",
      "https://images.unsplash.com/photo-1577643816920-65b43ba99fba?ixlib=rb-1.2.1",
      "https://images.unsplash.com/photo-1577643816920-65b43ba99fba?ixlib=rb-1.2.1",
      "https://images.unsplash.com/photo-1577643816920-65b43ba99fba?ixlib=rb-1.2.1",
    )

    val button = binding.button
    button.setOnClickListener {
      PhotoViewDialog.Builder(context = this, images = urls) { imageView, url ->
        Glide.with(this)
          .load(url)
          .into(imageView)
      }.build().show()
    }
  }
}
