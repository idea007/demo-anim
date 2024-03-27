package com.dafay.demo.anim.ui.anim.share

import android.app.SharedElementCallback
import android.content.Intent
import android.graphics.Color
import android.graphics.Matrix
import android.graphics.Rect
import android.graphics.RectF
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.widget.SeekBar
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import com.dafay.demo.anim.R
import com.dafay.demo.anim.utils.LogUtils
import com.dafay.demo.lib.base.base.BaseActivity
import com.idea.library.richeditor.utils.DpUtils
import kotlinx.android.synthetic.main.act_share_element_0_a.*

/**
 * Created by  idea on 2019-11-27$ 14:47$
 * des:
 */
class ShareElement0AActivity : BaseActivity() {
    override fun getLayoutId(bundle: Bundle?): Int {
        return R.layout.act_share_element_0_a
    }

    override fun onInitViews() {
        iv_image.setImageResource(R.mipmap.img_0)
        bindListener()
        setUpTransition()
    }

    private fun setUpTransition() {
        setExitSharedElementCallback(
            object : SharedElementCallback() {
                /**
                 *最先调用，用于动画开始前替换 ShareElements，比如在 Activity B翻过若干页大图之后，返回Activity A
                 *的时候需要缩小回到对应的小图，就需要在这里进行替换
                 */
                override fun onMapSharedElements(names: List<String>, sharedElements: MutableMap<String, View>) {
                    LogUtils.d("------A onMapSharedElements")
                }

                override fun onSharedElementStart(
                    sharedElementNames: MutableList<String>?,
                    sharedElements: MutableList<View>?,
                    sharedElementSnapshots: MutableList<View>?
                ) {
                    super.onSharedElementStart(sharedElementNames, sharedElements, sharedElementSnapshots)
                    LogUtils.d("------A onSharedElementStart")
                }

                override fun onSharedElementEnd(
                    sharedElementNames: MutableList<String>?,
                    sharedElements: MutableList<View>?,
                    sharedElementSnapshots: MutableList<View>?
                ) {
                    super.onSharedElementEnd(sharedElementNames, sharedElements, sharedElementSnapshots)
                    LogUtils.d("------A onSharedElementEnd")
                }

                override fun onCaptureSharedElementSnapshot(
                    sharedElement: View?,
                    viewToGlobalMatrix: Matrix?,
                    screenBounds: RectF?
                ): Parcelable {
                    LogUtils.d("------A onCaptureSharedElementSnapshot")
                    return super.onCaptureSharedElementSnapshot(sharedElement, viewToGlobalMatrix, screenBounds)

                }

                override fun onSharedElementsArrived(
                    sharedElementNames: MutableList<String>?,
                    sharedElements: MutableList<View>?,
                    listener: OnSharedElementsReadyListener?
                ) {
                    super.onSharedElementsArrived(sharedElementNames, sharedElements, listener)
                    LogUtils.d("------A onSharedElementsArrived")
                }
            })

    }

    private fun bindListener() {
        btn_click.setOnClickListener(View.OnClickListener {
            toActivityB()
        })
        sb_seekbar1.max = 200
        sb_seekbar1.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                var width = DpUtils.dp2px(this@ShareElement0AActivity, 100 + progress.toFloat())
                LogUtils.d("------ width=" + width)
                var params = iv_image.layoutParams
                params.width = width
                iv_image.layoutParams = params
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })

        sb_seekbar2.max = 200
        sb_seekbar2.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                var height = DpUtils.dp2px(this@ShareElement0AActivity, 100 + progress.toFloat())
                LogUtils.d("------ height=" + height)
                var params = iv_image.layoutParams
                params.height = height
                iv_image.layoutParams = params
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })
    }

    private fun toActivityB() {
        val intent = Intent(this@ShareElement0AActivity, ShareElement0BActivity::class.java)
        intent.putExtra(ShareElement0BActivity.EXTRA_IMG_RESOURCE, R.mipmap.img_0)
        intent.putExtra(ShareElement0BActivity.EXTRA_TEXTSIZE, tv_text.textSize)
        intent.putExtra(ShareElement0BActivity.EXTRA_TEXTCOLOR, Color.BLACK)
        intent.putExtra(ShareElement0BActivity.EXTRA_TEXT, tv_text.text)
        intent.putExtra(ShareElement0BActivity.EXTRA_TEXT_PADDING, Rect(
                tv_text.getPaddingLeft(),
                tv_text.getPaddingTop(),
                tv_text.getPaddingRight(),
                tv_text.getPaddingBottom()))
        var pair0 = Pair<View, String>(iv_image, "iv_image")
        var pair1 = Pair<View, String>(tv_text, "tv_text")
        val activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(this, pair0, pair1)
        startActivity(intent, activityOptions.toBundle())
    }
}