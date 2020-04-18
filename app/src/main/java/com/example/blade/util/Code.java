package com.example.blade.util;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.ShellUtils;
import com.blankj.utilcode.util.ToastUtils;

/**
 * @author Nico
 */
public class Code {
    public static void bug() {
        ToastUtils.showLong("好像失败了？\n建议点击左上角联系作者");
    }

    public static void _Success() {
        ToastUtils.showLong("执行完成\n*不如分享我给朋友");
    }

    private static void execute(final boolean asRoot, final String... commands) {
        new AsyncTask<String, Void, ShellUtils.CommandResult>() {
            @Override
            protected void onPreExecute() {
            }

            @Override
            protected ShellUtils.CommandResult doInBackground(String... commands) {
                return ShellUtils.execCmd(commands, asRoot);

            }

            @Override
            protected void onPostExecute(ShellUtils.CommandResult commandResult) {
                //ToastUtils.showLong(commandResult.toString());
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, commands);
    }


    public static void su(String commands, final boolean isRooted) {
        execute(isRooted, commands);
    }

    public interface OnPreferenceClick {
        void onClick(Preference preference, Object newValue);
    }

    public static void findPre(@NonNull final PreferenceFragmentCompat compat, @NonNull CharSequence key, final OnPreferenceClick onPreferenceChangeListener) {
        Preference pre = compat.findPreference(key);
        if (pre != null) {
            pre.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    onPreferenceChangeListener.onClick(preference, newValue);
                    return false;
                }
            });
        }

    }

    public static AppUtils.AppInfo getBean(final PackageManager pm, final PackageInfo pi) {
        if (pi == null) return null;
        ApplicationInfo ai = pi.applicationInfo;
        String packageName = pi.packageName;
        String name = ai.loadLabel(pm).toString();
        Drawable icon = ai.loadIcon(pm);
        String packagePath = ai.sourceDir;
        String versionName = pi.versionName;
        int versionCode = pi.versionCode;
        boolean isSystem = (ApplicationInfo.FLAG_SYSTEM & ai.flags) != 0;
        return new AppUtils.AppInfo(packageName, name, icon, packagePath, versionName, versionCode, isSystem);
    }
}
