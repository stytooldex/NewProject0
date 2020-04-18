package com.example.blade.util.shell

import com.blankj.utilcode.util.SPUtils
import com.blankj.utilcode.util.ToastUtils
import moe.shizuku.api.ShizukuService


object SHellKt {
    fun run(onSu: OnSu?, su: String) {
        when (SPUtils.getInstance().getInt("action_sh")) {
            0 -> {
                val result = com.jaredrummler.android.shell.Shell.SU.run(su)
                if (result.isSuccessful) {
                    ToastUtils.showLong("执行完毕,真是太鸡你太美了\n$result")
                } else {
                    ToastUtils.showLong("执行失败,也许未有权限\n$result")
                }
                onSu?.onStdout(result.stdout)
                onSu?.onStderr(result.stderr)

            }
            1 -> if (ShizukuService.pingBinder()) {
                ShizukuShell.getInstance().exec(Shell.Command(su))
            } else {
                ToastUtils.showLong("Shizuku服务并没在运行!")
            }
            else->{
                ToastUtils.showLong("请在右上角选择Root工作模式")
            }
        }
    }

    interface OnSu {
        fun onStdout(string: List<String?>?)
        fun onStderr(string: List<String?>?)
    }
}