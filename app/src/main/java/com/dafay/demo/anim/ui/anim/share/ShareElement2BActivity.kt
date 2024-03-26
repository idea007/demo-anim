package com.dafay.demo.anim.ui.anim.share

import android.os.Bundle
import android.transition.ChangeBounds
import android.transition.ChangeImageTransform
import android.transition.TransitionSet
import android.view.View
import androidx.core.app.SharedElementCallback
import androidx.core.view.ViewCompat
import com.dafay.demo.anim.R
import com.dafay.demo.anim.utils.LogUtils
import com.dafay.demo.lib.base.base.BaseActivity
import kotlinx.android.synthetic.main.act_share_element_2_b.*

/**
 * Created by  idea on 2019-11-27$ 14:47$
 * des:
 */
class ShareElement2BActivity : BaseActivity() {

    companion object {
        val EXTRA_IMG_RESOURCE = "extra_img_resource"
        val EXTRA_TEXTSIZE = "extra_textsize"
        val EXTRA_TEXTCOLOR = "extra_textcolor"
        val EXTRA_TEXT = "extra_text"
        val EXTRA_TEXT_PADDING = "extra_text_padding"

    }


    override fun getLayoutId(bundle: Bundle?): Int {
        return R.layout.act_share_element_2_b
    }

    override fun onInitViews() {

        postponeEnterTransition()

        initView()
        bindListener()


        iv_image.post({
            setUpTransition()
            startPostponedEnterTransition()
        })


    }


    private fun initView() {
        iv_image.setImageResource(intent.getIntExtra(EXTRA_IMG_RESOURCE, R.mipmap.img_10))
    }

    private fun bindListener() {

    }

    private fun setUpTransition() {
        ViewCompat.setTransitionName(iv_image, "iv_image")

        val decor = getWindow().getDecorView()
        var statusBar: View? = decor.findViewById(android.R.id.statusBarBackground)
        var navBar: View? = decor.findViewById(android.R.id.navigationBarBackground)

        if (statusBar != null) {
            ViewCompat.setTransitionName(statusBar, "statusBar")
        }

        if (navBar != null) {
            ViewCompat.setTransitionName(navBar, "navBar")
        }


        val changeBounds = ChangeBounds()
        val changeImageTransform = ChangeImageTransform()
        val transitionSet = TransitionSet()
        transitionSet.addTransition(changeBounds)
        transitionSet.addTransition(changeImageTransform)

        transitionSet.duration = 1000
        window.sharedElementEnterTransition = transitionSet


        this.setEnterSharedElementCallback(object : SharedElementCallback() {
            override fun onSharedElementStart(
                sharedElementNames: MutableList<String>?,
                sharedElements: MutableList<View>?,
                sharedElementSnapshots: MutableList<View>?
            ) {
                LogUtils.w("onSharedElementStart")
            }

            override fun onSharedElementEnd(
                sharedElementNames: MutableList<String>?,
                sharedElements: MutableList<View>?,
                sharedElementSnapshots: MutableList<View>?
            ) {
                LogUtils.w("onSharedElementEnd")
            }


            override fun onMapSharedElements(
                names: List<String>,
                sharedElements: Map<String, View>
            ) {
                LogUtils.w("onMapSharedElements")
            }

        })
    }

}