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

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.getstream.photoview.sample.databinding.ItemImageBinding

/**
 * Image in recyclerview
 */
class ImageViewHolder(binding: ItemImageBinding) : RecyclerView.ViewHolder(binding.root) {

  companion object {
    fun inflate(parent: ViewGroup): ImageViewHolder {
      val view = ItemImageBinding.inflate(
        LayoutInflater.from(parent.context),
        parent,
        false,
      )
      return ImageViewHolder(view)
    }
  }
}
