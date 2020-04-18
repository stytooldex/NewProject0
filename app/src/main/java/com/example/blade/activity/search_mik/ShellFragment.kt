package com.example.blade.activity.search_mik

import android.view.View
import com.blankj.utilcode.util.SPUtils
import com.blankj.utilcode.util.ThreadUtils
import com.example.blade.R
import com.example.blade.base.BaseFLazyFragment
import com.example.blade.util.shell.SHellKt
import kotlinx.android.synthetic.main.fragment_shell.view.*

/**
 * A simple [BaseFLazyFragment] subclass.
 */
class ShellFragment : BaseFLazyFragment() {
    override fun setLayoutRes(): Int {
        return R.layout.fragment_shell
    }

    override fun initView(view: View) {
        view.val_fab.setOnClickListener {
            SHellKt.run(object : SHellKt.OnSu {
                override fun onStdout(string: List<String?>?) {
                    view.textView2_su.text = string.toString()
                }

                override fun onStderr(string: List<String?>?) {
                    ThreadUtils.runOnUiThread {
                        view.textView2_su.text = string.toString()
                    }
                }

            }, SPUtils.getInstance().getString("shellMik", "su"))

            view.val_fab.hide()
        }
        view.val_fab.show()
    }

}