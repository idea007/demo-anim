package com.dafay.demo.anim.ui.anim.share

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.Drawable
import android.graphics.drawable.Drawable.ConstantState
import android.os.Bundle
import android.transition.Transition
import android.view.View
import android.widget.ImageView
import androidx.core.view.ViewCompat
import com.bumptech.glide.gifdecoder.GifDecoder
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.target.CustomViewTarget
import com.dafay.demo.anim.R
import com.dafay.demo.anim.utils.LogUtils
import com.dafay.demo.anim.utils.ObjectUtils
import com.dafay.demo.lib.base.base.BaseActivity
import com.idea.android.wallpaper.utils.glide.GlideApp
import kotlinx.android.synthetic.main.act_share_element_4_b.*


/**
 * Created by  idea on 2019-11-27$ 14:47$
 * des:
 */
class ShareElement4BActivity : BaseActivity() {

    companion object {
        val EXTRA_DEFAULT_BUNDLE = "extra_default_bundle"
        val BUNDLE_COMPLETELY_ARRIVE = "bundle_completely_arrive"
        val BUNDLE_CURRENT_INDEX = "bundle_current_index"

    }


    override fun getLayoutId(bundle: Bundle?): Int {
        return R.layout.act_share_element_4_b
    }

    override fun onInitViews() {
        postponeEnterTransition()
        initView()
        setUpTransition()
    }

    var gifDrawable: GifDrawable? = null
    var passIndex = 0

    private fun initView() {
        passIndex = ShareElement4AActivity.currentIndex
        iv_image_overlay.setImageDrawable(ShareElement4AActivity.currentDrawable)
        GlideApp.with(this).asGif().load(ShareElement4AActivity.gifRes)
            .into(object : CustomViewTarget<ImageView, GifDrawable>(iv_gif) {
                override fun onLoadFailed(errorDrawable: Drawable?) {
                }

                override fun onResourceCleared(placeholder: Drawable?) {
                }

                override fun onResourceReady(
                    resource: GifDrawable,
                    transition: com.bumptech.glide.request.transition.Transition<in GifDrawable>?
                ) {
                    gifDrawable = resource
                    iv_gif.setImageDrawable(gifDrawable)
                    gifDrawable?.stop()
                    val state: ConstantState = gifDrawable?.getConstantState()!!
                    if (state != null) { //不能混淆GifFrameLoader和GifState类
                        val gifFrameLoader: Any? = ObjectUtils.getValue(state, "frameLoader")
                        if (gifFrameLoader != null) {
                            val decoder: Any? = ObjectUtils.getValue(gifFrameLoader, "gifDecoder")
                            if (decoder != null && decoder is GifDecoder) {

                                decoder.resetFrameIndex()

                                for (i in 0..passIndex) {
                                    decoder.advance()
                                }
                                LogUtils.d("------ passIndex=${passIndex} decoder.currentFrameIndex=${decoder.currentFrameIndex}")
                                iv_gif.post(object : Runnable {
                                    override fun run() {
                                        iv_gif.visibility = View.INVISIBLE
                                        startPostponedEnterTransition()
                                    }
                                })
                            }
                        }
                    }
                }
            })
    }


    private fun setUpTransition() {
        ViewCompat.setTransitionName(iv_image_overlay, "iv_image_overlay")
        window.sharedElementEnterTransition.duration = 600
        window.sharedElementEnterTransition.addListener(object : Transition.TransitionListener {
            override fun onTransitionEnd(transition: Transition?) {
                if (!isClickBack) {
                    startGif()
                }
            }

            override fun onTransitionResume(transition: Transition?) {
            }

            override fun onTransitionPause(transition: Transition?) {
            }

            override fun onTransitionCancel(transition: Transition?) {
            }

            override fun onTransitionStart(transition: Transition?) {
            }

        })

    }

    private fun startGif() {
        iv_gif.post(object : Runnable {
            override fun run() {
                iv_gif.visibility = View.VISIBLE
                iv_image_overlay.visibility = View.GONE
                gifDrawable?.start()
            }
        })
    }

    var isClickBack = false
    override fun onBackPressed() {
        isClickBack = true
        onBackResult()
        super.onBackPressed()
    }

    /**
     * 点击会退时传入值
     */
    private fun onBackResult() {

        val intent = Intent()
        val bundle = Bundle()
        intent.putExtra(EXTRA_DEFAULT_BUNDLE, bundle)

        if (iv_image_overlay.visibility != View.GONE) {
            bundle.putBoolean(BUNDLE_COMPLETELY_ARRIVE, true)
        } else {

            gifDrawable?.stop()

            iv_image_overlay.setImageDrawable(gifDrawable?.current)
            iv_image_overlay.visibility = View.VISIBLE
            var currentIndex = gifDrawable?.frameIndex
            iv_gif.visibility = View.GONE
            bundle.putBoolean(BUNDLE_COMPLETELY_ARRIVE, false)
            bundle.putInt(BUNDLE_CURRENT_INDEX, currentIndex ?: 0)
        }
        setResult(Activity.RESULT_OK, intent)

    }
}