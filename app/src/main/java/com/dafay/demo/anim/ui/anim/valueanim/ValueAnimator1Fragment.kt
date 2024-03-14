package com.dafay.demo.anim.ui.anim.valueanim

import android.animation.ValueAnimator
import android.view.Choreographer
import android.view.View
import com.idea.android.animandtran.R
import com.dafay.demo.anim.ui.frg.BaseFragment
import com.dafay.demo.anim.utils.LogUtils
import kotlinx.android.synthetic.main.frg_value_animator_0.view.*


/**
 * Created by  idea on 2019-11-23$ 10:52$
 * des:
 */
class ValueAnimator1Fragment : BaseFragment() {

    private val DURATION_TIME: Long = 5000
    private val DEFAULT_DISTANCE = 500

    override fun getLayoutId(): Int {
        return R.layout.frg_value_animator_0
    }

    override fun onInitViews() {
        bindListener()
    }

    private fun bindListener() {

        mRootView.btn_valueanimator.setOnClickListener({

            var valueAnim = ValueAnimator.ofFloat(0f, 1f)
            valueAnim.duration = DURATION_TIME
            valueAnim.start()

            valueAnim.addUpdateListener(object : ValueAnimator.AnimatorUpdateListener {
                override fun onAnimationUpdate(animation: ValueAnimator) {
                    var progress = animation.animatedValue as Float
                    mRootView?.v_0.translationX = progress * DEFAULT_DISTANCE
                }
            })
            blockByCalculate()
        })


        mRootView.btn_custom_anim.setOnClickListener(View.OnClickListener {
            var choreographer = Choreographer.getInstance()
            startTime = 0
            postNextFrame(choreographer)
            blockByCalculate()
        })
    }


    var startTime: Long = 0
    var currentProgress = 0f // 0~1
    private fun postNextFrame(choreographer: Choreographer) {
        choreographer.postFrameCallback {
            if (startTime == 0L) {
                startTime = System.currentTimeMillis()
                currentProgress = 0f
            } else {
                currentProgress =
                    (System.currentTimeMillis() - startTime) / DURATION_TIME.toFloat()
            }

            if (currentProgress > 1) {
                currentProgress = 1f
                mRootView?.v_1.translationX = currentProgress * DEFAULT_DISTANCE
            } else {
                mRootView?.v_1.translationX = currentProgress * DEFAULT_DISTANCE
                postNextFrame(choreographer)
            }
        }
    }

    /**
     * 执行一个耗时运算
     */
    fun blockByCalculate() {
        var preTime = System.currentTimeMillis()
        for (i in 0..40) {
            fib(i)
        }
        LogUtils.d("-------- calculate durationTime=" + (System.currentTimeMillis() - preTime))
    }

    fun fib(n: Int): Int {
        if (n <= 1) {
            return n
        }
        return fib(n - 1) + fib(n - 2)
    }

}