package com.dafay.demo.anim.ui.anim.share

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Matrix
import android.graphics.Rect
import android.graphics.RectF
import android.os.Bundle
import android.os.Parcelable
import android.transition.*
import android.util.TypedValue
import android.view.View
import androidx.core.app.SharedElementCallback
import androidx.core.view.ViewCompat
import com.dafay.demo.anim.R
import com.dafay.demo.anim.ui.anim.custom.TextResize
import com.dafay.demo.anim.utils.LogUtils
import com.dafay.demo.lib.base.base.BaseActivity
import kotlinx.android.synthetic.main.act_share_element_0_b.*

/**
 * Created by  idea on 2019-11-27$ 14:47$
 * des:
 */
class ShareElement0BActivity : BaseActivity() {

    companion object {
        val EXTRA_IMG_RESOURCE = "extra_img_resource"
        val EXTRA_TEXTSIZE = "extra_textsize"
        val EXTRA_TEXTCOLOR = "extra_textcolor"
        val EXTRA_TEXT = "extra_text"
        val EXTRA_TEXT_PADDING = "extra_text_padding"
    }

    private var targetTextSize: Float = 0f
    private var targetTextColors: ColorStateList? = null
    private var targetTextPadding: Rect? = null

    override fun getLayoutId(bundle: Bundle?): Int {
        return R.layout.act_share_element_0_b
    }

    override fun onInitViews() {
        initView()
        setUpTransition()
        LogUtils.w("------B onCreate()")
    }

    private fun initView() {
        tv_text.setTextSize(30f)
        tv_text.setTextColor(Color.RED)
        tv_text.text = intent.getStringExtra(EXTRA_TEXT)
        iv_image.setImageResource(intent.getIntExtra(EXTRA_IMG_RESOURCE, R.mipmap.img_10))
    }


    private fun setUpTransition() {
        ViewCompat.setTransitionName(iv_image, "iv_image")
        ViewCompat.setTransitionName(tv_text, "tv_text")
        val changeBounds = ChangeBounds()
        val changeImageTransform = ChangeImageTransform()
        val textResize = TextResize()
        textResize.addTarget(tv_text)
        val transitionSet = TransitionSet()
        transitionSet.addTransition(changeBounds)
        transitionSet.addTransition(changeImageTransform)
        transitionSet.addTransition(textResize)
        transitionSet.duration = 1000
        window.sharedElementEnterTransition = transitionSet

        this.setEnterSharedElementCallback(object : SharedElementCallback() {

            /**
             * 最先调用，用于动画开始前替换 ShareElements ，比如在Activity B 翻过若干页大图之后，返回 Activity A
             * 的时候需要缩小回到对应的位置图片，就需要在这里进行替换
             */
            override fun onMapSharedElements(
                names: List<String>,
                sharedElements: Map<String, View>
            ) {
                for (name in names) {
                    LogUtils.w("------B onMapSharedElements name=$name")
                }
            }

            /**
             * 表示ShareElement已经全部就位，可以开始动画了
             */
            override fun onSharedElementsArrived(
                sharedElementNames: MutableList<String>?,
                sharedElements: MutableList<View>?,
                listener: OnSharedElementsReadyListener?
            ) {
                super.onSharedElementsArrived(sharedElementNames, sharedElements, listener)
                LogUtils.d("------B onSharedElementsArrived")
            }

            /**
             * 在之前的步骤里 (onMapSharedElements) 被从 ShareElements 列表里除掉的 View 会在此回调，
             * 不处理的话默认进行 alpha 动画消失
             */
            override fun onRejectSharedElements(rejectedSharedElements: MutableList<View>?) {
                super.onRejectSharedElements(rejectedSharedElements)
                LogUtils.d("------B onRejectSharedElements")
            }

            /**
             *在这里会把 ShareElement 里值得记录的信息存到为 Parcelable 格式，以发送到Activity B
             *默认处理规则是 ImageView 会特殊记录 Bitmap、ScaleType、Matrix，其它View只记录大小和位置
             */
            override fun onCaptureSharedElementSnapshot(
                sharedElement: View?,
                viewToGlobalMatrix: Matrix?,
                screenBounds: RectF?
            ): Parcelable {
                return super.onCaptureSharedElementSnapshot(
                    sharedElement,
                    viewToGlobalMatrix,
                    screenBounds
                )
            }

            /**
             *在这里会把 Activity A 传过来的 Parcelable 数据，重新生成一个View，这个 View 的大小和位置会与 Activity A 里的
             *ShareElement 一致，
             */
            override fun onCreateSnapshotView(context: Context?, snapshot: Parcelable?): View {
                LogUtils.d("------B onCreateSnapshotView")
                return super.onCreateSnapshotView(context, snapshot)
            }


            override fun onSharedElementStart(
                sharedElementNames: MutableList<String>?,
                sharedElements: MutableList<View>?,
                sharedElementSnapshots: MutableList<View>?
            ) {
                LogUtils.d("------B onSharedElementStart")

                targetTextSize = tv_text.textSize
                targetTextColors = tv_text.textColors
                targetTextPadding = Rect(
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
            }

            override fun onSharedElementEnd(
                sharedElementNames: MutableList<String>?,
                sharedElements: MutableList<View>?,
                sharedElementSnapshots: MutableList<View>?
            ) {
                LogUtils.d("------B onSharedElementEnd")
                tv_text.setTextSize(TypedValue.COMPLEX_UNIT_PX, targetTextSize)
                tv_text.setTextColor(targetTextColors)
                tv_text.setPadding(
                    targetTextPadding?.left!!, targetTextPadding?.top!!,
                    targetTextPadding?.right!!, targetTextPadding?.bottom!!
                )
                tv_text.requestLayout()
                forceSharedElementLayout(rl_container)
            }
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

    override fun onResume() {
        super.onResume()
        LogUtils.w("------B onResume()")
    }

    override fun onPause() {
        super.onPause()
        LogUtils.w("------B onPause()")
    }

    override fun onDestroy() {
        super.onDestroy()
        LogUtils.w("------B onDestroy()")
    }

}