package com.idea.android.duanzirobot

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    /**
     * 当第一次调用一个Activity就会执行
     */
    override fun onCreate(savedInstanceState: Bundle?) = try {
        super.onCreate(savedInstanceState)


        preSetContentView()
        if (isFullScreen()) {
            //去除状态栏
            getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }

        val layoutId = getLayoutId(savedInstanceState)
        if (layoutId > 0) {
            setContentView(layoutId)
        }
        onInitViews()
    } catch (e: Exception) {

    }


    open fun preSetContentView() {

    }


    open fun isFullScreen(): Boolean {
        return false
    }

    /**
     * 获取布局
     */
    protected abstract fun getLayoutId(bundle: Bundle?): Int

    protected abstract fun onInitViews()


}