package com.dafay.demo.anim.ui.frg

import android.graphics.Bitmap
import android.graphics.Rect
import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import android.widget.ImageView
import androidx.annotation.NonNull
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.dafay.demo.anim.R
import com.dafay.demo.anim.utils.LogUtils
import kotlinx.android.synthetic.main.frg_image_details.view.*

/**
 * Created by  idea on 2019-12-20$ 16:34$
 * des:
 */
class ImageDetailsFragment : BaseFragment() {

    companion object {
        fun newInstance(currentPosition: Int, startPosition: Int, imageRes: Int): ImageDetailsFragment {

            val args = Bundle()
            args.putInt("currentPosition", currentPosition)
            args.putInt("startPosition", startPosition)
            args.putInt("imageRes", imageRes)
            val fragment = ImageDetailsFragment()
            fragment.setArguments(args)
            return fragment
        }
    }

    private var mCurrentPosition = 0
    private var mStartPosition = 0
    private var mImageRes = -1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        resolveArguments()
    }

    private fun resolveArguments() {
        mCurrentPosition = arguments?.getInt("currentPosition")!!
        mStartPosition = arguments?.getInt("startPosition")!!
        mImageRes = arguments?.getInt("imageRes")!!
    }


    override fun getLayoutId(): Int {
        return R.layout.frg_image_details
    }

    fun getShareView(): ImageView? {
        return if (isViewInBounds(activity?.getWindow()?.decorView!!, rootView?.iv_image!!)) {
            rootView?.iv_image!!
        } else null
    }

    /**
     * Returns true if {@param view} is contained within {@param container}'s bounds.
     */
    private fun isViewInBounds(@NonNull container: View, @NonNull view: View): Boolean {
        val containerBounds = Rect()
        container.getHitRect(containerBounds)
        return view.getLocalVisibleRect(containerBounds)
    }

    override fun onInitViews() {
        rootView?.iv_image?.transitionName = mImageRes.toString()

        Glide.with(context!!).asBitmap().load(mImageRes)
            .listener(object : RequestListener<Bitmap> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Bitmap>?,
                    isFirstResource: Boolean
                ): Boolean {
                    handleStartPostponedEnterTransition()
                    return false
                }

                override fun onResourceReady(
                    resource: Bitmap?,
                    model: Any?,
                    target: Target<Bitmap>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    rootView.iv_image.setImageBitmap(resource)
                    handleStartPostponedEnterTransition()
                    return true
                }

            }).into(rootView.iv_image)


    }

    private fun handleStartPostponedEnterTransition() {
        if (mCurrentPosition == mStartPosition) {
            rootView?.iv_image?.getViewTreeObserver()
                ?.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
                    override fun onPreDraw(): Boolean {
                        rootView?.iv_image?.getViewTreeObserver()?.removeOnPreDrawListener(this)
                        activity?.startPostponedEnterTransition()
                        LogUtils.d("------B startPostponedEnterTransition")
                        return true
                    }
                })
        }

    }

}