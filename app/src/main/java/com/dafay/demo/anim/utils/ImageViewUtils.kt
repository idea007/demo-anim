package com.dafay.demo.anim.utils

import android.graphics.Matrix
import android.os.Build
import android.util.Log
import android.widget.ImageView
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method

/**
 * Created by  idea on 2020/1/2$ 下午3:19$
 * des:
 */
object ImageViewUtils {

    private val TAG: String = ImageViewUtils::class.java.name
    private var sAnimateTransformMethod: Method? = null
    private var sAnimateTransformMethodFetched = false


    /**
     * Sets the matrix to animate the content of the image view.
     */
    fun animateTransform(
        view: ImageView,
        matrix: Matrix?
    ) {
        if (Build.VERSION.SDK_INT < 21) {
            view.imageMatrix = matrix
        } else {
            fetchAnimateTransformMethod()
            if (sAnimateTransformMethod != null) {
                try {
                    sAnimateTransformMethod!!.invoke(view, matrix)
                } catch (e: IllegalAccessException) { // Do nothing
                } catch (e: InvocationTargetException) {
                    throw RuntimeException(e.cause)
                }
            }
        }
    }

    private fun fetchAnimateTransformMethod() {
        if (!sAnimateTransformMethodFetched) {
            try {
                sAnimateTransformMethod =
                    ImageView::class.java.getDeclaredMethod(
                        "animateTransform",
                        Matrix::class.java
                    )
                sAnimateTransformMethod?.setAccessible(true)
            } catch (e: NoSuchMethodException) {
                Log.e(
                    TAG,
                    "Failed to retrieve animateTransform method",
                    e
                )
            }
            sAnimateTransformMethodFetched = true
        }
    }


}