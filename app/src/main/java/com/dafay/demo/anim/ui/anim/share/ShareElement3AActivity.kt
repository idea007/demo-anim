package com.dafay.demo.anim.ui.anim.share

import android.content.Intent
import android.graphics.Matrix
import android.graphics.RectF
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.widget.ImageView
import androidx.core.app.ActivityOptionsCompat
import androidx.core.app.SharedElementCallback
import androidx.core.util.Pair
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dafay.demo.anim.R
import com.dafay.demo.anim.data.ResScouce
import com.dafay.demo.anim.ui.adapter.Grid2Adapter
import com.dafay.demo.anim.ui.weight.recyclerview.itemdecoration.MediaGridInset
import com.dafay.demo.anim.utils.LogUtils
import com.dafay.demo.lib.base.base.BaseActivity
import com.idea.library.richeditor.utils.DpUtils
import kotlinx.android.synthetic.main.act_share_element_3_a.*
import java.util.*

/**
 * Created by  idea on 2019-11-27$ 14:47$
 * des:
 */
class ShareElement3AActivity : BaseActivity() {

    val CODE_SHARE_ELEMENT_RETURN = 10010
    lateinit var mAdapter: Grid2Adapter
    lateinit var mLayoutManager: GridLayoutManager

    override fun getLayoutId(bundle: Bundle?): Int {
        return R.layout.act_share_element_3_a
    }

    override fun onInitViews() {
        initRecyclerView()
        prepareTransitions()
    }

    private var targetPosition = 0

    private fun prepareTransitions() {

        // A similar mapping is set at the ImagePagerFragment with a setEnterSharedElementCallback.
        setExitSharedElementCallback(
            object : SharedElementCallback() {
                override fun onMapSharedElements(names: List<String>, sharedElements: MutableMap<String, View>) {
                    LogUtils.d("------A onMapSharedElements")
                    // Locate the ViewHolder for the clicked position.
                    val selectedViewHolder = rv_recyclerview.findViewHolderForAdapterPosition(targetPosition)
                    if (selectedViewHolder == null || selectedViewHolder!!.itemView == null) {
                        return
                    }
                    // Map the first shared element name to the child ImageView.
                    LogUtils.d("------A onMapSharedElements names[0]=" + names[0])
                    sharedElements[names[0]] = selectedViewHolder!!.itemView.findViewById(R.id.iv_image)
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

    private fun initRecyclerView() {

        rv_recyclerview.apply {
            mLayoutManager = GridLayoutManager(this@ShareElement3AActivity, 2)
            layoutManager = mLayoutManager as RecyclerView.LayoutManager?
            addItemDecoration(MediaGridInset(2, DpUtils.dp2px(this@ShareElement3AActivity, 10f), true))
            mAdapter = Grid2Adapter(this@ShareElement3AActivity, this@ShareElement3AActivity)
            adapter = mAdapter
        }


        mAdapter.listener = object : Grid2Adapter.OnItemClickListener {
            override fun onItemClick(imageRes: Int, iv: ImageView, position: Int) {

                targetPosition = position

                val intent = Intent(this@ShareElement3AActivity, ShareElement3BActivity::class.java)
                var bundle = Bundle()
                bundle.putIntegerArrayList(ShareElement3BActivity.BUNDLE_IMAGE_LIST, mAdapter.datas as ArrayList<Int>?)
                bundle.putInt(ShareElement3BActivity.BUNDLE_CLICK_POSITION, position)
                intent.putExtra(ShareElement3BActivity.EXTRA_DEFAULT_BUNDLE, bundle)

                var pair0 = Pair<View, String>(iv, iv.transitionName)
                val activityOptions =
                    ActivityOptionsCompat.makeSceneTransitionAnimation(this@ShareElement3AActivity, pair0)
                startActivityForResult(intent, CODE_SHARE_ELEMENT_RETURN, activityOptions.toBundle())

            }
        }

        mAdapter.setDatas(ResScouce.IMAGE_MIPMAPS.toList())
    }


    override fun onActivityReenter(resultCode: Int, data: Intent?) {
        super.onActivityReenter(resultCode, data)

        LogUtils.d("------A onActivityReenter")
        postponeEnterTransition()
        var bundle = data?.getBundleExtra(ShareElement3BActivity.EXTRA_DEFAULT_BUNDLE)
        var resultPosition = bundle?.getInt(ShareElement3BActivity.BUNDLE_RESULT_CURRENT_POSITION)
        targetPosition = resultPosition ?: 0
        mLayoutManager.scrollToPosition(resultPosition ?: 0)
        // scrollToPosition 的任务正式执行，所有 startPostponedEnterTransition 放到这个任务之后
        rv_recyclerview.post(object : Runnable {
            override fun run() {
                startPostponedEnterTransition()
            }
        })
    }

}