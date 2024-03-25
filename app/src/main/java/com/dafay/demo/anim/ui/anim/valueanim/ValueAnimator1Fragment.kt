package com.dafay.demo.anim.ui.anim.valueanim

import android.animation.FloatEvaluator
import android.animation.ValueAnimator
import android.view.animation.LinearInterpolator
import com.dafay.demo.anim.ui.frg.BaseFragment
import com.idea.android.animandtran.R
import kotlinx.android.synthetic.main.frg_value_animator_1.view.btn_click
import kotlinx.android.synthetic.main.frg_value_animator_1.view.v_0


/**
 * Created by  idea on 2019-11-23$ 10:52$
 * des:
 */
class ValueAnimator1Fragment : BaseFragment() {

    override fun getLayoutId(): Int {
        return R.layout.frg_value_animator_1
    }

    override fun onInitViews() {
        rootView.btn_click.setOnClickListener {

        }
    }

    fun testAnim() {
        val valueAnimator = ValueAnimator.ofFloat(0f, 1f)
        // 动画持续时间
        valueAnimator.duration = 400
        // 插值器，⽤于设置时间完成度到动画完成度的计算公式，速度曲线。LinearInterpolator 为默认插值器
        valueAnimator.interpolator = LinearInterpolator()
        // 评估器，⽤于设置动画完成度到属性具体值的计算。FloatEvaluator 为默认求值器
        valueAnimator.setEvaluator(FloatEvaluator())
        // 监听器
        valueAnimator.addUpdateListener(object : ValueAnimator.AnimatorUpdateListener {
            override fun onAnimationUpdate(animation: ValueAnimator) {
                rootView.v_0.translationX = animation.animatedValue as Float * 500
            }
        })
        valueAnimator.start()
    }
}