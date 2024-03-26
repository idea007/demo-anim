package com.dafay.demo.anim.ui.anim.objectanim

import android.animation.AnimatorSet
import android.animation.Keyframe
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Path
import android.util.FloatProperty
import android.util.Property
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.dafay.demo.anim.ui.frg.BaseFragment
import com.dafay.demo.anim.utils.LogUtils
import com.dafay.demo.anim.R
import com.idea.library.richeditor.utils.DpUtils
import kotlinx.android.synthetic.main.frg_object_animator_0.view.*


/**
 * Created by  idea on 2019-11-23$ 10:52$
 * des:
 */
class ObjectAnimator0Fragment : BaseFragment() {

    private val TAG = ObjectAnimator0Fragment::class.java.simpleName

    private lateinit var targetView: TextView

    override fun getLayoutId(): Int {
        return R.layout.frg_object_animator_0
    }

    override fun onInitViews() {
        initView()
        bindListener()
    }

    private fun initView() {
        targetView = rootView.tv_text
    }

    @SuppressLint("ObjectAnimatorBinding")
    private fun bindListener() {

        addClickButton("translation_x", {
            var objectAnimator = ObjectAnimator.ofFloat(targetView, View.TRANSLATION_X, 0f, 500f, 600f)
            objectAnimator.setDuration(1000).start()
        })

        addClickButton("Property -> translation_x", {
            var objectAnimator = ObjectAnimator.ofFloat(targetView, TRANSLATION_X, 0f, 500f, 600f)
            objectAnimator.setDuration(1000).start()
        })

        addClickButton("路径动画", {
            var path = Path()
            path.moveTo(0f, 0f)
            path.lineTo(0f, 500f)
            path.lineTo(500f, 500f)
            path.lineTo(500f, 0f)
            path.lineTo(0f, 0f)
            var objectAnimator = ObjectAnimator.ofFloat(targetView, View.TRANSLATION_X, View.TRANSLATION_Y, path)
            objectAnimator.setDuration(1000).start()
        })

        addClickButton("路径动画 -> setCoordinates", {
            var path = Path()
            path.moveTo(0f, 0f)
            path.lineTo(0f, 500f)
            path.lineTo(500f, 500f)
            path.lineTo(500f, 0f)
            path.lineTo(0f, 0f)
            var objectAnimator = ObjectAnimator.ofMultiFloat(this, "setCoordinates", path)
            objectAnimator.setDuration(1000).start()
        })


        addClickButton("TextView 颜色变化", {
            var objectAnimator = ObjectAnimator.ofArgb(
                rootView.tv_text,
                "textColor",
                Color.parseColor("#000000"),
                Color.parseColor("#E012CD"),
                Color.parseColor("#00ff00")
            )
            objectAnimator.setDuration(1000).start()
        })


        addClickButton("TextView textSize 变化", {
            var objectAnimator = ObjectAnimator.ofFloat(
                rootView.tv_text,
                "textSize",
                20f, 50f
            )
            objectAnimator.setDuration(1000).start()

        })

        addClickButton("PropertyValuesHolder", {
            val xHolder = PropertyValuesHolder.ofFloat(View.TRANSLATION_X, 0f, 600f)
            val yHolder = PropertyValuesHolder.ofFloat(View.TRANSLATION_Y, 0f, 600f)
            val animator = ObjectAnimator.ofPropertyValuesHolder(targetView, xHolder, yHolder)
            animator.setDuration(1000)
            animator.start()
        })


        addClickButton("Keyframe", {
            val scaleFrame1 = Keyframe.ofFloat(0f, 1.0f)
            val scaleFrame2 = Keyframe.ofFloat(0.5f, 2.0f)
            val scaleFrame3 = Keyframe.ofFloat(0.8f, 1.5f)
            val scaleFrame4 = Keyframe.ofFloat(1.0f, 1.0f)
            val scaleX = PropertyValuesHolder.ofKeyframe(
                "scaleX",
                scaleFrame1,
                scaleFrame2,
                scaleFrame3,
                scaleFrame4
            )
            val scaleY = PropertyValuesHolder.ofKeyframe(
                "scaleY",
                scaleFrame1,
                scaleFrame2,
                scaleFrame3,
                scaleFrame4
            )
            val animator = ObjectAnimator.ofPropertyValuesHolder(targetView, scaleX, scaleY)
            animator.duration = 1000
            animator.addUpdateListener(object : ValueAnimator.AnimatorUpdateListener {
                override fun onAnimationUpdate(animation: ValueAnimator?) {
                    LogUtils.d(TAG, "curValue=" + animation?.animatedValue)
                }
            })
            animator.start()
        })
    }
    fun setCoordinates(x: Float, y: Float) {
        targetView.translationX = x
        targetView.translationY = y
    }

    @SuppressLint("NewApi")
    val TRANSLATION_X: Property<View, Float> = object : FloatProperty<View>("xxxxxxx") {
        override fun setValue(view: View, value: Float) {
            view.translationX = value * 1.1f
        }
        override fun get(view: View): Float {
            return view.translationX * 1.1f
        }
    }

    private fun addClickButton(content: String = "button", onClick: () -> Unit): Button {
        var button = Button(context)
        button.isAllCaps = false
        button.apply {
            var marginLayoutParams =
                ViewGroup.MarginLayoutParams(
                    DpUtils.screenWidth(context),
                    DpUtils.dp2px(context, 50f)
                )
            this.layoutParams = marginLayoutParams
            text = content
            setOnClickListener({ onClick() })
        }
        rootView.ll_button_container.addView(button)
        return button
    }
}