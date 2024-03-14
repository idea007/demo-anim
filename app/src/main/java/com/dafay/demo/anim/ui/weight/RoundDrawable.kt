package com.dafay.demo.anim.ui.weight

import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable

/**
 * Created by  idea on 2020/5/18$ 下午8:24$
 * des:
 */
class RoundDrawable(val context: Context, val width: Int, val height: Int) : Drawable() {
    // 画笔
    private var mPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)


    init {
        mPaint.color = Color.BLACK
        mPaint.style = Paint.Style.FILL
    }

    override fun draw(canvas: Canvas) {
        canvas.save()

        var pathBg=Path()
        pathBg.addRect(0f,0f,width.toFloat(),height.toFloat(),Path.Direction.CW)

        var pathMark=Path()
        var floatArray = floatArrayOf(20f, 20f, 20f, 20f, 20f, 20f, 20f, 20f)
        pathMark.addRoundRect(20f,20f,width-20f,height-20f,floatArray,Path.Direction.CW)

        pathBg.op(pathMark, Path.Op.DIFFERENCE)

        canvas.drawPath(pathBg,mPaint)
        canvas.restore()

    }

    override fun setAlpha(alpha: Int) {}
    override fun setColorFilter(colorFilter: ColorFilter?) {}
    override fun getOpacity(): Int {
        return PixelFormat.TRANSLUCENT
    }

}