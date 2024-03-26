package com.dafay.demo.anim.ui.frg

import android.transition.ChangeBounds
import android.transition.TransitionManager
import android.transition.TransitionSet
import android.view.ViewGroup
import com.dafay.demo.anim.R
import kotlinx.android.synthetic.main.frg_test_expand_view.view.*

/**
 * Created by  idea on 2019-12-20$ 16:34$
 * des:
 */
class TestExpandViewFragment : BaseFragment() {


    override fun getLayoutId(): Int {
        return R.layout.frg_test_expand_view
    }


    lateinit var layoutParams: ViewGroup.LayoutParams
    override fun onInitViews() {
        layoutParams = rootView.rl_test.layoutParams

        rootView.btn_expand.setOnClickListener({

            var transitionSet = TransitionSet().addTransition(
                ChangeBounds().apply {
                    duration = 2000
                }
            )
            TransitionManager.beginDelayedTransition(rootView.fl_container,transitionSet)
            layoutParams.height = 0
            rootView.rl_test.layoutParams = layoutParams

        })

        rootView.btn_unexpand.setOnClickListener({

            var transitionSet = TransitionSet().addTransition(
                ChangeBounds().apply {
                    duration = 2000
                }
            )
            TransitionManager.beginDelayedTransition(rootView.fl_container,transitionSet)
            layoutParams.height = 200*3
            rootView.rl_test.layoutParams = layoutParams

        })
    }


}