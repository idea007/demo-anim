package com.dafay.demo.anim.utils

import android.graphics.Matrix
import android.graphics.Rect
import android.widget.ImageView
import android.widget.ImageView.ScaleType

/**
 * Created by  idea on 2020/1/2$ 下午2:54$
 * des:
 */
object MatrixUtils {

    /**
     * 获取 ImageView 当前的 matrix
     */
    fun getImageViewMatrix(imageView: ImageView): Matrix? {
        var matrix: Matrix?
        val scaleType: ScaleType = imageView.getScaleType()
        if (scaleType == ScaleType.FIT_XY) {
            matrix = imageView.getImageMatrix()
            if (!matrix.isIdentity) {
                matrix = Matrix(matrix)
            } else {

                val left: Int = imageView.getLeft()
                val top: Int = imageView.getTop()
                val right: Int = imageView.getRight()
                val bottom: Int = imageView.getBottom()
                val bounds = Rect(left, top, right, bottom)

                val drawable = imageView.drawable ?: return null

                val drawableWidth: Int = drawable.getIntrinsicWidth()
                val drawableHeight: Int = drawable.getIntrinsicHeight()

                if (drawableWidth > 0 && drawableHeight > 0) {
                    val scaleX = bounds.width() as Float / drawableWidth
                    val scaleY = bounds.height() as Float / drawableHeight
                    matrix = android.graphics.Matrix()
                    matrix.setScale(scaleX, scaleY)
                } else {
                    matrix = null
                }
            }
        } else {
            matrix = Matrix(imageView.getImageMatrix())
        }

        return matrix
    }


}