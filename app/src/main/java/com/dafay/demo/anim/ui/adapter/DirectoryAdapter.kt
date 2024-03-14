package com.dafay.demo.anim.ui.adapter

import android.content.Context
import android.text.TextUtils
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.idea.android.animandtran.R
import kotlinx.android.synthetic.main.item_main_directory.view.*

/**
 * Created by  idea on 2019-11-21$ 14:34$
 * des: 主目录 adapter
 */
class DirectoryAdapter(context: Context) : BaseAdapter<String>(context) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return DirectoryAdapterViewHolder(layoutInflater.inflate(R.layout.item_main_directory, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        onBindMainDirectoryAdapterViewHolder(holder as DirectoryAdapterViewHolder, datas[position], position)
    }

    private fun onBindMainDirectoryAdapterViewHolder(
        holder: DirectoryAdapterViewHolder,
        title: String,
        position: Int
    ) {
        if (!TextUtils.isEmpty(title)) {
            holder.itemView.tv_title.text = title
        }

        holder.itemView.tv_title.setOnClickListener({
            listener?.onItemClick(title,position)
        })

    }


    var listener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(title: String, position: Int)
    }


    internal class DirectoryAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


}