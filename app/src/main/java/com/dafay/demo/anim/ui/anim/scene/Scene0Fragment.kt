package com.dafay.demo.anim.ui.anim.scene

import android.animation.ObjectAnimator
import android.os.Bundle
import android.os.Handler
import android.transition.ChangeBounds
import android.transition.TransitionManager
import android.view.View
import android.view.ViewTreeObserver
import com.dafay.demo.anim.R
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
        LogUtils.d("init ${rootView.rl_container.width} ${rootView.rl_container.height}")
        initView()
    }


    private fun initView() {
        test()
        bindListener()
    }

    private fun bindListener() {
        rootView.btn_start.setOnClickListener(View.OnClickListener {
            ObjectAnimator.ofFloat(rootView.tv_title, View.TRANSLATION_X, 0f, 100f).start()
        })

        rootView.btn_start_changebounds.setOnClickListener(View.OnClickListener {
            var changeBounds = ChangeBounds()
            changeBounds.duration = 2000
            TransitionManager.beginDelayedTransition(rootView.rl_container, changeBounds)
            val params = rootView.tv_title.getLayoutParams()
            params.width *= 2
            params.height *= 2
            rootView.tv_title.setLayoutParams(params)
        })
    }

    private fun test() {
        rootView.rl_container.post(object : Runnable {
            override fun run() {
                val viewWidth = rootView.rl_container.width
                val viewHeight = rootView.rl_container.height
                LogUtils.d("post() $viewWidth $viewHeight")
            }
        })

        rootView.rl_container.postOnAnimation(object : Runnable {
            override fun run() {
                val viewWidth = rootView.rl_container.width
                val viewHeight = rootView.rl_container.height
                LogUtils.d("postOnAnimation() $viewWidth $viewHeight")
            }
        })

        val observer = rootView.rl_container.getViewTreeObserver()
        observer.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
            override fun onPreDraw(): Boolean {
                val viewWidth = rootView.rl_container.width
                val viewHeight = rootView.rl_container.height
                LogUtils.d("onPreDraw() $viewWidth $viewHeight")
                return true
            }
        })

        observer.addOnDrawListener(object : ViewTreeObserver.OnDrawListener {
            override fun onDraw() {
                val viewWidth = rootView.rl_container.width
                val viewHeight = rootView.rl_container.height
                LogUtils.d("onDraw() $viewWidth $viewHeight")
            }
        })

        observer.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                val viewWidth = rootView.rl_container.width
                val viewHeight = rootView.rl_container.height
                LogUtils.d("onGlobalLayout() $viewWidth $viewHeight")
            }
        })
    }
}