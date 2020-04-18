package com.example.blade.util.permission;

import androidx.fragment.app.FragmentActivity;

import com.blankj.utilcode.constant.PermissionConstants;
import com.blankj.utilcode.util.PermissionUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.blade.R;
import com.shreyaspatil.MaterialDialog.MaterialDialog;


public class Permits {

    private final static String title = "授予权限";
    private final static String message = "您需要允许我权限用于工具处理图片\n仅用于根目录(SD)";
    private final static String name = "授予权限";
    private final static String format = "请重新授予权限";

    public static void iniQ(FragmentActivity activity, Callback callback, @PermissionConstants.Permission String... permissions) {
        PermissionUtils.permission(permissions).rationale((activity1, shouldRequest) -> new MaterialDialog.Builder(activity)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(name, R.drawable.ic_near_me_black_24dp, (dialogInterface, which) -> {
                    dialogInterface.dismiss();
                    shouldRequest.again(true);
                })

                .setNegativeButton(activity.getString(android.R.string.cancel), R.drawable.ic_close_black_24dp, (dialogInterface, which) -> {
                    dialogInterface.dismiss();
                    shouldRequest.again(false);
                    ToastUtils.showLong(format);
                }).build()).callback(new PermissionUtils.SimpleCallback() {
            @Override
            public void onGranted() {
                callback.ok();
            }

            @Override
            public void onDenied() {
            }
        }).request();
        //PermissionUtils.isGranted()
    }

    public interface Callback {
        void ok();
    }
}
