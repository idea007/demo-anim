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
import com.idea.library.richeditor.utils.DpUtils
import kotlinx.android.synthetic.main.frg_scene_3.view.*


/**
 * Created by  idea on 2019-11-23$ 10:52$
 * des: 目录
 */
class Scene3Fragment : BaseFragment() {

    override fun getLayoutId(): Int {
        return R.layout.frg_scene_3
    }

    override fun onInitViews() {
        initView()
        bindListener()
    }

    private var mViewWidth: Int = 0
    private var mViewHeight: Int = 0

    // 格子的高度
    private var mLatticeHeight = 0
    // 分割线的高度
    private var mDividerHeight = 0


    private lateinit var transitionSet: TransitionSet

    private fun initView() {

        mViewWidth = 1080
        mViewHeight = 2240

        mLatticeHeight = mViewHeight / 9
        mDividerHeight = DpUtils.dp2px(context!!, 3f) / 2 * 2

        updateLatticesPosition()

        transitionSet = TransitionSet()
            .addTransition(ChangeBounds().apply {
                duration = DURATION_TIME
            })

        var slide1 = Slide()
        slide1.slideEdge = Gravity.LEFT
        slide1.duration = DURATION_TIME
        slide1.addTarget(mRootView.tv_menu)
        transitionSet.addTransition(slide1)

        var slide2 = Slide()
        slide2.slideEdge = Gravity.RIGHT
        slide2.duration = DURATION_TIME
        slide2.addTarget(mRootView.iv_back)
        transitionSet.addTransition(slide2)


    }

    val DURATION_TIME: Long = 300
    private fun bindListener() {

        mRootView.rl_lattice_4.setOnClickListener(View.OnClickListener {


            TransitionManager.beginDelayedTransition(mRootView.rl_container, transitionSet)

            mRootView.tv_menu.visibility = View.GONE
            mRootView.iv_back.visibility = View.VISIBLE


            setMargins(mRootView.rl_lattice_1, 0, mLatticeHeight / 2 + mLatticeHeight * 1 - mLatticeHeight * 3, 0, 0)
            setMargins(mRootView.rl_lattice_2, 0, mLatticeHeight / 2 + mLatticeHeight * 2 - mLatticeHeight * 3, 0, 0)
            setMargins(mRootView.rl_lattice_3, 0, mLatticeHeight / 2 + mLatticeHeight * 3 - mLatticeHeight * 3, 0, 0)
            setMargins(mRootView.rl_lattice_4, 0, mLatticeHeight / 2 + mLatticeHeight * 4 - mLatticeHeight * 3, 0, 0)
            setMargins(mRootView.rl_lattice_5, 0, mLatticeHeight / 2 + mLatticeHeight * 5 + mLatticeHeight * 3, 0, 0)
            setMargins(mRootView.rl_lattice_6, 0, mLatticeHeight / 2 + mLatticeHeight * 6 + mLatticeHeight * 3, 0, 0)
            setMargins(mRootView.rl_lattice_7, 0, mLatticeHeight / 2 + mLatticeHeight * 7 + mLatticeHeight * 3, 0, 0)

        })


        mRootView.rl_lattice_5.setOnClickListener(View.OnClickListener {


            TransitionManager.beginDelayedTransition(mRootView.rl_container, transitionSet)

            mRootView.tv_menu.visibility = View.GONE
            mRootView.iv_back.visibility = View.VISIBLE


            setMargins(mRootView.rl_lattice_1, 0, mLatticeHeight / 2 + mLatticeHeight * 1 - mLatticeHeight * 4, 0, 0)
            setMargins(mRootView.rl_lattice_2, 0, mLatticeHeight / 2 + mLatticeHeight * 2 - mLatticeHeight * 4, 0, 0)
            setMargins(mRootView.rl_lattice_3, 0, mLatticeHeight / 2 + mLatticeHeight * 3 - mLatticeHeight * 4, 0, 0)
            setMargins(mRootView.rl_lattice_4, 0, mLatticeHeight / 2 + mLatticeHeight * 4 - mLatticeHeight * 4, 0, 0)
            setMargins(mRootView.rl_lattice_5, 0, mLatticeHeight / 2 + mLatticeHeight * 5 - mLatticeHeight * 4, 0, 0)

            setMargins(mRootView.rl_lattice_6, 0, mLatticeHeight / 2 + mLatticeHeight * 6 + mLatticeHeight * 2, 0, 0)
            setMargins(mRootView.rl_lattice_7, 0, mLatticeHeight / 2 + mLatticeHeight * 7 + mLatticeHeight * 2, 0, 0)


        })

        mRootView.rl_lattice_6.setOnClickListener(View.OnClickListener {

            TransitionManager.beginDelayedTransition(mRootView.rl_container, transitionSet)

            mRootView.tv_menu.visibility = View.GONE
            mRootView.iv_back.visibility = View.VISIBLE


            setMargins(mRootView.rl_lattice_1, 0, mLatticeHeight / 2 + mLatticeHeight * 1 - mLatticeHeight * 5, 0, 0)
            setMargins(mRootView.rl_lattice_2, 0, mLatticeHeight / 2 + mLatticeHeight * 2 - mLatticeHeight * 5, 0, 0)
            setMargins(mRootView.rl_lattice_3, 0, mLatticeHeight / 2 + mLatticeHeight * 3 - mLatticeHeight * 5, 0, 0)
            setMargins(mRootView.rl_lattice_4, 0, mLatticeHeight / 2 + mLatticeHeight * 4 - mLatticeHeight * 5, 0, 0)
            setMargins(mRootView.rl_lattice_5, 0, mLatticeHeight / 2 + mLatticeHeight * 5 - mLatticeHeight * 5, 0, 0)
            setMargins(mRootView.rl_lattice_6, 0, mLatticeHeight / 2 + mLatticeHeight * 6 - mLatticeHeight * 5, 0, 0)

            setMargins(mRootView.rl_lattice_7, 0, mLatticeHeight / 2 + mLatticeHeight * 7 + mLatticeHeight * 1, 0, 0)


        })

        mRootView.rl_lattice_7.setOnClickListener(View.OnClickListener {


            TransitionManager.beginDelayedTransition(mRootView.rl_container, transitionSet)

            mRootView.tv_menu.visibility = View.GONE
            mRootView.iv_back.visibility = View.VISIBLE

            setMargins(mRootView.rl_lattice_1, 0, mLatticeHeight / 2 + mLatticeHeight * 1 - mLatticeHeight * 6, 0, 0)
            setMargins(mRootView.rl_lattice_2, 0, mLatticeHeight / 2 + mLatticeHeight * 2 - mLatticeHeight * 6, 0, 0)
            setMargins(mRootView.rl_lattice_3, 0, mLatticeHeight / 2 + mLatticeHeight * 3 - mLatticeHeight * 6, 0, 0)
            setMargins(mRootView.rl_lattice_4, 0, mLatticeHeight / 2 + mLatticeHeight * 4 - mLatticeHeight * 6, 0, 0)
            setMargins(mRootView.rl_lattice_5, 0, mLatticeHeight / 2 + mLatticeHeight * 5 - mLatticeHeight * 6, 0, 0)
            setMargins(mRootView.rl_lattice_6, 0, mLatticeHeight / 2 + mLatticeHeight * 6 - mLatticeHeight * 6, 0, 0)
            setMargins(mRootView.rl_lattice_7, 0, mLatticeHeight / 2 + mLatticeHeight * 7 - mLatticeHeight * 6, 0, 0)


        })


        mRootView.rl_lattice_0.setOnClickListener(View.OnClickListener {

            if (mRootView.tv_menu.visibility == View.GONE) {


                TransitionManager.beginDelayedTransition(mRootView.rl_container, transitionSet)

                mRootView.tv_menu.visibility = View.VISIBLE
                mRootView.iv_back.visibility = View.GONE

                setMargins(mRootView.rl_lattice_0, 0, mLatticeHeight / 2, 0, 0)
                setMargins(mRootView.rl_lattice_1, 0, mLatticeHeight / 2 + mLatticeHeight * 1, 0, 0)
                setMargins(mRootView.rl_lattice_2, 0, mLatticeHeight / 2 + mLatticeHeight * 2, 0, 0)
                setMargins(mRootView.rl_lattice_3, 0, mLatticeHeight / 2 + mLatticeHeight * 3, 0, 0)
                setMargins(mRootView.rl_lattice_4, 0, mLatticeHeight / 2 + mLatticeHeight * 4, 0, 0)
                setMargins(mRootView.rl_lattice_5, 0, mLatticeHeight / 2 + mLatticeHeight * 5, 0, 0)
                setMargins(mRootView.rl_lattice_6, 0, mLatticeHeight / 2 + mLatticeHeight * 6, 0, 0)
                setMargins(mRootView.rl_lattice_7, 0, mLatticeHeight / 2 + mLatticeHeight * 7, 0, 0)

            }
        })

    }

    private fun updateLatticesPosition() {


        mRootView.rl_lattice_0.layoutParams.height = mLatticeHeight
        mRootView.rl_lattice_1.layoutParams.height = mLatticeHeight
        mRootView.rl_lattice_2.layoutParams.height = mLatticeHeight
        mRootView.rl_lattice_3.layoutParams.height = mLatticeHeight
        mRootView.rl_lattice_4.layoutParams.height = mLatticeHeight
        mRootView.rl_lattice_5.layoutParams.height = mLatticeHeight
        mRootView.rl_lattice_6.layoutParams.height = mLatticeHeight
        mRootView.rl_lattice_7.layoutParams.height = mLatticeHeight

        mRootView.rl_lattice_top.layoutParams.height = mLatticeHeight / 2
        mRootView.rl_lattice_boottom.layoutParams.height = mLatticeHeight / 2 + mDividerHeight * 4


        setMargins(mRootView.rl_lattice_0, 0, mLatticeHeight / 2, 0, 0)
        setMargins(mRootView.rl_lattice_1, 0, mLatticeHeight / 2 + mLatticeHeight * 1, 0, 0)
        setMargins(mRootView.rl_lattice_2, 0, mLatticeHeight / 2 + mLatticeHeight * 2, 0, 0)
        setMargins(mRootView.rl_lattice_3, 0, mLatticeHeight / 2 + mLatticeHeight * 3, 0, 0)
        setMargins(mRootView.rl_lattice_4, 0, mLatticeHeight / 2 + mLatticeHeight * 4, 0, 0)
        setMargins(mRootView.rl_lattice_5, 0, mLatticeHeight / 2 + mLatticeHeight * 5, 0, 0)
        setMargins(mRootView.rl_lattice_6, 0, mLatticeHeight / 2 + mLatticeHeight * 6, 0, 0)
        setMargins(mRootView.rl_lattice_7, 0, mLatticeHeight / 2 + mLatticeHeight * 7, 0, 0)

        setMargins(mRootView.rl_lattice_boottom, 0, mLatticeHeight / 2 + mLatticeHeight * 8 - mDividerHeight * 2, 0, 0)

    }


    fun setMargins(v: View, l: Int, t: Int, r: Int, b: Int) {
        if (v.getLayoutParams() is ViewGroup.MarginLayoutParams) {
            val p = v.getLayoutParams() as ViewGroup.MarginLayoutParams
            p.setMargins(l, t, r, b)
            v.requestLayout()
        }
    }


}