package com.dafay.demo.anim.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.idea.android.animandtran.R
import com.dafay.demo.anim.data.Node
import com.dafay.demo.anim.ui.weight.treeview.TreeHelper
import kotlinx.android.synthetic.main.item_tree.view.*

/**
 * Created by  idea on 2019-11-21$ 14:34$
 * des: 主目录 adapter
 */
class TreeViewAdapter<T>(context: Context, defaultExpandLevel: Int) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    lateinit var mLayoutInflater: LayoutInflater
    lateinit var mContext: Context
    var mDefaultExpandLevel = 1


    init {
        this.mContext = context
        mLayoutInflater = LayoutInflater.from(context)
        this.mDefaultExpandLevel = defaultExpandLevel
    }


    /**
     * 存储所有可见的Node
     */
    protected var mNodes = ArrayList<Node>()

    /**
     * 存储所有的Node
     */
    protected var mAllNodes = ArrayList<Node>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return TreeViewAdapterViewHolder(mLayoutInflater.inflate(R.layout.item_tree, parent, false))
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        onBindTreeViewAdapterViewHolder(holder as TreeViewAdapterViewHolder, mNodes[position], position)
    }

    private fun onBindTreeViewAdapterViewHolder(
        holder: TreeViewAdapterViewHolder,
        node: Node,
        position: Int
    ) {

        holder.itemView.tni_indicator.reset()
        holder.itemView.tni_indicator.setLevel(node)
        holder.itemView.tni_indicator.isLast(node.isLast)
        holder.itemView.tni_indicator.setState(node)
        holder.itemView.tni_indicator.isStart(node.isStart)

        holder.itemView.tv_title.text = node.label

        holder.itemView.rl_container.setOnClickListener(View.OnClickListener {

            expandOrCollapse(position)
            listener?.onItemClick(node, position)

        })

    }

    override fun getItemCount(): Int {
        return mNodes.size
    }


    fun setDatas(newDatas: ArrayList<T>) {

        /**
         * 对所有的Node进行排序
         */
        mAllNodes = TreeHelper.getSortedNodes(newDatas, mDefaultExpandLevel) as ArrayList<Node>

        /**
         * 过滤出可见的Node
         */
        mNodes = TreeHelper.filterVisibleNode(mAllNodes) as ArrayList<Node>

        notifyDataSetChanged()
    }

    /**
     * 相应ListView的点击事件 展开或关闭某节点
     *
     * @param position
     */
    fun expandOrCollapse(position: Int) {
        val node = mNodes[position]

        if (node != null)
        // 排除传入参数错误异常
        {
            if (!node.isLeaf) {
                node.isExpand = !node.isExpand
                mNodes = TreeHelper.filterVisibleNode(mAllNodes) as ArrayList<Node>
                notifyDataSetChanged()// 刷新视图
            }
        }
    }


    var listener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(node: Node, position: Int)
    }

    internal class TreeViewAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


}