package com.dafay.demo.anim.ui.anim.tran

import android.graphics.Rect
import android.os.Build
import android.transition.*
import android.view.View
import android.widget.ImageView
import androidx.annotation.RequiresApi
import com.dafay.demo.anim.R
import com.dafay.demo.anim.ui.frg.BaseFragment
import com.dafay.demo.anim.utils.ViewUtils
import com.idea.library.richeditor.utils.DpUtils
import kotlinx.android.synthetic.main.frg_tran_changebounds_0.view.*

/**
 * Created by  idea on 2019-11-23$ 10:52$
 * des:
 */
class TranChangeBounds0Fragment : BaseFragment() {
    override fun getLayoutId(): Int {
        return R.layout.frg_tran_changebounds_0
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onInitViews() {

        initView()

    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun initView() {


        rootView.btn_click_changebounds.setOnClickListener(View.OnClickListener {


            var transitionSet = TransitionSet()
                .addTransition(ChangeBounds().apply {
                    duration = 3000
                })

            TransitionManager.beginDelayedTransition(rootView.rl_container, transitionSet)

            rootView.civ_image.rotation = 45f
            ViewUtils.setMargins(
                rootView.civ_image,
                DpUtils.dp2px(context!!, 100f),
                DpUtils.dp2px(context!!, 100f),
                0,
                0
            )

        })


        rootView.btn_click_changetransform.setOnClickListener(View.OnClickListener {


            var transitionSet = TransitionSet()
                .addTransition(ChangeTransform().apply {
                    duration = 3000
                })

            TransitionManager.beginDelayedTransition(rootView.rl_container, transitionSet)

            rootView.civ_image.rotation = 45f
            ViewUtils.setMargins(
                rootView.civ_image,
                DpUtils.dp2px(context!!, 100f),
                DpUtils.dp2px(context!!, 100f),
                0,
                0
            )

        })

        rootView.btn_click_changeclipbounds.setOnClickListener(View.OnClickListener {


            rootView.civ_image.clipBounds = Rect(0, 0, rootView.civ_image.width, rootView.civ_image.height)

            var transitionSet = TransitionSet()
                .addTransition(ChangeClipBounds().apply {
                    duration = 3000
                })

            TransitionManager.beginDelayedTransition(rootView.rl_container, transitionSet)

            rootView.civ_image.clipBounds = Rect(
                rootView.civ_image.width / 4,
                rootView.civ_image.height / 4,
                rootView.civ_image.width * 3 / 4,
                rootView.civ_image.height * 3 / 4
            )


            rootView.civ_image.rotation = 45f
            ViewUtils.setMargins(
                rootView.civ_image,
                DpUtils.dp2px(context!!, 100f),
                DpUtils.dp2px(context!!, 100f),
                0,
                0
            )

        })


        rootView.btn_click_changeimagetransform.setOnClickListener(View.OnClickListener {


            var transitionSet = TransitionSet()
                .addTransition(ChangeImageTransform().apply {
                    duration = 3000
                })

            TransitionManager.beginDelayedTransition(rootView.rl_container, transitionSet)

            rootView.civ_image.scaleType = ImageView.ScaleType.CENTER_INSIDE

            rootView.civ_image.rotation = 45f
            ViewUtils.setMargins(
                rootView.civ_image,
                DpUtils.dp2px(context!!, 100f),
                DpUtils.dp2px(context!!, 100f),
                0,
                0
            )

        })

        rootView.btn_click_arcmotion.setOnClickListener(View.OnClickListener {


            var transitionSet = TransitionSet()
                .addTransition(ChangeBounds().apply {
                    duration = 3000
                    pathMotion = ArcMotion().apply {
                        minimumHorizontalAngle = 90f

                    }
                })


            TransitionManager.beginDelayedTransition(rootView.rl_container, transitionSet)
            ViewUtils.setMargins(
                rootView.civ_image,
                DpUtils.dp2px(context!!, 200f),
                DpUtils.dp2px(context!!, 20f),
                0,
                0
            )

        })

        rootView.btn_click_changescroll.setOnClickListener(View.OnClickListener {


            rootView.civ_image.scrollY = DpUtils.dp2px(context!!, 0f)

            var transitionSet = TransitionSet()
                .addTransition(ChangeScroll().apply {
                    duration = 3000
                    pathMotion = ArcMotion().apply {
                        minimumHorizontalAngle = 90f
                    }
                })

            TransitionManager.beginDelayedTransition(rootView.rl_container, transitionSet)
            rootView.civ_image.scrollY = DpUtils.dp2px(context!!, 100f)


        })


    }


}