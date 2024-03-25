package com.dafay.demo.anim.ui.anim.scene

import android.transition.*
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.idea.android.animandtran.R
import com.dafay.demo.anim.ui.frg.BaseFragment
import com.idea.library.richeditor.utils.DpUtils
import kotlinx.android.synthetic.main.frg_scene_2.view.*

/**
 * Created by  idea on 2019-11-23$ 10:52$
 * des:
 */
class Scene2Fragment : BaseFragment() {
    override fun getLayoutId(): Int {
        return R.layout.frg_scene_2
    }

    override fun onInitViews() {

        initView()

    }

    private fun initView() {

        rootView.ll_button_container?.addView(createButton("点击隐藏条目二") {
            TransitionManager.beginDelayedTransition(rootView.ll_container)
            rootView.v_1.visibility = View.GONE
        })


        rootView.ll_button_container?.addView(createButton("点击显示条目二") {
            TransitionManager.beginDelayedTransition(rootView.ll_container)
            rootView.v_1.visibility = View.VISIBLE
        })


        rootView.ll_button_container?.addView(createButton("点击隐藏条目二、三") {
            TransitionManager.beginDelayedTransition(rootView.ll_container)
            rootView.v_1.visibility = View.GONE
            rootView.v_2.visibility = View.GONE
        })


        rootView.ll_button_container?.addView(createButton("点击显示条目二、三") {
            TransitionManager.beginDelayedTransition(rootView.ll_container)
            rootView.v_1.visibility = View.VISIBLE
            rootView.v_2.visibility = View.VISIBLE
        })


        rootView.ll_button_container?.addView(createButton("点击隐藏条目二 指定 transition") {

            var transitionSet = TransitionSet()
                .addTransition(ChangeBounds().apply {
                    duration = 3000
                })
                .addTransition(Fade().apply {
                    duration = 3000
                })
                .addTransition(Slide().apply {
                    slideEdge = Gravity.LEFT
                    duration = 3000
                })

            TransitionManager.beginDelayedTransition(rootView.ll_container, transitionSet)

            rootView.v_1.visibility = View.GONE
        })


        rootView.ll_button_container?.addView(createButton("点击显示条目二 指定 transition") {

            var transitionSet = TransitionSet()
                .addTransition(ChangeBounds().apply {
                    duration = 3000
                })
                .addTransition(Fade().apply {
                    duration = 3000
                })
                .addTransition(Slide().apply {
                    slideEdge = Gravity.LEFT
                    duration = 3000
                })

            TransitionManager.beginDelayedTransition(rootView.ll_container, transitionSet)

            rootView.v_1.visibility = View.VISIBLE
        })


    }


    private fun createButton(content: String = "button", onClick: () -> Unit): Button {
        var button = Button(context)
        button.apply {


            var marginLayoutParams = ViewGroup.MarginLayoutParams(DpUtils.screenWidth(context), DpUtils.dp2px(context, 50f))
            this.layoutParams = marginLayoutParams

            text = content
            setOnClickListener({ onClick() })

        }

        return button

    }

}