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

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import coil.load
import io.getstream.photoview.sample.databinding.ActivityLauncherBinding
import io.getstream.photoview.sample.databinding.ActivitySimpleBinding

class LauncherActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    if (BuildConfig.BUILD_TYPE == "benchmark") {
      benchmarkLayout()
    } else {
      val binding = ActivityLauncherBinding.inflate(layoutInflater)
      setContentView(binding.root)

      binding.toolbar.setTitle(R.string.app_name)
      binding.list.adapter = ItemAdapter()
    }
  }

  private fun benchmarkLayout() {
    val binding = ActivitySimpleBinding.inflate(layoutInflater)
    setContentView(binding.root)

    val photoView = binding.ivPhoto
    photoView.load(
      "https://images.unsplash.com/photo-1577643816920-65b43ba99fba?ixlib=rb-1.2.1" +
        "&auto=format&fit=crop&w=3300&q=80",
    ) {
      crossfade(true)
    }
  }

  private class ItemAdapter : RecyclerView.Adapter<ItemViewHolder?>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
      val holder = ItemViewHolder.newInstance(parent)
      holder.itemView.setOnClickListener { _: View? ->
        val clazz: Class<*> = when (holder.adapterPosition) {
          0 -> SimpleSampleActivity::class.java
          1 -> ViewPagerActivity::class.java
          2 -> RotationSampleActivity::class.java
          3 -> PicassoSampleActivity::class.java
          4 -> CoilSampleActivity::class.java
          5 -> GlideSampleActivity::class.java
          6 -> ActivityTransitionActivity::class.java
          7 -> ImmersiveActivity::class.java
          else -> SimpleSampleActivity::class.java
        }
        val context = holder.itemView.context
        context.startActivity(Intent(context, clazz))
      }
      return holder
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
      holder.bind(options[position])
    }

    override fun getItemCount(): Int {
      return options.size
    }
  }

  private class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var mTextTitle: TextView

    init {
      mTextTitle = view.findViewById(R.id.title)
    }

    fun bind(title: String) {
      mTextTitle.text = title
    }

    companion object {
      fun newInstance(parent: ViewGroup): ItemViewHolder {
        val view = LayoutInflater.from(parent.context)
          .inflate(R.layout.item_sample, parent, false)
        return ItemViewHolder(view)
      }
    }
  }

  companion object {
    val options = arrayOf(
      "Simple Sample",
      "ViewPager Sample",
      "Rotation Sample",
      "Picasso Sample",
      "Coil Sample",
      "Glide Sample",
      "Activity Transition Sample",
      "Immersive Sample",
    )
  }
}
