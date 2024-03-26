package com.dafay.demo.anim.ui.frg

import android.content.Context
import android.graphics.PixelFormat
import android.transition.*
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.FrameLayout
import com.dafay.demo.anim.R
import com.dafay.demo.anim.ui.weight.GravityArcMotion
import com.dafay.demo.anim.ui.weight.RoundImageView
import com.dafay.demo.anim.utils.ViewUtils
import com.idea.library.richeditor.utils.DpUtils
import kotlinx.android.synthetic.main.frg_test_image_change.view.*


/**
 * Created by  idea on 2019-12-20$ 16:34$
 * des:
 */
class TestFrescoFragment : BaseFragment() {

    override fun getLayoutId(): Int {
        return R.layout.frg_test_image_change
    }

    override fun onInitViews() {

        rootView.iv_img2.setOnClickListener({
            changeTwoImage(rootView.iv_img1, rootView.iv_img2)
        })

    }

    private val windowManager: WindowManager by lazy {
        context?.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    }

    private fun changeTwoImage(ivImg1: RoundImageView, ivImg2: RoundImageView) {
        if (ivImg1.drawable == null || ivImg2.drawable == null) {
            return
        }

        // 1.根据原来的两个ImageView 复制出来两个imageview
        var copyImg1 = copyImageView(ivImg1)
        var copyImg2 = copyImageView(ivImg2)

        var containerView = FrameLayout(context)
        containerView.layoutParams = ViewGroup.MarginLayoutParams(
            DpUtils.screenWidth(context!!),
            DpUtils.screenHeight(context!!)
        )
//        containerView.setBackgroundColor(Color.parseColor("#30FF0000"))

        if (copyImg1 == null || copyImg2 == null) {
            return
        }

        var wmParamContainer = initParams(containerView)

        if (wmParamContainer == null) {
            return
        }

        wmParamContainer.width = DpUtils.screenWidth(context!!)
        wmParamContainer.height = DpUtils.screenHeight(context!!)
        wmParamContainer.x = 0
        wmParamContainer.y = 0
        windowManager.addView(containerView, wmParamContainer)


        containerView.addView(copyImg1)
        containerView.addView(copyImg2)

        var paramImg1 = ivImg1.layoutParams
        var paramImg2 = ivImg2.layoutParams

        var drawable1 = ivImg1.drawable
        var drawable2 = ivImg2.drawable

        var copyImg1Location: IntArray = intArrayOf(0, 0)
        ivImg1.getLocationOnScreen(copyImg1Location)
        ViewUtils.setMargins(copyImg1, copyImg1Location[0], copyImg1Location[1], 0, 0)

        var copyImg2Location: IntArray = intArrayOf(0, 0)
        ivImg2.getLocationOnScreen(copyImg2Location)
        ViewUtils.setMargins(copyImg2, copyImg2Location[0], copyImg2Location[1], 0, 0)


        // 2.复制出来的imageview 显示，原来的 imageview visible=gone
        // 3.复制出来的imageview 开始动画
        // 4.动画结束后 原来的 imageview 显示，设置新的图片，复制的 imageview 移除

        var transitionSet = TransitionSet()
            .addTransition(ChangeBounds().apply {
                pathMotion = GravityArcMotion().apply {
                    maximumAngle=90f
                }
                addTarget(copyImg1)
            })
            .addTransition(ChangeBounds().apply {
                pathMotion = ArcMotion().apply {
                    maximumAngle = 90f
                }
                addTarget(copyImg2)
            })
            .addTransition(ChangeImageTransform())
            .addTransition(ChangeTransform())
            .addListener(object : Transition.TransitionListener {
                override fun onTransitionEnd(transition: Transition?) {
                    ivImg1.visibility = View.VISIBLE
                    ivImg1.setImageDrawable(drawable2)

                    ivImg2.visibility = View.VISIBLE
                    ivImg2.setImageDrawable(drawable1)

                    ivImg1.post({
                        containerView.removeAllViews()
                        windowManager.removeView(containerView)
                    })

                }

                override fun onTransitionResume(transition: Transition?) {
                }

                override fun onTransitionPause(transition: Transition?) {
                }

                override fun onTransitionCancel(transition: Transition?) {
                }

                override fun onTransitionStart(transition: Transition?) {
                    ivImg1.visibility = View.GONE
                    ivImg2.visibility = View.GONE
                }

            })
        transitionSet.setDuration(400)
        containerView.post {
            TransitionManager.beginDelayedTransition(containerView, transitionSet)
            copyImg1.layoutParams.width = paramImg2.width
            copyImg1.layoutParams.height = paramImg2.height
            copyImg2.layoutParams.width = paramImg1.width
            copyImg2.layoutParams.height = paramImg1.height
            ViewUtils.setMargins(copyImg1, copyImg2Location[0], copyImg2Location[1], 0, 0)
            ViewUtils.setMargins(copyImg2, copyImg1Location[0], copyImg1Location[1], 0, 0)
        }
    }

    private fun initParams(view: View): WindowManager.LayoutParams? {
        val layoutParams = WindowManager.LayoutParams()
        layoutParams.token = view.getWindowToken()
        layoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION // 程序提示window
        layoutParams.format = PixelFormat.TRANSLUCENT // 支持透明
        layoutParams.flags =
            WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL or WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS or
                    WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN or WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH
        layoutParams.gravity = Gravity.LEFT or Gravity.TOP
        return layoutParams
    }

    private fun copyImageView(imageView: RoundImageView): RoundImageView? {
        var roundImageView = RoundImageView(
            imageView.context,
            5f * imageView.context.resources.displayMetrics.density
        )
        roundImageView.layoutParams = imageView.layoutParams
        roundImageView.scaleType = imageView.scaleType
        roundImageView.setImageDrawable(imageView.drawable)
        return roundImageView
    }


    private fun changeTwoImage1(ivImg1: RoundImageView, ivImg2: RoundImageView) {
        if (ivImg1.drawable == null || ivImg2.drawable == null) {
            return
        }

        // 1.根据原来的两个ImageView 复制出来两个imageview
        var copyImg1 = copyImageView(ivImg1)
        var copyImg2 = copyImageView(ivImg2)

        var containerView = FrameLayout(context)
        containerView.layoutParams.width = DpUtils.screenWidth(context!!)
        containerView.layoutParams.height = DpUtils.screenHeight(context!!)

        if (copyImg1 == null || copyImg2 == null) {
            return
        }

        var wmParamContainer = initParams(containerView)


        var wmParam1 = initParams(copyImg1)
        var wmParam2 = initParams(copyImg2)

        if (wmParam1 == null || wmParam2 == null) {
            return
        }

        wmParam1.width = ivImg1.width
        wmParam1.height = ivImg1.height

        wmParam2.width = ivImg2.width
        wmParam2.height = ivImg2.height


        var copyImg1Location: IntArray = intArrayOf(0, 0)
        ivImg1.getLocationOnScreen(copyImg1Location)
        wmParam1.x = copyImg1Location[0] //窗口位置的偏移量
        wmParam1.y = copyImg1Location[1]

        var copyImg2Location: IntArray = intArrayOf(0, 0)
        ivImg2.getLocationOnScreen(copyImg2Location)
        wmParam2.x = copyImg2Location[0] //窗口位置的偏移量
        wmParam2.y = copyImg2Location[1]


        windowManager.addView(copyImg1, wmParam1)
        windowManager.addView(copyImg2, wmParam2)


        // 2.复制出来的imageview 显示，原来的 imageview visible=gone

        var transitionSet = TransitionSet()
            .addTransition(ChangeBounds().apply {
                pathMotion = ArcMotion().apply {
                    minimumHorizontalAngle = 90f
                }
            })
            .addTransition(ChangeImageTransform())
            .addTransition(ChangeTransform())

        TransitionManager.beginDelayedTransition(containerView, transitionSet)


        // 3.复制出来的imageview 开始动画

        // 4.动画结束后 原来的 imageview 显示，设置新的图片，复制的 imageview 移除


    }


}