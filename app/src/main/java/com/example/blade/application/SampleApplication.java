package com.example.blade.application;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import me.weishu.reflection.Reflection;
import moe.shizuku.api.ShizukuClientHelper;
import moe.shizuku.api.ShizukuClientHelperPre23;
import moe.shizuku.api.ShizukuMultiProcessHelper;
import moe.shizuku.api.ShizukuService;

@SuppressLint("Registered")
public class SampleApplication extends Application {

    public static final String ACTION_SEND_BINDER = "moe.shizuku.client.intent.action.SEND_BINDER";

    public static String getProcessName() {
        if (Build.VERSION.SDK_INT >= 28)
            return Application.getProcessName();
        else {
            try {
                @SuppressLint("PrivateApi")
                Class<?> activityThread = Class.forName("android.app.ActivityThread");
                Method method = activityThread.getDeclaredMethod("currentProcessName");
                return (String) method.invoke(null);
            } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static boolean v3Failed;
    private static boolean v3TokenValid;

    public static boolean isShizukuV3Failed() {
        return v3Failed;
    }

    public static boolean isShizukuV3TokenValid() {
        return v3TokenValid;
    }

    public static void setShizukuV3TokenValid(boolean v3TokenValid) {
        SampleApplication.v3TokenValid = v3TokenValid;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("ShizukuSample", "初始化 " + ShizukuMultiProcessHelper.initialize(this, !getProcessName().endsWith(":test")));
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        Reflection.unseal(base);

        ShizukuClientHelper.setBinderReceivedListener(() -> {
            Log.d("ShizukuSample", "已收到");

            if (ShizukuService.getBinder() == null) {
                // ShizukuBinderReceiveProvider started without binder, should never happened
                Log.d("ShizukuSample", "活页夹为空");
                v3Failed = true;
                return;
            } else {
                try {
                    // test the binder first
                    ShizukuService.pingBinder();

                    if (Build.VERSION.SDK_INT < 23) {
                        String token = ShizukuClientHelperPre23.loadPre23Token(base);
                        setShizukuV3TokenValid(ShizukuService.setTokenPre23(token));
                    }

                    LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(ACTION_SEND_BINDER));
                } catch (Throwable tr) {
                    // blocked by SELinux or server dead, should never happened
                    Log.i("ShizukuSample", "无法与遥控器联系", tr);
                    v3Failed = true;
                    return;
                }
            }
        });
    }
}