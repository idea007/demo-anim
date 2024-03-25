package com.dafay.demo.anim.ui.act

import android.animation.ValueAnimator
import android.content.Intent
import android.view.animation.LinearInterpolator
import androidx.core.app.ActivityOptionsCompat
import com.idea.android.animandtran.R
import com.dafay.demo.anim.ui.frg.BaseFragment
import com.dafay.demo.anim.utils.LogUtils
import kotlinx.android.synthetic.main.frg_anim_abort.view.*

/**
 * Created by  idea on 2019-12-20$ 16:34$
 * des:
 */
class TestAnimAbortFragment : BaseFragment() {

    override fun getLayoutId(): Int {
        return R.layout.frg_anim_abort
    }

    override fun onInitViews() {
        bindListener()
    }


    private fun bindListener() {
        rootView.tv_text.setOnClickListener {

            var anim = ValueAnimator.ofFloat(0f, 1f)
            var progress = 0f
            anim.duration = 5000
            anim.interpolator = LinearInterpolator()
            anim.addUpdateListener(object : ValueAnimator.AnimatorUpdateListener {
                override fun onAnimationUpdate(animation: ValueAnimator?) {
                    progress = animation?.animatedValue as Float
                    LogUtils.d("---- progress=$progress")
                    rootView.tv_text.alpha = 1.0f - progress
                    rootView.v_bg.scaleY = 1f + progress * 10
                }
            })
            anim.start()
            val activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(activity!!)
            startActivity(
                Intent(activity, TestAnimAbortBActivity::class.java),
                activityOptions.toBundle()
            )
        }

    }


}