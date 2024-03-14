package com.dafay.demo.anim.ui.frg

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    lateinit var mRootView: View
    var mActivity: Activity? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mActivity = activity

        mRootView = inflater.inflate(getLayoutId(), null)
        onInitViews()

        return mRootView
    }

    protected abstract fun getLayoutId(): Int
    protected abstract fun onInitViews()

}