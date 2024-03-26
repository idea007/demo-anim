package com.dafay.demo.anim.ui.anim.share

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Rect
import android.os.Bundle
import android.transition.ChangeBounds
import android.transition.TransitionSet
import android.util.TypedValue
import android.view.View
import android.view.ViewTreeObserver
import androidx.core.app.SharedElementCallback
import com.dafay.demo.anim.R
import com.dafay.demo.anim.ui.anim.custom.ChangeTextTransition
import com.dafay.demo.anim.ui.anim.custom.TextResize
import com.dafay.demo.anim.utils.LogUtils
import com.dafay.demo.lib.base.base.BaseActivity
import kotlinx.android.synthetic.main.act_share_element_1_b.*

/**
 * Created by  idea on 2019-11-27$ 14:47$
 * des:
 */
class ShareElement1BActivity : BaseActivity() {

    companion object {
        val EXTRA_TEXTSIZE = "extra_textsize"
        val EXTRA_TEXTCOLOR = "extra_textcolor"
        val EXTRA_TEXT = "extra_text"

        val EXTRA_TEXT_PADDING = "extra_text_padding"


        val EXTRA_TEXTSIZE_1 = "extra_textsize_1"
        val EXTRA_TEXTCOLOR_1 = "extra_textcolor_1"
        val EXTRA_TEXT_1 = "extra_text_1"
        val EXTRA_TEXT_PADDING_1 = "extra_text_padding_1"

    }


    private var targetTextSize: Float = 0f
    private var targetTextColors: ColorStateList? = null
    private var targetPadding: Rect? = null


    override fun getLayoutId(bundle: Bundle?): Int {
        return R.layout.act_share_element_1_b
    }

    override fun onInitViews() {


        initView()
        test()
        setUpTransition()

    }


    private fun initView() {
        tv_text.text = intent.getStringExtra(EXTRA_TEXT)

        tv_text.setTag("tag_tv_text")

        tv_text.post(Runnable {
            LogUtils.w("tv_text.width=" + tv_text.width + " tv_text.height=" + tv_text.height)
        })


    }

    private fun forceSharedElementLayout(view: View) {
        val widthSpec = View.MeasureSpec.makeMeasureSpec(
            view.width,
            View.MeasureSpec.EXACTLY
        )
        val heightSpec = View.MeasureSpec.makeMeasureSpec(
            view.height,
            View.MeasureSpec.EXACTLY
        )
        view.measure(widthSpec, heightSpec)
        view.layout(view.left, view.top, view.right, view.bottom)
    }


    private fun setUpTransition() {

        val changeBounds = ChangeBounds()
        changeBounds.addTarget(tv_text)
        changeBounds.addTarget(tv_text_1)

        val textResize = TextResize()
        textResize.addTarget(tv_text)


        var startTextSize = intent.getFloatExtra(EXTRA_TEXTSIZE_1, 0f)
        var startTextColor = intent.getIntExtra(EXTRA_TEXTCOLOR_1, Color.BLACK)
        val myChangeTextTransition =
            ChangeTextTransition(
                startTextSize,
                tv_text_1.textSize,
                startTextColor,
                tv_text_1.currentTextColor
            )
        myChangeTextTransition.addTarget(tv_text_1)

        val transitionSet = TransitionSet()
        transitionSet.addTransition(changeBounds)
        transitionSet.addTransition(textResize)
        transitionSet.addTransition(myChangeTextTransition)
        transitionSet.duration = 5000
        window.sharedElementEnterTransition = transitionSet


        this.setEnterSharedElementCallback(object : SharedElementCallback() {

            override fun onSharedElementStart(
                sharedElementNames: MutableList<String>?,
                sharedElements: MutableList<View>?,
                sharedElementSnapshots: MutableList<View>?
            ) {

                LogUtils.w("onSharedElementStart")

                targetTextSize = tv_text.textSize
                targetTextColors = tv_text.textColors
                targetPadding = Rect(
                    tv_text.getPaddingLeft(),
                    tv_text.getPaddingTop(),
                    tv_text.getPaddingRight(),
                    tv_text.getPaddingBottom()
                )


                tv_text.setTextColor(intent.getIntExtra(EXTRA_TEXTCOLOR, Color.BLACK))
                var textSize = intent.getFloatExtra(EXTRA_TEXTSIZE, targetTextSize)
                tv_text.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize)
                val padding = intent.getParcelableExtra<Rect>(EXTRA_TEXT_PADDING)
                tv_text.setPadding(padding!!.left, padding.top, padding.right, padding.bottom)

                LogUtils.w("tv_text1.width=" + tv_text.width + " tv_text1.height=" + tv_text.height)

            }

            override fun onSharedElementEnd(
                sharedElementNames: MutableList<String>?,
                sharedElements: MutableList<View>?,
                sharedElementSnapshots: MutableList<View>?
            ) {

                LogUtils.w("onSharedElementEnd")


                tv_text.setTextSize(TypedValue.COMPLEX_UNIT_PX, targetTextSize)
                tv_text.setTextColor(targetTextColors)
                tv_text.setPadding(
                    targetPadding?.left!!, targetPadding?.top!!,
                    targetPadding?.right!!, targetPadding?.bottom!!
                )
                tv_text.requestLayout()

                forceSharedElementLayout(rl_container)

                LogUtils.w("tv_text1.width=" + tv_text.width + " tv_text1.height=" + tv_text.height)

                myChangeTextTransition.setIsEnter(false)

            }


            override fun onMapSharedElements(names: List<String>, sharedElements: Map<String, View>) {
                LogUtils.w("onMapSharedElements")
            }
        })


    }


    private fun test() {
        rl_container.post(object : Runnable {
            override fun run() {
                val viewWidth = rl_container.width
                val viewHeight = rl_container.height

                LogUtils.d("post() $viewWidth $viewHeight")
            }
        })

        val observer = rl_container.getViewTreeObserver()
        observer.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
            override fun onPreDraw(): Boolean {


                val viewWidth = rl_container.width
                val viewHeight = rl_container.height

                LogUtils.d("onPreDraw() $viewWidth $viewHeight")
                return true
            }

        })

        observer.addOnDrawListener(object : ViewTreeObserver.OnDrawListener {
            override fun onDraw() {

                val viewWidth = rl_container.width
                val viewHeight = rl_container.height

                LogUtils.d("onDraw() $viewWidth $viewHeight")
            }
        })

        observer.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {

                val viewWidth = rl_container.width
                val viewHeight = rl_container.height

                LogUtils.d("onGlobalLayout() $viewWidth $viewHeight")
            }
        })

    }


}