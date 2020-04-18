package com.example.blade.activity.tools.file_;

import androidx.fragment.app.FragmentActivity;

import com.blankj.utilcode.util.ToastUtils;
import com.jaredrummler.android.shell.CommandResult;
import com.jaredrummler.android.shell.Shell;

public class Shell_run {

    public static void mTar(boolean ll, FragmentActivity F, String... com) {
        CommandResult result;
        if (ll) {
            result = Shell.SU.run(com);
        } else {
            result = Shell.SH.run(com);
        }
        if (result.isSuccessful()) {
            ToastUtils.showLong("SU执行成功code:" + result.getStdout());
        } else {
            ToastUtils.showLong("SU执行失败code:" + result.getStdout());
        }
    }


}
