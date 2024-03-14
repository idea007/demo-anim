package com.dafay.demo.anim.ui.anim.motion

import androidx.recyclerview.widget.LinearLayoutManager
import com.idea.android.animandtran.R
import com.dafay.demo.anim.ui.adapter.SimpleListAdapter
import com.dafay.demo.anim.ui.frg.BaseFragment
import kotlinx.android.synthetic.main.frg_motion_2_a.view.*

/**
 * Created by  idea on 2019-12-09$ 10:37$
 * des:
 */
class Motion2Fragment : BaseFragment() {
    override fun getLayoutId(): Int {
        return R.layout.frg_motion_2_a
    }

    override fun onInitViews() {

        initRecyclerView()

    }

    private fun initRecyclerView() {

        mRootView.rv_recyclerview.apply {
            setHasFixedSize(true)
            adapter = SimpleListAdapter()
            layoutManager = LinearLayoutManager(context)
        }
    }

}