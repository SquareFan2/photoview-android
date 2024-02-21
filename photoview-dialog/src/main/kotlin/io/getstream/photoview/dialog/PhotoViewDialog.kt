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
package io.getstream.photoview.dialog

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.Px
import androidx.core.content.ContextCompat
import io.getstream.photoview.dialog.listeners.OnDismissListener
import io.getstream.photoview.dialog.listeners.OnImageChangeListener
import io.getstream.photoview.dialog.loader.ImageLoader
import io.getstream.photoview.dialog.viewer.builder.BuilderData
import io.getstream.photoview.dialog.viewer.dialog.ImageViewerDialog
import kotlin.math.roundToInt

//N.B.! This class is written in Java for convenient use of lambdas due to languages compatibility issues.
@Suppress("unused")
public class PhotoViewDialog<T>(
  context: Context,
  private val builderData: BuilderData<T>
) {
  private val dialog: ImageViewerDialog<T>

  init {
    dialog = ImageViewerDialog(context, builderData)
  }
  /**
   * Displays the built viewer if passed list of images is not empty
   *
   * @param animate whether the passed transition view should be animated on open. Useful for screen rotation handling.
   */
  /**
   * Displays the built viewer if passed list of images is not empty
   */
  @JvmOverloads
  public fun show(animate: Boolean = true) {
    if (builderData.images.isNotEmpty()) {
      dialog.show(animate)
    } else {
      Log.w("PhotoView", "Images list cannot be empty! Viewer ignored.")
    }
  }

  /**
   * Closes the viewer with suitable close animation
   */
  public fun close() {
    dialog.close()
  }

  /**
   * Dismisses the dialog with no animation
   */
  public fun dismiss() {
    dialog.dismiss()
  }

  /**
   * Updates an existing images list if a new list is not empty, otherwise closes the viewer
   */
  public fun updateImages(images: Array<T>) {
    updateImages(ArrayList(listOf(*images)))
  }

  /**
   * Updates an existing images list if a new list is not empty, otherwise closes the viewer
   */
  public fun updateImages(images: List<T>) {
    if (images.isNotEmpty()) {
      dialog.updateImages(images)
    } else {
      dialog.close()
    }
  }

  public fun currentPosition(): Int {
    return dialog.getCurrentPosition()
  }

  public fun setCurrentPosition(position: Int): Int {
    return dialog.setCurrentPosition(position)
  }

  /**
   * Updates transition image view.
   * Useful for a case when image position has changed and you want to update the transition animation target.
   */
  public fun updateTransitionImage(imageView: ImageView?) {
    dialog.updateTransitionImage(imageView)
  }

  /**
   * Builder class for [PhotoViewDialog]
   */
  public class Builder<T>(
    private val context: Context,
    images: List<T>,
    imageLoader: ImageLoader<T>
  ) {
    private val data: BuilderData<T>

    public constructor(
      context: Context,
      images: Array<T>,
      imageLoader: ImageLoader<T>
    ) : this(
      context, ArrayList<T>(
        listOf<T>(*images)
      ), imageLoader
    )

    init {
      data = BuilderData(images, imageLoader)
    }

    /**
     * Sets a position to start viewer from.
     *
     * @return This Builder object to allow calls chaining
     */
    public fun withStartPosition(position: Int): Builder<T> {
      data.startPosition = position
      return this
    }

    /**
     * Sets a background color value for the viewer
     *
     * @return This Builder object to allow calls chaining
     */
    public fun withBackgroundColor(@ColorInt color: Int): Builder<T> {
      data.backgroundColor = color
      return this
    }

    /**
     * Sets a background color resource for the viewer
     *
     * @return This Builder object to allow calls chaining
     */
    public fun withBackgroundColorResource(@ColorRes color: Int): Builder<T> {
      return withBackgroundColor(ContextCompat.getColor(context, color))
    }

    /**
     * Sets custom overlay view to be shown over the viewer.
     * Commonly used for image description or counter displaying.
     *
     * @return This Builder object to allow calls chaining
     */
    public fun withOverlayView(view: View): Builder<T> {
      data.overlayView = view
      return this
    }

    /**
     * Sets space between the images using dimension.
     *
     * @return This Builder object to allow calls chaining
     */
    public fun withImagesMargin(@DimenRes dimen: Int): Builder<T> {
      data.imageMarginPixels = context.resources.getDimension(dimen).roundToInt()
      return this
    }

    /**
     * Sets space between the images in pixels.
     *
     * @return This Builder object to allow calls chaining
     */
    public fun withImageMarginPixels(marginPixels: Int): Builder<T> {
      data.imageMarginPixels = marginPixels
      return this
    }

    /**
     * Sets overall padding for zooming and scrolling area using dimension.
     *
     * @return This Builder object to allow calls chaining
     */
    public fun withContainerPadding(@DimenRes padding: Int): Builder<T> {
      val paddingPx = context.resources.getDimension(padding).roundToInt()
      return withContainerPaddingPixels(paddingPx, paddingPx, paddingPx, paddingPx)
    }

    /**
     * Sets `start`, `top`, `end` and `bottom` padding for zooming and scrolling area using dimension.
     *
     * @return This Builder object to allow calls chaining
     */
    public fun withContainerPadding(
      @DimenRes start: Int, @DimenRes top: Int,
      @DimenRes end: Int, @DimenRes bottom: Int
    ): Builder<T> {
      withContainerPaddingPixels(
        context.resources.getDimension(start).roundToInt(),
        context.resources.getDimension(top).roundToInt(),
        context.resources.getDimension(end).roundToInt(),
        context.resources.getDimension(bottom).roundToInt()
      )
      return this
    }

    /**
     * Sets overall padding for zooming and scrolling area in pixels.
     *
     * @return This Builder object to allow calls chaining
     */
    public fun withContainerPaddingPixels(@Px padding: Int): Builder<T> {
      data.containerPaddingPixels = intArrayOf(padding, padding, padding, padding)
      return this
    }

    /**
     * Sets `start`, `top`, `end` and `bottom` padding for zooming and scrolling area in pixels.
     *
     * @return This Builder object to allow calls chaining
     */
    public fun withContainerPaddingPixels(start: Int, top: Int, end: Int, bottom: Int): Builder<T> {
      data.containerPaddingPixels = intArrayOf(start, top, end, bottom)
      return this
    }

    /**
     * Sets status bar visibility. True by default.
     *
     * @return This Builder object to allow calls chaining
     */
    public fun withHiddenStatusBar(value: Boolean): Builder<T> {
      data.shouldStatusBarHide = value
      return this
    }

    /**
     * Enables or disables zooming. True by default.
     *
     * @return This Builder object to allow calls chaining
     */
    public fun allowZooming(value: Boolean): Builder<T> {
      data.isZoomingAllowed = value
      return this
    }

    /**
     * Enables or disables the "Swipe to Dismiss" gesture. True by default.
     *
     * @return This Builder object to allow calls chaining
     */
    public fun allowSwipeToDismiss(value: Boolean): Builder<T> {
      data.isSwipeToDismissAllowed = value
      return this
    }

    /**
     * Sets a target [ImageView] to be part of transition when opening or closing the viewer/
     *
     * @return This Builder object to allow calls chaining
     */
    public fun withTransitionFrom(imageView: ImageView?): Builder<T> {
      data.transitionView = imageView
      return this
    }

    /**
     * Sets [OnImageChangeListener] for the viewer.
     *
     * @return This Builder object to allow calls chaining
     */
    public fun withImageChangeListener(imageChangeListener: OnImageChangeListener?): Builder<T> {
      data.imageChangeListener = imageChangeListener
      return this
    }

    /**
     * Sets [OnDismissListener] for viewer.
     *
     * @return This Builder object to allow calls chaining
     */
    public fun withDismissListener(onDismissListener: OnDismissListener?): Builder<T> {
      data.onDismissListener = onDismissListener
      return this
    }

    /**
     * Creates a [PhotoViewDialog] with the arguments supplied to this builder. It does not
     * show the dialog. This allows the user to do any extra processing
     * before displaying the dialog. Use [.show] if you don't have any other processing
     * to do and want this to be created and displayed.
     */
    public fun build(): PhotoViewDialog<T> {
      return PhotoViewDialog(context, data)
    }
    /**
     * Creates the [PhotoViewDialog] with the arguments supplied to this builder and
     * shows the dialog.
     *
     * @param animate whether the passed transition view should be animated on open. Useful for screen rotation handling.
     */
    /**
     * Creates the [PhotoViewDialog] with the arguments supplied to this builder and
     * shows the dialog.
     */
    @JvmOverloads
    public fun show(animate: Boolean = true): PhotoViewDialog<T> {
      val viewer = build()
      viewer.show(animate)
      return viewer
    }
  }
}
