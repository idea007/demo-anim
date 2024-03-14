package com.dafay.demo.anim.data

import java.util.*

/**
 * Created by  idea on 2019-11-25$ 14:00$
 * des:
 */
class Node(
    var id: Int, pId: Int,
    /**
     * 携带的数据
     */
    var label: String?
) {
    /**
     * 根节点pId为0
     */
    private var pId = 0

    /**
     * 当前的级别
     */
    /**
     * 获取level
     */
    var level: Int = 0
        get() = if (parent == null) 0 else parent!!.level + 1

    /**
     * 是否展开
     */
    /**
     * 设置展开
     *
     * @param isExpand
     */
    var isExpand = false
        set(isExpand) {
            field = isExpand
            if (!isExpand) {

                for (node in children) {
                    node.isExpand = isExpand
                }
            }
        }

    /**
     * 下一级的子Node
     */
    var children: ArrayList<Node> = ArrayList()

    /**
     * 父Node
     */
    var parent: Node? = null

    var isLast: Boolean = false
    var isStart: Boolean = false

    /**
     * 是否为跟节点
     *
     * @return
     */
    val isRoot: Boolean
        get() = parent == null

    /**
     * 判断父节点是否展开
     *
     * @return
     */
    val isParentExpand: Boolean
        get() = if (parent == null) false else parent!!.isExpand

    /**
     * 是否是叶子界点
     *
     * @return
     */
    val isLeaf: Boolean
        get() = children.size == 0


    init {
        this.pId = pId
    }

    fun getpId(): Int {
        return pId
    }

    fun setpId(pId: Int) {
        this.pId = pId
    }


}

