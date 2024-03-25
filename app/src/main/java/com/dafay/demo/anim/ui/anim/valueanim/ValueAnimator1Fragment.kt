package com.dafay.demo.anim.ui.anim.valueanim

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
           val valueAnimator = ValueAnimator.ofFloat(0f, 1f)
           valueAnimator.duration = 400
           valueAnimator.interpolator=LinearInterpolator()
           valueAnimator.addUpdateListener(object : ValueAnimator.AnimatorUpdateListener {
               override fun onAnimationUpdate(animation: ValueAnimator) {
                   val progress = animation.animatedValue as Float
                   rootView.v_0.translationX = progress * 500
               }
           })
           valueAnimator.start()
       }
    }
}