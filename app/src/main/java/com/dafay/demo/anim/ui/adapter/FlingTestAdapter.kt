package com.dafay.demo.anim.ui.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.dafay.demo.lib.base.adapter.BaseAdapter
import com.dafay.demo.anim.R
import kotlinx.android.synthetic.main.item_fling_test.view.*

/**
 * Created by  idea on 2019-11-21$ 14:34$
 * des: 主目录 adapter
 */
class FlingTestAdapter(context: Context) : BaseAdapter<Int>(context) {

    private val glideRequestManager: RequestManager

    init {
        glideRequestManager = Glide.with(context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return FlingTestAdapterViewHolder(
            layoutInflater.inflate(
                R.layout.item_fling_test,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        onBindGifAdapterViewHolder(holder as FlingTestAdapterViewHolder, datas[position], position)
    }

    private fun onBindGifAdapterViewHolder(
        holder: FlingTestAdapterViewHolder,
        imageRes: Int,
        position: Int
    ) {

        glideRequestManager.asGif().load(imageRes).into(holder.itemView.iv_gif)


        if (position % 2 == 0) {
            holder.itemView.tv_des.setText(R.string.middletext)
        } else {
            holder.itemView.tv_des.setText(R.string.largetext)
        }

        holder.itemView.tv_sorder.text = position.toString()

        holder.itemView.iv_gif.setOnClickListener(View.OnClickListener {
            listener?.onItemClick(imageRes, holder.itemView.iv_gif, position)
        })

    }

    var listener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(res: Int, iv: ImageView, position1: Int)
    }

    internal class FlingTestAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


}