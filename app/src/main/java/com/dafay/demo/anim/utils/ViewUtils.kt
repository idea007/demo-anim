package com.dafay.demo.anim.utils

import android.view.View
import android.view.ViewGroup

/**
 * Created by  idea on 2019-12-13$ 10:49$
 * des:
 */
object ViewUtils {

    /**
     * 改变 view 的 margins 值
     */
    @JvmStatic
    fun setMargins(v: View, l: Int, t: Int, r: Int, b: Int) {
        if (v.getLayoutParams() is ViewGroup.MarginLayoutParams) {
            val p = v.getLayoutParams() as ViewGroup.MarginLayoutParams
            p.setMargins(l, t, r, b)
            v.requestLayout()
        }
    }
}