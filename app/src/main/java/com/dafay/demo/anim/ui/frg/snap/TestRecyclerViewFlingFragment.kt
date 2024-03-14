package com.dafay.demo.anim.ui.frg.snap

import android.view.View
import android.widget.SeekBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.RecyclerView
import com.idea.android.animandtran.R
import com.dafay.demo.anim.data.ResScouce
import com.dafay.demo.anim.ui.adapter.FlingTestAdapter
import com.dafay.demo.anim.ui.frg.BaseFragment
import com.dafay.demo.anim.utils.LogUtils
import kotlinx.android.synthetic.main.frg_recyclerview_fling.view.*

/**
 * Created by  idea on 2019-12-20$ 16:34$
 * des:
 * <a href="https://juejin.im/entry/5be9013e6fb9a049fd0f5daf"> </a>
 *
 * https://medium.com/@takusemba/customize-your-recyclerview-with-snaphelper-3f883b889f0d
 *
 * 原来解析
 * https://www.jianshu.com/p/e54db232df62
 *
 */
class TestRecyclerViewFlingFragment : BaseFragment() {

    private val TAG =
        TestRecyclerViewFlingFragment::class.java.simpleName

    override fun getLayoutId(): Int {
        return R.layout.frg_recyclerview_fling
    }

    override fun onInitViews() {

        initRecyclerView()

        loadData()

        bindListener()

    }

    var DEFAULT_FLING_LIMIT = 5000
    private var isBlockMode = true

    private fun bindListener() {
        mRootView.sb_seekbar.max = 1000
        mRootView.sb_seekbar.progress = 500
        mRootView.tv_velocity_limit.text = "" + mRootView.sb_seekbar.progress * 10
        mRootView.btn_mode.setOnClickListener({
            if (mRootView.btn_mode.text == "阻塞模式") {
                mRootView.btn_mode.text = "流畅模式"
                mRootView.rl_seek_container.visibility = View.GONE
                isBlockMode = false
            } else {
                mRootView.btn_mode.text = "阻塞模式"
                mRootView.rl_seek_container.visibility = View.VISIBLE
                isBlockMode = true
            }
        })


        mRootView.sb_seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                mRootView.tv_velocity_limit.text = "" + progress * 10
                DEFAULT_FLING_LIMIT = progress * 10
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })

    }


    lateinit var mLayoutManager: LinearLayoutManager
    lateinit var mAdapter: FlingTestAdapter

    //    private var snapPosition = RecyclerView.NO_POSITION
    var startSnapHelper: StartSnapHelper = StartSnapHelper()


    private fun initRecyclerView() {
        mRootView.rv_recyclerview.apply {
            mLayoutManager = LinearLayoutManager(context)
            layoutManager = mLayoutManager
            mAdapter = FlingTestAdapter(context)
            adapter = mAdapter
        }


//        startSnapHelper.attachToRecyclerView(mRootView.rv_recyclerview)

        /**
         *  SCROLL_STATE_DRAGGING, 先是手指拖拽的状态
        SCROLL_STATE_SETTLING，再是手指松开但是RecyclerView还在滑动
        SCROLL_STATE_IDLE， 最后是RecyclerView滚动停止状态
         */
        mRootView.rv_recyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                when (newState) {
                    RecyclerView.SCROLL_STATE_IDLE -> {
                        LogUtils.w(TAG, "------ onScrollStateChanged newState=SCROLL_STATE_IDLE")
                    }

                    RecyclerView.SCROLL_STATE_DRAGGING -> {
                        LogUtils.w(
                            TAG,
                            "------ onScrollStateChanged newState=SCROLL_STATE_DRAGGING"
                        )
                    }

                    RecyclerView.SCROLL_STATE_SETTLING -> {
                        LogUtils.w(
                            TAG,
                            "------ onScrollStateChanged newState=SCROLL_STATE_SETTLING "
                        )
                    }
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
//                LogUtils.w(TAG, "----- onScrolled dx=$dx dy=$dy")
                val snapPosition = getSnapPosition(recyclerView)
                LogUtils.w(TAG, "----- onScrolled snapPosition=$snapPosition")

            }
        })

        mRootView.rv_recyclerview.setOnFlingListener(object : RecyclerView.OnFlingListener() {
            override fun onFling(velocityX: Int, velocityY: Int): Boolean {
                LogUtils.w(TAG, "------1 onFling velocityX=$velocityX velocityY=$velocityY")

                if (!isBlockMode) {
                    return false
                }

                if (Math.abs(velocityX) < DEFAULT_FLING_LIMIT && Math.abs(velocityY) < DEFAULT_FLING_LIMIT) {
                    return false
                }


                val targetView = getFirstVisibleView(mLayoutManager)
                if (targetView == null) {
                    return false
                }

                var distance = 0
                var orientationHelper: OrientationHelper? = null

                if (mLayoutManager.canScrollVertically()) {
                    orientationHelper = startSnapHelper.getVerticalHelper(mLayoutManager)
                    var decoratedEnd = orientationHelper.getDecoratedEnd(targetView)
                    var decoratedMeasurement = orientationHelper.getDecoratedMeasurement(targetView)


                    if (velocityY > 0) {
                        if (decoratedMeasurement > decoratedEnd) {
                            distance = Math.abs(decoratedEnd)
                        } else {
                            LogUtils.w(
                                TAG,
                                "------1 onFling velocityY>0  decoratedEnd>decoratedMeasurement"
                            )
                            distance = Math.abs(decoratedEnd - decoratedMeasurement)
                        }


                    } else {
                        if (decoratedMeasurement > decoratedEnd) {
                            distance = -Math.abs(decoratedMeasurement - decoratedEnd)
                        } else {
                            LogUtils.w(
                                TAG,
                                "------1 onFling velocityY<0  decoratedEnd>decoratedMeasurement"
                            )
                            distance =
                                -Math.abs(decoratedMeasurement - decoratedEnd % decoratedMeasurement)
                        }
                    }

                    LogUtils.w(
                        TAG,
                        "------1 onFling distance=$distance decoratedEnd=$decoratedEnd decoratedMeasurement=$decoratedMeasurement"
                    )

                    mRootView.rv_recyclerview.smoothScrollBy(0, distance)


                } else if (mLayoutManager.canScrollHorizontally()) {
                    orientationHelper = startSnapHelper.getVerticalHelper(mLayoutManager)

                }

                return true

            }

        })

    }

    fun getFirstVisibleView(layoutManager: LinearLayoutManager): View? {
        val firstChild =
            layoutManager.findFirstVisibleItemPosition()
        val isLastItem = (layoutManager
            .findLastCompletelyVisibleItemPosition()
                == layoutManager.getItemCount() - 1)
        if (firstChild == RecyclerView.NO_POSITION || isLastItem) {
            return null
        }
        val child = layoutManager.findViewByPosition(firstChild)
        return child
    }

    private fun getSnapPosition(recyclerView: RecyclerView): Int {
        val layoutManager = recyclerView.layoutManager ?: return RecyclerView.NO_POSITION
        val snapView =
            startSnapHelper.findSnapView(layoutManager) ?: return RecyclerView.NO_POSITION
        return layoutManager.getPosition(snapView)
    }

    private fun loadData() {
        mAdapter.setDatas(ResScouce.GIF_MIPMAPS.toList())
        mAdapter.addDatas(ResScouce.GIF_MIPMAPS.toList())
    }


}