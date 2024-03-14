package com.dafay.demo.anim.ui.anim.share

import android.app.Activity
import android.app.SharedElementCallback
import android.content.Intent
import android.graphics.Matrix
import android.graphics.RectF
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import com.idea.android.animandtran.R
import com.dafay.demo.anim.ui.adapter.ImagePagerAdapter
import com.dafay.demo.anim.ui.frg.BaseFragment
import com.dafay.demo.anim.ui.frg.ImageDetailsFragment
import com.dafay.demo.anim.utils.LogUtils
import com.idea.android.duanzirobot.BaseActivity
import kotlinx.android.synthetic.main.act_share_element_3_b.*

/**
 * Created by  idea on 2019-11-27$ 14:47$
 * des:
 */
class ShareElement3BActivity : BaseActivity() {

    companion object {
        val EXTRA_DEFAULT_BUNDLE = "extra_default_bundle"
        val BUNDLE_IMAGE_LIST = "bundle_image_list"
        val BUNDLE_CLICK_POSITION = "bundle_click_position"
        val BUNDLE_RESULT_CURRENT_POSITION = "bundle_result_current_position"

    }

    var passedImageList: List<Int>? = null
    var passedClickPosition = 0
    lateinit var mAdapter: ImagePagerAdapter

    override fun getLayoutId(bundle: Bundle?): Int {
        return R.layout.act_share_element_3_b
    }

    override fun onInitViews() {
        resolveIntent()
        postponeEnterTransition()
        initViewPager()
        setUpTransition()

    }

    private fun setUpTransition() {

        setEnterSharedElementCallback(object : SharedElementCallback() {
            override fun onMapSharedElements(names: MutableList<String>, sharedElements: MutableMap<String, View>?) {

                LogUtils.d("------B onMapSharedElements")

                val sharedElement =
                    (mAdapter?.getItem(vp_viewpager.currentItem) as ImageDetailsFragment)?.getShareView()

                if (sharedElement != null) {
                    LogUtils.d("------B onMapSharedElements names.get(0)=" + names.get(0))
                    sharedElements?.set(names.get(0), sharedElement)
                }

            }


            override fun onSharedElementStart(
                sharedElementNames: MutableList<String>?,
                sharedElements: MutableList<View>?,
                sharedElementSnapshots: MutableList<View>?
            ) {
                super.onSharedElementStart(sharedElementNames, sharedElements, sharedElementSnapshots)
                LogUtils.d("------B onSharedElementStart")
            }

            override fun onSharedElementEnd(
                sharedElementNames: MutableList<String>?,
                sharedElements: MutableList<View>?,
                sharedElementSnapshots: MutableList<View>?
            ) {
                super.onSharedElementEnd(sharedElementNames, sharedElements, sharedElementSnapshots)
                LogUtils.d("------B onSharedElementEnd")
            }

            override fun onCaptureSharedElementSnapshot(
                sharedElement: View?,
                viewToGlobalMatrix: Matrix?,
                screenBounds: RectF?
            ): Parcelable {
                LogUtils.d("------B onCaptureSharedElementSnapshot")
                return super.onCaptureSharedElementSnapshot(sharedElement, viewToGlobalMatrix, screenBounds)

            }

            override fun onSharedElementsArrived(
                sharedElementNames: MutableList<String>?,
                sharedElements: MutableList<View>?,
                listener: OnSharedElementsReadyListener?
            ) {
                super.onSharedElementsArrived(sharedElementNames, sharedElements, listener)
                LogUtils.d("------B onSharedElementsArrived")
            }
        })
    }

    private fun resolveIntent() {
        var bundle = intent.getBundleExtra(EXTRA_DEFAULT_BUNDLE)
        passedImageList = bundle.getIntegerArrayList(BUNDLE_IMAGE_LIST)
        passedClickPosition = bundle.getInt(BUNDLE_CLICK_POSITION)

    }

    private fun initViewPager() {
        vp_viewpager.apply {

            var fragments = ArrayList<BaseFragment>()
            for (i in 0 until passedImageList?.size!!) {
                fragments.add(ImageDetailsFragment.newInstance(i, passedClickPosition, passedImageList!![i]))
            }
            mAdapter = ImagePagerAdapter(supportFragmentManager, fragments)
            this.adapter = mAdapter

            this.currentItem = passedClickPosition
        }
    }


    override fun onBackPressed() {
        onBackResult()
        super.onBackPressed()
    }

    /**
     * 点击会退时传入值
     */
    private fun onBackResult() {
        val intent = Intent()
        val bundle = Bundle()
        bundle.putInt(BUNDLE_RESULT_CURRENT_POSITION, vp_viewpager.currentItem)
        intent.putExtra(EXTRA_DEFAULT_BUNDLE, bundle)
        setResult(Activity.RESULT_OK, intent)

    }

}