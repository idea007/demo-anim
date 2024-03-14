package com.dafay.demo.anim.ui.weight

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import com.dafay.demo.anim.utils.LogUtils
import java.lang.ref.WeakReference

/**
 * Created by  idea on 2020/5/15$ 下午2:14$
 * des:
 * <a href="https://www.jianshu.com/p/19997b0b5b24"></a>
 * <a href="https://github.com/GcsSloop/AndroidNote/blob/master/CustomView/Advance/%5B07%5DPath_Over.md"> </a>
 */
class CustomImageView : AppCompatImageView {

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }


    fun init() {
        LogUtils.d("init() width=$width height=$height")

        mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    }


    private var mMaskBitmap: Bitmap? = null
    private var mPaint: Paint? = null
    private var mWeakBitmap: WeakReference<Bitmap>? = null
    private val sXfermode: Xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_IN)

    override fun onDraw(canvas: Canvas) {

        LogUtils.d("onDraw() width=$width height=$height")

        if (!isInEditMode) {

            // 1.保存层级
            val i = canvas.saveLayer(
                0.0f,
                0.0f,
                width.toFloat(),
                height.toFloat(),
                null,
                Canvas.ALL_SAVE_FLAG
            )

            try {
                // 2. 最终的bitmap 是否已经生成
                var bitmap = if (mWeakBitmap != null) mWeakBitmap?.get() else null
                // Bitmap not loaded.
                //
                if (bitmap == null || bitmap.isRecycled()) {
                    val drawable = drawable
                    if (drawable != null) {
                        // 3.1 原始 bitmap
                        // Allocation onDraw but it's ok because it will not always be called.
                        bitmap = Bitmap.createBitmap(
                            width,
                            height, Bitmap.Config.ARGB_8888
                        )
                        val bitmapCanvas = Canvas(bitmap)
                        drawable.setBounds(0, 0, width, height)
                        drawable.draw(bitmapCanvas)

                        // 3.2 用来 mark bitmap
                        // If mask is already set, skip and use cached mask.
                        if (mMaskBitmap == null || mMaskBitmap!!.isRecycled()) {
                            mMaskBitmap = getBitmap()
                        }

                        // 3.3 合成最终的bitmap
                        // Draw Bitmap.
                        mPaint!!.reset()
                        mPaint!!.isFilterBitmap = false
                        mPaint!!.xfermode = sXfermode
                        bitmapCanvas.drawBitmap(mMaskBitmap, 0.0f, 0.0f, mPaint)

                        mWeakBitmap = WeakReference<Bitmap>(bitmap)
                    }
                }

                // 2. 最终的bitmap 已经生成 使用
                if (bitmap != null) {
                    mPaint!!.xfermode = null
                    canvas.drawBitmap(bitmap, 0.0f, 0.0f, mPaint)
                    return
                }

            } catch (e: Exception) {
                LogUtils.e(
                    String.format(
                        "Failed to draw, Id :: %s. Error occurred :: %s",
                        id,
                        e.toString()
                    )
                )

            } finally {
                // 1.1 还原层级
                canvas.restoreToCount(i)
            }

        } else {
            super.onDraw(canvas)
        }
    }

    /**
     * 获取 mark bitmap,做用于与原图
     */
    private fun getBitmap(): Bitmap {
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.color = Color.BLACK

        var path2 = Path()
        var rectF2 = RectF(0f, 0f, width.toFloat(), height.toFloat())

        var floatArray = floatArrayOf(30f, 30f, 30f, 30f, 0f, 0f, 0f, 0f)
        path2.addRoundRect(rectF2, floatArray, Path.Direction.CW)
        canvas.drawPath(path2, paint)
        return bitmap
    }


}