package com.dafay.demo.anim.ui.anim.share

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.gifdecoder.GifDecoder
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.target.CustomViewTarget
import com.dafay.demo.anim.R
import com.dafay.demo.anim.data.ResScouce
import com.dafay.demo.anim.ui.adapter.GifAdapter
import com.dafay.demo.anim.ui.weight.recyclerview.itemdecoration.MediaGridInset
import com.dafay.demo.anim.utils.LogUtils
import com.dafay.demo.anim.utils.ObjectUtils
import com.dafay.demo.anim.utils.asGif
import com.dafay.demo.lib.base.base.BaseActivity
import com.idea.android.wallpaper.utils.glide.GlideApp
import com.idea.library.richeditor.utils.DpUtils
import kotlinx.android.synthetic.main.act_share_element_4_a.*

/**
 * Created by  idea on 2019-11-27$ 14:47$
 * des:
 */
class ShareElement4AActivity : BaseActivity() {

    val CODE_SHARE_ELEMENT_RETURN = 10010


    companion object {
        var currentIndex: Int = 0
        var currentDrawable: Drawable? = null
        var shareGifDrawable: GifDrawable? = null
        var gifRes: Int = -1
    }

    lateinit var mAdapter: GifAdapter
    lateinit var mLayoutManager: GridLayoutManager

    var checkIv: ImageView? = null

    override fun getLayoutId(bundle: Bundle?): Int {
        return R.layout.act_share_element_4_a
    }

    override fun onInitViews() {
        initRecyclerView()
    }

    private fun initRecyclerView() {

        rv_recyclerview.apply {
            mLayoutManager = GridLayoutManager(this@ShareElement4AActivity, 2)
            layoutManager = mLayoutManager as RecyclerView.LayoutManager?
            addItemDecoration(
                MediaGridInset(
                    2,
                    DpUtils.dp2px(this@ShareElement4AActivity, 10f),
                    true
                )
            )
            mAdapter = GifAdapter(this@ShareElement4AActivity, this@ShareElement4AActivity)
            adapter = mAdapter
        }


        mAdapter.listener = object : GifAdapter.OnItemClickListener {
            override fun onItemClick(imageRes: Int, iv: ImageView, position: Int) {

                checkIv = iv
                val drawable = iv.drawable ?: return
                shareGifDrawable = drawable.asGif() ?: return
                currentDrawable = shareGifDrawable?.current ?: return
                currentIndex = shareGifDrawable?.frameIndex ?: 0
                shareGifDrawable = shareGifDrawable
                gifRes = mAdapter.datas.get(position)
                shareGifDrawable?.stop()

                // 共享元素设置
                ViewCompat.setTransitionName(iv, "iv_image_overlay")
                val intent = Intent(this@ShareElement4AActivity, ShareElement4BActivity::class.java)
                var pair0 = androidx.core.util.Pair<View, String>(iv, iv.transitionName)
                val activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    this@ShareElement4AActivity,
                    pair0
                )
                startActivityForResult(
                    intent,
                    CODE_SHARE_ELEMENT_RETURN,
                    activityOptions.toBundle()
                )
            }
        }

        mAdapter.setDatas(ResScouce.GIF_MIPMAPS.toList())
    }


    override fun onActivityReenter(resultCode: Int, data: Intent?) {
        super.onActivityReenter(resultCode, data)

        LogUtils.d("------A onActivityReenter")
        var bundle = data?.getBundleExtra(ShareElement4BActivity.EXTRA_DEFAULT_BUNDLE)
        var isUnCompletelyArrive =
            bundle?.getBoolean(ShareElement4BActivity.BUNDLE_COMPLETELY_ARRIVE) ?: false

        var index = currentIndex
        if (!isUnCompletelyArrive) {
            index = bundle?.getInt(ShareElement4BActivity.BUNDLE_CURRENT_INDEX) ?: currentIndex
        }
        postponeEnterTransition()
        GlideApp.with(this).asGif().load(gifRes)
            .into(object : CustomViewTarget<ImageView, GifDrawable>(checkIv!!) {
                override fun onLoadFailed(errorDrawable: Drawable?) {
                }

                override fun onResourceCleared(placeholder: Drawable?) {
                }

                override fun onResourceReady(
                    resource: GifDrawable,
                    transition: com.bumptech.glide.request.transition.Transition<in GifDrawable>?
                ) {

                    checkIv?.setImageDrawable(resource)
                    resource?.stop()
                    val state: Drawable.ConstantState = resource?.getConstantState()!!
                    if (state != null) { //不能混淆GifFrameLoader和GifState类
                        val gifFrameLoader: Any? = ObjectUtils.getValue(state, "frameLoader")
                        if (gifFrameLoader != null) {
                            val decoder: Any? =
                                ObjectUtils.getValue(gifFrameLoader, "gifDecoder")
                            if (decoder != null && decoder is GifDecoder) {

                                decoder.resetFrameIndex()
                                for (i in 0..index) {
                                    decoder.advance()
                                }
                                LogUtils.d("------A passIndex=${index} decoder.currentFrameIndex=${decoder.currentFrameIndex}")
                                resource.start()
                                checkIv?.post({
                                    startPostponedEnterTransition()
                                })
                            }
                        }
                    }
                }
            })
    }

}