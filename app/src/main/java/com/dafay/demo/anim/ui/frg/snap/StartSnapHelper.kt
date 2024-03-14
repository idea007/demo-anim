package com.dafay.demo.anim.ui.frg.snap

import android.view.View
import androidx.annotation.Nullable
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.RecyclerView
import com.dafay.demo.anim.utils.LogUtils


/**
 * Created by  idea on 2020/5/19$ 下午4:27$
 * des:
 */
class StartSnapHelper : LinearSnapHelper() {

    private var mVerticalHelper: OrientationHelper? = null
    private var mHorizontalHelper: OrientationHelper? = null


    @Throws(IllegalStateException::class)
    open override fun attachToRecyclerView(@Nullable recyclerView: RecyclerView?) {
        super.attachToRecyclerView(recyclerView)
    }


    override fun calculateDistanceToFinalSnap(
        layoutManager: RecyclerView.LayoutManager,
        targetView: View
    ): IntArray? {
        val out = IntArray(2)

        if (layoutManager.canScrollHorizontally()) {
            out[0] = distanceToStart(targetView, getHorizontalHelper(layoutManager))
        } else {
            out[0] = 0
        }

        if (layoutManager.canScrollVertically()) {
            out[1] = distanceToStart(targetView, getVerticalHelper(layoutManager))
        } else {
            out[1] = 0
        }

        LogUtils.w("------ calculateDistanceToFinalSnap out[0]=${out[0]} out[1]=${out[1]}")
        return out
    }

    fun distanceToStart(
        targetView: View,
        helper: OrientationHelper
    ): Int {

        var thumDecoratedStart = helper.getDecoratedStart(targetView)
        var startAfterPadding = helper.startAfterPadding
        LogUtils.w("------ distanceToStart thumDecoratedStart=$thumDecoratedStart startAfterPadding=$startAfterPadding")
        return helper.getDecoratedStart(targetView) - helper.startAfterPadding
    }

     fun getVerticalHelper(layoutManager: RecyclerView.LayoutManager): OrientationHelper {
        if (mVerticalHelper == null) {
            mVerticalHelper = OrientationHelper.createVerticalHelper(layoutManager)
        }
        return mVerticalHelper!!
    }

     fun getHorizontalHelper(layoutManager: RecyclerView.LayoutManager): OrientationHelper {
        if (mHorizontalHelper == null) {
            mHorizontalHelper = OrientationHelper.createHorizontalHelper(layoutManager)
        }
        return mHorizontalHelper!!
    }


    override fun findSnapView(layoutManager: RecyclerView.LayoutManager?): View? {
        if (layoutManager is LinearLayoutManager) {

            if (layoutManager.canScrollHorizontally()) {
                return getStartView(layoutManager, getHorizontalHelper(layoutManager))
            } else {
                return getStartView(layoutManager, getVerticalHelper(layoutManager))
            }
        }

        return super.findSnapView(layoutManager)
    }

    private fun getStartView(
        layoutManager: RecyclerView.LayoutManager,
        helper: OrientationHelper
    ): View? {
        LogUtils.w("------ getStartView ")

        if (layoutManager is LinearLayoutManager) {
            val firstChild =
                layoutManager.findFirstVisibleItemPosition()
            val isLastItem = (layoutManager
                .findLastCompletelyVisibleItemPosition()
                    == layoutManager.getItemCount() - 1)
            if (firstChild == RecyclerView.NO_POSITION || isLastItem) {
                return null
            }
            val child = layoutManager.findViewByPosition(firstChild)
            return if (helper.getDecoratedEnd(child) >= helper.getDecoratedMeasurement(child) / 2
                && helper.getDecoratedEnd(child) > 0
            ) {
                child
            } else {
                if (layoutManager.findLastCompletelyVisibleItemPosition()
                    == layoutManager.getItemCount() - 1
                ) {
                    null
                } else {
                    layoutManager.findViewByPosition(firstChild + 1)
                }
            }
        }
        return super.findSnapView(layoutManager)
    }


}