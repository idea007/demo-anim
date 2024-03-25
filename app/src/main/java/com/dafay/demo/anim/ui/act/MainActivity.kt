package com.dafay.demo.anim.ui.act

import android.animation.ValueAnimator
import android.content.Intent
import android.os.Bundle
import android.view.Choreographer
import com.idea.android.animandtran.R
import com.dafay.demo.anim.ui.anim.motion.Motion0Fragment
import com.dafay.demo.anim.ui.anim.motion.Motion1Fragment
import com.dafay.demo.anim.ui.anim.motion.Motion2Fragment
import com.dafay.demo.anim.ui.anim.objectanim.ObjectAnimator0Fragment
import com.dafay.demo.anim.ui.anim.scene.Scene2Fragment
import com.dafay.demo.anim.ui.anim.scene.Scene1Fragment
import com.dafay.demo.anim.ui.anim.share.*
import com.dafay.demo.anim.ui.anim.tran.TranChangeBounds0Fragment
import com.dafay.demo.anim.ui.anim.valueanim.ValueAnimator0Fragment
import com.dafay.demo.anim.ui.frg.*
import com.dafay.demo.anim.ui.frg.snap.TestRecyclerViewFlingFragment
import com.dafay.demo.anim.ui.weight.ExpandableDirLayout
import com.dafay.demo.anim.utils.LogUtils
import com.idea.android.duanzirobot.BaseActivity
import kotlinx.android.synthetic.main.act_main.*
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : BaseActivity() {


    override fun getLayoutId(bundle: Bundle?): Int {
        return R.layout.act_main
    }

    override fun onInitViews() {
        initGuide()
    }


    private fun initGuide() {
        addObjectAnimatorColumn()
        addValueAnimatorColumn()
        addTransitionColumn()
        addMotionLayoutColumn()
        addOtherTestColumn()
    }

    private fun addOtherTestColumn() {
        var expandableDirLayout5 = ExpandableDirLayout(this)
        expandableDirLayout5.setTitle("Test")
        expandableDirLayout5.addChildView("TestGif", "", {
            HostActivity.startHostActWithAFrg(this, TestGifPlayFragment())
        })

        expandableDirLayout5.addChildView("TestImageMatrix", "", {
            HostActivity.startHostActWithAFrg(this, TestImageMatrixFragment())
        })

        expandableDirLayout5.addChildView("动画执行过程中的跳转", "", {
            HostActivity.startHostActWithAFrg(
                this,
                TestAnimAbortFragment()
            )
        })

        expandableDirLayout5.addChildView("TestExpandView", "", {
            HostActivity.startHostActWithAFrg(
                this,
                TestExpandViewFragment()
            )
        })

        expandableDirLayout5.addChildView("CustomImageView", "", {
            HostActivity.startHostActWithAFrg(
                this,
                TestCustomImageFragment()
            )
        })

        expandableDirLayout5.addChildView("Image Change", "", {
            HostActivity.startHostActWithAFrg(
                this,
                TestImageChangeFragment()
            )
        })

        expandableDirLayout5.addChildView("RecyclerView fling", "", {
            HostActivity.startHostActWithAFrg(
                this,
                TestRecyclerViewFlingFragment()
            )
        })

        ll_guide_container.addView(expandableDirLayout5)

    }

    private fun addMotionLayoutColumn() {
        var expandableDirLayout4 = ExpandableDirLayout(this)
        expandableDirLayout4.setTitle("MotionLayout")
        expandableDirLayout4.addChildView("MotionLayout:0", "", {
            HostActivity.startHostActWithAFrg(this, Motion0Fragment())
        })
        expandableDirLayout4.addChildView("MotionLayout:1", "", {
            HostActivity.startHostActWithAFrg(this, Motion1Fragment())
        })

        expandableDirLayout4.addChildView("MotionLayout:2", "", {
            HostActivity.startHostActWithAFrg(this, Motion2Fragment())
        })
        ll_guide_container.addView(expandableDirLayout4)

    }

    private fun addTransitionColumn() {
        var expandableDirLayout3 = ExpandableDirLayout(this)
        expandableDirLayout3.setTitle("Transition")
        expandableDirLayout3.addChildView("Scene: TransitionManager.go()", "", {
            HostActivity.startHostActWithAFrg(this, Scene1Fragment())
        })

        expandableDirLayout3.addChildView("Scene: beginDelayedTransition()", "", {
            HostActivity.startHostActWithAFrg(this, Scene2Fragment())
        })

        expandableDirLayout3.addChildView("Transition:", "Transition 常用子类", {

            HostActivity.startHostActWithAFrg(
                this,
                TranChangeBounds0Fragment()
            )
        })

        expandableDirLayout3.addChildView("Share Elements 0", "共享动画：ImageView & TextView", {
            startActivity(Intent(this@MainActivity, ShareElement0AActivity::class.java))
        })

        expandableDirLayout3.addChildView("Share Elements 2", "共享动画：状态栏和导航栏的处理", {
            startActivity(Intent(this@MainActivity, ShareElement2AActivity::class.java))
        })

        expandableDirLayout3.addChildView("Share Elements 3", "共享动画：RecyclerView to ViewPager", {
            startActivity(Intent(this@MainActivity, ShareElement3AActivity::class.java))
        })

        expandableDirLayout3.addChildView("Share Elements 4", "共享动画：Gif 共享到新页面继续播放", {
            startActivity(Intent(this@MainActivity, ShareElement4AActivity::class.java))
        })

        ll_guide_container.addView(expandableDirLayout3)
    }

    private fun addValueAnimatorColumn() {

        var expandableDirLayout = ExpandableDirLayout(this)
        expandableDirLayout.setTitle("ValueAnimator")
        expandableDirLayout.addChildView("ValueAnimator1",
            "ValueAnimator 原理",
            {
                HostActivity.startHostActWithAFrg(this, ValueAnimator0Fragment())
            })
        expandableDirLayout.addChildView("ValueAnimator2", "", {})
        ll_guide_container.addView(expandableDirLayout)


    }

    private fun addObjectAnimatorColumn() {
        var expandableDirLayout = ExpandableDirLayout(this)
        expandableDirLayout.setTitle("ObjectAnimator")
        expandableDirLayout.addChildView("ObjectAnimator1",
            "ObjectAnimator 常规用法",
            {
                HostActivity.startHostActWithAFrg(this, ObjectAnimator0Fragment())
            })
        ll_guide_container.addView(expandableDirLayout)
    }


    val MM_SS_SECOND = "HH:mm:ss"
    val sdf = SimpleDateFormat(MM_SS_SECOND)
    var preSecond = -1
    var currentSecond = 0
    var frameIndex = 0
    var preFrameTime: Long = 0

    private fun postNextFrame(choreographer: Choreographer) {
        choreographer.postFrameCallback {

            var calendar = Calendar.getInstance()
            currentSecond = calendar.get(Calendar.SECOND)

            if (preSecond != currentSecond) {
                preSecond = currentSecond

                LogUtils.w("-" + sdf.format(calendar.time) + " 当前秒 共 $frameIndex 帧 ")

                frameIndex = 0
            }
            frameIndex++

            LogUtils.w(sdf.format(calendar.time) + " 打印 doFrame 第 $frameIndex 帧 frameTime=" + (it - preFrameTime))

            postNextFrame(choreographer)
            preFrameTime = it
        }

    }

    private fun printFrame() {

        val display = windowManager.defaultDisplay
        LogUtils.i("手机 refreshRate =${display.refreshRate}")
        var choreographer = Choreographer.getInstance()
        postNextFrame(choreographer)


    }

    /**
     * 通过 anim 打印帧率
     */
    private fun printFrameByAnim() {
        val MM_SS_SECOND = "HH:mm:ss"
        val sdf = SimpleDateFormat(MM_SS_SECOND)
        var preSecond = -1
        var currentSecond = 0
        var frameIndex = 0

        var anim = ValueAnimator
            .ofFloat(0f, 1f)
            .setDuration(24 * 60 * 60 * 1000)

        anim.addUpdateListener(object : ValueAnimator.AnimatorUpdateListener {
            override fun onAnimationUpdate(animation: ValueAnimator?) {

                var calendar = Calendar.getInstance()
                currentSecond = calendar.get(Calendar.SECOND)

                if (preSecond != currentSecond) {
                    preSecond = currentSecond
                    frameIndex = 0
                }
                frameIndex++

                LogUtils.w("当前时间：" + sdf.format(calendar.time) + " 打印 doFrame 第 $frameIndex 帧 ")

            }
        })

        anim.start()
    }

}
