/*
 * Copyright 2018 stfalcon.com
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

package io.getstream.photoview.dialog.viewer.builder

import android.graphics.Color
import android.view.View
import android.widget.ImageView
import io.getstream.photoview.dialog.listeners.OnDismissListener
import io.getstream.photoview.dialog.listeners.OnImageChangeListener
import io.getstream.photoview.dialog.loader.ImageLoader

public class BuilderData<T>(
  public val images: List<T>,
  public val imageLoader: ImageLoader<T>
) {
  public var backgroundColor: Int = Color.BLACK
  public var startPosition: Int = 0
  public var imageChangeListener: OnImageChangeListener? = null
  public var onDismissListener: OnDismissListener? = null
  public var overlayView: View? = null
  public var imageMarginPixels: Int = 0
  public var containerPaddingPixels: IntArray = IntArray(4)
  public var shouldStatusBarHide: Boolean = true
  public var isZoomingAllowed: Boolean = true
  public var isSwipeToDismissAllowed: Boolean = true
  public var transitionView: ImageView? = null
}