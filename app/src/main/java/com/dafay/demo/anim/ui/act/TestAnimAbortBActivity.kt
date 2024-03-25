package com.dafay.demo.anim.ui.act

import android.os.Bundle
import android.transition.Fade
import android.transition.TransitionSet
import com.idea.android.animandtran.R
import com.dafay.demo.lib.base.base.BaseActivity

/**
 * Created by  idea on 2019-11-27$ 14:47$
 * des:
 */
class TestAnimAbortBActivity : BaseActivity() {
    override fun getLayoutId(bundle: Bundle?): Int {
        return R.layout.act_test_anim_abort_b
    }

    override fun onInitViews() {

//        rl_container.alpha = 0f

//        rl_container.animate().apply {
//            duration = 5000
//            alpha(1f)
//            start()
//        }

        var tran=TransitionSet().addTransition(Fade().apply {
            duration=5000
            Fade.IN
        })
        window.enterTransition=tran

    }







}