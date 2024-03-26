package com.dafay.demo.anim.ui.act

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.dafay.demo.anim.R
import com.dafay.demo.lib.base.base.BaseActivity

/**
 * Created by  idea on 2019-11-23$ 10:34$
 * des:
 */

public class HostActivity : BaseActivity() {

    companion object {
        private lateinit var targetFragment: Fragment
        private var isFullScreen: Boolean = false

        fun startActivity(passContext: Context?, passFragment: Fragment?, passIsFullScreen: Boolean = false) {
            passContext ?: return
            passFragment ?: return
            this.targetFragment = passFragment
            this.isFullScreen = passIsFullScreen
            var intent = Intent(passContext, HostActivity::class.java)
            passContext.startActivity(intent)
        }
    }


    override fun isFullScreen(): Boolean {
        return isFullScreen
    }

    override fun getLayoutId(bundle: Bundle?): Int {
        return R.layout.act_host
    }

    @SuppressLint("CommitTransaction")
    override fun onInitViews() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fl_container, targetFragment)
            .commitAllowingStateLoss()
    }
}