package com.dafay.demo.anim.ui.frg

import android.transition.ChangeBounds
import android.transition.TransitionManager
import android.transition.TransitionSet
import android.view.ViewGroup
import com.idea.android.animandtran.R
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
        layoutParams = mRootView.rl_test.layoutParams

        mRootView.btn_expand.setOnClickListener({

            var transitionSet = TransitionSet().addTransition(
                ChangeBounds().apply {
                    duration = 2000
                }
            )
            TransitionManager.beginDelayedTransition(mRootView.fl_container,transitionSet)
            layoutParams.height = 0
            mRootView.rl_test.layoutParams = layoutParams

        })

        mRootView.btn_unexpand.setOnClickListener({

            var transitionSet = TransitionSet().addTransition(
                ChangeBounds().apply {
                    duration = 2000
                }
            )
            TransitionManager.beginDelayedTransition(mRootView.fl_container,transitionSet)
            layoutParams.height = 200*3
            mRootView.rl_test.layoutParams = layoutParams

        })
    }


}