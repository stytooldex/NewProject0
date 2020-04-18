package com.example.blade.activity.tools.file_;

import androidx.fragment.app.FragmentActivity;

import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.SDCardUtils;
import com.blankj.utilcode.util.ToastUtils;

public class file_Run {

    @SuppressWarnings("all")
    public static void mTar2(FragmentActivity F, String AD, boolean newValue, boolean newToast) {
        String a = SDCardUtils.getSDCardPathByEnvironment() + AD;
        if (newValue) {
            if (FileUtils.isFileExists(a)) {
                if (FileUtils.delete(a)) {
                    if (FileUtils.createOrExistsFile(a)) {
                        if (newToast) {
                            ToastUtils.showLong("已生效建议重启QQ");
                        }
                    }
                }
            } else {
                //Toast.makeText(F, "不存在该文件夹，若想咨询作者解答 请联系我！", Toast.LENGTH_SHORT).show();
            }

        } else {
            if (FileUtils.isFile(a)) {
                if (FileUtils.delete(a)) {
                    if (FileUtils.createOrExistsDir(a)) {
                        if (newToast) {
                            ToastUtils.showLong("已生效建议重启QQ");
                        }
                    } else {
                    }
                }
            }
        }
    }

}
