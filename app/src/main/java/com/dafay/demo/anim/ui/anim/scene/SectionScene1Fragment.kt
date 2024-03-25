package com.dafay.demo.anim.ui.anim.scene

import android.transition.ChangeBounds
import android.transition.Slide
import android.transition.TransitionManager
import android.transition.TransitionSet
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import com.idea.android.animandtran.R
import com.dafay.demo.anim.ui.frg.BaseFragment
import com.dafay.demo.anim.utils.LogUtils
import com.idea.library.richeditor.utils.DpUtils
import kotlinx.android.synthetic.main.frg_section_scene_1.*
import kotlinx.android.synthetic.main.frg_section_scene_1.view.*


/**
 * Created by  idea on 2019-11-23$ 10:52$
 * des: 目录
 */
class SectionScene1Fragment : BaseFragment() {
    override fun getLayoutId(): Int {
        return R.layout.frg_section_scene_1
    }

    override fun onInitViews() {

        initView()

    }

    private var mViewWidth: Int = 0
    private var mViewHeight: Int = 0

    // 格子的高度
    private var mLatticeHeight = 0
    // 分割线的高度
    private var mDividerHeight = 0


    private fun initView() {


        rootView.rl_container.post({
            mViewWidth = rootView.rl_container.getMeasuredWidth()
            mViewHeight = rootView.rl_container.getMeasuredHeight()
            LogUtils.d("post() $mViewWidth $mViewHeight")
            mLatticeHeight = mViewHeight / 9
            mDividerHeight = DpUtils.dp2px(context!!, 3f) / 2 * 2


            updateLatticesPosition()

            updateDividersPosition()

        })


        bindListener()


    }


    val DURATION_TIME: Long = 2000
    private fun bindListener() {

        rootView.tv_setting.setOnClickListener(View.OnClickListener {


            var transitionSet = TransitionSet()
                .addTransition(ChangeBounds().apply {
                    duration = DURATION_TIME
                })

            var slide1 = Slide()
            slide1.slideEdge = Gravity.LEFT
            slide1.duration = DURATION_TIME
            slide1.addTarget(rootView.tv_menu)
            transitionSet.addTransition(slide1)

            var slide2 = Slide()
            slide2.slideEdge = Gravity.RIGHT
            slide2.duration = DURATION_TIME
            slide2.addTarget(rootView.rl_back)
            transitionSet.addTransition(slide2)

            TransitionManager.beginDelayedTransition(rootView.rl_container, transitionSet)


            setMargins(
                rootView.v_divider_2,
                0,
                mLatticeHeight / 2 + mLatticeHeight * 2 - mDividerHeight / 2 - mLatticeHeight * 5,
                0,
                0
            )
            setMargins(
                rootView.v_divider_3,
                0,
                mLatticeHeight / 2 + mLatticeHeight * 3 - mDividerHeight / 2 - mLatticeHeight * 5,
                0,
                0
            )
            setMargins(
                rootView.v_divider_4,
                0,
                mLatticeHeight / 2 + mLatticeHeight * 4 - mDividerHeight / 2 - mLatticeHeight * 5,
                0,
                0
            )
            setMargins(
                rootView.v_divider_5,
                0,
                mLatticeHeight / 2 + mLatticeHeight * 5 - mDividerHeight / 2 - mLatticeHeight * 5,
                0,
                0
            )
            setMargins(
                rootView.v_divider_6,
                0,
                mLatticeHeight / 2 + mLatticeHeight * 6 - mDividerHeight / 2 - mLatticeHeight * 5,
                0,
                0
            )

            setMargins(
                rootView.v_divider_7,
                0,
                mLatticeHeight / 2 + mLatticeHeight * 7 - mDividerHeight / 2 - mLatticeHeight * 5,
                0,
                0
            )


            setMargins(rootView.tv_setting, 0, mLatticeHeight / 2 + mLatticeHeight * 6 - mLatticeHeight * 5, 0, 0)
            rootView.tv_about.layoutParams.height = mLatticeHeight
            setMargins(rootView.tv_about, 0, mLatticeHeight / 2 + mLatticeHeight * 7 + mLatticeHeight, 0, 0)

            tv_menu.visibility = View.GONE
            rl_back.visibility = View.VISIBLE


//            mRootView.v_divider_6.translationY = -mLatticeHeight * 6f
//            mRootView.v_divider_5.translationY = -mLatticeHeight * 6f
//            mRootView.v_divider_4.translationY = -mLatticeHeight * 6f
//            mRootView.v_divider_3.translationY = -mLatticeHeight * 6f
//            mRootView.v_divider_2.translationY = -mLatticeHeight * 6f
//            mRootView.v_divider_1.translationY = -mLatticeHeight * 6f


        })

        rootView.tv_about.setOnClickListener(View.OnClickListener {

            var transitionSet = TransitionSet()
                .addTransition(ChangeBounds().apply {
                    duration = DURATION_TIME
                })

            var slide1 = Slide()
            slide1.slideEdge = Gravity.LEFT
            slide1.duration = DURATION_TIME
            slide1.addTarget(rootView.tv_menu)
            transitionSet.addTransition(slide1)

            var slide2 = Slide()
            slide2.slideEdge = Gravity.RIGHT
            slide2.duration = DURATION_TIME
            slide2.addTarget(rootView.rl_back)
            transitionSet.addTransition(slide2)



            TransitionManager.beginDelayedTransition(rootView.rl_container, transitionSet)


            rl_back.visibility = View.VISIBLE
            tv_menu.visibility = View.GONE


            setMargins(
                rootView.v_divider_3,
                0,
                mLatticeHeight / 2 + mLatticeHeight * 3 - mDividerHeight / 2 - mLatticeHeight * 6,
                0,
                0
            )
            setMargins(
                rootView.v_divider_4,
                0,
                mLatticeHeight / 2 + mLatticeHeight * 4 - mDividerHeight / 2 - mLatticeHeight * 6,
                0,
                0
            )
            setMargins(
                rootView.v_divider_5,
                0,
                mLatticeHeight / 2 + mLatticeHeight * 5 - mDividerHeight / 2 - mLatticeHeight * 6,
                0,
                0
            )
            setMargins(
                rootView.v_divider_6,
                0,
                mLatticeHeight / 2 + mLatticeHeight * 6 - mDividerHeight / 2 - mLatticeHeight * 6,
                0,
                0
            )
            setMargins(
                rootView.v_divider_7,
                0,
                mLatticeHeight / 2 + mLatticeHeight * 7 - mDividerHeight / 2 - mLatticeHeight * 6,
                0,
                0
            )
            setMargins(
                rootView.v_divider_8,
                0,
                mLatticeHeight / 2 + mLatticeHeight * 8 - mDividerHeight / 2 - mLatticeHeight * 6,
                0,
                0
            )

            setMargins(rootView.tv_setting, 0, mLatticeHeight / 2 + mLatticeHeight * 6 - mLatticeHeight * 6, 0, 0)
            setMargins(rootView.tv_about, 0, mLatticeHeight / 2 + mLatticeHeight * 7 - mLatticeHeight * 6, 0, 0)


        })

        rootView.rl_back.setOnClickListener(View.OnClickListener {


            var transitionSet = TransitionSet()
                .addTransition(ChangeBounds().apply {
                    duration = DURATION_TIME
                })

            var slide1 = Slide()
            slide1.slideEdge = Gravity.LEFT
            slide1.duration = DURATION_TIME
            slide1.addTarget(rootView.tv_menu)
            transitionSet.addTransition(slide1)

            var slide2 = Slide()
            slide2.slideEdge = Gravity.RIGHT
            slide2.duration = DURATION_TIME
            slide2.addTarget(rootView.rl_back)
            transitionSet.addTransition(slide2)

            TransitionManager.beginDelayedTransition(rootView.rl_container, transitionSet)


            setMargins(rootView.v_divider_0, 0, mLatticeHeight / 2 - mDividerHeight / 2, 0, 0)
            setMargins(rootView.v_divider_1, 0, mLatticeHeight / 2 + mLatticeHeight - mDividerHeight / 2, 0, 0)
            setMargins(rootView.v_divider_2, 0, mLatticeHeight / 2 + mLatticeHeight * 2 - mDividerHeight / 2, 0, 0)
            setMargins(rootView.v_divider_3, 0, mLatticeHeight / 2 + mLatticeHeight * 3 - mDividerHeight / 2, 0, 0)
            setMargins(rootView.v_divider_4, 0, mLatticeHeight / 2 + mLatticeHeight * 4 - mDividerHeight / 2, 0, 0)
            setMargins(rootView.v_divider_5, 0, mLatticeHeight / 2 + mLatticeHeight * 5 - mDividerHeight / 2, 0, 0)
            setMargins(rootView.v_divider_6, 0, mLatticeHeight / 2 + mLatticeHeight * 6 - mDividerHeight / 2, 0, 0)
            setMargins(rootView.v_divider_7, 0, mLatticeHeight / 2 + mLatticeHeight * 7 - mDividerHeight / 2, 0, 0)
            setMargins(rootView.v_divider_8, 0, mLatticeHeight / 2 + mLatticeHeight * 8 - mDividerHeight / 2, 0, 0)
            setMargins(rootView.v_divider_9, 0, mLatticeHeight / 2 + mLatticeHeight * 8 - mDividerHeight / 2, 0, 0)


            setMargins(rootView.tv_setting, 0, mLatticeHeight / 2 + mLatticeHeight * 6, 0, 0)
            setMargins(rootView.tv_about, 0, mLatticeHeight / 2 + mLatticeHeight * 7, 0, 0)



            rl_back.visibility = View.GONE
            tv_menu.visibility = View.VISIBLE

        })


    }

    private fun updateLatticesPosition() {

        rootView.tv_menu.layoutParams.height = mLatticeHeight
        rootView.tv_about.layoutParams.height = mLatticeHeight
        rootView.tv_setting.layoutParams.height = mLatticeHeight

        rootView.tv_top.layoutParams.height = mLatticeHeight / 2
        rootView.tv_bottom.layoutParams.height = mLatticeHeight / 2

        rootView.rl_back.layoutParams.height = mLatticeHeight


        setMargins(rootView.tv_menu, 0, mLatticeHeight / 2, 0, 0)
        setMargins(rootView.rl_back, 0, mLatticeHeight / 2, 0, 0)

        setMargins(rootView.tv_setting, 0, mLatticeHeight / 2 + mLatticeHeight * 6, 0, 0)
        setMargins(rootView.tv_about, 0, mLatticeHeight / 2 + mLatticeHeight * 7, 0, 0)


    }

    private fun updateDividersPosition() {

        setMargins(rootView.v_divider_0, 0, mLatticeHeight / 2 - mDividerHeight / 2, 0, 0)
        setMargins(rootView.v_divider_1, 0, mLatticeHeight / 2 + mLatticeHeight - mDividerHeight / 2, 0, 0)
        setMargins(rootView.v_divider_2, 0, mLatticeHeight / 2 + mLatticeHeight * 2 - mDividerHeight / 2, 0, 0)
        setMargins(rootView.v_divider_3, 0, mLatticeHeight / 2 + mLatticeHeight * 3 - mDividerHeight / 2, 0, 0)
        setMargins(rootView.v_divider_4, 0, mLatticeHeight / 2 + mLatticeHeight * 4 - mDividerHeight / 2, 0, 0)
        setMargins(rootView.v_divider_5, 0, mLatticeHeight / 2 + mLatticeHeight * 5 - mDividerHeight / 2, 0, 0)
        setMargins(rootView.v_divider_6, 0, mLatticeHeight / 2 + mLatticeHeight * 6 - mDividerHeight / 2, 0, 0)
        setMargins(rootView.v_divider_7, 0, mLatticeHeight / 2 + mLatticeHeight * 7 - mDividerHeight / 2, 0, 0)
        setMargins(rootView.v_divider_8, 0, mLatticeHeight / 2 + mLatticeHeight * 8 - mDividerHeight / 2, 0, 0)
        setMargins(rootView.v_divider_9, 0, mLatticeHeight / 2 + mLatticeHeight * 8 - mDividerHeight / 2, 0, 0)



        rootView.tv_setting.bringToFront()
        rootView.v_divider_6.bringToFront()
        rootView.tv_about.bringToFront()
        rootView.v_divider_7.bringToFront()
        rootView.tv_bottom.bringToFront()
        rootView.v_divider_8.bringToFront()
        rootView.v_divider_9.bringToFront()
        rootView.tv_menu.bringToFront()
        rootView.rl_back.bringToFront()
        rootView.v_divider_1.bringToFront()
        rootView.tv_top.bringToFront()
        rootView.v_divider_0.bringToFront()

    }


    fun setMargins(v: View, l: Int, t: Int, r: Int, b: Int) {
        if (v.getLayoutParams() is ViewGroup.MarginLayoutParams) {
            val p = v.getLayoutParams() as ViewGroup.MarginLayoutParams
            p.setMargins(l, t, r, b)
            v.requestLayout()
        }
    }

}