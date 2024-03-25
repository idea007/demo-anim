package com.dafay.demo.anim.ui.adapter

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.dafay.demo.lib.base.adapter.BaseAdapter
import com.idea.android.animandtran.R
import kotlinx.android.synthetic.main.item_gif.view.*

/**
 * Created by  idea on 2019-11-21$ 14:34$
 * des: 主目录 adapter
 */
class GifAdapter(context: Context, activity: Activity) : BaseAdapter<Int>(context) {

    private val glideRequestManager: RequestManager
    private var mActivity: Activity

    init {
        glideRequestManager = Glide.with(context)
        this.mActivity = activity
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return GifAdapterViewHolder(layoutInflater.inflate(R.layout.item_gif, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        onBindGifAdapterViewHolder(holder as GifAdapterViewHolder, datas[position], position)
    }

    private fun onBindGifAdapterViewHolder(
        holder: GifAdapterViewHolder,
        imageRes: Int,
        position: Int
    ) {

        glideRequestManager.asGif().load(imageRes).into(holder.itemView.iv_gif)

        holder.itemView.iv_gif.setOnClickListener(View.OnClickListener {
            listener?.onItemClick(imageRes, holder.itemView.iv_gif, position)
        })

    }

    var listener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(res: Int, iv: ImageView, position1: Int)
    }

    internal class GifAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


}