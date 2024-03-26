package com.dafay.demo.anim.ui.anim.share

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import com.dafay.demo.anim.R
import com.dafay.demo.lib.base.base.BaseActivity
import kotlinx.android.synthetic.main.act_share_element_2_a.*
import java.util.*

/**
 * Created by  idea on 2019-11-27$ 14:47$
 * des:
 */
class ShareElement2AActivity : BaseActivity() {
    override fun getLayoutId(bundle: Bundle?): Int {
        return R.layout.act_share_element_2_a
    }

    override fun onInitViews() {

        iv_image.setImageResource(R.mipmap.img_0)
        bindListener()

    }

    private fun bindListener() {

        btn_click_one.setOnClickListener(View.OnClickListener {
            toActivityB(true)
        })

        btn_click_two.setOnClickListener({
            toActivityB(false)
        })

    }

    private fun toActivityB(isIncludeBar: Boolean) {

        val intent = Intent(this@ShareElement2AActivity, ShareElement2BActivity::class.java)
        intent.putExtra(ShareElement0BActivity.EXTRA_IMG_RESOURCE, R.mipmap.img_0)
        var pair0 = Pair<View, String>(iv_image, "iv_image")
        val participants = ArrayList<Pair<View, String>>()
        participants.add(pair0)

        if (isIncludeBar) {
            val decor = getWindow().getDecorView()
            var statusBar: View? = decor.findViewById(android.R.id.statusBarBackground)
            var navBar: View? = decor.findViewById(android.R.id.navigationBarBackground)

            if (statusBar != null) {
                var pair2 = Pair<View, String>(statusBar, "statusBar")
                participants.add(pair2)
            }

            if (navBar != null) {
                var pair3 = Pair<View, String>(navBar, "navBar")
                participants.add(pair3)
            }
        }

        var array: Array<Pair<View, String>> = participants.toTypedArray()
        val activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(this, *array)
        startActivity(intent, activityOptions.toBundle())
    }

}