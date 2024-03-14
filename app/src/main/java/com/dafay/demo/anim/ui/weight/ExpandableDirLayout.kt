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
import com.idea.android.animandtran.R
import com.idea.library.richeditor.utils.DpUtils

/**
 * Created by  idea on 2019-11-26$ 10:58$
 * des:
 */
class ExpandableDirLayout : RelativeLayout {


    private lateinit var tv_title: TextView
    private lateinit var rl_expandable_container: RelativeLayout
    private lateinit var rl_container: RelativeLayout

    // view width
    private var mViewWidth = 0
    // view height
    private var mViewHeight = 0

    // 分割线的高度
    private var mChildHeight = 0

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

    private var isExpand = false
    private fun bindListener() {

        tv_title.setOnClickListener(OnClickListener {


            val params = rl_expandable_container.getLayoutParams()

            var valueAnimator: ValueAnimator
            if (!isExpand) {
                valueAnimator =
                    ValueAnimator.ofInt(0, rl_expandable_container.childCount * mChildHeight)
            } else {
                valueAnimator =
                    ValueAnimator.ofInt(rl_expandable_container.childCount * mChildHeight, 0)
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
        mViewWidth = w
        mViewHeight = h

        mChildHeight = DpUtils.dp2px(context, 70f)
    }

    private fun findViews() {
        tv_title = this.findViewById(R.id.tv_title)
        rl_expandable_container = this.findViewById(R.id.rl_expandable_container)
        rl_container = this.findViewById(R.id.rl_container)
    }

    fun setTitle(title: String) {
        tv_title.text = title
    }


    fun addChildView(title: String, des: String = "", onClick: () -> Unit) {

        rl_expandable_container.post(Runnable {

            var childContainerView =
                LayoutInflater.from(context).inflate(R.layout.layout_expandable_child, null)

            var marginLayoutParams = MarginLayoutParams(mViewWidth, mChildHeight)
            marginLayoutParams.setMargins(
                0,
                mChildHeight * rl_expandable_container.childCount,
                0,
                0
            )
            childContainerView.layoutParams = marginLayoutParams

            var titleView = childContainerView.findViewById<TextView>(R.id.tv_title)
            var titleDes = childContainerView.findViewById<TextView>(R.id.tv_des)

            titleView.text = "• " + title
            titleDes.text = "" + des

            childContainerView.setBackgroundColor(Color.parseColor("#d5d8dc"))
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                childContainerView.foreground =
                    context.getResources().getDrawable(R.drawable.ripple_rect_color_40808080)
            }
            childContainerView.setOnClickListener(OnClickListener {
                onClick()
            })
            rl_expandable_container.addView(childContainerView)

        })

    }


}