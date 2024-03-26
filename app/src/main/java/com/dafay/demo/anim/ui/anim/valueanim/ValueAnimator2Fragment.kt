package com.dafay.demo.anim.ui.anim.valueanim

import android.animation.ValueAnimator
import android.os.Handler
import android.os.Looper
import android.view.Choreographer
import com.dafay.demo.anim.ui.frg.BaseFragment
import com.dafay.demo.anim.utils.LogUtils
import com.dafay.demo.anim.R
import kotlinx.android.synthetic.main.frg_value_animator_2.view.btn_custom_anim
import kotlinx.android.synthetic.main.frg_value_animator_2.view.btn_valueanim
import kotlinx.android.synthetic.main.frg_value_animator_2.view.v_0
import kotlinx.android.synthetic.main.frg_value_animator_2.view.v_1


/**
 * Created by  idea on 2019-11-23$ 10:52$
 * des:
 */
class ValueAnimator2Fragment : BaseFragment() {

    private val DURATION_TIME: Long = 2000
    private val DEFAULT_DISTANCE = 500
    private val mainHandler = Handler(Looper.getMainLooper())
    private var startTime: Long = 0

    // [0,1]
    private var currentProgress = 0f

    override fun getLayoutId(): Int {
        return R.layout.frg_value_animator_2
    }

    override fun onInitViews() {
        rootView.btn_valueanim.setOnClickListener {
            val valueAnim = ValueAnimator.ofFloat(0f, 1f)
            valueAnim.duration = DURATION_TIME
            valueAnim.addUpdateListener(object : ValueAnimator.AnimatorUpdateListener {
                override fun onAnimationUpdate(animation: ValueAnimator) {
                    val progress = animation.animatedValue as Float
                    rootView.v_0.translationX = progress * DEFAULT_DISTANCE
                }
            })
            valueAnim.start()
            mainHandler.postDelayed({ blockByCalculate() }, 1000)
        }

        rootView.btn_custom_anim.setOnClickListener {
            startTime = 0
            val choreographer = Choreographer.getInstance()
            postNextFrame(choreographer)
            mainHandler.postDelayed({ blockByCalculate() }, 1000)
        }
    }

    private fun postNextFrame(choreographer: Choreographer) {
        choreographer.postFrameCallback {
            if (startTime <= 0L) {
                startTime = System.currentTimeMillis()
                currentProgress = 0f
            } else {
                currentProgress = (System.currentTimeMillis() - startTime) / DURATION_TIME.toFloat()
            }
            if (currentProgress > 1) {
                currentProgress = 1f
                rootView.v_1.translationX = currentProgress * DEFAULT_DISTANCE
                return@postFrameCallback
            }
            rootView.v_1.translationX = currentProgress * DEFAULT_DISTANCE
            postNextFrame(choreographer)
        }
    }

    /**
     * 执行一个耗时运算
     */
    fun blockByCalculate() {
        var preTime = System.currentTimeMillis()
        fib(38)
        LogUtils.d("calculate durationTime=" + (System.currentTimeMillis() - preTime))
    }

    fun fib(n: Int): Int {
        if (n <= 1) {
            return n
        }
        return fib(n - 1) + fib(n - 2)
    }

}