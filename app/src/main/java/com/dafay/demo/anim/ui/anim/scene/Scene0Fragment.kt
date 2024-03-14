package com.dafay.demo.anim.ui.anim.scene

import android.animation.ObjectAnimator
import android.os.Bundle
import android.os.Handler
import android.transition.ChangeBounds
import android.transition.TransitionManager
import android.view.View
import android.view.ViewTreeObserver
import com.idea.android.animandtran.R
import com.dafay.demo.anim.ui.frg.BaseFragment
import com.dafay.demo.anim.utils.LogUtils
import kotlinx.android.synthetic.main.frg_scene_0.view.*


/**
 * Created by  idea on 2019-11-23$ 10:52$
 * des: 测试 ViewGroup 关键方法执行，ViewTreeObserver 的回调
 *
 */
class Scene0Fragment : BaseFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Handler().post(object : Runnable {
            override fun run() {
                LogUtils.d("handler post")
            }
        })

    }

    override fun getLayoutId(): Int {
        return R.layout.frg_scene_0
    }

    override fun onInitViews() {
        LogUtils.d("init ${mRootView.rl_container.width} ${mRootView.rl_container.height}")
        initView()
    }


    private fun initView() {

        test()
        bindListener()

    }

    private fun bindListener() {

        mRootView.btn_start.setOnClickListener(View.OnClickListener {
            ObjectAnimator.ofFloat(mRootView.tv_title, View.TRANSLATION_X, 0f, 100f).start()
        })


        mRootView.btn_start_changebounds.setOnClickListener(View.OnClickListener {

            var changeBounds = ChangeBounds()
            changeBounds.duration = 2000
            TransitionManager.beginDelayedTransition(mRootView.rl_container, changeBounds)

            val params = mRootView.tv_title.getLayoutParams()
            params.width *= 2
            params.height *= 2
            mRootView.tv_title.setLayoutParams(params)

        })


    }

    private fun test() {

        mRootView.rl_container.post(object : Runnable {
            override fun run() {
                val viewWidth = mRootView.rl_container.width
                val viewHeight = mRootView.rl_container.height

                LogUtils.d("post() $viewWidth $viewHeight")
            }
        })

        mRootView.rl_container.postOnAnimation(object : Runnable {
            override fun run() {

                val viewWidth = mRootView.rl_container.width
                val viewHeight = mRootView.rl_container.height

                LogUtils.d("postOnAnimation() $viewWidth $viewHeight")
            }
        })

        val observer = mRootView.rl_container.getViewTreeObserver()
        observer.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
            override fun onPreDraw(): Boolean {


                val viewWidth = mRootView.rl_container.width
                val viewHeight = mRootView.rl_container.height

                LogUtils.d("onPreDraw() $viewWidth $viewHeight")
                return true
            }

        })

        observer.addOnDrawListener(object : ViewTreeObserver.OnDrawListener {
            override fun onDraw() {

                val viewWidth = mRootView.rl_container.width
                val viewHeight = mRootView.rl_container.height

                LogUtils.d("onDraw() $viewWidth $viewHeight")
            }
        })

        observer.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {

                val viewWidth = mRootView.rl_container.width
                val viewHeight = mRootView.rl_container.height

                LogUtils.d("onGlobalLayout() $viewWidth $viewHeight")
            }
        })

    }


}