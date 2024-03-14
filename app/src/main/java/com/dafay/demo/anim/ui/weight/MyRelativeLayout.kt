package com.dafay.demo.anim.ui.weight

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.widget.RelativeLayout
import com.dafay.demo.anim.utils.LogUtils

/**
 * Created by  idea on 2019-11-23$ 18:10$
 * des: 用于测试 ViewGroup 关键方法执行
 */
class MyRelativeLayout : RelativeLayout {
    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }


    fun init() {
        LogUtils.d("init() width=$width height=$height")
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        LogUtils.d("onMeasure() width=$width height=$height")
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(changed, l, t, r, b)
        LogUtils.d("onLayout() width=$width height=$height")

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        LogUtils.d("onDraw() width=$width height=$height")
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        LogUtils.d("onSizeChanged() width=$width height=$height")
    }
}