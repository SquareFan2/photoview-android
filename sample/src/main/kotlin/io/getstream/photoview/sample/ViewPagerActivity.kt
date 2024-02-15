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
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.PagerAdapter
import io.getstream.photoview.PhotoView
import io.getstream.photoview.sample.databinding.ActivityViewPagerBinding

class ViewPagerActivity : AppCompatActivity() {
  public override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val binding = ActivityViewPagerBinding.inflate(layoutInflater)
    setContentView(binding.root)

    binding.viewPager.adapter = SamplePagerAdapter()
  }

  internal class SamplePagerAdapter : PagerAdapter() {
    override fun getCount(): Int {
      return sDrawables.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): View {
      val photoView = PhotoView(container.context)
      photoView.setImageResource(sDrawables[position])
      // Now just add PhotoView to ViewPager and return it
      container.addView(
        photoView,
        ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.MATCH_PARENT,
      )
      return photoView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
      container.removeView(`object` as View)
    }

    override fun isViewFromObject(view: View, item: Any): Boolean {
      return view === item
    }

    companion object {
      private val sDrawables = intArrayOf(
        R.drawable.wallpaper,
        R.drawable.wallpaper,
        R.drawable.wallpaper,
        R.drawable.wallpaper,
        R.drawable.wallpaper,
        R.drawable.wallpaper,
      )
    }
  }
}
