package com.dafay.demo.anim.ui.weight

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View.OnClickListener
import android.widget.RelativeLayout
import android.widget.TextView
import com.dafay.demo.anim.R
import com.idea.library.richeditor.utils.DpUtils

/**
 * Created by  idea on 2019-11-26$ 10:58$
 * des:
 */
class ExpandableDirLayout : RelativeLayout {

    private lateinit var tv_title: TextView
    private lateinit var rl_expandable_container: RelativeLayout
    private lateinit var rl_container: RelativeLayout

    private var viewWidth = 0
    private var viewHeight = 0
    private var childViewHeight = 0
    private var isExpand = false

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context)
    }

    fun init(context: Context) {
        LayoutInflater.from(context).inflate(R.layout.layout_expandable_dir, this, true)
        findViews()
        bindListener()
    }

    private fun findViews() {
        tv_title = this.findViewById(R.id.tv_title)
        rl_expandable_container = this.findViewById(R.id.rl_expandable_container)
        rl_container = this.findViewById(R.id.rl_container)
    }

    private fun bindListener() {
        tv_title.setOnClickListener(OnClickListener {
            val params = rl_expandable_container.getLayoutParams()
            val valueAnimator = if (!isExpand) {
                ValueAnimator.ofInt(0, rl_expandable_container.childCount * childViewHeight)
            } else {
                ValueAnimator.ofInt(rl_expandable_container.childCount * childViewHeight, 0)
            }
            valueAnimator.duration = 300
            valueAnimator.addUpdateListener(object : ValueAnimator.AnimatorUpdateListener {
                override fun onAnimationUpdate(animation: ValueAnimator?) {
                    params.height = animation?.animatedValue as Int
                    rl_expandable_container.layoutParams = params
                }
            })
            valueAnimator.start()
            isExpand = !isExpand
        })
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        viewWidth = w
        viewHeight = h
        childViewHeight = DpUtils.dp2px(context, 70f)
    }

    fun setTitle(title: String) {
        tv_title.text = title
    }

    fun addChildView(title: String, des: String = "", onClick: () -> Unit) {
        rl_expandable_container.post {
            val childContainerView =
                LayoutInflater.from(context).inflate(R.layout.layout_expandable_child, null)
            val marginLayoutParams = MarginLayoutParams(viewWidth, childViewHeight)
            marginLayoutParams.setMargins(
                0,
                childViewHeight * rl_expandable_container.childCount,
                0,
                0
            )
            childContainerView.layoutParams = marginLayoutParams

            val titleView = childContainerView.findViewById<TextView>(R.id.tv_title)
            val titleDes = childContainerView.findViewById<TextView>(R.id.tv_des)
            titleView.text = "• " + title
            titleDes.text = "" + des
            childContainerView.setBackgroundColor(Color.parseColor("#d5d8dc"))
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                childContainerView.foreground = context.getResources().getDrawable(R.drawable.ripple_rect_color_40808080)
            }
            childContainerView.setOnClickListener { onClick() }
            rl_expandable_container.addView(childContainerView)
        }
    }
}