package com.dafay.demo.anim.ui.act

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.idea.android.animandtran.R
import com.idea.android.duanzirobot.BaseActivity

/**
 * Created by  idea on 2019-11-23$ 10:34$
 * des:
 */

public class HostActivity : BaseActivity() {

    companion object {
        private lateinit var mShowFragment: Fragment
        private var isFullScreen: Boolean = false

        fun startHostActWithAFrg(passContext: Context?, passFragment: Fragment?, passIsFullScreen: Boolean = false) {
            passContext ?: return
            passFragment ?: return

            this.mShowFragment = passFragment
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

    override fun onInitViews() {

        if (mShowFragment != null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fl_container, mShowFragment)
                // .addToBackStack(null)  回退直接退出 act
                .commit()
        }
    }


}