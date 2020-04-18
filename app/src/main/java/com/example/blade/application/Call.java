package com.example.blade.application;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.StrictMode;

import androidx.annotation.CallSuper;

import com.blankj.utilcode.util.CrashUtils;
import com.github.megatronking.stringfog.annotation.StringFogIgnore;
import com.lzf.easyfloat.EasyFloat;
import com.tencent.smtt.export.external.TbsCoreSettings;
import com.tencent.smtt.sdk.QbSdk;

import java.util.HashMap;

import static com.blankj.utilcode.util.AppUtils.isAppDebug;
import static com.ycbjie.webviewlib.X5LogUtils.setIsLog;

/**
 * @author Nico
 */
@StringFogIgnore
public class Call extends SampleApplication {

    @SuppressLint("StaticFieldLeak")
    private static Call app;


    private void initQ() {
        //ImageUtils.toRoundCorner                   ()
        if (!QbSdk.isTbsCoreInited()) {
            HashMap<String, Object> map = new HashMap<>(16);
            map.put(TbsCoreSettings.TBS_SETTINGS_USE_SPEEDY_CLASSLOADER, true);
            QbSdk.initTbsSettings(map);
            QbSdk.initX5Environment(this, null);
        }
    }

    @Override
    @CallSuper
    public void onCreate() {
        super.onCreate();

        initQ();
        app = this;
        setIsLog(isAppDebug());
        EasyFloat.init(this, isAppDebug());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
            builder.detectFileUriExposure();
        }
        CrashUtils.init();


       /* registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
                ThreadUtils.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        View decorView = activity.getWindow().getDecorView();
                        Paint paint = new Paint();
                        ColorMatrix cm = new ColorMatrix();
                        cm.setSaturation(0);
                        paint.setColorFilter(new ColorMatrixColorFilter(cm));
                        decorView.setLayerType(View.LAYER_TYPE_HARDWARE, paint);
                    }
                });
            }

            @Override
            public void onActivityStarted(@NonNull Activity activity) {

            }

            @Override
            public void onActivityResumed(@NonNull Activity activity) {

            }

            @Override
            public void onActivityPaused(@NonNull Activity activity) {

            }

            @Override
            public void onActivityStopped(@NonNull Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(@NonNull Activity activity) {

            }
        });*/
    }


    public static Call getInstance() {
        return app;
    }


}
