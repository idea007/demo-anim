package com.dafay.demo.anim.ui.adapter

/**
 * Created by  idea on 2019-12-20$ 16:50$
 * des:
 */

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.dafay.demo.anim.ui.frg.BaseFragment

@Suppress("DEPRECATION")
class ImagePagerAdapter(fm: FragmentManager, private val mFragments: List<BaseFragment>) :
    FragmentStatePagerAdapter(fm) {

    override fun getCount(): Int {
        return mFragments.size
    }

    override fun getItem(position: Int): Fragment {
        return mFragments.get(position)
    }
}