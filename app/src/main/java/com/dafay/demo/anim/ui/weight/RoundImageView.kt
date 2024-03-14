package com.dafay.demo.anim.ui.weight

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Outline
import android.util.AttributeSet
import android.view.View
import android.view.ViewOutlineProvider
import androidx.appcompat.widget.AppCompatImageView
import com.idea.android.animandtran.R

/**
 * Created by  idea on 2020/5/15$ 下午2:14$
 * des:
 * <a href="https://www.jianshu.com/p/19997b0b5b24"></a>
 */
class RoundImageView : AppCompatImageView {

    constructor(context: Context, radius: Float) : super(context) {
        this.radius = radius
        init(context, null)
    }

    constructor(context: Context) : super(context) {
        init(context, null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context, attrs)
    }

    private var radius = 0f

    fun init(context: Context, attrs: AttributeSet?) {

        if (attrs != null) {
            val ta: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.RoundImageView)
            if (ta != null) {
                radius = ta.getDimension(R.styleable.RoundImageView_radius, 0f)
                ta.recycle()
            }
        }

        outlineProvider = CIRCULAR_OUTLINE
        clipToOutline = true

    }

    val CIRCULAR_OUTLINE: ViewOutlineProvider = object : ViewOutlineProvider() {
        override fun getOutline(
            view: View,
            outline: Outline
        ) {
            outline.setRoundRect(
                view.paddingLeft,
                view.paddingTop,
                view.width - view.paddingRight,
                view.height - view.paddingBottom, radius
            )
        }
    }

}