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

import android.content.Context
import android.view.MotionEvent
import androidx.drawerlayout.widget.DrawerLayout

/**
 * Hacky fix for Issue #4 and
 * http://code.google.com/p/android/issues/detail?id=18990
 *
 *
 * ScaleGestureDetector seems to mess up the touch events, which means that
 * ViewGroups which make use of onInterceptTouchEvent throw a lot of
 * IllegalArgumentException: pointerIndex out of range.
 *
 *
 * There's not much I can do in my code for now, but we can mask the result by
 * just catching the problem and ignoring it.
 */
class HackyDrawerLayout(context: Context) : DrawerLayout(context) {
  override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
    return try {
      super.onInterceptTouchEvent(ev)
    } catch (e: IllegalArgumentException) {
      e.printStackTrace()
      false
    }
  }
}
