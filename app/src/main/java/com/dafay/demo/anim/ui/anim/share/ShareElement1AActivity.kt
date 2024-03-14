package com.dafay.demo.anim.ui.anim.share

import android.content.Intent
import android.graphics.Color
import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import com.idea.android.animandtran.R
import com.dafay.demo.anim.utils.LogUtils
import com.idea.android.duanzirobot.BaseActivity
import kotlinx.android.synthetic.main.act_share_element_1_a.*


/**
 * Created by  idea on 2019-11-27$ 14:47$
 * des:
 */
class ShareElement1AActivity : BaseActivity() {
    override fun getLayoutId(bundle: Bundle?): Int {
        return R.layout.act_share_element_1_a
    }


    override fun onInitViews() {

        bindListener()

        Thread(Runnable {

            LogUtils.d("Thread Runnable pid: " + android.os.Process.myPid() + " Thread: " + android.os.Process.myTid() + " name: " + Thread.currentThread().getName())

            tv_text_async.setText("000000")
            tv_text_async.setTextColor(Color.BLUE)
            tv_text_async.setTextSize(30f)

            LogUtils.d("Thread Runnable pid: " + android.os.Process.myPid() + " Thread: " + android.os.Process.myTid() + " name: " + Thread.currentThread().getName())


        }).start()


    }

    override fun onResume() {
        super.onResume()
        LogUtils.d("pid: " + android.os.Process.myPid() + " Thread: " + android.os.Process.myTid() + " name: " + Thread.currentThread().getName())
    }

    private fun bindListener() {


        tv_text.setOnClickListener(View.OnClickListener {

            val intent = Intent(this@ShareElement1AActivity, ShareElement1BActivity::class.java)
            intent.putExtra(ShareElement1BActivity.EXTRA_TEXTSIZE, tv_text.textSize)
            intent.putExtra(ShareElement1BActivity.EXTRA_TEXTCOLOR, Color.BLACK)
            intent.putExtra(ShareElement1BActivity.EXTRA_TEXT, tv_text.text)

            intent.putExtra(
                ShareElement1BActivity.EXTRA_TEXT_PADDING,
                Rect(
                    tv_text.getPaddingLeft(),
                    tv_text.getPaddingTop(),
                    tv_text.getPaddingRight(),
                    tv_text.getPaddingBottom()
                )
            )

            intent.putExtra(ShareElement1BActivity.EXTRA_TEXTSIZE_1, tv_text_1.textSize)
            intent.putExtra(ShareElement1BActivity.EXTRA_TEXTCOLOR_1, Color.BLACK)
            intent.putExtra(ShareElement1BActivity.EXTRA_TEXT_1, tv_text_1.text)

            intent.putExtra(
                ShareElement1BActivity.EXTRA_TEXT_PADDING_1,
                Rect(
                    tv_text_1.getPaddingLeft(),
                    tv_text_1.getPaddingTop(),
                    tv_text_1.getPaddingRight(),
                    tv_text_1.getPaddingBottom()
                )
            )


            var pair1 = Pair<View, String>(tv_text, "tv_text")
            var pair2 = Pair<View, String>(tv_text_1, "tv_text_1")

            val activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(this, pair1, pair2)
            startActivity(intent, activityOptions.toBundle())

        })


    }


}