package com.dafay.demo.anim.ui.adapter

import android.app.Activity
import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.dafay.demo.lib.base.adapter.BaseAdapter
import com.idea.android.animandtran.R
import kotlinx.android.synthetic.main.item_grid_2.view.*

/**
 * Created by  idea on 2019-11-21$ 14:34$
 * des: 主目录 adapter
 */
class Grid2Adapter(context: Context, activity: Activity) : BaseAdapter<Int>(context) {

    private val glideRequestManager: RequestManager
    private var mActivity: Activity

    init {
        glideRequestManager = Glide.with(context)
        this.mActivity = activity
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return Grid2AdapterViewHolder(layoutInflater.inflate(R.layout.item_grid_2, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        onBindGrid2AdapterViewHolder(holder as Grid2AdapterViewHolder, datas[position], position)
    }

    private fun onBindGrid2AdapterViewHolder(
        holder: Grid2AdapterViewHolder,
        imageRes: Int,
        position: Int
    ) {

        // 共享元素设置
        ViewCompat.setTransitionName(holder.itemView.iv_image, datas[position].toString())

        glideRequestManager.load(imageRes).listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                return false
            }
        }).into(holder.itemView.iv_image)


        holder.itemView.iv_image.setOnClickListener(View.OnClickListener {
            listener?.onItemClick(imageRes, holder.itemView.iv_image, position)
        })

    }

    var listener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(res: Int, iv: ImageView, position1: Int)
    }

    internal class Grid2AdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


}