package com.dafay.demo.anim.ui.frg

import android.widget.SeekBar
import com.idea.android.animandtran.R
import com.dafay.demo.anim.ui.weight.gif.GifImageView
import com.dafay.demo.anim.utils.LogUtils
import kotlinx.android.synthetic.main.frg_test_gif_play.view.*

/**
 * Created by  idea on 2019-12-20$ 16:34$
 * des:
 */
class TestGifPlayFragment : BaseFragment() {


    override fun getLayoutId(): Int {
        return R.layout.frg_test_gif_play
    }


    override fun onInitViews() {
        mRootView.sb_seekbar1.max = 100
        mRootView.sb_seekbar1.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {


                val per = progress.toFloat() / 100
                mRootView.giv_image.setPercent(per)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })

        mRootView.giv_image.setGifResource(R.mipmap.git_0, object : GifImageView.OnPlayListener {
            override fun onPlayRestart() {
                LogUtils.d("------ onPlayRestart()")
            }

            override fun onPlayStart() {
                LogUtils.d("------ onPlayStart()")
            }

            override fun onPlayEnd() {
                LogUtils.d("------ onPlayEnd()")
            }

            override fun onPlaying(percent: Float) {
                LogUtils.d("------ onPlaying() percent=" + percent)
            }

            override fun onPlayPause(pauseSuccess: Boolean) {
                LogUtils.d("------ onPlayPause() pauseSuccess=" + pauseSuccess)
            }

        })

        mRootView.giv_image.pause()
    }


}